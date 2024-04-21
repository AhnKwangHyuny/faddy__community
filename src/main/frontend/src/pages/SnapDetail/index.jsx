import React from "react";
import ProfileFollowAction from "./Components/ProfileFollowAction/ProfileFollowAction";
import Header from "pages/SnapDetail/Components/Header/Header"
import ImageCarousel from "widgets/Carousel/Carousel";
import Interaction from "features/interaction";


const SnapDetail = ({userId}) => {

    return (
        <div>
        <Header/>
        <div id = {userId} className="main__body">
            <ProfileFollowAction/>
            <ImageCarousel/>
        </div>
        <Interaction/>

        </div>
    );

};

export default SnapDetail;