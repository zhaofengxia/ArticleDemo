package test;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import bean.Article;
import bean.Comment;
import service.ArticleService;
import service.CommentService;
import util.DataBaseUtils;

/**
 * 测试：给文章插入数据
 * @author zhaofengxia
 *
 */

public class TestInsertOperation {
	
//	@Test
//	public void insertArticle(){
//		String sql="INSERT INTO t_article(id,header,name,content,author,"
//				+ "description,is_published,is_delete,create_time,update_time,"
//				+ "user_id,category_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";
//		String id=UUID.randomUUID().toString();
//		String header="Java Web实用技术";
//		String name="如何将MyEclipse项目导入eclipse";
//		String content="我们经常会在网上下载一些开源项目，或者从别的地方迁移一些项目进来，但经常会发现导入后各种报错。"
//				+ "这是初学java肯定会遇到的问题，本文对一些常见的处理方案做一个总结。"
//				+ "（本文将MyEclipse项目导入eclipse的过程为例，其他情况也可参考这个流程）";
//	    String author="Jack";
//		String description="解决项目导入的冲突问题...";
//		int isPublished=1;
//		int isDelete=0;
//		String create_time="2016-10-19 10:43:10";
//		String update_time="2016-10-19 10:43:10";
//		String userId="319600c3-550a-4f9f-80cf-deebe2376528";
//		int categoryId=2;
//		DataBaseUtils.update(sql,id,header,name,content,author,description,isPublished,isDelete,create_time,update_time,userId,categoryId);
//		System.out.println("新增成功！");
//	}
	
	@Test
	public void getArticle(){
		String sql="select * from t_article where id=?";
		Article article=DataBaseUtils.queryForBean(sql, Article.class, "8df53f7b-2c52-4b89-b2e0-c65a1c9c5ab1");
		System.out.println(article);
	}
	
	/**
	 * 获取分类列表
	 */
	@Test
	public void getCategoryList(){
		String sql="select * from t_category where 1=1";
		List list=DataBaseUtils.queryForList(sql);
		System.out.println(list);
	}
	
	@Test
	public void getArticlesByCategoryId(){
		ArticleService as=new ArticleService();
		List list=as.getArticlesByCategoryId(2, 0, 10);
		System.out.println(list);
	}
	
	@Test
	public void testSaveComment(){
//		CommentService cs=new CommentService();
//		Comment comment=new Comment();
//		comment.setId(UUID.randomUUID().toString());
//		comment.setContent("很不错的文章，赞一个！");
//		comment.setArticleId("66ecd4bf-47c8-49d8-9583-fd7553a624c2");
//		comment.setUserId("3bef0690-e658-47c7-8e67-dc1efa340bd9");
//		cs.saveComment(comment);
//		System.out.println("保存成功！");
		System.out.println(DataBaseUtils.queryForList("SELECT a.content FROM t_comment a LEFT JOIN t_user b "
				+ "ON a.user_id=b.id WHERE a.article_id='66ecd4bf-47c8-49d8-9583-fd7553a624c2'"));
	}
	
	@Test
	public void testGetCommentsByArticleId(){
		CommentService cs=new CommentService();
		System.out.println(cs.getCommentsByArticleId("66ecd4bf-47c8-49d8-9583-fd7553a624c2"));
	}
	

}
