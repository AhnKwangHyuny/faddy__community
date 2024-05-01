import { END_POINTS ,   AXIOS_BASE_URL } from 'constants/api';
import {userRequestInstance} from 'api/axiosInstance';

export const postUserLogin = async({username, password}) => {

    const data = {
            username: username,
            password: password
        };


    return await userRequestInstance.post(END_POINTS.LOGIN , data);
}

export const saveImages = async (files) => {

    return await userRequestInstance.post(END_POINTS.UPLOAD_IMAGES , files , {
        headers: {
            'content-Type': 'multipart/form-data',
        },
    });
}

export const saveImage = async (formData) => {
    return await userRequestInstance.post(END_POINTS.UPLOAD_IMAGE , formData , {
        headers: {
            'content-Type': 'multipart/form-data',
        },
    });
}

export const uploadSnap =  async (snapData) => {

    return await userRequestInstance.post(END_POINTS.POST_SNAP , snapData );
}

export const reissueToken = (refreshToken) => {
    const config = {
        headers: {
            'Authorization': `Bearer ${refreshToken}`,
        }
    }

    return userRequestInstance.post(END_POINTS.REISSUE_TOKEN , config);
}

/** 좋아요 요청 */
export const postLike = async (snapId) => {
    try {
        const response = await userRequestInstance.post(END_POINTS.LIKE_CLICK(snapId));
        console.log(response);
        return response.data;
    } catch (error) {
        console.error('좋아요 요청 실패', error);
        throw error;
    }
};
