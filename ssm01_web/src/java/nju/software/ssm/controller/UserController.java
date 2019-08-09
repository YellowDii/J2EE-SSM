package nju.software.ssm.controller;

import nju.software.ssm.domain.UserInfo;
import nju.software.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv=new ModelAndView();
        List<UserInfo> userList=userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String  save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(Integer id) throws Exception{
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        //主意前端页面中的变量名
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
}
