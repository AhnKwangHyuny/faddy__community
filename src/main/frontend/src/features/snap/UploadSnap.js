import axios from 'axios';
import { isValidDescription, isValidTags, isValidCategories, isValidUserId } from 'utils/snap/SnapDataValidation';
import {useNavigate} from "react-router-dom";
import {userRequestInstance} from "api/axiosInstance";

const uploadSNSPost = async (userId, imageList , description, tags, selectedCategories) => {

    const navigate = useNavigate();

    try {
        /**
         * 입력 데이터 유효성 검사
         */

        if (!isValidUserId(userId)) {
            throw new Error('Invalid user ID');
            // 로그 아웃 구현

            navigate("/login");

        }

        if(imageList.length === 0) {
            throw new Error("이미지를 한 장 이상 꼭 넣어주세요!");
        }

        if (!isValidDescription(description)) {
            // descripton으로 커서 이동

        }

        if (!isValidTags(tags)) {
            // 태그로 커서 이동
        }

        if (!isValidCategories(selectedCategories)) {
            //카테고리고 커서 이동
        }


        // 태그 엔티티 생성 및 ID 목록 받아오기
        const tagIds = await createTags(tags);

        // 카테고리 엔티티 생성 및 ID 목록 받아오기
        const categoryIds = await createCategories(selectedCategories);

        // Snap 엔티티 생성
        const snapData = {
            description,
            userId,
            tagIds,
            categoryIds
        };

        const response = await axios.post('/api/v1/snaps', snapData);
        const snapId = response.data.id;

        // 이미지 엔티티와 Snap 엔티티 간 관계 지어주기
        await updateSnapImageRelationship(imageList, snapId);

        // 성공 메시지 등 필요한 처리
        return { success: true };
    } catch (error) {

        console.error('Error uploading Snap post:', error);
        return { success: false, error: error.message };
    }
};

const updateSnapImageRelationship = async (imageList, snapId) => {
    try {

        const requestBody = {
            "snapId": snapId,
            "images": imageList
        };

        await userRequestInstance.put('/snap', requestBody);
    } catch (error) {
        console.error('Error linking images to Snap:', error);
        throw error;
    }
};

const createTags = async (tags) => { // tags = [ name1 , name2 ... ]
    try {
        const requestBody = {
            contentType : "SNAP",
            tags: tags,

        };

        const response = await userRequestInstance.post('/api/v1/hashTags', requestBody);

        return response.data.tagIds;
    } catch (error) {
        console.error('Error creating tags:', error);
        throw error;
    }
};


const createCategories = async (selectedCategories) => {
    try {
        // 카테고리 엔티티 생성 및 ID 목록 받아오기
        const response = await axios.post('/api/categories', selectedCategories);
        return response.data.categoryIds;
    } catch (error) {
        console.error('Error creating categories:', error);
        throw error;
    }
};


export default uploadSNSPost;