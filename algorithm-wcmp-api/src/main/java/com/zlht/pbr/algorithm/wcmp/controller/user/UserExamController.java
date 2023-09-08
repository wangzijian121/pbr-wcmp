package com.zlht.pbr.algorithm.wcmp.controller.user;

import com.zlht.pbr.algorithm.wcmp.controller.base.BaseController;
import com.zlht.pbr.algorithm.wcmp.service.ExamServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zijian Wang
 */
@RestController
@RequestMapping("/wechat/user/exam")
public class UserExamController extends BaseController {


    @Autowired
    private ExamServiceI examServiceI;



}
