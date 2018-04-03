<%@ page language="java" import="bean.User,bean.Comment,service.CommentService,java.util.*" pageEncoding="UTF-8"%>
<%
	//因为评论是需要登录的，如果没有登录我们就调整到登录页面去
	User user=(User)session.getAttribute("user");
    if(user==null){
    	out.print("-1");
    	return;
    }
    String txt=request.getParameter("txt").toString();
    String articleId=request.getParameter("articleId").toString();
    
    CommentService cs=new CommentService();
    Comment comment=new Comment();
    comment.setId(UUID.randomUUID().toString());
    comment.setUserId(user.getId());
    comment.setArticleId(articleId);
    comment.setContent(txt);
    cs.saveComment(comment);
%>