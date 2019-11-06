package com.yxr.service;

import com.yxr.dto.QuestionDTO;
import com.yxr.mapper.QuestionMapper;
import com.yxr.mapper.UserMapper;
import com.yxr.model.Question;
import com.yxr.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;


    public List<QuestionDTO> queryQuestionDTO() {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> qutions = questionMapper.select();
        if(qutions != null && qutions.size()>0){
            for (Question qution : qutions) {
                QuestionDTO questionDTO = new QuestionDTO();
                User user = userMapper.findById(qution.getCreator());
                if (user != null){
                    questionDTO.setUser(user);
                }
                BeanUtils.copyProperties(qution, questionDTO);
                questionDTOList.add(questionDTO);
            }
        }
        return questionDTOList;
    }
}
