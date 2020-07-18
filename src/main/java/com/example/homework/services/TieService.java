package com.example.homework.services;


import com.example.homework.Mapper.TieMapper;
import com.example.homework.Mapper.UserMapper;
import com.example.homework.dto.PageDTO;
import com.example.homework.dto.TieDTO;
import com.example.homework.model.Tie;
import com.example.homework.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TieService {

    @Autowired
    private TieMapper tieMapper;

    @Autowired
    private UserMapper userMapper;

    public Tie findById(Integer id) {
        Tie oneTie = tieMapper.findById(id);
        return oneTie;
    }

    public List<TieDTO> selectByTitle(String title) {
        List<TieDTO> select = tieMapper.findByTitle(title);
        return select;
    }

    public List<Tie> adminShow(){
        List<Tie> ties = tieMapper.adminShowAllTie();
        return ties;
    }


    // 用来首页分页展示帖子
    public PageDTO list(Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        Integer totalCount = tieMapper.count();
        pageDTO.setPageNation(totalCount, page, size);
        if (page < 1) {
            page = 1; }
        if (page > pageDTO.getTotalPage()) {
            page = pageDTO.getTotalPage(); }
        //size*(page-1)
        Integer offset = size * (page - 1);
        //在 tieDTO 进行 Tie 和 User 匹配
        // 1. 查询 tie 表每一条数据，放在 ties 对象里面，类型为 list<Tie>
        List<Tie> ties = tieMapper.list(offset,size);
        // 2. 新建 tieDTOList 动态数组
        List<TieDTO> tieDTOList = new ArrayList<>();
        // 3. 循环 ties ，在循环中，把 ties 起个小名 tie
        for (Tie tie : ties) {
            // 3.1 通过每一条贴的 creatorId（创建人ID），在 user 表中查询一一对应的数据 ，用 user 对象承接
            User user = userMapper.ByTieIdFinUserId(tie.getCreatorId());
            // 3.2 创建 tieDTO 对象
            TieDTO tieDTO = new TieDTO();
            // 3.3 BeanUtils.copyProperties方法：
            // 把参数一：tie 对象上的属性，拷贝到参数二：tieDTO 对象上的属性
            BeanUtils.copyProperties(tie,tieDTO);
            // 4. 把 user类 也set进 tieDTO 里面
            // 此时 tieDTO 对象就同时存在 tie的内容和 user的内容
            tieDTO.setUser(user);
            // 5. 把 tieDTO对象 装进 tieDTOList 数组里面，实现一一对应
            tieDTOList.add(tieDTO);
            // 6. 完成了
        }
        pageDTO.setTie(tieDTOList);
        return pageDTO;
    }


}
