import React, { useState } from 'react';
import {getLevelInfo} from "data/LevelData";
import { useParams, useSearchParams, useNavigate } from 'react-router-dom';
import {mapToComment} from "pages/styleBoardDetailPage/utils/responseMapper";

const CommentCard = ({ comment, onReplyClick }) => {
    const adComment = mapToComment(comment);
    const levelInfo = getLevelInfo(adComment.author.level);

    return (
        <div className="comment-card">
            <div className="comment-card__container">
                <div className="avatar-container">
                    <img className="avatar" src={adComment.author.profileImageUrl} alt={adComment.author.nickname} />
                </div>
                <div className="comment-details">
                    <div className="profile-info">
                        <span className="level" style={{ color: levelInfo.color} }>{levelInfo.level} {levelInfo.name}</span>
                        <span className="username">{adComment.author.nickname}</span>
                        <span className="timestamp"> · {adComment.created_at}</span>
                    </div>
                    <p className="comment-content">{adComment.content}</p>
                    <div className="comment-meta">
                        <div className="reply-button" onClick={onReplyClick}>
                            <span>답글</span>
                        </div>
                        <div className="like-button">
                            <span className="material-icons like">thumb_up_off_alt</span>
                           {adComment.likeCount > 0 && (
                             <span className="count">{adComment.likeCount}</span>
                           )}
                        </div>
                    </div>
                </div>
                <div className="more-button">
                    <span className="material-icons more">more_vert</span>
                </div>
            </div>

            {adComment.replies && adComment.replies.length > 0 && (
                <ul className="reply-list">
                    {adComment.replies.map((reply) => (
                        <li className="reply-card-item" key={reply.id}>
                            <ReplyCard reply={reply} />
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

const ReplyCard = ({ reply }) => {
    const adReply = mapToComment(reply);
    const levelInfo = getLevelInfo(adReply.author.level);

    return (
         <div className="reply-card">
            <div className="reply-card__container">
                <div className="avatar-container">
                    <img className="avatar" src={adReply.author.profileImageUrl} alt={adReply.nickname} />
                </div>
                <div className="comment-details">
                    <div className="profile-info">
                        <span className="level" style={{ color: levelInfo.color }}>{levelInfo.level} {levelInfo.name}</span>
                        <span className="username">{adReply.author.nickname}</span>
                        <span className="timestamp"> · {adReply.created_at}</span>
                    </div>
                    <p className="comment-content">{adReply.content}</p>
                     <div className="reply-meta">
                        <div className="like-button-reply">
                            <span className="material-icons like-rp">thumb_up_off_alt</span>
                            {adReply.likeCount > 0 && (
                                <span className="count-rp">{adReply.likeCount}</span>
                            )}
                        </div>
                    </div>
                </div>

                <div className="more-button">
                    <span className="material-icons more">more_vert</span>
                </div>
            </div>
        </div>
    );
};

export default CommentCard;
