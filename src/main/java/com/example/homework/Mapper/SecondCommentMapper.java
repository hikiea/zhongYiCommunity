package com.example.homework.Mapper;


import com.example.homework.model.SecondComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SecondCommentMapper {

    //添加回复的回复
    @Insert("insert into secondcomment(parent_id,create_time,content,comment_username,parentCommentUUID) values (#{parent_id},#{create_time},#{content},#{comment_username},#{parentCommentUUID})")
    void addSecondCommentMapper(SecondComment secondComment);

    //查询所有回复的回复
    @Select("select * from secondcomment")
    List<SecondComment> showAllSecondComment();

}
