package com.zlht.pbr.algorithm.wcmp.service;

import com.zlht.pbr.algorithm.wcmp.dao.entity.Course;
import com.zlht.pbr.algorithm.wcmp.utils.PageInfo;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * 课程服务接口
 *
 * @author zijian Wang
 */
public interface CourseServiceI extends BaseServiceI {


    /**
     * 查询课程
     *
     * @param linkCode
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<Course>> queryCourseList(String linkCode, int currentPage, int pageSize);


    /**
     * 创建课程
     *
     * @param exam
     * @return
     */

    Map<String, Object> createCourse(String linkCode, Course exam);

    /**
     * 更新课程
     *
     * @param linkCode
     * @param id
     * @param exam
     * @return
     */
    Map<String, Object> updateCourse(String linkCode, int id, Course exam);

    /**
     * 删除课程
     *
     * @param
     * @param linkCode
     * @param id
     * @return
     */
    Map<String, Object> deleteCourse(String linkCode, int id);


    /**
     * 通过课程ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkCourseExistById(String linkCode,int id);
}
