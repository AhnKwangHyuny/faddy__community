import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart as fasHeart } from '@fortawesome/free-solid-svg-icons';
import {faHeart} from "@fortawesome/free-regular-svg-icons";

const LikeButton = () => {
    const [isLiked, setIsLiked] = useState(false);
    const [animationClass, setAnimationClass] = useState('');

    const handleLikeClick = () => {
        setIsLiked(!isLiked);
        setAnimationClass('animate');
    };

    useEffect(() => {
        let animationTimeout;

        if (isLiked) {
            animationTimeout = setTimeout(() => {
                setAnimationClass('');
            }, 2500);
        } else {
            clearTimeout(animationTimeout);
            setAnimationClass('');
        }

        return () => clearTimeout(animationTimeout);
    }, [isLiked]);

    return (
        <div className="like">
            <div className={`like-button ${isLiked ? 'liked' : ''}`} onClick={handleLikeClick}>
                <FontAwesomeIcon
                    icon={isLiked ? fasHeart : faHeart}
                    className={`heart-icon ${animationClass}`}
                />
            </div>
            <div className="like-count">
                <span className="count">16</span>
            </div>
        </div>
    );
};

export default LikeButton;