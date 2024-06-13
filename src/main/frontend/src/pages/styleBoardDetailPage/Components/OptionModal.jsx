import React, { useState, useEffect } from 'react';

const OptionModal = ({ onClose, onOptionButtonClick }) => {
    const [show, setShow] = useState(false);
    const [showConfirmation, setShowConfirmation] = useState(false);

    useEffect(() => {
        setShow(true);
    }, []);

    const handleClose = () => {
        setShow(false);
        setTimeout(onClose, 300); // Match the transition duration
    };

    const handleDeleteClick = () => {
        setShowConfirmation(true);
    };

    const handleConfirmDelete = () => {
        setShowConfirmation(false);
        setShow(false);
        setTimeout(() => {
            // 여기에서 삭제 요청을 보냅니다.
            console.log("삭제 요청 보냄");
            onClose();
        }, 300);
    };

    const handleCancelDelete = () => {
        setShowConfirmation(false);
    };

    return (
        <div className="modal-overlay" onClick={handleClose}>
            {!showConfirmation && (
                <div
                    className={`modal-content ${show ? 'show' : 'hide'}`}
                    onClick={(e) => e.stopPropagation()}
                    onTouchStart={(e) => e.stopPropagation()} // 모바일 터치 이벤트 추가
                >
                    <button onClick={handleClose} className="close-button">
                        <span class="material-symbols-outlined">
                            close
                        </span>
                    </button>
                    <div className="modal-buttons">
                        <button onClick={() => window.location.href = '/edit'}>수정하기</button>
                        <button onClick={handleDeleteClick}>삭제하기</button>
                    </div>
                </div>
            )}
            {showConfirmation && (
                <div
                    className={`modal-content confirmation-modal`}
                    onClick={(e) => e.stopPropagation()}
                    onTouchStart={(e) => e.stopPropagation()} // 모바일 터치 이벤트 추가
                >
                    <p>정말 삭제하시겠습니까?</p>
                    <div className="confirmation-buttons">
                        <button onClick={handleConfirmDelete}>예</button>
                        <button onClick={handleCancelDelete}>아니오</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default OptionModal;
