

// 토큰 갱신 요청 (refresh-token)
import {userRequestInstance} from "./axiosInstance";
import {END_POINTS} from "../constants/api";


export const getUserId = async(username) => {

    const config = {
        username,
    }

    return await userRequestInstance.post(END_POINTS.GET_USER_ID , config );

};