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