package com.example.homework.Mapper;

import com.example.homework.dto.TieDTO;
import com.example.homework.model.Tie;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface TieMapper {

    @Select("select * from tie")
    List<Tie> adminShowAllTie();

    //发布留言，插入数据库
    @Insert("insert into tie(title,content,username,date,creatorId) values (#{title},#{content},#{username},#{date},#{creatorId})")
    void addTie(Tie tie);

    //展示所有帖子,分页,倒叙
    @Select("select * from tie order by id desc limit #{offset},#{size}")
    List<Tie> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from tie")
    Integer count();

    //通过查询username，展示个人发帖
    @Select("select * from tie where username = #{username} ORDER BY id desc" )
    List<Tie> showOneTie(@Param("username") String username);

    //通过id，找到指定的帖子
    @Select("select * from tie where id = #{id}")
    Tie findById(@Param("id") Integer id);

    //更新帖子数据
    @Update("update tie set title = #{title},content = #{content},date = #{date} where id = #{id}")
    void updateTie(Tie tie);

    //删除帖子
    @Delete("delete from tie where id = #{id}")
    void deleteTie(Integer id);

    //模糊查询
    @Select("select * from (select * from tie where title like '%${title}%') as temp")
    List<TieDTO> findByTitle(@Param("title") String title);

}
