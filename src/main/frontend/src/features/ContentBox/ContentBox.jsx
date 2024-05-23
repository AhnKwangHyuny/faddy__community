import React, {useEffect, useState} from 'react';

const ContentBox = ({icon,placeholder = "자유롭게 글 작성해주세요.",onSubmit}) => {
    const [content, setContent] = useState('');

    const handleCommentChange = (e) => {
        setContent(e.target.value);
    };

    const handleOnSubmit = () => {
        onSubmit(content);

        // input box 초기화
        setContent("");
    }

    return (
        <div className="content-inputBox">
            <div className="content-inputBox__wrapper">
                <div className="emoticon-box">
                    <div className="emoticon-icon">
                        {icon}
                    </div>
                </div>
                <div className="inputBox">
                    <input
                        type="text"
                        className="textForm"
                        placeholder={placeholder}
                        value={content}
                        onChange={handleCommentChange}
                    />
                </div>
                <div className="submit-content">
                    <div className="submit-button" onClick={handleOnSubmit}>
                        <span className="material-icons">send</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ContentBox;