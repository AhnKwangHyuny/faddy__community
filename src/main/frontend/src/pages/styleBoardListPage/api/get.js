import { END_POINTS } from 'constants/api';
import { userRequestInstance } from 'api/axiosInstance';

export const getStyleBoards = async (filterOptions = {}) => {
    const params = {
        sort: 'newest',
        page: 1,
        size: 5,
        ...filterOptions
    };

    try {
        const response = await userRequestInstance.get(END_POINTS.GET_STYLE_BOARDS, { params });

        console.log('response', response.data.data);

        return response.data.data;
    } catch (error) {
        console.error('Error fetching style boards:', error);
        throw error;
    }
};
