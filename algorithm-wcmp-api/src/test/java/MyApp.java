import com.zlht.pbr.algorithm.wcmp.remote.client.ExampleClient;
import feign.Feign;

import java.util.HashMap;
import java.util.Map;

public class MyApp {
    public static void main(String... args) {
        ExampleClient exampleClient = Feign.builder()
                .target(ExampleClient.class, "http://127.0.0.1:8080");
        String name = "root";
        String password = "123456";
        Map<String,Object> map =new HashMap<>();
        map.put("username",name);
        map.put("password",password);
        String loginStr = exampleClient.login(map);
        System.out.println(loginStr);
    }
}