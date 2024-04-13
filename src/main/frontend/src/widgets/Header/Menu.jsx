import React from 'react'

import {searchKeyword } from '../../data/header'
import { Link, useLocation } from 'react-router-dom'

const Menu = () => {
    const location = useLocation();

    return (
        <nav className='header__menu'>

            <ul className='keyword'>
                {searchKeyword.map((keyword, key) => (
                    <li key={key} className={location.pathname === keyword.src ? 'active' : ''}>
                        <Link to={keyword.src}>
                            {keyword.title}
                        </Link>
                    </li>
                ))}
            </ul>
        </nav>
    )
}

export default Menu