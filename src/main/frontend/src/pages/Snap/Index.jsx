import CreationMenuBar from "widgets/CreationMenuBar/CreationMenuBar";
import ImageUploader from "widgets/ImageUploader/ImageUploader";
import Description from "pages/Snap/Components/Description/Description";
import HashTagBox from "./Components/HashTagBox/HashTagBox";
import SnapCategory from "pages/Snap/Components/SnapCategory/SnapCategory";
import { useState } from "react";
import { useAuth } from "shared/context/AuthContext";
import uploadSnap from "../../features/snap/UploadSnap";

const Snap = () => {
    const [imageList, setImageList] = useState([]);
    const [description, setDescription] = useState("");
    const [tags, setTags] = useState([]);
    const [selectedCategories, setSelectedCategories] = useState({});
    const { userId } = useAuth();

    const handleUploadSnap = async () => {
        try {
            const result = await uploadSnap(userId, imageList, description, tags, selectedCategories);
            if (result.success) {
                // Handle success, e.g., navigate to a different page
            } else {
                // Handle error, e.g., display an error message
                console.error(result.error);
            }
        } catch (error) {
            console.error("Error uploading SNS post:", error);
        }
    };

    return (
        <section className="main__body">
            <CreationMenuBar
                imageList={imageList}
                description={description}
                tags={tags}
                selectedCategories={selectedCategories}
                onUploadSnap={handleUploadSnap}
            />
            <ImageUploader imageList={imageList} setImageList={setImageList} />
            <Description description={description} setDescription={setDescription} />
            <HashTagBox tags={tags} setTags={setTags} />
            <SnapCategory selectedCategories={selectedCategories} setSelectedCategories={setSelectedCategories} />
        </section>
    );
};

export default Snap;