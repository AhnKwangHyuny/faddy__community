import React from 'react';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse, faCamera, faComments, faUser } from '@fortawesome/free-solid-svg-icons';
// import { fa } from '@fortawesome/free-regular-svg-icons';

export default function BottomNavigationBar() {
    const [value, setValue] = React.useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <div className="tabBar__container">
            <nav className="nav__bar">
                <ul className="item__list">
                    <li className="item">
                        <Link to="/styleShare" className="item__container">
                            <div className="icon__container">
                                <FontAwesomeIcon icon={faHouse} className="icon"/>
                            </div>
                            <div className="menu__name">Home</div>
                        </Link>
                    </li>
                    <li className="item">
                        <Link to="/snaps" className="item__container">
                            <div className="icon__container">
                                <FontAwesomeIcon icon={faCamera} className="icon"/>
                            </div>
                            <div className="menu__name">Snap</div>
                        </Link>
                    </li>
                    <li className="item">
                        <Link to="/talks" className="item__container">
                            <div className="icon__container">
                                <FontAwesomeIcon icon={faComments} className="icon"/>
                            </div>
                            <div className="menu__name">Talk</div>
                        </Link>
                    </li>
                    <li className="item">
                        <Link to="/talks" className="item__container">
                            <div className="icon__container">
                                <FontAwesomeIcon icon={faComments} className="icon"/>
                            </div>
                            <div className="menu__name">Talk</div>
                        </Link>
                    </li>
                    <li className="item">
                        <Link to="/myPage" className="item__container">
                            <div className="icon__container">
                                <FontAwesomeIcon icon={faUser} className="icon"/>
                            </div>
                            <div className="menu__name">My</div>
                        </Link>
                    </li>
                </ul>
            </nav>
        </div>
    );
}
