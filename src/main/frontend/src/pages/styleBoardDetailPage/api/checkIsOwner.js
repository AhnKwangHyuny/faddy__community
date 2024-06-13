import { END_POINTS } from 'constants/api';
import { userRequestInstance } from 'api/axiosInstance';


const checkIsOwner = async (styleBoardId) => {
    try {
        const response = await userRequestInstance.get(END_POINTS.CHECK_IS_STYLE_BOARD_OWNER(styleBoardId));
        return response.data.data;
    } catch (error) {
        console.error('Error fetching style board detail:', error);
        throw error;
    }
};

export default checkIsOwner;
