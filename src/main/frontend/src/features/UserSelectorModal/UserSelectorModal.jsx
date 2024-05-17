import React, { useState, useEffect } from 'react';
import Header from 'features/UserSelectorModal/components/Header';
import UserSelectorList from 'features/UserSelectorModal/components/UserSelectorList';
import SearchBar from 'features/SearchBar/SearchBar';
import { getFollowList } from 'api/get';

const UserSelectorModal = () => {
    const [isClosed, setIsClosed] = useState(false);
    const [followedUsers, setFollowedUsers] = useState([]);
    const [selectedUserIds, setSelectedUserIds] = useState([]);
    const [isActive, setIsActive] = useState(false);

    const handleClose = () => {
        setIsClosed(true);
    };

    const handleUserSelectionChange = (selectedUserIds) => {
        setSelectedUserIds(selectedUserIds);
        setIsActive(selectedUserIds.length > 0);
    };

    const handleCreateChatRoom = () => {
        console.log("선택된 유저 ID:", selectedUserIds);
        // 선택된 유저들과 나 자신을 포함하여 채팅방을 생성하는 로직을 여기에 구현하세요.
    };

    useEffect(() => {
        const fetchFollowedUsers = async () => {
            try {
                const users = await getFollowList();
                setFollowedUsers(users);
            } catch (error) {
                console.error('팔로우 리스트를 가져오는데 실패했습니다.', error);
            }
        };

        fetchFollowedUsers();
    }, []);

    return (
        <div className="user-selector-modal">
            <div className="modal-main-container">
                <Header onClose={handleClose} onDone={handleCreateChatRoom} isActive={isActive} />
                <SearchBar />
                <div className="friend-container">
                    <span className="friend"> 친구 </span>
                </div>
                <UserSelectorList
                    followedUsers={followedUsers}
                    onUserSelectionChange={handleUserSelectionChange}
                />
            </div>
        </div>
    );
};

export default UserSelectorModal;
