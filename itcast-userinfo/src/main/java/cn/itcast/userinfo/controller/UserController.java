package cn.itcast.userinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.userinfo.pojo.User;
import cn.itcast.userinfo.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据id查询用户数据
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public User queryUserById(@PathVariable("id") Long id) {

        User user = this.userService.queryUserById(id);
        return user;

    }

    /**
     * 分页查询用户数据
     * 
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List<User> queryListByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "2") Integer rows) {
        List<User> list = this.userService.queryListByPage(page, rows);
        return list;
    }

}
