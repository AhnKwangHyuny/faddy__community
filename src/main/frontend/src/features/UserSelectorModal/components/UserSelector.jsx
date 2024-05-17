import React from 'react';
import { useState } from 'react';


const UserSelector = ({ user, isSelected, onSelectUser }) => {
  const [isChecked, setIsChecked] = useState(false);

  return (
    <div className="user-profile">
      <div className="user-info">
        <div className="user-image-container">
            <img src={user.profileImageUrl} alt="profile" />
        </div>
        <div className = "user-nickname">
            <span className="user-name">{user.nickname}</span>
        </div>
      </div>
      <div className="user-checkbox">
        <input
          type="checkbox"
          checked={isChecked}
          onChange={(e) => {
            setIsChecked(e.target.checked);
            onSelectUser(user);
          }}
        />
      </div>
    </div>
  );
};

export default UserSelector;
