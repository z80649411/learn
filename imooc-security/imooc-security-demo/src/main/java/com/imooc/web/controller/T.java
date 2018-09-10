package com.imooc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 邓仁波 on 2017-11-6.
 */
@RequestMapping
@Controller
public class T {
    @RequestMapping("/c")
    public ModelAndView c(){
        return new ModelAndView("t");
    }

    @RequestMapping("/index")
    public ModelAndView i(){
        return new ModelAndView("index");
    }
}
