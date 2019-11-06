package com.yxr.controller;

import com.yxr.mapper.QuestionMapper;
import com.yxr.mapper.UserMapper;
import com.yxr.model.Question;
import com.yxr.model.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    //private static final Logger logger = LoggerFactory.getLogger(PublishController.class);
    public static Logger logger  =  Logger.getLogger(PublishController.class);

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        logger.info("we are come in ........");
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title == null || title == "" ) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description =="") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag =="") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length>0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if(name.equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.getUserByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if(user == null){
            model.addAttribute("error","用户不存在");
            return "publish";
        }

        //ctrl + alt + v : 提取参数
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.insert(question);
        return "redirect:/";
    }


    /**
     *  swagger test function()
     * @return
     */
    @GetMapping("/{name}")
    public String test(@PathVariable("name") String name){
        //return JSON.parseObject("{'success':'0','name':' "+name+"'}");
        return "{'success':'0','name':' "+name+"'}";
    }
}
