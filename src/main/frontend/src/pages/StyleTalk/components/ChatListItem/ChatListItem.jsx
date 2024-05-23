import { Link, useLocation , useNavigate } from 'react-router-dom'

const ChatListItem = ({chatItem})=> {
    const navigate = useNavigate();

    const {
        id,
        title,
        created_at,
        type,
        updated_at
    } = chatItem;

    return (
        <div className="chatListItem">
            <Link to={`/talks/detail/${type.toLowerCase()}/${id}`}>
                <div className="chatListItem-container">
                    <div className="chat-thumbnail">
                        <div className="chat-thumbnail-container">
                            <img
                                src = "/default_profile.jpg"
                                alt = "test image"
                            />
                        </div>
                    </div>

                    <div className="chat-label-container">
                         <div className="chat-label__title">
                            <span className="title">테스트 제목입니다.</span>
                            <span className="headCount"> 34 </span>
                         </div>

                         <div className="chat-label__recentMessage">
                            아 행복했던 날들 이었다. 이젠 더는 없겠지만 추억으로 남겨야지
                         </div>
                         <div className = "chat-label__hashTag">
                            <div className="hashTagList">
                                <div className = "hashTag">
                                    #테스트
                                </div>
                                <div className = "hashTag">
                                    #봄날씨
                                </div>
                                <div className = "hashTag">
                                    #추억
                                </div>
                            </div>
                         </div>
                    </div>

                    <div className="chat-metaInfo">
                        <div className="chat-metaInfo__unreadCount">
                            <div className = "unreadCount-icon">
                                <span class="material-icons circle">
                                    circle
                                </span>
                                <div className = "unreadCount">
                                    <span className = "count">
                                        3
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div className="chat-metaInfo__recent-time">
                            <span className = "time">
                                오후 10:05
                            </span>
                        </div>
                    </div>
                </div>
            </Link>
        </div>
    )

};

export default ChatListItem;