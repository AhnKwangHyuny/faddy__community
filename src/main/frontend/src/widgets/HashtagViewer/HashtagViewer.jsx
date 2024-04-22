import React from 'react';

const HashtagViewer = ({hashtagList}) => {

    return (
        <div className = "hashtag-viewer">
            <ul className="hashtag-viewer__wrapper">
                {hashtagList.map((hashtag) => (
                    <li className="hashtag" key={hashtag}>
                        #{hashtag}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default HashtagViewer;
