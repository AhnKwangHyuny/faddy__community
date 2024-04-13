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
                        <div className="item__container">
                            <div className="icon__container">
                                <FontAwesomeIcon icon={faHouse} />
                            </div>
                            <div className="menu__name">Home</div>
                        </div>
                    </li>

                    <Link to = "/snaps">
                        <li className="item">
                                <div className="item__container">
                                    <div className="icon__container">
                                        <FontAwesomeIcon icon={faCamera} />
                                    </div>
                                    <div className="menu__name">Snap</div>
                                </div>
                        </li>
                    </Link>

                    <li className="item">
                        <div className="item__container">
                            <div className="icon__container">
                                <FontAwesomeIcon icon={faComments} />
                            </div>
                            <div className="menu__name">Talk</div>
                        </div>
                    </li>

                    <li className="item">
                        <div className="item__container">
                            <div className="icon__container">
                                <FontAwesomeIcon icon={faComments} />
                            </div>
                            <div className="menu__name">Talk</div>
                        </div>
                    </li>

                    <li className="item">
                        <div className="item__container">
                            <div className="icon__container">
                                <FontAwesomeIcon icon={faUser} />
                            </div>
                            <div className="menu__name">My</div>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
    );
}
