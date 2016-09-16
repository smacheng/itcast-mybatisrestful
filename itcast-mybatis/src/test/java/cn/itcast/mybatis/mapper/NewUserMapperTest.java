package cn.itcast.mybatis.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.pojo.User;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class NewUserMapperTest {

    private NewUserMapper newUserMapper;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        this.newUserMapper = sqlSession.getMapper(NewUserMapper.class);
    }

    @Test
    public void testSelectOne() {
        User user = new User();
        // user.setuserName("zhengpengpeng1");
        user.setAge(21);
        User result = this.newUserMapper.selectOne(user);
        System.out.println(result);

    }

    @Test
    public void testSelect() {
        User user = new User();
        user.setAge(20);
        List<User> list = this.newUserMapper.select(user);
        for (User user2 : list) {
            System.out.println(user2);
        }
    }

    @Test
    public void testSelectCount() {
        User user = new User();
        user.setAge(20);
        int count = this.newUserMapper.selectCount(user);
        System.out.println(count);

    }

    @Test
    public void testSelectByPrimaryKey() {
        User user = this.newUserMapper.selectByPrimaryKey(1l);
        System.out.println(user);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(null);
        user.setAge(20);
        user.setuserName("zhengpengpeng2");

        this.newUserMapper.insert(user);
        System.out.println(user);
    }

    @Test
    public void testInsertSelective() {
        User user = new User();
        user.setId(null);
        user.setAge(20);
        user.setuserName("zhengpengpeng3");

        this.newUserMapper.insertSelective(user);
        System.out.println(user);
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setAge(201);
       // user.setName("zhengpengpeng");
        this.newUserMapper.delete(user);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        this.newUserMapper.deleteByPrimaryKey(14l);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        User user = new User();
        user.setId(2l);
        user.setName("chenzhen");
        this.newUserMapper.updateByPrimaryKey(user);

    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        User user = new User();
        user.setId(10l);
        user.setName("chenzhen");
        this.newUserMapper.updateByPrimaryKeySelective(user);
    }

    // -------------------------以下根据条件查询---------------------------------------------
    @Test
    public void testSelectCountByExample() {
        Example example = new Example(User.class);
        List<Object> list = new ArrayList<Object>();
        list.add("lina");
        list.add("lilei");
        example.createCriteria().andIn("userName", list);
        int count = this.newUserMapper.selectCountByExample(example);
        System.out.println(count);
    }

    @Test
    public void testDeleteByExample() {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name", "chenzhen");
        int count = this.newUserMapper.deleteByExample(example);
        System.out.println(count);
    }

    @Test
    public void testSelectByExample() {
        Example example = new Example(User.class);
        // 加入排序条件
        example.setOrderByClause("id DESC");
        // 加入查询条件
        List<Object> param = new ArrayList<Object>();
        param.add("lina");
        param.add("lilei");
        example.createCriteria().andIn("userName", param);
        List<User> list = this.newUserMapper.selectByExample(example);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateByExampleSelective() {
        // 更新的数据,把符合条件的user性别设置为3
        User user = new User();
        user.setSex(3);
        ;
        // 更新的条件，更新年龄为21的user
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("age", 21);
        int count = this.newUserMapper.updateByExampleSelective(user, example);
        System.out.println(count);
    }

    @Test
    public void testUpdateByExample() {
    }

    // ----------------------分页------------------------------------

    //原来的方式实现分页
    @Test
    public void testPage() {
        List<User> listByPage = this.newUserMapper.queryListByPage(2, 2);
        for (User user : listByPage) {
            System.out.println(user);
        }
    }

    //使用分页插件实现分页
    @Test
    public void testPageHelper() {
        //设置分页信息，第一个参数代表其实页码，第二个参数代表每页显示条数
        PageHelper.startPage(2, 2);
        //查询所有的数据，分页插件实现分页查询
        List<User> list = this.newUserMapper.select(null);
        for (User user : list) {
            System.out.println(user);
        }
        //创建PageInfo，可以获得分页所需要的数据
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        //获取数据总条数
        System.out.println(pageInfo.getTotal());
        //获取总页数
        System.out.println(pageInfo.getPages());
    }
}
