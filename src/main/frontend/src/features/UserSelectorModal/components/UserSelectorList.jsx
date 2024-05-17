import React, { useState } from 'react';

import UserSelector from 'features/UserSelectorModal/components/UserSelector';

const UserSelectorList = () => {
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

    // following user data set 15개 nickname , profileImageUrl
    const followedUsers = [
        {
            id: 1,
            nickname: 'Chris Kim',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 2,
            nickname: 'Younghee Lee',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 3,
            nickname: 'Minsu Park',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 4,
            nickname: 'Youngsoo Choi',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 5,
            nickname: 'Chulsoo Lee',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 6,
            nickname: 'Jinwoo Kim',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 7,
            nickname: 'Hana Lee',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 8,
            nickname: 'Sungmin Park',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 9,
            nickname: 'Jiyoung Lee',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 10,
            nickname: 'Hyejin Kim',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 11,
            nickname: 'Joonoh Lee',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 12,
            nickname: 'Soojin Kim',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 13,
            nickname: 'Hyeonju Lee',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 14,
            nickname: 'Jihoon Park',
            profileImageUrl: '/default_profile.jpg'
        },
        {
            id: 15,
            nickname: 'Yuna Kim',
            profileImageUrl: '/default_profile.jpg'
        },
    ];

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
            <p>선택된 사용자: {selectedUsers.map((user) => user.nickname).join(', ')}</p>
        </div>
    );
};

export default UserSelectorList;