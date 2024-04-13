import axios from 'axios';

import { AXIOS_BASE_URL, NETWORK } from 'constants/api';

export const axiosInstance = axios.create({
  baseURL: AXIOS_BASE_URL,
  withCredentials: true,
  useAuth: true,
});

export const userRequestInstance = axios.create({
  baseURL: AXIOS_BASE_URL,
});


// 클라이언트가 서버로 요청 시 토큰 담아서 요청
userRequestInstance.interceptors.request.use((config) => {
  const token = localStorage.getItem('authorization');

  if(token) {

    config.headers.Authorization = token;

  }

  return config;
} , (error) => {
      return Promise.reject(error);
    }
);


// 유저 헤더에 쿠키 추가
export const setAccessToken = (token) => {
  userRequestInstance.defaults.headers.common.Authorization = `Bearer ${token}`;
};

