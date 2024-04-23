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


