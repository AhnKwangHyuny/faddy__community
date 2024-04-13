import Layout from 'widgets/Layout/Layout';
import HighlightSnapCard from "widgets/HighlightSnapCard/HighlightSnapCard";
import TrendSnapCardList from "pages/StyleShare/TrendSnapCardList";


function StyleShare() {

    return (
        <Layout>
            <section id="StyleShare__main__section">
                <TrendSnapCardList/>

                <HighlightSnapCard/>
            </section>
        </Layout>
      );

};


export default StyleShare;
