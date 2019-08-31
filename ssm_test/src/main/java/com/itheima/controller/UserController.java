package com.itheima.controller;

import com.itheima.domain.Result;
import com.itheima.domain.User;
import com.itheima.service.ResultService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResultService resultService;
    /**
     * 登陆
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, HttpSession session){
        User loginUser = userService.loginUser(user);
        if (loginUser == null){
            return "error";
        }
        session.setAttribute("user",loginUser);
        return "redirect:/user/index";
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView modelAndView,HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Result> resultList = resultService.findResultByuserId(user);
        modelAndView.addObject("resultList",resultList);
        modelAndView.setViewName("user_index");
        return modelAndView;
    }
    @RequestMapping("/lucky")
    @ResponseBody
    public Object lucky(String prize,HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Result> resultList = resultService.findResultByuserId(user);
        if(resultList.size() >= 3){
            return "fail";
        }
        Result result = new Result();
        result.setPrize(prize);
        result.setUserId(user.getId());
        resultService.save(result);
        return "success";
    }
}
