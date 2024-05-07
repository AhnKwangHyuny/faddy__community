import { END_POINTS ,   AXIOS_BASE_URL } from 'constants/api';
import {userRequestInstance} from 'api/axiosInstance';

/**
 *  image 삭제 request
 * */
export const deleteImage = async(request) => {

    const data = {
        hashedName : request.hashedName,
        url : request.url,
    };

    const config = {
        data: data
    };

    return await userRequestInstance.delete(END_POINTS.DELETE_IMAGE , config);
}

/**
 *  복수의 image 삭제 요청
 * */

export const deleteImages = async(imageList) => {


    const config = {
        data: imageList,
    };

    return await userRequestInstance.delete(END_POINTS.DELETE_IMAGES , config);
}

/** 좋아요 취소 요청*/
export const deleteLike = async (snapId) => {
    try {
        const response = await userRequestInstance.delete(END_POINTS.UNLIKE_CLICK(snapId));
        return response.data;
    } catch (error) {
        console.error('좋아요 취소 요청 실패', error);
        throw error;
    }
};

/**언팔로우 요청*/
export const unFollow = async(toUsername) => {
     try {

            const response = await userRequestInstance.delete(END_POINTS.UNFOLLOW(toUsername));

            return response.status === 200;

        } catch (error) {
            console.error(error.data.message);
            return false;
        }
}