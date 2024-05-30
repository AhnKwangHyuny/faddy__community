import SockJS from "sockjs-client";

import React, { useState, useEffect, useRef } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Stomp } from "@stomp/stompjs";
import { fetchChatMessages, checkChatRoomAccess } from "api/get";
import {isValidURL} from "utils/utils";

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


const getCurrentUsername = () => {
    return localStorage.getItem("username");
};

const convertResponseToChat = (messageData) => {
    const currentUsername = getCurrentUsername();
    let direction;

    const messageType = messageData.type.toLowerCase();

    if (messageType === "system" || messageType === "timestamp") {
        direction = "center";
    } else {
        console.log(messageData.sender, currentUsername);
        direction = messageData.sender === currentUsername ? "incoming" : "outgoing";
    }

    return {
        model: {
            content: messageData.content,
            direction: direction,
            type: messageType,
        },
        avatar: direction === "outgoing" ? {
            src: "/default_profile.jpg",
            nickname: "영식이",
        } : null,
    };
};

const getMessageComponent = (data) => (
    Array.isArray(data) ? data.map((item, index) => (
        <MessageGroup key={index}>
            {item.avatar && <Avatar userInfo={item.avatar} />}
            <Message model={item.model}>
                <MessageContent message={item.model.content} type = {item.model.type} />
                <MessageMetaInfo>
                    <SentTime />
                    <ReadCount />
                </MessageMetaInfo>
            </Message>
        </MessageGroup>
    )) : null
);

const ChatRoom = () => {
    const { type, id } = useParams();
    const navigate = useNavigate();
    const [messages, setMessages] = useState([]);
    const [inputValue, setInputValue] = useState("");
    const [stompClient, setStompClient] = useState(null);
    const messageEndRef = useRef(null);

    const scrollToBottom = () => {
        messageEndRef.current?.scrollIntoView({ behavior: "smooth" });
    };

    useEffect(() => {
        scrollToBottom();
    }, [messages]);

    const userInfo = {
        userProfileImageUrl: "/default_profile.jpg",
        nickname: "nickon",
    };

    const loadChats = async () => {
        try {
            const response = await fetchChatMessages(id);
            const chatMessages = response.map(convertResponseToChat);
            setMessages(chatMessages);
        } catch (error) {
            console.error('채팅 리스트를 조회하는데 실패했습니다.', error);
        }
    };

    const checkUserPermission = async (roomId) => {
        try {
            const result = await checkChatRoomAccess(roomId);
            if (!result) {
                alert('채팅방 접근 권한이 없습니다.');
                navigate(-1);
            }
        } catch (error) {
            console.error('채팅방 접근 권한을 확인하는데 실패했습니다.', error);
            alert('채팅방 접근 권한을 확인하는데 실패했습니다.');
            navigate(-1);
        }
    };

    const handleEnter = (client, headers) => {
        const token = `${localStorage.getItem('GRANT_TYPE')} ${localStorage.getItem('ACCESS_TOKEN')}`;
        client.send(`/pub/talks/${id}/enter`, headers, JSON.stringify({ token }));
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

        const headers = {
            Authorization: `${bearer} ${token}`,
        };

        client.connect(headers, async (frame) => {
            await checkUserPermission(id);
            await loadChats();
            handleEnter(client, headers);

            client.subscribe(`/sub/talks/${id}`, (message) => {
                const response = JSON.parse(message.body);

                if (Array.isArray(response)) {
                    const newChats = response.map(convertResponseToChat);
                    setMessages((prevMessages) => [...prevMessages, ...newChats]);
                } else {
                    const newChat = convertResponseToChat(response);
                    setMessages((prevMessages) => [...prevMessages, newChat]);
                }
            });
        });

        return () => {
            if (client) {
                client.disconnect();
            }
        };
    }, [id]);

    const handleSend = (input) => {
        if (!input.trim()) return;

        const contentType = isValidURL(input) ? "IMAGE" : "TEXT";

        const token = `${localStorage.getItem('GRANT_TYPE')} ${localStorage.getItem('ACCESS_TOKEN')}`;

        const newMessage = {
            contentType: contentType,
            content: input,
            token,
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
                    <MessageList>
                        {getMessageComponent(messages)}
                        <div ref={messageEndRef} />
                    </MessageList>
                </ChatContainer>
                <ContentBox
                    icon={<span className="material-icons button-icon">sentiment_satisfied_alt</span>}
                    onSubmit={handleSend}
                    value={inputValue}
                    onChange={(e) => setInputValue(e.target.value)}
                />
            </MainContainer>
        </section>
    );
};

export default ChatRoom;
