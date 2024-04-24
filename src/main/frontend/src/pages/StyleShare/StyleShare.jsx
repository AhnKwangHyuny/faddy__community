import Layout from 'widgets/Layout/Layout';
import TrendSnapCardList from "pages/StyleShare/components/TrendSnapList/TrendSnapList";
import React from "react";
import SnapSortingButton from "pages/StyleShare/components/SnapSortingButton/SnapSortingButton";
import SortingMenu from "features/Sorting/SortingMenu/SortingMenu";


function StyleShare() {

    return (
        <Layout>
            <section id="style-share">
                <TrendSnapCardList/>
                <SnapSortingButton/>
                <SortingMenu/>
            </section>
        </Layout>
      );

};


export default StyleShare;
