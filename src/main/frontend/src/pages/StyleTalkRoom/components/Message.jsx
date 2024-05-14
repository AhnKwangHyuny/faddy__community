import React from 'react';

const Message = ({ children, model }) => {
  const messageClass = model.direction === 'outgoing' ? 'message-outgoing' : 'message-incoming';

  return <div className={`message ${messageClass}`}>{children}</div>;
};

export default Message;