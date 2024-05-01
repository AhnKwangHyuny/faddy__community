export const PROD = process.env.NODE_ENV === 'production';
export const DEV = process.env.NODE_ENV === 'development';

export const DEVICE = process.env.NODE_DEVICE  === 'DESKTOP';


export const API_PATH = '/api';
export const API_USER = '/api/v1/users';
export const API_AUTH = '/api/v1/auths';
export const API_AUTH_CODE = '/api/v1/auth-codes';

export const API_IMAGE = '/api/images';

export const API_HASH_TAG = 'api/v1/hashTags';

export const API_SNAP  = 'api/v1/snaps';

export const API_CATEGORY  = 'api/v1/categories';

export const API_LIKE = 'api/v1/likes';

export const BASE_URL = DEV
  ? DEVICE ? 'http://localhost:9000' : 'http://172.30.1.38:9000'
  : `${window.location.protocol};//${process.env.AXIOS_PROD_BASE_URL}`

export const AXIOS_BASE_URL = DEV
  ? DEVICE ? 'http://localhost:9000' : 'http://172.30.1.38:9000'
  : `${window.location.protocol};//${process.env.AXIOS_PROD_BASE_URL}`


export const END_POINTS = {
  USERS: '/users',

  // API REQUEST URL
  // email API
  GET_EMAIL_AUTH_CODE: `${API_AUTH_CODE}`,
  VERIFY_AUTH_CODE : `${API_AUTH_CODE}/verify`,
  DELETE_AUTH_CODE : `${API_AUTH_CODE}`,


  //USER API
  CHECK_USER_ID : `${API_USER}/check-duplication/userId`,
  CHECK_USER_NICKNAME :`${API_USER}/check-duplication/nickname`,
  GET_USER_ID : `${API_USER}/userId`,
  CHECK_USER_ID_EXISTS : `${API_USER}/check-userId`,

  //token API
  REISSUE_TOKEN : `${API_AUTH}/reissue-token`,
  VERIFY_TOKEN : `${API_AUTH}/verify-token`,

  //SIGN UP API
  SIGN_UP : `${API_USER}`,

  //User Login API
  LOGIN : `${API_USER}/login`,

  //Images
  UPLOAD_IMAGES : `${API_IMAGE}/multiple`,
  UPLOAD_IMAGE : `${API_IMAGE}`,
  DELETE_IMAGE : `${API_IMAGE}`,
  DELETE_IMAGES : `${API_IMAGE}/multiple`,


  //HashTag
  UPLOAD_HASH_TAG : `${API_HASH_TAG}`,

  //SNAP
  POST_SNAP : `${API_SNAP}`,
  UPDATE_SNAP_IMAGE_RELATIONSHIP: `${API_IMAGE}/images/link`,
  SNAP_DETAIL: `${API_SNAP}/detail`,

  //Category
  UPLOAD_CATEGORY : `${API_CATEGORY}`,

  //lie request
  LIKE_CLICK: (snapId) => `${API_LIKE}/${snapId}`,
  UNLIKE_CLICK: (snapId) => `${API_LIKE}/${snapId}`,
  GET_LIKE_COUNT: (snapId) => `${API_LIKE}/${snapId}`,

  //location
  SNAP_DETAIL_LOCATION : `/snaps/detail`,
};


export const NETWORK = {
  RETRY_COUNT: 2,
  TIMEOUT: 15000,
};

export const HTTP_STATUS_CODE = {
  SUCCESS: 200,
  CREATED: 201,
  NO_CONTENT: 204,
  BAD_REQUEST: 400,
  UNAUTHORIZED: 401,
  NOT_FOUND: 404,
  CONTENT_TOO_LARGE: 413,
  INTERNAL_SERVER_ERROR: 500,
};

export const ERROR_CODE = {
  DUPLICATED_NICKNAME: 1013,
  LARGE_IMAGE_FILE: 5001,
  TOKEN_ERROR_RANGE: 9000,
  INVALID_REFRESH_TOKEN: 9101,
  INVALID_ACCESS_TOKEN: 9102,
  EXPIRED_REFRESH_TOKEN: 9103,
  EXPIRED_ACCESS_TOKEN: 9104,
  INVALID_TOKEN_VALIDATE: 9105,
  NULL_REFRESH_TOKEN: 9106,
  UNAUTHORIZED: 9201,
  UNEXPECTED_TOKEN_ERROR: 9999,
};

export const HTTP_ERROR_MESSAGE = {
  [HTTP_STATUS_CODE.NOT_FOUND]: {
    HEADING: '스타일이 빠진 곳',
    BODY: '요청하신 페이지를 찾을 수 없습니다.',
    BUTTON: '홈으로 가기',
  },
  [HTTP_STATUS_CODE.INTERNAL_SERVER_ERROR]: {
    HEADING: '현재 페이지를 표시할 수 없습니다.',
    BODY: `잠시 후 다시 시도해주세요.`,
    BUTTON: '새로고침',
  },
  [HTTP_STATUS_CODE.BAD_REQUEST]: {
    HEADING: '잘못된 요청입니다.',
    BODY: '확인 후 다시 시도해주세요.',
    BUTTON: '홈으로 가기',
  },
};

export const ERROR_MESSAGE = '오류가 발생했습니다. 잠시 후 다시 시도해주세요.';

export const ACCESS_TOKEN_KEY = 'ACCESS_TOKEN';

//export const KAKAO_AUTH_API_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.KAKAO_API_KEY}&redirect_uri=${BASE_URL}/${process.env.KAKAO_REDIRECT_URI}&response_type=code`;

//export const GOOGLE_AUTH_API_URL = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${process.env.GOOGLE_CLIENT_ID}&response_type=code&redirect_uri=${BASE_URL}/${process.env.GOOGLE_REDIRECT_URI}&scope=https%3A//www.googleapis.com/auth/userinfo.profile`;
