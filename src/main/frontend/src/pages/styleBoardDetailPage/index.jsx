import {testData} from './data/testData';
import Header from "pages/styleBoardDetailPage/Components/Header";
import TagList from "pages/styleBoardDetailPage/Components/TagList";
import MetaInfo from "pages/styleBoardDetailPage/Components/MetaInfo";
import ContentViewer from "pages/styleBoardDetailPage/Components/ContentViewer";
import InputBox from "pages/styleBoardDetailPage/Components/InputBox";
import InteractionBar from "pages/styleBoardDetailPage/Components/InteractionBar";
import ContentDivider from "shared/ui/ContentDivider";
import CommentSection from "pages/styleBoardDetailPage/Components/CommentSection";


const StyleBoardDetailPage = () => {


    return (
        <section style={{ minHeight: '1300px' , marginBottom: '60px' }}>
            <Header topic={"스타일 보드"}  />
            <TagList topic={testData.topic} tags={testData.tags} />
            <MetaInfo userProfile={testData.userProfile} interaction={testData.interaction}/>
            <ContentViewer title = {testData.title} content={testData.content} />
            <InteractionBar likes={testData.interaction.likes} shares={testData.interaction.shares} />
            <ContentDivider />
            <CommentSection comments={testData.comments} />
            <InputBox />
        </section>
    );
}

export default StyleBoardDetailPage;