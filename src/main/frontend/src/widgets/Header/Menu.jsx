import React, { useState, useCallback } from 'react';
import menuData from 'data/header/menuData';
import { Link, useLocation } from 'react-router-dom';
import CreateRoomModal from 'pages/StyleTalk/components/CreateRoomModal/CreateRoomModal';

const Menu = () => {
  const location = useLocation();
  const currentPath = location.pathname;
  const [showModal, setShowModal] = useState(false);

  const handleShowModal = () => {
      setShowModal(true);
  };

  const handleCloseModal = () => {
        setShowModal(false);
    }

  const renderMenuItems = useCallback((items) => {
    return items.map((item, key) => (
      <li key={key} className={currentPath === item.src ? 'active' : ''}>
        {item.type === 'link' ? (
          <Link to={item.src}>{item.title}</Link>
        ) : item.type === 'modal' ? (
          <button onClick={handleShowModal}>{item.title}</button>
        ) : null}
      </li>
    ));
  }, [currentPath, handleShowModal]);

  return (
    <div>
      <nav className='header__menu'>
        <ul className='keyword'>
          {renderMenuItems(menuData[currentPath] || [])}
        </ul>
      </nav>
      {showModal && <CreateRoomModal isShowModal = {showModal} onClose = {handleCloseModal} />}
    </div>
  );
};

export default Menu;