import React, {useState} from 'react';

import {testData} from 'pages/styleBoardDetailPage/data/testData';
import Header from 'pages/styleBoardEditPage/Components/Header';
import TitleEditor from "pages/styleBoardEditPage/Components/TitleEditor";
import ContentEditor from "pages/styleBoardEditPage/Components/ContentEditor";
import HashTagBox from 'pages/Snap/Components/HashTagBox/HashTagBox';

const StyleBoardEditPage = () => {

    const [title , setTitle] = useState(testData.title);
    const [content , setContent] = useState(testData.content);
    const [tags , setTags] = useState(testData.tags);
    const isLabelHide = true;


    const onSubmit = () => {
        console.log("submit");
    }

    return (
        <section>
            <Header onSubmit={onSubmit}/>
            <TitleEditor title={title} setTitle={setTitle}/>
            <ContentEditor content={content} setContent={setContent}/>
            <HashTagBox labelHide={false} tags = {tags} setTags = {setTags}/>
        </section>
    );
}

export default StyleBoardEditPage;