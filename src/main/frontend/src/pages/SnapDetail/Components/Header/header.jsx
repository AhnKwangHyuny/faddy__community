import UserProfile from 'widgets/UserProfile/UserProfile';
import {BackButton} from "widgets/Button/Button";

const Header = () => {
    return (
        <section className="snapDetail">
            <BackButton/>
            <UserProfile level={"Lv4"}/>
        </section>
    );

}

export default Header;