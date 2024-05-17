import React, { useState } from 'react';

import UserSelector from 'features/UserSelectorModal/components/UserSelector';

const UserSelectorList = ({followedUsers}) => {
    const [selectedUsers, setSelectedUsers] = useState([]);

    const handleUserSelect = (user) => {
        setSelectedUsers((prevSelectedUsers) => {
            if (prevSelectedUsers.includes(user)) {
                // 체크박스 선택 취소 시 해당 사용자를 제거
                return prevSelectedUsers.filter((selectedUser) => selectedUser !== user);
            } else if (prevSelectedUsers.length < 100) {
                return [...prevSelectedUsers, user];
            } else {
                return prevSelectedUsers;
            }
        });
    };


    return (
        <div className="user-selector-list">
            <ul className="selectors">
                {followedUsers.map((user) => (
                    <li key={user.id}>
                        <UserSelector
                            user={user}
                            isSelected={selectedUsers.includes(user)}
                            onSelectUser={handleUserSelect}
                        />
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default UserSelectorList;