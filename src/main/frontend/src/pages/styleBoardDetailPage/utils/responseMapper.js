import {formatCreatedAt} from './formatDate';

export const mapToComment = (comment) => {
   return {
       id: comment.id,
       content: comment.content,
       createdAt: formatCreatedAt(comment.createdAt),
       author: {
           nickname: comment.author.nickname,
           level: comment.author.level,
           profileImageUrl: comment.author.profileImageUrl,
       },
       content: comment.content,
       parentCommentId: comment.parentCommentId || null,
       children: comment.children ? comment.children.map(mapToComment) : null,
   };
};