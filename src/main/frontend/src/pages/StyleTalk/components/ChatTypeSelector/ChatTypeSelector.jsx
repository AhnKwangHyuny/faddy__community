
const ChatTypeSelector = () => {

    // 오픈채팅을 클릭했을 때 호출될 함수
    const handleOpenChatClick = () => {
        console.log("오픈채팅 선택됨");
        // 여기에 오픈채팅 관련 로직을 추가할 수 있습니다.
    };

    // 친구채팅을 클릭했을 때 호출될 함수
    const handleFriendChatClick = () => {
        console.log("친구채팅 선택됨");
        // 여기에 친구채팅 관련 로직을 추가할 수 있습니다.
    };

    return (
        // React 컴포넌트
        <section id="ChatTypeSelector">
            <div className="chatTypeSelector__container">
                <div className="chat-name__container">
                    <div className="chat-name">
                        <div className="chat-name__wrapper">
                            <button
                                className="chat-name__button"
                                onClick={handleOpenChatClick}
                            >
                                <span className="sorting-name">오픈톡</span>
                            </button>
                        </div>
                    </div>
                    <div className="chat-name">
                        <div className="chat-name__wrapper">
                            <button
                                className="chat-name__button"
                                onClick={handleFriendChatClick}
                            >
                                <span className="sorting-name">친구톡</span>
                            </button>
                        </div>
                    </div>
                </div>
                <hr className="hdlne line" />
            </div>
        </section>
    );
};


export default ChatTypeSelector;