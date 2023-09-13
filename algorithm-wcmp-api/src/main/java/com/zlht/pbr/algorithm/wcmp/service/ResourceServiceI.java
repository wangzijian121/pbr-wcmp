package com.zlht.pbr.algorithm.wcmp.service;

import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface ResourceServiceI {
    /**
     * 上传
     *
     * @param file
     * @return
     */
    Map<String, Object> createResource( MultipartFile file);

    /**
     * 删除文件
     *
     * @param resourceId
     * @return
     */
    Map<String, Object> deleteResource(int resourceId);


    /**
     * 下载文件
     *
     * @param resourceId
     * @return
     */
    ResponseEntity downloadResource(int resourceId);

    /**
     * 获取nginx链接
     * @param resourceId
     * @return
     */
    Map<String, Object> getUrlByResourceId(int resourceId);

    /**
     * 校验资源类型
     *
     * @param type
     * @return
     */
    boolean checkFileType(String type);

    /**
     * 校验资源大小
     *
     * @param size
     * @return
     */
    boolean checkFileSize(long size);

    /**
     * 判断资源元数据 是否存在
     *
     * @param fullName
     * @return
     */
    boolean resourceExist(String fullName);

}
