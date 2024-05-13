import React, { useState } from "react";
import ContentBox from "features/ContentBox/ContentBox";
import Avatar from "widgets/Avatar/Avatar";
import AboutHeader from "features/AboutHeader/AboutHeader";

// pages/StyleTalkRoom/components/Chat/index.js
import MainContainer from "pages/StyleTalkRoom/components/MainContainer";
import ChatContainer from 'pages/StyleTalkRoom/components/ChatContainer';
import MessageList from 'pages/StyleTalkRoom/components/MessageList';
import MessageGroup from 'pages/StyleTalkRoom/components/MessageGroup';
import MessageInfo from 'pages/StyleTalkRoom/components/MessageInfo';
import Message from 'pages/StyleTalkRoom/components/Message';
import SentTime from 'pages/StyleTalkRoom/components/SentTime';
import ReadCount from 'pages/StyleTalkRoom/components/ReadCount';
import TimeStampLine from "pages/StyleTalkRoom/components/TimeStampLine";
import DividerLine from "pages/StyleTalkRoom/components/DividerLine";


const defaultMessage = [
   {
       model: {
           message: "How are you?",
           direction: "incoming",
       },
       avatar: {
           src: "/default_profile.jpg",
           nickname: "bloodstrawberry",
       },
   },
   {
       model: {
           message: "I'm fine, thank you, and you?",
           direction: "outgoing",
       },
   },
   {
       model: {
           message: "I'm fine, too. thank you, and you?",
           direction: "incoming",
       },
       avatar: {
           src: "/default_profile.jpg",
           nickname: "bloodstrawberry",
       },
   },
];

const getMessageComponent = (data) => {
   return data.map((item, index) => {
       return (
           <MessageGroup>
                <Message key={index} model={item.model}>
                   <SentTime/>
                   <ReadCount/>
               </Message>

               {item.avatar ? (
                  <Avatar userInfo={item.avatar} />
               ) : null}

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

   const handleSend = (input) => {
       let newMessage = {
           model: {
               message: input,
               direction: "outgoing",
           },
       };
       setMessages([...messages, newMessage]);
   };

   return (
       <section id = "Style-talk">
           <AboutHeader title={"#title"} />
           <DividerLine/>
           <MainContainer>
               <ChatContainer>
                   <TimeStampLine />
                   <MessageList>{getMessageComponent(defaultMessage)}</MessageList>
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