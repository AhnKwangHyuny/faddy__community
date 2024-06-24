import React, { useState, useEffect } from 'react';
import Layout from "widgets/Layout/Layout";
import { useInView } from "react-intersection-observer";
import BestStyleBoardCarouselHeader from 'features/styleBoard/bestStyleBoardCarouselHeader/index';
import SearchFilterModal from "features/Search/components/SearchFilterModal";
import StyleBoardList from "pages/styleBoardListPage/components/StyleBoardList";
import CreateTopicModal from "pages/styleBoardListPage/components/CreateTopicModal";
import { testData } from "pages/styleBoardListPage/data/testData";
import FloatingActionButton from 'shared/components/FloatingActionButton';
import { useLocation } from 'react-router-dom';
import {getStyleBoards} from "pages/styleBoardListPage/api/get";

const StyleBoardListPage = () => {
    const [showSearchFilterModal, setShowSearchFilterModal] = useState(false);
    const [showCreateTopicModal, setShowCreateTopicModal] = useState(false);
    const [showFloatingButton, setShowFloatingButton] = useState(true);
    const location = useLocation();

    //styleBoard data && infinite scroll
    const [styleBoards, setStyleBoards] = useState([]);
    const [page, setPage] = useState(-1);
    const [hasMore, setHasMore] = useState(true);
    const { ref, inView } = useInView();
    const [filterOptions , setFilterOptions] = useState({});

    // styleBoard data load
    const loadStyleBoardData = async () => {
        try {
            const data = await getStyleBoards(filterOptions);
            setStyleBoards(prevStyleBoards => [...prevStyleBoards, ...data]);
            setHasMore(data.length === 4);
        } catch (e) {
            console.error(`Error loading styleBoard data: ${e.message}`);
        }
    }

    useEffect(() => {
        if (inView && hasMore) {
            loadStyleBoardData();
            setPage(prevPage => prevPage + 1);
        }
    }, [inView]);

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
                <StyleBoardList styleBoards={testData} listRef={ref} />
                <SearchFilterModal showModal={showSearchFilterModal} setShowModal={setShowSearchFilterModal} />
                <CreateTopicModal showModal={showCreateTopicModal} setShowModal={handleCloseCreateTopicModal} />
                {showFloatingButton && <FloatingActionButton onClick={handleCreateTopicClick} />}
            </section>
        </Layout>
    );
};

export default StyleBoardListPage;
