import React, { useState } from 'react';
import CommentModal from "widgets/Modal/CommentModal/CommentModal"

const CommentInputBox = () => {
    const [showModal, setShowModal] = useState(false);
    const [comment, setComment] = useState('');

    const handleOpenModal = () => {
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setComment('');
    };

    const handleSubmitComment = () => {
        // 댓글 처리 로직 추가
        handleCloseModal();
    };

    const handleCommentChange = (e) => {
        setComment(e.target.value);
    };

    return (
        <div className="comment-inputBox">
            <div
                className="comment-inputBox__wrapper"
                onClick={showModal ? null : handleOpenModal}
            >
                <div className="emoticon-box">
                    <div className="emoticon-icon">
            <span className="material-icons button-icon">
              sentiment_satisfied_alt
            </span>
                    </div>
                </div>
                <div className="inputBox">
                    <input
                        type="text"
                        className="textForm"
                        placeholder="자유롭게 댓글 달아주세요."
                        value={comment}
                        onChange={handleCommentChange}
                        disabled={!showModal}
                    />
                </div>
                <div className="submit-comment">
                    <div className="submit-button" onClick={handleSubmitComment}>
                        <span className="material-icons">send</span>
                    </div>
                </div>
            </div>
            <CommentModal
                isOpen={showModal}
                onClose={handleCloseModal}
                onSubmit={handleSubmitComment}
                comment={comment}
                setComment={setComment}
            />
        </div>
    );
};

export default CommentInputBox;