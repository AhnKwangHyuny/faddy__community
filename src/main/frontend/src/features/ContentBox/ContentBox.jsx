import React, {useEffect, useState} from 'react';

const ContentBox = ({icon,placeholder = "자유롭게 글 작성해주세요.",onSubmit}) => {
    const [comment, setComment] = useState('');

    const handleCommentChange = (e) => {
        setComment(e.target.value);
    };

    const handleOnSubmit = () => {
        onSubmit();

        // input box 초기화
        setComment("");
    }

    return (
        <div className="comment-inputBox">
            <div className="comment-inputBox__wrapper">
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
                        value={comment}
                        onChange={handleCommentChange}
                    />
                </div>
                <div className="submit-comment">
                    <div className="submit-button" onClick={handleOnSubmit}>
                        <span className="material-icons">send</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ContentBox;