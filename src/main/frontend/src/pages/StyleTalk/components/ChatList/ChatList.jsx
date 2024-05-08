import ChatListItem from "pages/StyleTalk/components/ChatListItem/ChatListItem";

const ChatList = () => {
    const chatItems = Array.from({ length: 15 }).map((_, index) => (
            <ChatListItem key={index} />
        ));

    return (
        <section id="chatList">
            {chatItems}
        </section>
    );
}

export default ChatList;