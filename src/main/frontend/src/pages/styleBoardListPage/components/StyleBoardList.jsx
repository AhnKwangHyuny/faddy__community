import React from "react";
import StyleBoard from "./StyleBoard";

const StyleBoardList = ({ styleBoards = [] }) => {
    return (
        <div>
            <ul>
                {styleBoards.map((styleBoard) => (
                    <li key={styleBoard.id}>
                        <StyleBoard data={styleBoard} />
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default StyleBoardList;
