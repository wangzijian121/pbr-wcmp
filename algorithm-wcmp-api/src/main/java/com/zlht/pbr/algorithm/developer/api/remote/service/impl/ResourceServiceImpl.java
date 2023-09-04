package com.zlht.pbr.algorithm.wcmp.api.remote.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pbr.algorithm.wcmp.api.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.wcmp.api.remote.service.ResourceServiceI;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.remote.client.ManagementClient;
import com.zlht.pbr.algorithm.wcmp.remote.factory.ManagementClientFactory;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * @author zi jian Wang
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceServiceI {
    private static final Logger logger = LogManager.getLogger(ResourceServiceImpl.class);
    @Autowired
    private ManagementClientFactory managementClientFactory;

    @SneakyThrows
    @Override
    public Result createResource(int userId, MultipartFile file, MultiValueMap<String, String> values) {
        Result result = null;
        if (!canOperator(userId)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ManagementClient managementClient = managementClientFactory.getManagementClient();
        values.add("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
        loadForManagementClient(managementClient, values);

        // 将MultipartFile转换为Resource
        Resource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);
        if (!managementClient.checkConnect()) {
            logger.error("management disconnect!");
            result.setCode(400);
            result.setMsg("management disconnect!");
            result.setData(null);
            return result;
        }
        ResponseEntity<String> responseEntity = managementClient.sendRequest("wcmp/upload", HttpMethod.POST,
                new HttpEntity<>(body, managementClient.getHeaders()));
        try {
            result = objectMapper.readValue(responseEntity.getBody(), Result.class);
        } catch (IOException e) {
            logger.error("response 解析失败！");
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public ResponseEntity downloadResource(int userId, String uuid, MultiValueMap<String, String> values) {
        if (!canOperator(userId)) {
            return (ResponseEntity) ResponseEntity.status(401);
        }
        ManagementClient managementClient = managementClientFactory.getManagementClient();
        loadForManagementClient(managementClient, values);


        if (!managementClient.checkConnect()) {
            logger.error("management disconnect!");
            return (ResponseEntity) ResponseEntity.status(401);
        }
        String suffix = UriComponentsBuilder.fromPath("wcmp/download")
                .queryParam("uuid", uuid)
                .build()
                .toUriString();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(null, managementClient.getHeaders());
        ResponseEntity response = managementClient.sendRequest(suffix, HttpMethod.GET, requestEntity);
        return response;
    }

    @Override
    public Result deleteResource(int userId, String uuid, MultiValueMap<String, String> values) {
        Result result = null;
        if (!canOperator(userId)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ManagementClient managementClient = managementClientFactory.getManagementClient();
        loadForManagementClient(managementClient, values);
        String suffix = UriComponentsBuilder.fromPath("wcmp/delete")
                .queryParam("uuid", uuid)
                .build()
                .toUriString();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(new LinkedMultiValueMap<>(), managementClient.getHeaders());
        ResponseEntity<String> responseEntity = managementClient.sendRequest(suffix, HttpMethod.DELETE, requestEntity);
        try {
            result = objectMapper.readValue(responseEntity.getBody(), Result.class);
        } catch (IOException e) {
            logger.error("response 解析失败！");
            throw new RuntimeException(e);
        }
        return result;
    }

}