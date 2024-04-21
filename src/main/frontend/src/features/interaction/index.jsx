
import React, { useState } from 'react';
import LikeButton from "widgets/LikeButton/LikeButton";
import CommentButton from "widgets/CommentButton/CommentButton";
import ShareButton from "widgets/ShareButton/ShareButton";

const Interaction = () => {
    const [isLiked, setIsLiked] = useState(false);
    const [commentOpen, setCommentOpen] = useState(false);

    const handleLikeClick = () => {
        setIsLiked(!isLiked);
    };

    const handleCommentClick = () => {
        setCommentOpen(!commentOpen);
    };

    const handleShareClick = () => {
        // 공유 기능 구현
    };

    return (
        <section className="interaction">
            <LikeButton/>
            <CommentButton/>
            <ShareButton/>
        </section>
    );
};

export default Interaction;