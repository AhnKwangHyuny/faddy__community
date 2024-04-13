import React, { useRef, useEffect, useState } from 'react';
import Tagify from '@yaireo/tagify';
import '@yaireo/tagify/dist/tagify.css';

const HashTagBox = ({ tags, setTags }) => {
    const inputRef = useRef(null);
    const tagifyInstance = useRef(null);

    useEffect(() => {
        if (inputRef.current) {
            tagifyInstance.current = new Tagify(inputRef.current, {
                maxTags: 5,
                placeholder: "type something",
                dropdown: {
                    enabled: 0, // always show suggestions dropdown
                    maxItems: 5,
                },
            });

            tagifyInstance.current.on('change', handleTagChange);
        }

        return () => {
            if (tagifyInstance.current) {
                tagifyInstance.current.off('change', handleTagChange);
                tagifyInstance.current.destroy();
                tagifyInstance.current = null;
            }
        };
    }, []);

    const handleTagChange = (e) => {
        const parsed = JSON.parse(e.detail.value);
        const value = parsed[0].value;

        setTags(value);
    };

    useEffect(() => {
        console.log('Tags updated:', tags);
    }, [tags]);

    return (
        <div className="tags">
            <div className="tags__wrapper">
                <div className="tags__header">
                    <div className="logo">태그</div>
                </div>
                <div className="tags__input">
                    <input
                        ref={inputRef}
                        name="tags-outside"
                        className="tagify--outside"
                        placeholder="태그를 입력 후 엔터를 누르세요 (최대 5개)"
                    />
                </div>
                <div className="tags__footer">
                    <div className="line"></div>
                </div>
            </div>
        </div>
    );
};

export default HashTagBox;