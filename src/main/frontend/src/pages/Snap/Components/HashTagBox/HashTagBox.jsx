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
                    enabled: 0,
                    maxItems: 5,
                },
            });

            tagifyInstance.current.on('change', handleTagChange);
            tagifyInstance.current.on('remove', handleTagRemove);
        }

        return () => {
            if (tagifyInstance.current) {
                tagifyInstance.current.off('change', handleTagChange);
                tagifyInstance.current.off('remove', handleTagRemove);
                tagifyInstance.current.destroy();
                tagifyInstance.current = null;
            }
        };
    }, []);

    const handleTagChange = (e) => {

        if(e.detail.value === "") {
            return;
        }

        const values = JSON.parse(e.detail.value);

        const tagList = values.map(item => item.value);

        setTags(tagList);
    };


    const handleTagRemove = (e) => {
        const { index } = e.detail;

        // 태그 리스트가 비었을 때 return
        if (tags.length === 0) {
            return;
        }

        // 마지막 태그를 삭제하려는 경우
        if (index === tags.length - 1) {
            setTags(tags.slice(0, -1));
        } else {
            setTags(tags.filter((_item, i) => i !== index));
        }
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
                    <input ref={inputRef} name="tags-outside" className="tagify--outside" placeholder="태그를 입력 후 엔터를 누르세요 (최대 5개)" />
                </div>
                <div className="tags__footer">
                    <div className="line"></div>
                </div>
            </div>
        </div>
    );
};

export default HashTagBox;