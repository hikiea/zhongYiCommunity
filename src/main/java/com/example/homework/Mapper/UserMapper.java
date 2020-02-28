package com.example.homework.Mapper;


import com.example.homework.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //注册成功，插入一条数据
    @Insert("insert into user(username,password,token) values (#{username},#{password},#{token})")
    void add(User user);

    //检验注册的username是否已经存在数据库中，通过User类创建findByUsername对象，传进username进行与数据库比较
    @Select("select * from user where username = #{username}")
    User findByUsername(@Param("username") String username);

    //登录用：检验账号密码是否正确
    @Select("select * from user where username = #{username} and password = #{password}")
    User findByUsername_login(@Param("username") String username,
                              @Param("password") String password);

    //遍历数据库所有数据，用List<User>承接
    @Select("select * from user")
    List<User> showAllData();

    //找每一个User独有的token
    @Select("select * from user where username=#{username}")
    User username_to_setToken(@Param("username") String username);

    //查询浏览器的token是否与用户的token一致，保持登录态
    @Select("select * from user where token = #{token}")
    User findByToken(String token);

    //通过ID，检索所有数据
    @Select("select * from user where id = #{id}")
    List<User> id_to_findByALlData(Integer id);

    //通过comment_id，查username
    @Select("select * from user where id = #{comment_id} ")
    User findById(Integer comment_id);

    //通过ID，查username
    @Select("select * from user where id = #{userId} ")
    User findByUserId(@Param("userId") Integer userId);

    //通过id，修改密码
    @Update("update user set password = #{newPassword} WHERE id = #{userId} ")
    void updateById(@Param("newPassword") String newPassword,
                    @Param("userId") Integer id);

    @Select("select * from user where id = #{id}")
    User ByTieIdFinUserId(@Param("id") Integer id);

}
