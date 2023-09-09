package com.zlht.pbr.algorithm.wcmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.wcmp.dao.entity.Course;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.CourseMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.security.AuthLinkCodeServiceI;
import com.zlht.pbr.algorithm.wcmp.service.CourseServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 考试服务
 *
 * @author zijian Wang
 */
@Service
public class CourseServiceImpl extends BaseServiceImpl implements CourseServiceI {

    private static final Logger logger = LogManager.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private AuthLinkCodeServiceI authLinkCodeServiceI;

    @Override
    public Result<PageInfo<Course>> queryCourseList(String linkCode, int currentPage, int pageSize) {

        Result result = new Result();
        if (!authLinkCodeServiceI.checkLinkCodeValidity(linkCode)) {
            result.setMsg("linkCode错误!！");
            result.setCode(400);
            return result;
        }
        Page<Course> page = new Page<>(currentPage, pageSize);
        QueryWrapper<Course> wapper = new QueryWrapper<Course>();
        wapper.eq("link_code", linkCode);
        Page<Course> coursePage = courseMapper.selectPage(page, wapper);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(coursePage.getRecords());
        result.setCode(200);
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(pageInfo);
        return result;
    }

    @Override
    public Map<String, Object> createCourse(String linkCode, Course course) {
        Map<String, Object> map = new HashMap<>(3);
        if (!authLinkCodeServiceI.checkLinkCodeValidity(course.getLinkCode())) {
            putMsg(map, 400, "linkCode错误!！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("link_code", linkCode);
        queryWrapper.eq("name", course.getName());
        //exist?
        if (courseMapper.exists(queryWrapper)) {
            putMsg(map, 400, "课程已存在!");
            return map;
        }
        try {
            course.setLinkCode(linkCode);
            courseMapper.insert(course);
            putMsg(map, Status.SUCCESS.getCode(), "创建课程成功！");
        } catch (Exception e) {
            String errMsg = "创建课程失败";
            logger.error("createCourse() method .message={}, course={}", errMsg, course, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public Map<String, Object> updateCourse(String linkCode, int id, Course course) {
        Map<String, Object> map = new HashMap<>(3);

        if (!checkCourseExistById(linkCode, id)) {
            putMsg(map, 400, "所更新的课程ID不存在!");
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", course.getName());
        checkWrapper.ne("id", id);
        //exist?
        if (courseMapper.exists(checkWrapper)) {
            putMsg(map, 400, "课程名已存在!");
            return map;
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            putMsg(map, Status.SUCCESS.getCode(), "更新课程成功！");
            courseMapper.update(course, queryWrapper);
        } catch (Exception e) {
            String errMsg = "更新课程失败";
            logger.error("updateCourse() method .message={}, course={}", errMsg, course, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteCourse(String linkCode, int id) {
        Map<String, Object> map = new HashMap<>(3);
        if (!authLinkCodeServiceI.checkLinkCodeValidity(linkCode)) {
            putMsg(map, 400, "linkCode错误!！");
            return map;
        }
        if (!checkCourseExistById(linkCode, id)) {
            putMsg(map, 400, "所删除的课程ID不存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("link_code", linkCode);
        queryWrapper.eq("id", id);
        try {
            courseMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除课程成功！");
        } catch (Exception e) {
            String errMsg = "删除课程失败";
            logger.error("deleteCourse() method .message={}, id={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public boolean checkCourseExistById(String linkCode, int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("link_code", linkCode);
        queryWrapper.eq("id", id);
        return courseMapper.exists(queryWrapper);
    }
}
