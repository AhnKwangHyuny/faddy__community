//message content components
import React from 'react';

const MessageContent = (message) => {

    // url 형태인 경우
    if(url.match(/(http(s)?:\/\/)([a-z0-9\w]+\.*)+[a-z0-9]{2,4}/gi)){
        return <div className="message-content"><a
        href={url}
        target="_blank"
        rel="noreferrer"
        className="message-content-url"
        >
            {url}
        </a></div>;
    }

    // url 형태가 아닌 경우
    const message = data.message;

    return <div className="message-content">{message}</div>;
}

export default MessageContent;