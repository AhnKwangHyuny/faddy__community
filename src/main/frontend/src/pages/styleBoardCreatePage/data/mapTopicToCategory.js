import { topics } from './topicsData';

const topicToCategoryMap = {
    question: 'QUESTION',
    daily: 'DAILY_SHARE',
    fashion: 'FASHION_SHARE',
    tips: 'STYLE_TIP',
    news: 'NEWS_SHARE'
};

export const mapTopicToCategory = (topic) => {
    return topicToCategoryMap[topic] || null;
};
