import React from "react";

import Header from "pages/SnapDetail/Components/Header/header";


const SnapDetail = ({userId}) => {

    return (
        <div id = {userId} className="main__body">
            <Header/>
        </div>
    );

};

export default SnapDetail;