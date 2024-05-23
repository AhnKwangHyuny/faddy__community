import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import ContentBox from "features/ContentBox/ContentBox";
import Avatar from "widgets/Avatar/Avatar";
import AboutHeader from "features/AboutHeader/AboutHeader";
import MainContainer from "pages/StyleTalkRoom/components/MainContainer";
import ChatContainer from 'pages/StyleTalkRoom/components/ChatContainer';
import MessageList from 'pages/StyleTalkRoom/components/MessageList';
import MessageGroup from 'pages/StyleTalkRoom/components/MessageGroup';
import MessageMetaInfo from 'pages/StyleTalkRoom/components/MessageMetaInfo';
import Message from 'pages/StyleTalkRoom/components/Message';
import SentTime from 'pages/StyleTalkRoom/components/SentTime';
import ReadCount from 'pages/StyleTalkRoom/components/ReadCount';
import TimeStampLine from "pages/StyleTalkRoom/components/TimeStampLine";
import DividerLine from "pages/StyleTalkRoom/components/DividerLine";
import MessageContent from "pages/StyleTalkRoom/components/MessageContent";
import { fetchChatMessages } from "api/get";

const defaultMessage = [
    {
        model: {
            content: "How are you dwdqwdwqdwdqwdqwdwqdqwdwqdqwdqwdqwqwdqwdqwdwq?",
            direction: "outgoing",
            type: "text",
        },
        avatar: {
            src: "/default_profile.jpg",
            nickname: "영식이",
        },
    },
    {
        model: {
            content: "I'm fine, thank you, and you?",
            direction: "incoming",
            type: "text",
        },
    },
    {
        model: {
            content: "I'm fine, too. thank you, and you?",
            direction: "outgoing",
            type: "text",
        },
        avatar: {
            src: "/default_profile.jpg",
            nickname: "영식이",
        },
    },
];

const convertResponseToChat = (messageData) => {
    return {
        model: {
            content: messageData.content,
            direction: messageData.sender === 1 ? "outgoing" : "incoming",
            type: messageData.type.toLowerCase(),
        },
        avatar: {
            src: "/default_profile.jpg",
            nickname: "영식이",
        },
    };
};


const getMessageComponent = (data) => {
    return Array.isArray(data) ? data.map((item, index) => (
        <MessageGroup key={index}>
            {item.avatar ? (
                <Avatar userInfo={item.avatar} />
            ) : null}
            <Message key={index} model={item.model}>
                <MessageContent message={item.model.content} />
                <MessageMetaInfo>
                    <SentTime />
                    <ReadCount />
                </MessageMetaInfo>
            </Message>
        </MessageGroup>
    )) : null;
};

const ChatRoom = () => {
    const { type, id } = useParams();
    const navigate = useNavigate();
    const [messages, setMessages] = useState(defaultMessage);
    const [inputValue, setInputValue] = useState("");
    const [stompClient, setStompClient] = useState(null);
    const userInfo = {
        userProfileImageUrl: "/default_profile.jpg",
        nickname: "nickon",
    };

    const loadChats = async () => {
        try {
            const response = await fetchChatMessages(id);
            setMessages(response.data);
        } catch (error) {
            console.error('채팅 리스트를 조회하는데 실패했습니다.', error);
        }
    };

    useEffect(() => {
        const socket = new SockJS("/ws/chat");
        const client = Stomp.over(socket);
        setStompClient(client);

        const token = localStorage.getItem('ACCESS_TOKEN');
        const bearer = localStorage.getItem('GRANT_TYPE');

        if (!token || !bearer) {
            console.error('토큰 또는 권한 유형이 누락되었습니다.');
            alert('인증 정보가 누락되었습니다. 다시 로그인해 주세요.');
            navigate('/login');
            return;
        }

        console.log(`Token: ${token}`);
        console.log(`Bearer: ${bearer}`);

        const headers = {
            Authorization: `${bearer} ${token}`,
        };

        client.connect(headers, (frame) => {
            console.log('Connected: ' + frame);
            loadChats();

            client.subscribe(`/sub/talks/${id}`, (message) => {
                const response = JSON.parse(message.body);

                const newChat = convertResponseToChat(response);

                setMessages(prevMessages => [...(prevMessages || []), newChat]);
            });
        }, (error) => {
            console.error('Connection error: ', error);
            alert('채팅방 연결에 실패했습니다. 다시 시도해주세요 [ERROR]');
            navigate(-1);
        });

        return () => {
            if (client) {
                client.disconnect();
            }
        };
    }, [id]);

   const handleSend = (input) => {
       if (!input.trim()) return;

       const token = localStorage.getItem('ACCESS_TOKEN');
       const bearer = localStorage.getItem('GRANT_TYPE');

       if (!token || !bearer) {
           console.error('토큰 또는 권한 유형이 누락되었습니다.');
           alert('인증 정보가 누락되었습니다. 다시 로그인해 주세요.');
           navigate('/login');
           return;
       }

       const newMessage = {
           contentType: "TEXT",
           content: input,
           token: `${bearer} ${token}`
       };

       if (stompClient) {
           stompClient.send(`/pub/talks/${id}/send`, {}, JSON.stringify(newMessage));
       }

       setInputValue("");
   };





    return (
        <section id="Style-talk">
            <AboutHeader title={"#title"} />
            <DividerLine />
            <MainContainer>
                <ChatContainer>
                    <TimeStampLine />
                    <MessageList>{getMessageComponent(messages)}</MessageList>
                </ChatContainer>
                <ContentBox icon={<span className="material-icons button-icon">sentiment_satisfied_alt</span>} onSubmit={handleSend} />
            </MainContainer>
        </section>
    );
};

export default ChatRoom;
