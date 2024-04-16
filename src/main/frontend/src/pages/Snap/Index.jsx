import CreationMenuBar from "widgets/CreationMenuBar/CreationMenuBar";
import ImageUploader from "widgets/ImageUploader/ImageUploader";
import Description from "pages/Snap/Components/Description/Description";
import HashTagBox from "./Components/HashTagBox/HashTagBox";
import SnapCategory from "pages/Snap/Components/SnapCategory/SnapCategory"
import {useState} from "react";
import {useAuth} from "shared/context/AuthContext";


const Snap = () => {
    const [imageList, setImageList] = useState([]);
    const [description, setDescription] = useState("");
    const [tags, setTags] = useState([]);
    const [selectedCategories, setSelectedCategories] = useState({});
    const {userId} = useAuth();

    return (
        <section className="main__body">
            <CreationMenuBar imageList={imageList} setImageList = {setImageList}/>
            <ImageUploader imageList={imageList} setImageList={setImageList} />
            <Description description={description} setDescription={setDescription} />
            <HashTagBox tags={tags} setTags={setTags} />
            <SnapCategory selectedCategories={selectedCategories} setSelectedCategories={setSelectedCategories} />
        </section>
    );
};

export default Snap;