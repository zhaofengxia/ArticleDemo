<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="service.ArticleService,service.CommentService,bean.Comment,java.util.*"%>

<%  
	String id=request.getParameter("id");
    ArticleService articleService=new ArticleService();
    Map<String,Object> map=articleService.getContentByArticleId(id);
    pageContext.setAttribute("article",map);
    System.out.print(map);
    
    CommentService commentService=new CommentService();
    List<Map<String,Object>> list=commentService.getCommentsByArticleId(id);
    pageContext.setAttribute("comments", list);
    System.out.print(list);
    
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include  file="common/taglib.jsp"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script src="${basePath}/static/js/jQuery.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/public.css"/>
<style>

	.wrap {
		margin:20px auto;
		width:1000px;
	}
	
	.left {
		width: 70%;
		float:left;
	}
	
	.article{
		font-family: 微软雅黑;
		line-height:30px;
	}
	
	.article .title {
		text-align: center;
	    font-size: 28px;
	    color: #565353;
	    letter-spacing: 2px;
	    margin-top:20px;
	}
	
	.article .light-font{
		font-size:14px;
		color:#666;
	}
	
	.article .info{
		font-size:14px;
		color:#3c3a3a;
	}
	
	.wrap hr {
		opacity:0.6;
		margin-bottom:30px;
	}
	
	.article .content p{
		text-indent:0em;
		margin-bottom:20px;
		font-family: 微软雅黑;
	}
	
	
	.commentBox .comment_input{
		width: 100%;
	    height: 70px;
	    padding: 9px;
	    border: 1px solid #d0d5d9;
	    box-shadow: inset 1px 1px 5px #ccc;
	}
	
	.commentBox .button{
		width: 90px;
	    height: 28px;
	    line-height: 26px;
	    border: 1px solid #1e80c2;
	    background: #3498db;
	    color: #fff;
	    cursor: pointer;
	    border-radius: 2px;
	    transition: all 0.3s ease;
	    float:right;
	    margin: 19px;
    	margin-right: -22px;
	}
	
	.right {
		width: 30%;
		float:right;
		text-align:right;
	}
	
	.clearfix {
		clear: both;
	}
	
	.header_pic {
	    position: relative;
	    top: 18px;
	    left: -16px
	}
	
	.comment_word p {
		text-indent: 8px;
		font-family: 微软雅黑;
	}
	
	code {
    	font-size: 20px;
	}
	
</style>
	
</head>
<body>

<!-- 头部页面 -->
<%@include file="common/header.jsp" %>


<div class='wrap'>
	<div class='left'>
		<!-- 内容区 -->
		<div class='article'>
			<div class='title'></div>
			<div class='category'><span class='light-font'>分类：</span><span class='info'></span></div>
			<div class='publicDate'><span class='light-font'>发布时间：</span><span class='info'>${article.create_time}</span></div>
			<hr/>
			<div class='content'>
				${article.content }
			</div>
		</div>
		
		<!-- 评论区 -->
		<div class='commentBox'>
			<textarea class="comment_input" id="commenttxt" placeholder="请输入评论信息(600)..." maxlength="600"></textarea>
		          <input type="button" value="保存评论" class="button">
		</div>
		<div class='clearfix'></div>
		<br/><hr/>
		<div class='mb64' class="comment_list">
			<c:forEach items="${comments}" var="comment">
				<div class="comment_infor clearfix">
			    	<div style='border-bottom:1px solid #ccc' class="comment_word">
			             <p style='border-bottom:20px solid #fff'>${comment.username}说：</p>
			             <p class='mb32'>${comment.content}</p>
			    	 </div>
			    </div>
		    </c:forEach>
		</div>
		
	</div>
	
	
	<div class='right mt32'>
		<div class='author'>
		<img src='${basePath}/static/img/1.jpg' class='header_pic' width='90' height='90'></img>
		作者：张三
		</div>
	</div>
	
	
</div>

<div class='clearfix'></div>

<!-- 底部页面 -->
<%@include file="common/footer.jsp" %>

<script>
	$(".button").eq(0).on('click',function(){
		debugger;
		var txt=$('#commenttxt').val();
		$.post("${basePath}/controller/CommentController.jsp",{txt:txt,articleId:"${article.id}"},function(data){
			debugger;
			data=data.trim();
			if(data=='-1'){
				alert('请先登录！');
			}else if(data=='1'){
				alert('保存成功！');
				location.reload();
			}
		});
	});
</script>

</body>
</html>