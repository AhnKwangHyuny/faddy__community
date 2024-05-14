import Layout from "widgets/Layout/Layout";
import ChatTypeSelector from "pages/StyleTalk/components/ChatTypeSelector/ChatTypeSelector";
import ChatList from "pages/StyleTalk/components/ChatList/ChatList";
import CreateRoomModal from "pages/StyleTalk/components/CreateRoomModal/CreateRoomModal";

const StyleTalk = () => {

    return (
        <Layout>
            <CreateRoomModal/>
            <ChatTypeSelector/>
            <ChatList/>
        </Layout>

    )

};

export default StyleTalk;