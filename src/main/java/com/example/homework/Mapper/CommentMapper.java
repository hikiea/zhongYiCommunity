package com.example.homework.Mapper;

import com.example.homework.dto.CommentDTO;
import com.example.homework.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    //评论插入实现
    @Insert("insert into comment(parent_id,type,comment_id,create_time,modify_time,like_count,content,comment_username,commentUUID) values (#{parent_id},#{type},#{comment_id},#{create_time},#{modify_time},#{like_count},#{content},#{comment_username},#{commentUUID})")
    void insert(Comment comment);

    //展示所有评论
    @Select("select * from comment where parent_id = #{id}")
    List<CommentDTO> findById(@Param("id") Integer id);

    //通过ID查多少条评论
    @Select("select COUNT(*) FROM comment where parent_id = #{id}")
    Integer count(@Param("id") Integer id);
}
