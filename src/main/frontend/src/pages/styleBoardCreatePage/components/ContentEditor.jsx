import React, { useState, useRef, useEffect, useMemo } from 'react';
import { uploadImageFromFile } from "utils/Image/ImageUtils";
import { deleteImage } from "api/delete";
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

const ContentEditor = ({ content, setContent }) => {
    const quillRef = useRef(null);
    const IMAGE_CATEGORY = 'style_board';
    const [imageList, setImageList] = useState([]);
    const imageListRef = useRef(imageList);

    useEffect(() => {
        imageListRef.current = imageList;
    }, [imageList]);

    useEffect(() => {
        if (quillRef.current) {
            const quillInstance = quillRef.current.getEditor();
            const toolbar = quillInstance.getModule('toolbar');

            // 이미지 업로드 핸들러 등록
            toolbar.addHandler('image', handleImageUpload);

            // 텍스트 변경 이벤트 핸들러 등록 (이미지 삭제 감지용)
            quillInstance.on('text-change', handleImageDelete);

            // Cleanup 함수: 컴포넌트가 unmount될 때 이벤트 리스너 제거
            return () => {
                quillInstance.off('text-change', handleImageDelete);
            };
        }
    }, []);

    // 이미지 url를 통해 useState imageList에 저장된 imageInfo 조회
    const findImageInfoByUrl = (imageUrl) => {
        return imageListRef.current.find(imageInfo => imageInfo.url === imageUrl);
    };

    // 이미지 업로드 핸들러
    const handleImageUpload = () => {
        const input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');
        input.click();

        input.onchange = async () => {
            const file = input.files[0];

            // 이미지 업로드 api 요청
            const uploadedImage = await uploadImageFromFile(file, IMAGE_CATEGORY);

            console.log(uploadedImage.data);

            const imageInfo = {
                hashedName: uploadedImage.data.hashedName,
                url: uploadedImage.data.url,
            };

            setImageList(prevList => {
                const updatedList = [...prevList, imageInfo];
                imageListRef.current = updatedList; // 업데이트된 리스트를 ref에도 반영
                return updatedList;
            });

            // 이미지 저장 후 url 반환 (s3 object url)
            if (uploadedImage?.data?.url != null) {
                const quillInstance = quillRef.current.getEditor();
                const range = quillInstance.getSelection(true);
                quillInstance.insertEmbed(range.index, 'image', uploadedImage.data.url);
                return;
            }

            // 이미지 전송 오류 시
            alert('이미지 업로드에 실패했습니다.');
            return;
        };
    };

    // 이미지 삭제 핸들러
    const handleImageDelete = (delta, oldDelta, source) => {
        if (source === 'user') {
            const deletedImages = oldDelta.ops.filter(op => op.insert && op.insert.image && !delta.ops.some(newOp => newOp.insert === op.insert));

            deletedImages.forEach(op => {
                const imageUrl = op.insert.image;
                sendDeleteRequest(imageUrl);
            });
        }
    };

    // 서버로 이미지 삭제 요청 전송 핸들러
    const sendDeleteRequest = async (imageUrl) => {
        try {
            // 이미지 url을 통해 imageInfo 조회
            const imageInfo = findImageInfoByUrl(imageUrl);
            console.log(imageUrl, imageListRef.current);
            console.log(imageInfo);
            const deletedImageInfo = await deleteImage(imageInfo);

            console.log('Image deleted successfully');
        } catch (error) {
            console.error('Error deleting image:', error);
        }
    };

    // 최초 렌더링 시 modules 저장 후 매 렌더링 마다 module 새로 생성 하는 이슈 해결
    const modules = useMemo(() => {
        return {
            toolbar: {
                container: [
                    ['image'],
                    [{ header: [1, 2, 3, false] }],
                    ['bold', 'italic', 'underline', 'strike', 'blockquote'],
                ],
                handlers: {
                    image: handleImageUpload,
                },
            },
        };
    }, []);

    return (
        <div className="content-editor-container">
            <ReactQuill
                ref={quillRef}
                value={content}
                onChange={setContent}
                placeholder="본문을 입력하세요"
                className="content-editor"
                modules={modules}
            />
        </div>
    );
};

export default ContentEditor;
