package com.zlht.pbr.algorithm.wcmp.remote.client;

import com.zlht.pbr.algorithm.wcmp.remote.configuration.ManagementConfiguration;
import com.zlht.pbr.algorithm.wcmp.remote.configuration.WcServerConfiguration;
import com.zlht.pbr.algorithm.wcmp.remote.factory.RestTemplateFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 微信Api客户端
 * @author zi jian Wang
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WcServerClient {


    private String requestType;
    private Integer timeOut;
    private HttpHeaders headers;
    private HttpHeaders proxy;
    private WcServerConfiguration wcServerConfiguration;

    /**
     * 连通状态检查
     */
    public Boolean checkConnect() {
        return null;
    }

    /**
     * 发送请求
     */
    public ResponseEntity<String> sendRequest(String suffix, HttpMethod httpMethod, HttpEntity requestEntity) {
        return null;
    }

    /**
     * load header
     */
    public void setClientHeader(String headerName, String headerValue) {
        this.headers.set(headerName, headerValue);
    }

}
