import React from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

const ContentViewer = ({ title , content }) => {
    return (
        <div className="content-viewer">
            <div className = "title-container">
                <span className = "title"> {title}</span>
            </div>

            <div className= "content">
                <ReactQuill
                    value={content}
                    readOnly={true}
                    theme="bubble"
                />
            </div>

        </div>
    );
};

export default ContentViewer;
