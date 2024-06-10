import React, { useState } from 'react'

import Logo from "shared/ui/Logo/Logo"
import {BackButton} from "widgets/Button/Button"
import MenuIcons from "pages/SnapDetail/Components/MenuIcons/MenuIcons.jsx";
import {SubmitButton} from "shared/ui/Button/SubmitButton";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faAngleLeft} from '@fortawesome/free-solid-svg-icons';

const Header = ({topic}) => {


    return (
        <header className="Creation__menu__bar">
            <div className="menu__bar__container">
                <div className="frame__menu">
                    <div className="back__icon__wrapper">
                        <FontAwesomeIcon
                            icon={faAngleLeft}
                        />
                    </div>
                    <div className="title">{topic}</div>
                    <span class="material-symbols-outlined submit-button" style={{ color : '#000000' }}>
                        more_vert
                    </span>
                </div>
            </div>
        </header>
    )
}

export default Header