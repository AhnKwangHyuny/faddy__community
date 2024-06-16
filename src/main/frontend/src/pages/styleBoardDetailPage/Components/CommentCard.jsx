import React, { useState } from 'react';
import getLevelData from "../../../utils/user/getLevelData";
import { useParams, useSearchParams, useNavigate } from 'react-router-dom';
import {mapToComment} from "pages/styleBoardDetailPage/utils/responseMapper";

const CommentCard = ({ comment, onReplyClick }) => {

    const adComment = mapToComment(comment);
    const { color } = getLevelData(adComment.author.level);
    const levelColor = color ? `#${color}` : '#000000';

    return (
        <div className="comment-card">
            <div className="comment-card__container">
                <div className="avatar-container">
                    <img className="avatar" src={adComment.author.profileImageUrl} alt={adComment.author.nickname} />
                </div>
                <div className="comment-details">
                    <div className="profile-info">
                        <span className="level" style={{ color: levelColor }}>{adComment.author.level}</span>
                        <span className="username">{adComment.author.nickname}</span>
                        <span className="timestamp"> · {adComment.createdAt}</span>
                    </div>
                    <p className="comment-content">{adComment.content}</p>
                    <div className="comment-meta">
                        <div className="reply-button" onClick={onReplyClick}>
                            <span>답글</span>
                        </div>
                        <div className="like-button">
                            <span className="material-icons like">thumb_up_off_alt</span>
                            <span className="count">10</span>
                        </div>
                    </div>
                </div>
                <div className="more-button">
                    <span className="material-icons more">more_vert</span>
                </div>
            </div>

            {comment.replyComments && comment.replyComments.length > 0 && (
                <ul className="reply-list">
                    {comment.replyComments.map((replyComment) => (
                        <li className="reply-card-item" key={replyComment.id}>
                            <ReplyCard comment={replyComment} />
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

const ReplyCard = ({ comment }) => {
    const { color } = getLevelData(comment.user.level);
    const levelColor = color ? `#${color}` : '#000000';

    return (
        <div className="reply-card">
            <div className="reply-card__container">
                <div className="avatar-container">
                    <img className="avatar" src={comment.user.imageUrl} alt={comment.username} />
                </div>
                <div className="comment-details">
                    <div className="profile-info">
                        <span className="level" style={{ color: levelColor }}>{comment.user.level}</span>
                        <span className="username">{comment.user.username}</span>
                        <span className="timestamp"> · {comment.createdAt}</span>
                    </div>
                    <p className="comment-content">{comment.content}</p>
                </div>
                <div className="more-button">
                    <span className="material-icons more">more_vert</span>
                </div>
            </div>
        </div>
    );
};

export default CommentCard;
