import {saveImage} from 'api/post';
import {deleteImages} from "api/delete";

export const UploadImage = async (event) => {

    // 파일 하나만 가져오기
    const file = event.target.files[0];

    if (!file) {
        return alert('파일이 업로드되지 않았습니다.');
    }

    const maxSize = 5 * 1024 * 1024;
    if (file.size > maxSize) {
        return alert('파일 크기가 5MB를 초과합니다.');
    }

    const formData = new FormData();

    // 파일 하나만 추가
    formData.append('image', file);

    try {
        // image 정보 반환 (이름 , url , hashName , size ... )
        return await saveImage(formData);
    } catch (error) {
        alert('파일 업로드에 실패했습니다. 다시 시도해 주세요.');
        return;
    }

}
const convert_image_to_dto = (imageObj) => {
        if(!imageObj.url || !imageObj.hashedName) {
            // 이미지 실패 로직
            return alert ("이미지 삭제에 실패했습니다. ");

        }

        const dto = {
            hashedName : imageObj.hashedName,
            url : imageObj.url,
        }

        return dto;
}

const convert_imageList_to_dto = (imageList) => {

    const dto = [];

    //imageList를 순회하면서 imageList의 요소인 hashedName 과 url를 찾음
    imageList.forEach((image) => {

        // 각 이미지에서 hashedName과 url을 추출
        const {url , hashedName} = image;

        const imageInfo = {
            "hashedName" : hashedName,
            "url" : url,
        }

        dto.push(imageInfo);

    });


    return dto;
}

export const deleteImageList = async (imageList) => {

    if(imageList.length === 0) {
        return;
    }

    const requestBody = convert_imageList_to_dto(imageList);

    try {
        // image 정보 반환 (이름 , url , hashName , size ... )
        return await deleteImages(requestBody);

    } catch (error) {
        console.error(error);
        alert('이미지 삭제에 실패했습니다.');
        return;
    }
};




