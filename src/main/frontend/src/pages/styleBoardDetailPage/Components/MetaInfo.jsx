import React from 'react';

const MetaInfo = ({ userProfile, interaction }) => {
    return (
        <div className="meta-info">
            <div className = "profile-image_container">
                <img src={userProfile.profileImageUrl} alt="프로필 이미지" className="profile-image" />
            </div>

            <div className = "userProfile-interaction_container">
                <div className="user-profile">
                    <span className="user-level">{userProfile.level}</span>
                    <span className="user-nickname">{userProfile.nickname}</span>
                    <span className="created-at">• {userProfile.createdAt}</span>
                </div>
                <div className="interaction">
                    <div className="interaction-icon">
                        <span className="material-symbols-outlined icon">visibility</span>
                        <span className="count">{interaction.views}</span>
                    </div>
                    <div className="interaction-icon">
                        <span className="material-symbols-outlined icon">thumb_up</span>
                        <span className="count">{interaction.likes}</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default MetaInfo;
