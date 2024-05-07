import Layout from "widgets/Layout/Layout";
import ChatTypeSelector from "pages/StyleTalk/components/ChatTypeSelector/ChatTypeSelector";
import ChatList from "pages/StyleTalk/components/ChatList/ChatList";

const StyleTalk = () => {

    return (
        <Layout>
            <ChatTypeSelector/>
            <ChatList/>
        </Layout>

    )

};

export default StyleTalk;