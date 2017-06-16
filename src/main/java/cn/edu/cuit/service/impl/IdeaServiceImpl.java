package cn.edu.cuit.service.impl;

import cn.edu.cuit.mapper.IdeaMapper;
import cn.edu.cuit.pojo.Idea;
import cn.edu.cuit.pojo.User;
import cn.edu.cuit.service.IdeaService;
import javafx.scene.chart.PieChart;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/3/31.
 */

@Service
public class IdeaServiceImpl implements IdeaService{

    @Autowired
    private IdeaMapper ideaMapper;

    public List<Idea> selectIdea(){
       List<Idea> ideaList = ideaMapper.selectIdea();
        return ideaList;

    }

    public boolean toIdea(Integer uid, String idea){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date= sdf.format(dt);
        if (ideaMapper.insertIdea(uid,idea,date) > 0){
            return true;
        }

        return true;
    }

}
