package test;

 
import java.util.List;
import java.util.Map;

import bean.User;
import util.DataBaseUtils;

public class TestDataBaseUtils {
	public static void main(String[] args) {
//		DataBaseUtils.config("jdbc.properties");
//		Connection conn=DataBaseUtils.getConnection();
//		System.out.println(conn);
//		String id=UUID.randomUUID()+"";
//		String createTime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//		DataBaseUtils.update("INSERT INTO t_user(id,username,password,sex,create_time,is_delete,address,telephone) "
//				+ "VALUES(?,?,?,?,?,?,?,?)",id,"王五","123456",0,"2018-03-30",0,"保密","保密");
//		List list=DataBaseUtils.queryForList("select * from t_user");
//        System.out.println(list);
//        Map map=DataBaseUtils.queryForMap("select * from t_user");
//        System.out.println(map);
        User user=DataBaseUtils.queryForBean("select * from t_user limit 1", User.class);
        System.out.println(user);
		
	}
	


}
