package cn.com.mybatis.test;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.com.mybatis.datasource.DataConnection;
import cn.com.mybatis.po.User;

public class MyBatisTest {
	public static DataConnection dataConnection = new DataConnection();
	@Test
	public static void TestSelect() throws IOException{
		SqlSession sqlSession = dataConnection.getSqlSession();
		User user = sqlSession.selectOne("test.findUserById", 1);
		System.out.println("username = "+user.getUsername());
		List<User> list = sqlSession.selectList("test.findAll");
		for(User tmp:list){
			System.out.println("province = "+tmp.getProvince());
		}
		sqlSession.close();
	}
	public static void TestInsert() throws IOException{
		SqlSession sqlSession = dataConnection.getSqlSession();
		User user = new User();
		user.setUsername("郭哥");
		user.setBirthday("1990-12-23");
		user.setPassword("963258");
		user.setGender("男");
		user.setProvince("山东");
		user.setCity("菏泽");
		user.setEmail("hahah@qq.com");
		sqlSession.insert("test.insertUser", user);
		sqlSession.commit();
		sqlSession.close();
	}
	public static void TestDelete() throws IOException{
		SqlSession sqlSession = dataConnection.getSqlSession();
		sqlSession.delete("test.deleteUser", 2);
		sqlSession.commit();
		sqlSession.close();
	}
	public static void TestUpdate() throws IOException{
		SqlSession sqlSession = dataConnection.getSqlSession();
		User user = sqlSession.selectOne("test.findUserById",3);
		user.setUsername("毕十三");
		sqlSession.update("test.updateUserName", user);
		sqlSession.commit();
		sqlSession.close();
	}
	public static void main(String[] args){
		try {
			//TestSelect();
			//TestInsert();
			TestDelete();
			TestUpdate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
