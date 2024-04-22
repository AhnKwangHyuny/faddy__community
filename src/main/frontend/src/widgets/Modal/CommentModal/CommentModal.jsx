import React, { useState } from 'react';

const CommentModal = ({ isOpen, onClose  }) => {
    const [comment, setComment] = useState('');

    const handleCommentChange = (e) => {
        setComment(e.target.value);
    };

    const handleSubmit = () => {
        // 댓글 제출 로직 추가
        onClose();
    };

    return (
        <div className={`comment-modal ${isOpen ? 'open' : ''}`}>

            <div className="comment-modal__wrapper">
                <div className="comment-modal__header">
                    <div className="close-button">

                    </div>

                    <div className="comment-count__container">
                        <span className="comment-count">

                        </span>
                    </div>
                </div>


                <div className="comment-modal__content">
                    <ul className="comment-list">
                        <li className="comment-wrapper">
                            <div className="comment">

                            </div>
                        </li>
                    </ul>
                </div>
            </div>


        </div>
    );
};

export default CommentModal;