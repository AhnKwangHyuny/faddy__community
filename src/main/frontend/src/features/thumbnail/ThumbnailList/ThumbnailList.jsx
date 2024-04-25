import React from 'react';
import Thumbnail from "widgets/Snap/Thumbnail/Thumbnail";
import groupThumbnailsByPair from "utils/thumbnail/groupThumbnailsByPair";
const ThumbnailList = () => {
    const thumbnails = [
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

    ];

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
        </ul>
    );
};

export default ThumbnailList;