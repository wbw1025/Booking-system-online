package cn.edu.cuit.service;

import cn.edu.cuit.pojo.Idea;

import java.util.List;

/**
 * Created by admin on 2017/3/31.
 */
public interface IdeaService {

    //获取意见列表
    List<Idea> selectIdea();

    //提交意见
    boolean toIdea(Integer uid,String idea);
}
