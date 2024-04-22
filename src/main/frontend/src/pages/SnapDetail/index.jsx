import React from "react";
import ProfileFollowAction from "./Components/ProfileFollowAction/ProfileFollowAction";
import Header from "pages/SnapDetail/Components/Header/Header"
import ImageCarousel from "widgets/Carousel/Carousel";
import Interaction from "features/interaction";
import HashtagViewer from "widgets/HashtagViewer/HashtagViewer";
import TextArea from "widgets/TextArea/TextArea";
import CommentInputBox from "widgets/Comment/CommentInputBox/CommentInputBox";


const SnapDetail = ({userId}) => {
    const textData = `오늘은 정말 맑은 날씨입니다. 산책하기 좋은 날이네요!

저녁에는 친구들과 만나서 저녁 식사를 할 예정입니다.
정말 기대됩니다!`;

    return (
        <div>
        <Header/>
        <div id = {userId} className="main__body">
            <ProfileFollowAction/>
            <ImageCarousel/>
        </div>
        <Interaction/>
        <HashtagViewer hashtagList={['나이키', '한정판', '황금돼지띠', '킴미히']}/>
        <TextArea textData={textData}/>
        <CommentInputBox/>
        </div>
    );

};

export default SnapDetail;