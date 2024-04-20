import React from "react";
import ProfileFollowAction from "./Components/ProfileFollowAction/ProfileFollowAction";
import Header from "pages/SnapDetail/Components/Header/Header"
import ImageCarousel from "widgets/Carousel/Carousel";


const SnapDetail = ({userId}) => {

    return (
        <>
        <Header/>
        <div id = {userId} className="main__body">
            <ProfileFollowAction/>
            <ImageCarousel/>
        </div>
        </>
    );

};

export default SnapDetail;