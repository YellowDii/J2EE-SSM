package nju.software.ssm.controller;

import com.github.pagehelper.PageInfo;
import nju.software.ssm.domain.Orders;
import nju.software.ssm.service.IOrdersService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.awt.event.MouseEvent;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    //未分页，不使用PageHelper
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception{
//        ModelAndView mv = new ModelAndView();
//        List<Orders> ordersList=ordersService.findAll();
//        mv.addObject("ordersList",ordersList);
//        mv.setViewName("orders-list");
//        return mv;
//    }

    //使用PageHelper进行分页
    //参数最好选用对象Integer代替int，在日志编写时容易获取参数对象名
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList=ordersService.findAll(page,size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo=new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id",required=true)Integer ordersId) throws Exception {
        ModelAndView mv=new ModelAndView();
        Orders orders=ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("order-show");
        return mv;
    }

}
