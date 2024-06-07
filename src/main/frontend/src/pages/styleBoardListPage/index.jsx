import React from 'react';
import Layout from "widgets/Layout/Layout";
import BestStyleBoardCarouselHeader from 'features/styleBoard/bestStyleBoardCarouselHeader/index';
import SearchFilterModal from "features/Search/components/SearchFilterModal";
import StyleBoardList from "pages/styleBoardListPage/components/StyleBoardList";
import {testData} from "pages/styleBoardListPage/data/testData";

const StyleBoardListPage= () => {
    const [showModal, setShowModal] = React.useState(false);


    return (
        <Layout>
            <section style={{ padding: '20px 10px' }}>
                <BestStyleBoardCarouselHeader setShowModal = {setShowModal}/>
                <StyleBoardList styleBoards = {testData} />
                {/* Modal Component */}
                <SearchFilterModal showModal = {showModal} setShowModal = {setShowModal}/>
            </section>
        </Layout>
    );
};

export default StyleBoardListPage;
