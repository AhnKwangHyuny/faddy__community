import React, { useEffect, useState } from "react";
import ProfileFollowAction from "./Components/ProfileFollowAction/ProfileFollowAction";
import Header from "pages/SnapDetail/Components/Header/Header";
import { ImageCarouselWithHTML } from "widgets/Carousel/Carousel";
import Interaction from "features/interaction";
import HashtagViewer from "widgets/HashtagViewer/HashtagViewer";
import TextArea from "widgets/TextArea/TextArea";
import CommentInputBox from "widgets/Comment/CommentInputBox/CommentInputBox";
import { useNavigate } from "react-router-dom";
import { getSnapData } from "api/get";

const SnapDetail = () => {
    const navigate = useNavigate();
    const encryptedSnapId = new URLSearchParams(window.location.search).get('snapId');
    const [snap, setSnap] = useState(null);
    const [userId, setUserId] = useState(null); // userId 상태 추가

    const textData = `오늘은 정말 맑은 날씨입니다. 산책하기 좋은 날이네요! 저녁에는 친구들과 만나서 저녁 식사를 할 예정입니다. 정말 기대됩니다!`;

    // 컴포넌트 마운트 시 userId 확인 및 snap data 서버에 요청
    useEffect(() => {

        // 스냅 아이디가 존재하지 않으면 전 페이지로 리다이렉트
        if (!encryptedSnapId) {
            alert("접근할 수 없는 스냅입니다.");
            return navigate(-1);
        }

        getSnapData(encryptedSnapId);
    }, [encryptedSnapId, userId]);

    const testSetting = {
        dots: true,
        infinite: true,
        speed: 600,
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
    };

    const testDataList = Array.from({ length: 6 }, () => <img src="/default_profile.jpg" alt="default image" />);

    return (
        <div>
            <Header />
            <div id={userId} className="main__body">
                <ProfileFollowAction />
                <ImageCarouselWithHTML setting={testSetting} htmlList={testDataList} />
            </div>
            <Interaction />
            <HashtagViewer hashtagList={['나이키', '한정판', '황금돼지띠', '킴미히']} />
            <TextArea textData={textData} />
            <CommentInputBox />
        </div>
    );
};

export default SnapDetail;