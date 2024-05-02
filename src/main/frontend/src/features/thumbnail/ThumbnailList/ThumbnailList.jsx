import React, {useEffect, useState} from 'react';
import Thumbnail from "widgets/Snap/Thumbnail/Thumbnail";
import groupThumbnailsByPair from "utils/thumbnail/groupThumbnailsByPair";
import {useInView} from "react-intersection-observer";
import {getMoreThumbnails} from "api/get";
import LoadingAnimation from "shared/ui/LoadingUI/Loading"

const ThumbnailList = () => {
    const thumbnailss = [
        {
            imageUrl: '/test_image1.png',
            userProfile: '/default_profile.jpg',
            writer: 'user1',
            hashtags: ['#tag1', '#tag2'],
            views: 100000,
            likes: 100,
        },
        {
            imageUrl: 'test_image2.png',
            userProfile: '/default_profile.jpg',
            writer: 'user2',
            hashtags: ['#tag3', '#tag4'],
            views: 2000,
            likes: 200,
        },
        {
            imageUrl: 'test_image3.png',
            userProfile: '/default_profile.jpg',
            writer: 'user3',
            hashtags: ['#tag3', '#tag4'],
            views: 2000,
            likes: 200,
        },
        {
            imageUrl: 'test_image4.png',
            userProfile: '/default_profile.jpg',
            writer: 'user4',
            hashtags: ['#tag3', '#tag4'],
            views: 2000,
            likes: 200,
        },
    ]

    const [thumbnails, setThumbnails] = useState(thumbnailss);
    const [page, setPage] = useState(1);
    const [hasMore, setHasMore] = useState(true);
    const [ref, inView] = useInView();
    const [scrollPosition, setScrollPosition] = useState(0);


    // 스크롤 로더
    const loadThumbnailsFromServer = () => {
        // 중복 요청 불가
        let isRequestInProgress = false;
        // 서버에 추가 데이터 요청
        const loadData = async(page) => {
            // loading 중 중복 요청 불가
            if (isRequestInProgress) return;
            isRequestInProgress = true;

            // const data = await getMoreThumbnails(page);
            //test
            const data = thumbnailss;

            setThumbnails([...thumbnails, ...data]);
            setPage(page + 1);
            setHasMore(data.length === 2); // 추가 데이터가 더 있는지 확인
        }

        loadData(page);
    };

    useEffect(() => {

        if (inView) {
            console.log("도달");
            console.log(thumbnails);
            loadThumbnailsFromServer();
        }
    }, [inView]);

    // inView 감지로 data loading 시 그 inview 위치에서 스크롤 시작
    useEffect(() => {
        window.addEventListener('scroll', () => {
            const scrollPosition = window.scrollY;
            setScrollPosition(scrollPosition);
        });
        return () => {
            window.removeEventListener('scroll', () => {});
        };
    }, []);

    // 업데이트된 thumbnails 상태 사용
    const groups = groupThumbnailsByPair(thumbnails);


    return (
        <ul className="thumbnail-list">

            {groups.map((group, index) => (
                <li className="thumbnail-group" key={index} data-index={`vo-${index}`}>
                    {group.map((thumbnail, i) => (
                        <div key={i} className="thumbnail-wrapper">
                            {thumbnail && <Thumbnail
                                imageUrl={thumbnail.imageUrl}
                                userProfile={thumbnail.userProfile}
                                writer={thumbnail.writer}
                                hashtags={thumbnail.hashtags}
                                views={thumbnail.views}
                                likes={thumbnail.likes}
                            />}
                        </div>
                    ))}
                </li>
            ))}

            <div ref={ref}>
                <LoadingAnimation/>
            </div>
        </ul>
    );
};

export default ThumbnailList;