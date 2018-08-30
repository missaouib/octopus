package com.targaryen.octopus.controller;

import com.targaryen.octopus.vo.ShiftVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/octopus", produces= MediaType.TEXT_HTML_VALUE)
public class HelloWorld {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    String sayHello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/thymeleaf")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/greeting");
        mv.addObject("title","欢迎使用Thymeleaf!");
        return mv;
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/side/bar/index")
    public String sideBarIndex() {
        return "sideBarIndex";
    }

    @RequestMapping(value = "/side/bar/table/page")
    public String tablePage(ModelMap map) {
        List<ShiftVo> shiftList = new ArrayList<>();

        ShiftVo shiftVo = new ShiftVo();
        shiftVo.setRoleId(1);
        shiftVo.setRoleName("随便什么职务名字");
        shiftVo.setShiftCount(2);
        shiftVo.setShiftDay(3);
        shiftVo.setShiftId(1);
        shiftVo.setShiftStart("????");
        shiftVo.setShiftEnd("!!!!");
        shiftList.add(shiftVo);

        map.addAttribute("shiftList", shiftList);
        return "tablePage";
    }
}
