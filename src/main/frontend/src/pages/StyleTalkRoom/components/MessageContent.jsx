import React from 'react';

const MessageContent = ({ message }) => {
    // URL 형태인 경우
    if(message.match(/(http(s)?:\/\/)([a-z0-9\w]+\.*)+[a-z0-9]{2,4}/gi)){
        return (
            <div className="message-content">
                <a href={message} target="_blank" rel="noreferrer" className="message-content-url">
                    {message}
                </a>
            </div>
        );
    }

    // 이미지 URL인 경우
    if(message.match(/\.(jpeg|jpg|gif|png)$/)){
        return (
            <div className="message-content">
                <img src={message} alt="이미지" className="message-content-image" />
            </div>
        );
    }

    // URL 형태가 아닌 텍스트인 경우
    return <div className="message-content">{message}</div>;
}

export default MessageContent;