package cn.itcast.userinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.itcast.userinfo.mapper.UserMapper;
import cn.itcast.userinfo.pojo.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据id查询用户数据
     * 
     * @param id
     * @return
     */
    public User queryUserById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询用户数据
     * 
     * @param page
     * @param rows
     * @return
     */
    public List<User> queryListByPage(Integer page, Integer rows) {
        // 第一个参数是页码，第二个参数是每页显示数据条数
        PageHelper.startPage(page, rows);
        return this.userMapper.select(null);
    }

    /**
     * 新增用户
     * 
     * @param user
     */
    public void saveUser(User user) {
        user.setId(null);
        this.userMapper.insert(user);

    }

    /**
     * 更新用户
     * 
     * @param user
     */
    public void updateUser(User user) {
        this.userMapper.updateByPrimaryKeySelective(user);

    }

    /**
     * 删除用户
     * 
     * @param id
     */
    public void deleteUser(Long id) {
        this.userMapper.deleteByPrimaryKey(id);
    }
}
