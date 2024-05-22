import ChatListItem from "pages/StyleTalk/components/ChatListItem/ChatListItem";
import { getTalkList } from "api/get";
import { useState, useEffect } from "react";
import LoadingAnimation from "shared/ui/LoadingUI/Loading"

const ChatList = ({ chatType }) => {
    const [chatList, setChatList] = useState([]);
    const [loading , setLoading] = useState(true);

    // chat type에 따른 chat List 서버에 api 요청
    useEffect(() => {
        const fetchChatList = async () => {
            setLoading(true);
            try {
                const response = await getTalkList(chatType);
                setChatList(response);
            } catch (error) {
                console.error("Failed to fetch chat list", error);
            } finally {
                setLoading(false);
            }
        };

        fetchChatList();
    }, [chatType]);

    return (
        <section id="chatList">
            {loading ? (
                <LoadingAnimation/>
            ) : (
                chatList.map((chatItem, index) => (
                    <ChatListItem key={index} chatItem={chatItem} />
                ))
            )}
        </section>
    );
}

export default ChatList;
