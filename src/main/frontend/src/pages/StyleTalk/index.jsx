import Layout from "widgets/Layout/Layout";
import ChatTypeSelector from "pages/StyleTalk/components/ChatTypeSelector/ChatTypeSelector";
import ChatList from "pages/StyleTalk/components/ChatList/ChatList";
import CreateRoomModal from "pages/StyleTalk/components/CreateRoomModal/CreateRoomModal";
import UserSelectorModal from "features/UserSelectorModal/UserSelectorModal";
const StyleTalk = () => {

    return (
        <Layout>
            <UserSelectorModal/>
            <CreateRoomModal/>
            <ChatTypeSelector/>
            <ChatList/>
        </Layout>

    )

};

export default StyleTalk;