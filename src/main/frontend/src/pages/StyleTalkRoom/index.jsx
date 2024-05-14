import React, { useState } from "react";
import ContentBox from "features/ContentBox/ContentBox";
import Avatar from "widgets/Avatar/Avatar";
import AboutHeader from "features/AboutHeader/AboutHeader";
import MainContainer from "pages/StyleTalkRoom/components/MainContainer";
import ChatContainer from 'pages/StyleTalkRoom/components/ChatContainer';
import MessageList from 'pages/StyleTalkRoom/components/MessageList';
import MessageGroup from 'pages/StyleTalkRoom/components/MessageGroup';
import MessageMetaInfo from 'pages/StyleTalkRoom/components/MessageMetaInfo';
import Message from 'pages/StyleTalkRoom/components/Message';
import SentTime from 'pages/StyleTalkRoom/components/SentTime';
import ReadCount from 'pages/StyleTalkRoom/components/ReadCount';
import TimeStampLine from "pages/StyleTalkRoom/components/TimeStampLine";
import DividerLine from "pages/StyleTalkRoom/components/DividerLine";
import MessageContent from "pages/StyleTalkRoom/components/MessageContent";
const defaultMessage = [
   {
       model: {
           message: "How are you dwdqwdwqdwdqwdqwdwqdqwdwqdqwdqwdqwqwdqwdqwdwq?",
           direction: "outgoing",
           type: "text",
       },
       avatar: {
           src: "/default_profile.jpg",
           nickname: "영식이",
       },
   },
   {
       model: {
           message: "I'm fine, thank you, and you?",
           direction: "incoming",
           type: "text",
       },
   },
   {
       model: {
           message: "I'm fine, too. thank you, and you?",
           direction: "outgoing",
           type: "text",

       },
       avatar: {
           src: "/default_profile.jpg",
           nickname: "영식이",
       },
   },
];

const getMessageComponent = (data) => {
   return data.map((item, index) => {
       return (
           <MessageGroup>
               {item.avatar ? (
                   <Avatar userInfo={item.avatar} />
               ) : null}
               <Message key={index} model={item.model}>
                   <MessageContent message={item.model.message} />
                   <MessageMetaInfo>
                       <SentTime />
                       <ReadCount />
                   </MessageMetaInfo>
               </Message>
           </MessageGroup>
       );
   });
};

const ChatUI = () => {
   const [messages, setMessages] = useState(defaultMessage);
   const [inputValue, setInputValue] = useState('');
   const userInfo = {
       userProfileImageUrl: "/default_profile.jpg",
       nickname: "nickon",
   };

   const fileEditIcon = () => {
       return (
           <span className="material-icons button-icon">
               sentiment_satisfied_alt
           </span>
       )
   }
   // request save chat data to server db




   const handleSend = (input) => {
       let newMessage = {
           model: {
               message: input,
               direction: "incoming",
           },
       };
       setMessages([...messages, newMessage]);
   };

   return (
       <section id="Style-talk">
           <AboutHeader title={"#title"} />
           <DividerLine/>
           <MainContainer>
               <ChatContainer>
                   <TimeStampLine />
                   <MessageList>{getMessageComponent(messages)}</MessageList>
               </ChatContainer>
               <ContentBox icon={fileEditIcon()} onSubmit={handleSend} />
           </MainContainer>
       </section>
   );
};

const StyleTalkRoom = () => {
   const contentBoxInputSubmitHandler = () => {
       console.log("hello");
   };

   return (
       <div>
           <AboutHeader title={"#title"} />
           <ChatUI />
       </div>
   )
}

export default StyleTalkRoom;