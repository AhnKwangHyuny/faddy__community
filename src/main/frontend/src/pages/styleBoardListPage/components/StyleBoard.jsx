import React from 'react';

const StyleBoard = ({ data }) => {
    const { title, userProfile, interaction, createdAt, thumbnailImage } = data;

    return (
        <div className="style-board">
            <div className="style-board-content">
                <div className="style-board-title">
                    <span className="title">{title}</span>
                </div>
                <div className="user-profile">
                    <span className="level">{userProfile.level}</span>
                    <span className="nickname">{userProfile.nickname} </span>
                    <span className="created-at"> · {createdAt}</span>
                </div>
                <div className="interaction">
                    <div className="interaction-icon">
                        <span className="material-symbols-outlined">visibility</span>
                        <span className="count">{interaction.views}</span>
                    </div>
                    <div className="interaction-icon">
                        <span className="material-symbols-outlined">thumb_up</span>
                        <span className="count">{interaction.likes}</span>
                    </div>
                    <div className="interaction-icon">
                        <span className="material-symbols-outlined">article</span>
                        <span className="count">{interaction.commentCount}</span>
                    </div>
                </div>
            </div>
            <div className="thumbnail-container">
                <img src="default_profile.jpg" alt={title} className="thumbnail-image" />
            </div>
        </div>
    );
}

export default StyleBoard;
