package cn.itcast.mybatis.mapper;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.pojo.User;

public class UserMapperTest {

    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        this.userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testQueryUserById() {
        User user = this.userMapper.queryUserById(1l);
        System.out.println(user);
    }

    @Test
    public void testQueryUserByUserNameAndPassword() {
        User user = this.userMapper.queryUserByUserNameAndPassword("zhangsan", "123456");
        System.out.println(user);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setAge(20);
        user.setBirthday(new Date());
        user.setId(null);
        user.setName("zhengpengpeng1");
        user.setSex(2);
        user.setPassword("123");
        user.setuserName("zhengpengpeng1");

        this.userMapper.saveUser(user);

        System.out.println(user);

    }

    @Test
    public void testUpdateUserById() {
        User user = new User();
        user.setAge(20);
        user.setBirthday(new Date());
        user.setId(7l);
        user.setName("zhengpengpeng1");
        user.setSex(2);
        user.setPassword("123");
        user.setuserName("zhengpengpeng1");
        this.userMapper.updateUserById(user);
    }

    @Test
    public void testDelUserById() {
        this.userMapper.delUserById(9l);
    }

}
