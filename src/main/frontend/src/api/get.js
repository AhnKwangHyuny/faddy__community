// 토큰 갱신 요청 (refresh-token)
import {userRequestInstance} from "./axiosInstance";
import {END_POINTS} from "../constants/api";


export const getUserId = async(username) => {
    const config = {
        params: {
            username,
        }
    }

    return await userRequestInstance.get(END_POINTS.GET_USER_ID , config );

};

export const checkUserIdExists = async (userId) => {

    const config = {
        headers: {
            'Content-Type': 'application/json'
        }
    }

    const requestBody = JSON.stringify({ userId });

    return await userRequestInstance.post(END_POINTS.CHECK_USER_ID_EXISTS,requestBody , config);

};



export const verifyToken = (accessToken) => {
    const config = {
        headers : {
            'Authentication': `Bearer ${accessToken}`,
        }
    }

    return userRequestInstance.get(END_POINTS.VERIFY_TOKEN , config);
}

/**스냅 데이터 가져오기*/
export const getSnapData = async (snapId) => {

    const config = {
        headers: {
            'Content-Type': 'application/json'
        },
        params: {
            snapId: snapId
        }
    }

    return await userRequestInstance.get(END_POINTS.SNAP_DETAIL, config);
};

/**좋아요 수 조회*/
export const getLikeCount = async (snapId) => {
    try {
        const response = await userRequestInstance.get(END_POINTS.GET_LIKE_COUNT(snapId));
        return response.data.data;
    } catch (error) {
        console.error('좋아요 수 조회 요청 실패', error);
        throw error;
    }
};

/**paging loader */
export const getMoreThumbnails = async (page) => {
    try {
        const response = await userRequestInstance.get(END_POINTS.MORE_THUMBNAILS(page));
        const data = await response.data.data;
        console.log(data);

        return data;

    } catch (error) {
        if (error.response) {
          console.error(`Error ${error.response.status}: ${error.response.data.message}`);
          console.error(`Error Code: ${error.response.data.code}`);
        } else {
          console.error('Error: ', error.message);
        }
        throw error;
      }
}