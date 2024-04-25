import React, { useState } from 'react';
import {formatCount} from "utils/typeFormatUtils/typeFormatUtils";
import LikeButton from "widgets/LikeButton/LikeButton";


const Thumbnail = ({ imageUrl, userProfile, writer, hashtags, views, likes: initialLikes }) => {
    const [isLiked, setIsLiked] = useState(false);
    const [likes, setLikes] = useState(initialLikes);

    const handleLikeClick = () => {
        setIsLiked(!isLiked);
        setLikes(isLiked ? likes -1  : likes + 1);

        // like logic
    };

    return (
        <div className="thumbnail-card">
            <div className="image-container">
                <img src={imageUrl} alt="Thumbnail" />

                <div className="like-button__container">
                    <LikeButton
                        onLikeChange={handleLikeClick}
                        showLikeCount={false}
                    />
                </div>
            </div>
            <div className="content">
                <div className="user-profile">
                    <img src={userProfile} alt="User Profile" />
                    <span>{writer}</span>
                </div>
                {hashtags && hashtags.length > 0 && (
                    <div className="hashtags">
                        {hashtags.map((hashtag) => (
                            <span key={hashtag}>{hashtag}</span>
                        ))}
                    </div>
                )}
                <div className="stats">
                    <div className="view__container">
                        <span className={"material-icons view"}>
                            visibility
                        </span>

                        <span className="view-count">
                            {formatCount(views)}
                        </span>
                    </div>

                    <div className="like__container">
                       <span
                           className={`material-icons like`}
                           style={{color: isLiked ? 'red' : 'inherit'}}
                       >
                            {isLiked ? 'favorite' : 'favorite_border'}
                        </span>

                        <span className="like-count">
                            {formatCount(likes)}
                        </span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Thumbnail;