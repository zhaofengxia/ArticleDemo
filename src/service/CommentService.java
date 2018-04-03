package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import bean.Comment;
import util.DataBaseUtils;

public class CommentService {
	/**
	 * 保存评论
	 */
	public void saveComment(Comment comment){
		String sql="INSERT INTO t_comment(id,user_id,content,article_id,create_time,is_delete) VALUES(?,?,?,?,?,?)";
		DataBaseUtils.update(sql,comment.getId(),comment.getUserId(),comment.getContent(),comment.getArticleId(),new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()),0);
	}
	
	/**
	 * 根绝文章id查询它的所有评论
	 */
	public List<Map<String,Object>> getCommentsByArticleId(String id){
		return DataBaseUtils.queryForList("SELECT b.username,a.content from t_comment a LEFT JOIN t_user b "
				+ "ON a.user_id=b.id where a.article_id=?", id);
	}

}
