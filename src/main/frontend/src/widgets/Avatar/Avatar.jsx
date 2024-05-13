import React from 'react';

const Avatar = ({ userInfo }) => {
  const { src, nickname } = userInfo;

  return (
    <div className="user-profile">
      <div className="user-profile__avatar">
        <img src={src} alt={nickname} className="user-profile__avatar-img" />
      </div>
      <div className="user-profile__name">{nickname}</div>
    </div>
  );
};

export default Avatar;