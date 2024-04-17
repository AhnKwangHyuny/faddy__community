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
  const token = localStorage.getItem('ACCESS_TOKEN');

  if(token) {

    setAccessToken(token); // 토큰 설정

  }

  return config;

} , (error) => {
      return Promise.reject(error);
    }
);


// 초큰 유효성 검사
userRequestInstance.interceptors.response.use((response) => {
  return response;

}, async (error) => {
  const originalRequest = error.config;
  console.log(error.config);

  if (error.response.status === 403 && !originalRequest._retry) {

    originalRequest._retry = true; // 재시도 플래그 설정

    // 토큰 재발급
    await refreshAccessToken();

    // 재발급 후 다시 요청. originalRequest를 사용하여 요청을 다시 보냅니다.
    return userRequestInstance(originalRequest);
  }
  return Promise.reject(error);
});

// 유저 헤더에 쿠키 추가
export const setAccessToken = (token) => {
  userRequestInstance.defaults.headers.common.Authorization = `Bearer ${token}`;
};

// 토큰 갱신
const refreshAccessToken = async (refreshToken) => {

  const config = {
    "REFRESH_TOKEN" : refreshToken,
  }

  const response = await userRequestInstance.post("/api/v1/auths/reissue-token" , config);
  console.log(response.data);
  const { accessToken } = response.data; // 여기서 구조 분해 할당을 사용하여 accessToken 추출

  localStorage.setItem('ACCESS_TOKEN', accessToken);
}