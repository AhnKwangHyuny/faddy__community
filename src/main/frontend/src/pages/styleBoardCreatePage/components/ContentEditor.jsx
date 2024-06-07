import React from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

const ContentEditor = ({ content, setContent }) => {
    return (
        <div className="content-editor-container">
            <ReactQuill
                value={content}
                onChange={setContent}
                placeholder="본문을 입력하세요"
                className="content-editor"
            />
        </div>
    );
};

export default ContentEditor;
