package com.yxr.controller;

import com.yxr.dto.QuestionDTO;
import com.yxr.mapper.QuestionMapper;
import com.yxr.mapper.UserMapper;
import com.yxr.model.User;
import com.yxr.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length>0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if(name.equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.getUserByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionDTOList = indexService.queryQuestionDTO();
        model.addAttribute("questionDTOList",questionDTOList);
        return "index";
    }


}
