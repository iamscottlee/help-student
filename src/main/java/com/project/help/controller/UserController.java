package com.project.help.controller;

import com.project.help.dao.UserMapper;
import com.project.help.pojo.User;
import com.project.help.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserMapper usermapper;

    //注册暂未实现
    @RequestMapping(value = "/register")
    public String register() {
        return "user/register";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(@ModelAttribute("user") User user1, HttpSession session) {

//        String userName = (String) session.getAttribute("username");
//        if (StringUtils.isEmpty(userName)){
//            return "redirect:login/"
//        }
//

        //检测该用户是否已经存在
//        User user=userService.getUserByUserName(user1.getUser_name());
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(user1.getUserName());
        if (!usermapper.selectByExample(userExample).isEmpty()) {
            return "redirect:user/register";
        }
        return "redirect:user/login";
    }

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/user/login";
    }

    @RequestMapping(value = "/loginValidate", method = RequestMethod.POST)
    public String loginValidate(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpSession httpSession) {
        if (username == null || password == null)
            return "/user/login";
        else {
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUserNameEqualTo(username).andPasswordEqualTo(password);
            if (!usermapper.selectByExample(userExample).isEmpty()) {//如果能找到
                httpSession.setAttribute("username", username);
                return "redirect:/stu/student/stuList";
            } else {
                return "/user/login";

            }
        }

    }

    //登出未实现
    @RequestMapping(value = "/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("username");
        return "redirect:/user/login";
    }


}










