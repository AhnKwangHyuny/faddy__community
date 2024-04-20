import React from "react";
import ProfileFollowAction from "./Components/ProfileFollowAction/ProfileFollowAction";
import Header from "pages/SnapDetail/Components/Header/Header"


const SnapDetail = ({userId}) => {

    return (
        <div id = {userId} className="main__body">
            <Header/>
            <ProfileFollowAction/>
        </div>
    );

};

export default SnapDetail;