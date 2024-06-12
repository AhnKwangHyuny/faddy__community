import { END_POINTS } from 'constants/api';
import { userRequestInstance } from 'api/axiosInstance';


const getStyleBoardDetail = async (styleBoardId, topic) => {
    try {
        const response = await userRequestInstance.get(END_POINTS.getStyleBoardDetail(styleBoardId, topic));
        return response.data;
    } catch (error) {
        console.error('Error fetching style board detail:', error);
        throw error;
    }
};

export default getStyleBoardDetail;
