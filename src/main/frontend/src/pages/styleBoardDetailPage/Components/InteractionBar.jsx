import React, { useState } from 'react';

const InteractionBar = ({ initialLikes, shares }) => {
    const [likes, setLikes] = useState(initialLikes);
    const [liked, setLiked] = useState(false);

    const handleLike = () => {
        if (liked) {
            setLikes(likes - 1);
        } else {
            setLikes(likes + 1);
        }
        setLiked(!liked);
    };

    return (
        <div className="interaction-bar">
            <div className="interaction-bar__item like" onClick={handleLike}>
                <span className={`material-icons thumb-up ${liked ? 'liked' : ''}`}>thumb_up</span>
                <span className="interaction-bar__count">32</span>
            </div>
            <div className="interaction-bar__item share">
                <span className="material-icons share-icon">share</span>
                <span className = "name">공유하기</span>
            </div>
        </div>
    );
};

export default InteractionBar;
