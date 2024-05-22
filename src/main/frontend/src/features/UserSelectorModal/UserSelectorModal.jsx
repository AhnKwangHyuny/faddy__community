import React, { useState, useEffect } from 'react';
import Header from 'features/UserSelectorModal/components/Header';
import UserSelectorList from 'features/UserSelectorModal/components/UserSelectorList';
import SearchBar from 'features/SearchBar/SearchBar';
import { getFollowList } from 'api/get';
import { createFriendChatRoom } from 'api/post';
import {useNavigate} from 'react-router-dom';

const UserSelectorModal = ({ isShowModal, onClose }) => {
   const [isClosed, setIsClosed] = useState(!isShowModal);
   const [followedUsers, setFollowedUsers] = useState([]);
   const [isActive, setIsActive] = useState(false);
   const navigate = useNavigate();

   // user 선택 시 해당 user Id 값 가져오기
   const [selectedUserIds, setSelectedUserIds] = useState([]);
   const handleClose = () => {
       setIsClosed(true);
       onClose();
   };

   const handleUserSelectionChange = (selectedUserIds) => {
       setSelectedUserIds(selectedUserIds);
       setIsActive(selectedUserIds.length > 0);
   };

   const handleUserSelect = (userId) => {
       setSelectedUserIds((prevSelectedUserIds) => {
           const newSelectedUserIds = prevSelectedUserIds.includes(userId)
               ? prevSelectedUserIds.filter((id) => id !== userId)
               : [...prevSelectedUserIds, userId];
           handleUserSelectionChange(newSelectedUserIds);
           return newSelectedUserIds;
       });
       console.log(selectedUserIds);
   };

   const handleCreateChatRoom = async () => {
       console.log("선택된 유저 ID:", selectedUserIds);
       // 채팅 생성 요청 API 호출
       const request = {
           masterId: localStorage.getItem('userId'),
           memberIds: selectedUserIds,
           type: "FRIEND"
       };
       //서버에 친쿠톡 데이터 리스트 요청 api
       const result = await createFriendChatRoom(request);
       // result status가 '200'이면 채당 chatRoom으로 이동
       if(result.status === 200){
            navigate(`/talks/${result.roomId}/friend`);
            return;
       };
   }

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
                   handleUserSelect={handleUserSelect}
                   selectedUserIds={selectedUserIds}
               />
           </div>
       </div>
   );
};

export default UserSelectorModal;