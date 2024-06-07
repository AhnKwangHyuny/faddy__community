import React, { useState, useEffect } from 'react';
import Layout from "widgets/Layout/Layout";
import BestStyleBoardCarouselHeader from 'features/styleBoard/bestStyleBoardCarouselHeader/index';
import SearchFilterModal from "features/Search/components/SearchFilterModal";
import StyleBoardList from "pages/styleBoardListPage/components/StyleBoardList";
import CreateTopicModal from "pages/styleBoardListPage/components/CreateTopicModal";
import { testData } from "pages/styleBoardListPage/data/testData";
import FloatingActionButton from 'shared/components/FloatingActionButton';
import { useLocation } from 'react-router-dom';

const StyleBoardListPage = () => {
    const [showSearchFilterModal, setShowSearchFilterModal] = useState(false);
    const [showCreateTopicModal, setShowCreateTopicModal] = useState(false);
    const [showFloatingButton, setShowFloatingButton] = useState(true);
    const location = useLocation();

    // redirect 시 floating button을 보여주기
    useEffect(() => {
        setShowFloatingButton(true);
    }, [location]);

    const handleCreateTopicClick = () => {
        setShowCreateTopicModal(true);
        setShowFloatingButton(false);
    };

    const handleCloseCreateTopicModal = () => {
        setShowCreateTopicModal(false);
        setShowFloatingButton(true);
    };

    return (
        <Layout>
            <section style={{ padding: '20px 10px' }}>
                <BestStyleBoardCarouselHeader setShowModal={setShowSearchFilterModal} />
                <StyleBoardList styleBoards={testData} />
                <SearchFilterModal showModal={showSearchFilterModal} setShowModal={setShowSearchFilterModal} />
                <CreateTopicModal showModal={showCreateTopicModal} setShowModal={handleCloseCreateTopicModal} />
                {showFloatingButton && <FloatingActionButton onClick={handleCreateTopicClick} />}
            </section>
        </Layout>
    );
};

export default StyleBoardListPage;
