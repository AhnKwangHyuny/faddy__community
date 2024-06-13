import { END_POINTS } from 'constants/api';
import { userRequestInstance } from 'api/axiosInstance';


const getStyleBoardDetailData = async (styleBoardId, category) => {
    try {
        const response = await userRequestInstance.get(END_POINTS.GET_STYLE_BOARD_DETAIL(styleBoardId, category));
        return response.data.data;
    } catch (error) {
        console.error('Error fetching style board detail:', error);
        throw error;
    }
};

export default getStyleBoardDetailData;
