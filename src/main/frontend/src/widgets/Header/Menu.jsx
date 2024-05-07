import React from 'react'

import menuData from 'data/header/menuData'
import { Link, useLocation } from 'react-router-dom'

const Menu = () => {
  const location = useLocation();
  const currentPath = location.pathname;

  const renderMenuItems = (items) => {
    return items.map((item, key) => (
      <li key={key} className={currentPath === item.src ? 'active' : ''}>
        <Link to={item.src}>
          {item.title}
        </Link>
      </li>
    ));
  }

  return (
    <nav className='header__menu'>
      <ul className='keyword'>
        {renderMenuItems(menuData[currentPath] || [])}
      </ul>
    </nav>
  )
}

export default Menu