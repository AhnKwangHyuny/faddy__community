import Layout from 'widgets/Layout/Layout';
import TrendSnapCardList from "pages/StyleShare/components/TrendSnapList/TrendSnapList";
import React from "react";
import SnapSortingButton from "pages/StyleShare/components/SnapSortingButton/SnapSortingButton";


function StyleShare() {

    return (
        <Layout>
            <section id="style-share">
                <TrendSnapCardList/>
                <SnapSortingButton/>
            </section>
        </Layout>
      );

};


export default StyleShare;
