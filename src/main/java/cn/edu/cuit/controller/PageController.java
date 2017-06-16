package cn.edu.cuit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by admin on 2017/2/23.
 */
@Controller
public class PageController {
    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page){
        return page;
    }
}
