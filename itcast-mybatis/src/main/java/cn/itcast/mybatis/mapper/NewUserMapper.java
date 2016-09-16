package cn.itcast.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.mybatis.pojo.User;

import com.github.abel533.mapper.Mapper;

public interface NewUserMapper extends Mapper<User> {

    /**
     * 
     * @param page 从那一条数据开始查（数据偏移量）
     * @param rows 每页显示的数据条数
     * @return
     */
    public List<User> queryListByPage(@Param("page") Integer page, @Param("rows") Integer rows);

}
