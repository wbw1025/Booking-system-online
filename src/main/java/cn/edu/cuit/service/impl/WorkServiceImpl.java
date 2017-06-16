package cn.edu.cuit.service.impl;

import cn.edu.cuit.mapper.WorkMapper;
import cn.edu.cuit.pojo.Work;
import cn.edu.cuit.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/4/26.
 */
@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkMapper workMapper;


    public List<Work> findWork(){
        List<Work> result =workMapper.selectWork();
        return result;
    }

    public void delWork(Integer id){
        if (id!=null){
            workMapper.deleteByPrimaryKey(id);
        }
    }

   public void addWork(String name,String date,Integer anum,Integer pnum){
        Work work=new Work();
        work.setName(name);
        work.setDate(date);
        work.setAnum(anum);
        work.setPnum(pnum);
        workMapper.insert(work);
    }


    public void editWork(Integer id, String name,String date,Integer anum,Integer pnum){
        if (id!=null){
           Work work=new Work();
            work.setName(name);
            work.setDate(date);
            work.setAnum(anum);
            work.setPnum(pnum);
            work.setWid(id);
            workMapper.updateByPrimaryKey(work);
        }
    }
}
