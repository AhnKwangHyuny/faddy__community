
import React, { useState } from 'react';
import LikeButton from "widgets/LikeButton/LikeButton";
import ShareButton from "widgets/ShareButton/ShareButton";
import CommentButton from "widgets/Comment/CommentButton/CommentButton";

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