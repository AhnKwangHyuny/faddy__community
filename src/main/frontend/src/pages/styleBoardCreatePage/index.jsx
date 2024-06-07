import React, { useState, useEffect } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { isValidTopic } from 'pages/styleBoardCreatePage/data/validTopics';
import { mapTopicToTitle } from 'pages/styleBoardCreatePage/data/mapTopicToTitle';
import Header from "pages/styleBoardCreatePage/components/Header";
import TitleInput from 'pages/styleBoardCreatePage/components/TitleInput';
import ContentEditor from 'pages/styleBoardCreatePage/components/ContentEditor';
import HashTagBox from 'pages/Snap/Components/HashTagBox/HashTagBox';

const StyleBoardCreatePage = () => {
    const [searchParams] = useSearchParams();
    const topicValue = searchParams.get('topic');
    const [tags , setTags] = useState([]);
    const navigate = useNavigate();

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    //완료 버튼 클릭시 수행
    const onSubmit = () => {
        console.log("submit");
    }

    useEffect(() => {
        if (!isValidTopic(topicValue)) {
            alert('없는 주제입니다.');
            navigate(-1);
        }
    }, [topicValue, navigate]);

    const mappedTopic = mapTopicToTitle(topicValue);

    return (
        <section style={{ minHeight: '700px' }}>
            <Header topic={mappedTopic} onSubmit={onSubmit} />
            <TitleInput title={title} setTitle={setTitle} />
            <ContentEditor content={content} setContent={setContent} />
            <HashTagBox labelHide = {false} tags={tags} setTags={setTags} />
        </section>
    );
};

export default StyleBoardCreatePage;
