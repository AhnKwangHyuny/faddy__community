import React, { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';
import {userRequestInstance} from "../api/axiosInstance";


// 인증 상태 확인 함수
const checkAuthStatus = async () => {
    try {
        const token = localStorage.getItem('ACCESS_TOKEN');
        if (!token) {
            return false;
        }
        const response = await userRequestInstance.get('/api/v1/auths/verify-token');

        // 인증 성공 시 true 반환
        return response.status === 200;

    } catch (error) {
        // 403 에러 발생으로 userRequesrInstance에서 response intercept
        console.log(error);
        return false;
    }
};

// 인증된 사용자만 접근 가능한 컴포넌트를 위한 보호된 경로 컴포넌트
const ProtectedRoute = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(null);

    useEffect(() => {
        const verifyAuth = async () => {
            const authStatus = await checkAuthStatus();
            setIsAuthenticated(authStatus);
        };

        verifyAuth();

    }, []);

    if (isAuthenticated === null) {
        // 인증 상태 확인 중...
        return <div>인증 확인 중...</div>;
    }

    return isAuthenticated ? children : <Navigate to="/login" />;
};

export default ProtectedRoute;
