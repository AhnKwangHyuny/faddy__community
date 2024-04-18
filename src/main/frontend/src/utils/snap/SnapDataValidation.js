import { snap_category } from 'data/category';
import {checkUserIdExists} from "../../api/get";
export const isValidDescription = (description) => {
    // 설명 필드 유효성 검사 로직 구현
    return description.length <= 1500;
};

export const isValidTags = (tags) => {
    // 태그 유효성 검사 로직 구현
    return tags.length <= 10 && tags.every(tag => typeof tag === 'string' && tag.trim().length > 0);
};

export const isValidCategories = (selectedCategories) => {
    if (!Array.isArray(selectedCategories) || selectedCategories.length === 0) {
        return false;
    }

    return selectedCategories.every(category => {
        const categoryGroup = Object.keys(category)[0];
        const selectedItem = category[categoryGroup];

        const matchingCategory = snap_category.find(c => c.group === categoryGroup);
        if (!matchingCategory) {
            return false;
        }

        return matchingCategory.items.includes(selectedItem);
    });
};

export const isValidUserId = (userId) => {
    // 사용자 ID 유효성 검사 로직 구현

    // db에 사용자 있는지 확인
    checkUserIdExists(userId);

    return typeof userId === 'string' && userId !== "";
};