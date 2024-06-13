import { useParams, useSearchParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { mapTopicToCategory } from "pages/styleBoardCreatePage/data/mapTopicToCategory";
import { validateData } from "pages/styleBoardDetailPage/services/validation";
import {testData} from "pages/styleBoardDetailPage/data/testData";
import {formatCreatedAt} from "pages/styleBoardDetailPage/utils/formatDate";
import {mapTopicToHashTagName} from "pages/styleBoardDetailPage/data/mapTopicToHashTagName";

import Header from "pages/styleBoardDetailPage/Components/Header";
import TagList from "pages/styleBoardDetailPage/Components/TagList";
import MetaInfo from "pages/styleBoardDetailPage/Components/MetaInfo";
import ContentViewer from "pages/styleBoardDetailPage/Components/ContentViewer";
import InputBox from "pages/styleBoardDetailPage/Components/InputBox";
import InteractionBar from "pages/styleBoardDetailPage/Components/InteractionBar";
import ContentDivider from "shared/ui/ContentDivider";
import CommentSection from "pages/styleBoardDetailPage/Components/CommentSection";

import getStyleBoardDetailData from "pages/styleBoardDetailPage/api/getStyleBoardDetailData";

const StyleBoardDetailPage = () => {
    const navigate = useNavigate();

    const [searchParams] = useSearchParams();
    const { id } = useParams();
    const topic = searchParams.get('topic');

    const [styleBoardData, setStyleBoardData] = useState(null);

    const category = mapTopicToCategory(topic).toLowerCase();
    const hashTagName = mapTopicToHashTagName(topic);

    const DATA_LOAD_ERROR_MESSAGE = "[error] 데이터를 불러오는데 실패했습니다.";

    useEffect(() => {
        const loadData = async () => {
            try {
                const response = await getStyleBoardDetailData(id, category);
                // 데이터 유효성 검증
//                 validateData(response);

                // 데이터 변환
                const userProfile = {
                    nickname: response.nickname,
                    level: response.userLevel,
                    profileImageUrl: response.profileImageUrl,
                    createdAt: formatCreatedAt(response.createdAt),
                };

                setStyleBoardData({ ...response, userProfile });
            } catch (error) {
                console.error(DATA_LOAD_ERROR_MESSAGE, error);
                alert(DATA_LOAD_ERROR_MESSAGE);
                navigate(-1); // 전 페이지로 이동
            }
        };
        loadData();
    }, [id, category, navigate]);

    return (
        <section style={{ minHeight: '1300px', marginBottom: '60px' }}>
            {styleBoardData && (
                <>
                    <Header topic={"스타일 보드"} />
                    <TagList topic={hashTagName} tags={styleBoardData.hashTags} />
                    <MetaInfo userProfile={styleBoardData.userProfile} interaction={testData.interaction} />
                    <ContentViewer title={styleBoardData.title} content={styleBoardData.content} />
                    <InteractionBar likes={testData.interaction.likes} shares={testData.interaction.shares} />
                    <ContentDivider />
                    <CommentSection comments={testData.comments} />
                    <InputBox />
                </>
            )}
        </section>
    );
}

export default StyleBoardDetailPage;
