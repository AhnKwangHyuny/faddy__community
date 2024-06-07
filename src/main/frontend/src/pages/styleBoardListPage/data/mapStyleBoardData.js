const mapStyleBoardData = (serverData) => {
  return serverData.map(item => ({
    boardId: item.id,
    boardTitle: item.title,
    creator: item.author,
    stats: {
      likesCount: item.likes,
      commentsCount: item.comments
    }
  }));
};

export default mapStyleBoardData;