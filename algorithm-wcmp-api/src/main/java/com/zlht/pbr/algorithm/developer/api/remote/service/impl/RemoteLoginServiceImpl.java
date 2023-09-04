package com.zlht.pbr.algorithm.wcmp.api.remote.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pbr.algorithm.wcmp.api.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.wcmp.api.wcmp.service.SessionServiceI;
import com.zlht.pbr.algorithm.wcmp.api.remote.service.RemoteLoginServiceI;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.remote.client.ManagementClient;
import com.zlht.pbr.algorithm.wcmp.remote.factory.ManagementClientFactory;
import com.zlht.pbr.algorithm.wcmp.remote.model.RemoteResult;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@Service
public class RemoteLoginServiceImpl extends BaseServiceImpl implements RemoteLoginServiceI {


    @Autowired
    private ManagementClientFactory managementClientFactory;

    @Autowired
    private SessionServiceI sessionServiceI;

    @Override
    public boolean checkConnect() {
        return managementClientFactory.getManagementClient().checkConnect();
    }


    @Override
    public Result login(String username, String password, String ip) {

        ManagementClient managementClient = managementClientFactory.getManagementClient();
        managementClient.setClientHeader("X-Real-IP", ip);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, managementClient.getHeaders());
        ResponseEntity<String> responseEntity = managementClient.sendRequest("wcmp/login", HttpMethod.POST, requestEntity);

        ObjectMapper objectMapper = new ObjectMapper();
        RemoteResult<Map<String, Object>> remoteResult = null;
        try {
            remoteResult = objectMapper.readValue(responseEntity.getBody(), RemoteResult.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Result<Map<String, Object>> result = new Result(remoteResult.getCode(), remoteResult.getMsg(), remoteResult.getData());

        if (result.getCode() == Status.SUCCESS.getCode()) {
            String sessionId = result.getData().get("sessionId").toString();
            int userId = Integer.parseInt(result.getData().get("id").toString());
            sessionServiceI.createSession(userId, ip, sessionId);
        }
        return result;
    }

    @Override
    public Result logout(String ip, int useId, MultiValueMap<String, String> values) {
        ManagementClient managementClient = managementClientFactory.getManagementClient();
        managementClient.setClientHeader("X-Real-IP", ip);
        loadForManagementClient(managementClient, values);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(new LinkedMultiValueMap<>(), managementClient.getHeaders());
        ResponseEntity<String> responseEntity = managementClient.sendRequest("wcmp/logout", HttpMethod.POST, requestEntity);
        ObjectMapper objectMapper = new ObjectMapper();
        RemoteResult<Map<String, Object>> remoteResult = null;
        sessionServiceI.signOut(ip, useId);
        try {
            remoteResult = objectMapper.readValue(responseEntity.getBody(), RemoteResult.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Result<Map<String, Object>> result = new Result(remoteResult.getCode(), remoteResult.getMsg(), remoteResult.getData());
        return result;
    }

}
