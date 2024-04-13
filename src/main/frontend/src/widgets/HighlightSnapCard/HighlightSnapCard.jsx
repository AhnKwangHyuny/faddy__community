import React from 'react';
import default_image from 'shared/img/default_profile.jpg';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faEye , faHeart} from '@fortawesome/free-regular-svg-icons';


const HighlightSnapCard = () => {
    const username = "testUser";
    const ranking = 1;

    return (
        <div className="snap__root">
            <div className="thumbnail__container">
                <div className="username">
                    @ {username}
                </div>
                <div className = "image__container">
                    <img className="thumbnail" loading="lazy" alt="default image" src={default_image} />
                </div>

            </div>
            <div className="ranker__name__container">

                <div className="ranking__container">
                    <div className="ranking">
                        {ranking}
                    </div>

                </div>

                <div className = "username__container">
                    <div className="username">
                        테니스치는남자
                    </div>
                </div>
            </div>
        </div>
    );
};

export default HighlightSnapCard;
