import React, { useState } from 'react';

const InputBox = () => {
    const [comment, setComment] = useState('');

    const handleSubmitComment = () => {
        // 댓글 처리 로직 추가
    };

    const handleCommentChange = (e) => {
        setComment(e.target.value);
    };

    return (
        <div className="input-box">
            <div className="input-box__wrapper">
                <div className="emoticon-box">
                    <div className="emoticon-icon">
                        <span className="material-icons button-icon">
                          sentiment_satisfied_alt
                        </span>
                    </div>
                </div>
                <div className="input-field">
                    <input
                        type="text"
                        className="text-form"
                        placeholder="자유롭게 댓글 달아주세요."
                        value={comment}
                        onChange={handleCommentChange}
                    />
                </div>
                <div className="submit-comment">
                    <div className="submit-button" onClick={handleSubmitComment}>
                        <span className="material-icons">send</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default InputBox;
