package bean;

import java.util.Date;

import annotation.Column;
import annotation.Table;

@Table(tableName="t_article")
public class Article {
	
	@Column(field="id",type="varchar(100)",primaryKey=true)
	private String id;
	
	@Column(field = "header" , type = "varchar(100)")
	private String header;
	
	@Column(field = "name" , type = "varchar(60)")
	private String name;
	
	@Column(field = "content" , type = "text")
	private String content;
	
	@Column(field = "author" , type = "varchar(30)")
	private String author;
	
	@Column(field = "description" , type = "varchar(100)")
	private String description;
	
	@Column(field = "is_published" , type = "int(1)")
	private Integer isPublished;
	
	@Column(field = "is_delete" , type = "int(1)")
    private Integer isDelete;
    
	@Column(field = "create_time" , type = "datetime")
    private Date createTime;
    
	@Column(field = "update_time" , type = "timestamp" , defaultNull = false)
    private Date updateTime;
    
	@Column(field = "user_id" , type = "varchar(100)" , defaultNull = false)
    private String userId;
    
	@Column(field = "category_id" , type = "int(2)" , defaultNull = false)
    private Integer categoryId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", header=" + header + ", name=" + name + ", content=" + content + ", author="
				+ author + ", description=" + description + ", isPublished=" + isPublished + ", isDelete=" + isDelete
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", userId=" + userId + ", categoryId="
				+ categoryId + "]";
	}
    
    
}
