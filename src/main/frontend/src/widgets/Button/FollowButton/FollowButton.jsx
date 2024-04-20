import React, { useState } from 'react';
import {userRequestInstance} from "../../../api/axiosInstance";

export const FollowButtonByName = ({ userId, friendId }) => {
    const [isFollowing, setIsFollowing] = useState(false);
    const [isLoading, setIsLoading] = useState(false);

    const handleFollowClick = async () => {
        setIsLoading(true);

        try {
            const response = await userRequestInstance.post('/api/follow', {
                userId,
                friendId,
            });

            if (response.status === 200) {
                setIsFollowing(!isFollowing);
            } else {
                // 에러 처리 로직을 추가할 수 있습니다.
                console.error('팔로우 요청에 실패했습니다.');
            }
        } catch (error) {
            console.error('팔로우 요청 중 에러가 발생했습니다.', error);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="follow-button">
            <div className="follow-button__wrapper">
                <button
                    className="button"
                    onClick={handleFollowClick}
                    disabled={isLoading}
                >
                    {isLoading ? (
                        <span className="loading-spinner"></span>
                    ) : isFollowing ? (
                        '친구헤제'
                    ) : (
                        '친구추가'
                    )}
                </button>
            </div>
        </div>


    );
};

export default FollowButtonByName;