package test;

import bean.Article;
import util.TableUtils;

public class TestMain {
	public static void main(String[] args) {
		String sql=TableUtils.getCreateTableSQL(Article.class);
		System.out.println(sql);
	}

}
