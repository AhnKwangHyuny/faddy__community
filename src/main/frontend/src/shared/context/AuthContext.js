// AuthContext.js
import React, { createContext, useContext, useState } from 'react';
import { UserIdEntity } from 'entity/user/UserIdEntity'; // createUserEntity 함수를 임포트합니다.

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    const login = (userId) => {
        // 사용자 정보 생성
        const newUser = UserIdEntity(userId);

        // 로그인 로직 구현, 예를 들어 서버로부터 받은 사용자 데이터를 저장
        setUser(newUser);
    };

    const logout = () => {
        // 로그아웃 로직 구현, 예를 들어 사용자 상태를 null로 설정
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
