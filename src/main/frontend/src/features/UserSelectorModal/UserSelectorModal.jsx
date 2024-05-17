import React from 'react';
import { useState , useEffect } from 'react';

import Header from "features/UserSelectorModal/components/Header";
import UserSelectorList from "features/UserSelectorModal/components/UserSelectorList";
import SearchBar from "features/SearchBar/SearchBar";
import { getFollowList } from "api/get";

const UserSelectorModal = () => {

    const [isClosed , setIsClosed] = useState(false);
    const [followedUsers, setFollowedUsers] = useState([]);

    const handleClose = () => {
        setIsClosed(true);
    }

    useEffect(() => {
        // 모달이 열릴 때 팔로우 리스트를 가져오기
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
        <div className = "user-selector-modal">
            <div className = "modal-main-container">
                <Header onClick={handleClose}/>
                <SearchBar/>
                <div className = "friend-container">
                    <span className = "friend"> 친구 </span>
                  </div>
                <UserSelectorList followedUsers={followedUsers} />
            </div>
        </div>
    );
}

export default UserSelectorModal;