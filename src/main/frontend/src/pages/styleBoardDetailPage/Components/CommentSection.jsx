import React, {useEffect, useState} from 'react';
import CommentCard from "pages/styleBoardDetailPage/Components/CommentCard";
import getLevelData from "../../../utils/user/getLevelData";
import {testData , comments} from "pages/styleBoardDetailPage/data/testData";

const CommentModal = ({showModal ,setShowModal}) => {
    const [comment, setComment] = useState('');

    const handleCLose = () => {
        setShowModal(false);
    };


    const handleCommentChange = (e) => {
        setComment(e.target.value);
    };

    const handleSubmit = () => {
        // 댓글 제출 로직 추가
    };

    return (
        <div className="comment-section">
            <div className="comment-section__wrapper">
                <div className="comment-section__header">
                    <div className="comment-label">
                        <span className="label">
                            댓글 12
                        </span>
                    </div>
                </div>


                <div className="content">
                    <ul className="comment-list">
                        {comments.map((comment) => (
                            <li className="comment-wrapper" key={comment.id}>
                                <CommentCard comment={comment}/>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default CommentModal;