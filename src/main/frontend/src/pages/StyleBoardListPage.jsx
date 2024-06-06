import React from 'react';
import Layout from "widgets/Layout/Layout";
import BestStyleBoardCarouselHeader from 'features/styleBoard/bestStyleBoardCarouselHeader/index';


const StyleBoardListPage= () => {
    return (
        <Layout>
            <section style={{ padding: '20px 10px' }}>
                <BestStyleBoardCarouselHeader/>
            </section>
        </Layout>
    );
};

export default StyleBoardListPage;
