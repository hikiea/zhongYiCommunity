package com.example.homework.Mapper;
import com.example.homework.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface HeadMapper {

    @Update("update user set headName = #{headName} where token = #{token}")
    void updateUserHead(User user);

}
