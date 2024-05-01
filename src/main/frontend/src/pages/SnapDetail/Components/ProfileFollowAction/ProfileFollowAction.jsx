import UserProfile from 'widgets/UserProfile/UserProfile';
import FollowButton from "widgets/Button/FollowButton/FollowButton";
import MoreIcon from "shared/ui/MoreIcon/MoreIcon";

const ProfileFollowAction = ({user}) => {
    return (
        <section className="profile-follow__section">
            <UserProfile level={"Lv4"}/>
            <FollowButton userId={"dwqdwq"} friendId={"dwqdwqdqw"}/>
            <MoreIcon/>
        </section>
    );

}

export default ProfileFollowAction;