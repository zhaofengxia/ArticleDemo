package util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.ResultSetMetaData;


public class DataBaseUtils {
	private static String username;
	private static String password;
	private static String dataBaseName;
	
	static{
		config("jdbc.properties");
	}
	
	/**
	 * 配置数据库的基本信息
	 */
	public static void config(String path){
		InputStream inputStream=DataBaseUtils.class.getClassLoader().getResourceAsStream(path);
		Properties p=new Properties();
		try {
			p.load(inputStream);
			username=p.getProperty("db.username");
			password=p.getProperty("db.password");
			dataBaseName=p.getProperty("db.dataBaseName");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 */
	 public static Connection getConnection(){
		 Connection connection=null;
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dataBaseName+"?"
					+ "useUnicode=true&characterEncoding=utf8",username,password);
		 }catch (ClassNotFoundException e) {
			e.printStackTrace();
		 }catch(SQLException e){
			e.printStackTrace();
		 }
		 return connection;
	 }
	 
	 /**
	  * 关闭资源
	  */
	 public static void closeConnection(Connection connection,PreparedStatement ps,ResultSet rs){
		 
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(connection!=null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 
	 /**
	  * DML操作
	  */
	 public static void update(String sql,Object...objects){
		 Connection connection=getConnection();
		 PreparedStatement ps=null;
		 try {
			ps=(PreparedStatement) connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++){
				ps.setObject(i+1, objects[i]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(connection, ps, null);
		}
	 }
	  
	  /**
	   * 查询出数据，并且list返回
	   */
	  public static List<Map<String,Object>> queryForList(String sql,Object...objects){
		 List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		 Connection connection=getConnection();
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 try {
			ps=(PreparedStatement) connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++){
				ps.setObject(i+1, objects[i]);
			}
			rs=(ResultSet) ps.executeQuery();
			while(rs.next()){
				ResultSetMetaData resultSetMetaData=(ResultSetMetaData) rs.getMetaData();
				int count=resultSetMetaData.getColumnCount();//获取列数
				Map<String,Object> map=new HashMap<String,Object>();
				for(int i=0;i<count;i++){
					map.put(resultSetMetaData.getColumnName(i+1),rs.getObject(resultSetMetaData.getColumnName(i+1)));
				}
				result.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection, ps, rs);
		}	
		return result;
	  }
	  
	  /**
	   * 查询出数据，并且map返回
	   */
	  public static Map<String,Object> queryForMap(String sql,Object...objects){
		  Map<String,Object> result=new HashMap<String,Object>();
		  List<Map<String,Object>> list=queryForList(sql, objects);
		  if(list.size()!=1){
			  return null;
		  }
		  result=list.get(0);
		  return result;
	  }
	  
	  /**
	   * 查询出数据，并且返回一个JavaBean
	   */
	  public static <T>T queryForBean(String sql,Class clazz,Object...objects){
		  T obj=null;
		  Map<String,Object> map=null;
		  Field field=null;
		  try {
			obj=(T)clazz.newInstance();//创建一个新的Bean实例
			map=queryForMap(sql, objects);//先将结果集放在一个Map中
				
		   } catch (InstantiationException | IllegalAccessException e) {
			   e.printStackTrace();
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
		   if(map==null){
			   return null;
		   }
		   
		   //遍历Map
		   for(String columnName : map.keySet()){
			   Method method=null;
			   String propertyName=StringUtils.columnToProperty(columnName);
			   try {
				   field=clazz.getDeclaredField(propertyName);
			   } catch (NoSuchFieldException e) {
					e.printStackTrace();
			   } catch (SecurityException e) {
					e.printStackTrace();
			   }
			   
			   //获取JavaBean中的字段
			   String fieldType=field.toString().split(" ")[1];//获取该字段的类型
			   Object value=map.get(columnName);
			   if(value==null){
				   continue;
			   }
			   /** 获取set方法的名字**/
			   String setMethodName="set"+StringUtils.upperCaseFirstCharacter(propertyName);
			   
			   try {
				   /**获取值的类型**/
				   String valueType=value.getClass().getName();
				   
				   /**查看类型是否匹配**/
				   if(!fieldType.equalsIgnoreCase(valueType)){
					  if(fieldType.equalsIgnoreCase("java.lang.Integer")){
						  value=Integer.parseInt(String.valueOf(value));
					  }else if(fieldType.equalsIgnoreCase("java.lang.String")){
						  value=String.valueOf(value);
					  }else if(fieldType.equalsIgnoreCase("java.util.Date")){
						  valueType="java.util.Date";
						  String dateStr = String.valueOf(value);
						  Timestamp ts =Timestamp.valueOf(dateStr);
						  Date date=new Date(ts.getTime());
						  value=date;
					  }
					  
				   }
					/**获取set方法**/
					method=clazz.getDeclaredMethod(setMethodName, Class.forName(fieldType));
					   /**执行set方法**/
					method.invoke(obj,value);
			   } catch (Exception e) {
				e.printStackTrace();
			   } 
			

		   }
		  
		  return obj;
	  }
	  
	 
}
