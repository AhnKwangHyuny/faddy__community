import {useNavigate} from "react-router-dom";
import { faAngleLeft } from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import React from "react";

export const BackButton = ({
    label = '뒤로가기',
    className = '',
    onClick = null,
}) => {

    const navigate = useNavigate();

    const handleClick = () => {
        if(onClick) {
            onClick();
        }

        navigate(-1);
    }

    return (
        <div className="back-button">
            <div className="back-button__wrapper">
                <button
                    type="button"
                    className={`back-button ${className}`}
                    onClick={handleClick}
                >
                    <FontAwesomeIcon
                        icon={faAngleLeft}
                        style={{
                            touchAction: 'none',
                            userSelect: 'none',
                            WebkitTapHighlightColor: 'transparent',
                        }}
                    />
                </button>
            </div>
        </div>
    );
};