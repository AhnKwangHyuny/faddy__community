import React from 'react';
import { useState } from 'react';

import Header from "features/UserSelectorModal/components/Header";
import UserSelectorList from "features/UserSelectorModal/components/UserSelectorList";
import SearchBar from "features/SearchBar/SearchBar";

const UserSelectorModal = () => {

    const [isClosed , setIsClosed] = useState(false);

    const handleClose = () => {
        setIsClosed(true);
    }

    return (
        <div className = "user-selector-modal">
            <div className = "modal-main-container">
                <Header onClick={handleClose}/>
                <SearchBar/>
                <div className = "friend-container">
                    <span className = "friend"> 친구 </span>
                  </div>
                <UserSelectorList/>
            </div>
        </div>
    );
}

export default UserSelectorModal;