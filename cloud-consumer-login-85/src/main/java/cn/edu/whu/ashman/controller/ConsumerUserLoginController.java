package cn.edu.whu.ashman.controller;

import cn.edu.whu.ashman.entities.CommonResult;
import cn.edu.whu.ashman.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhuyuhan
 * @date 2020-07-11 19:52
 */
@RestController
public class ConsumerUserLoginController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/login/sendMessageCode/{phoneNumber}")
    public CommonResult getMessageCode(@PathVariable("phoneNumber") String phoneNumber){
        System.out.println("客户端"+"手机号码为："+phoneNumber);
        return restTemplate.getForObject(serverURL+"/login/sendMessageCode/"+phoneNumber,CommonResult.class);
    }

    /**
     * 手机号注册按钮提交
     * @param user
     * @param code
     * @return
     */
    @GetMapping("/consumer/login/createByPhone/{code}")
    public CommonResult createUserByPhone(User user, @PathVariable("code") int code){
        //路径参数这样配置
        return restTemplate.postForObject(serverURL+"/login/createByPhone/"+code,user,CommonResult.class);
    }

    /**
     * 微信授权注册
     * @param userJson
     * @return
     */
    @GetMapping("/consumer/login/createByWeChat")
    public CommonResult createUserByWeChat(String userJson){
        //http请求体这样传
        return restTemplate.postForObject(serverURL+"/login/createByWeChat",userJson,CommonResult.class);
    }

    /**
     * 修改密码可能会用到
     * @param user
     * @return
     */
    @GetMapping("/consumer/login/update")
    public CommonResult updateUser(User user){
        return restTemplate.postForObject(serverURL+"/login/update",user,CommonResult.class);
    }

    /**
     * 按用户名查询，可用于测试
     * @param username
     * @return
     */
    @GetMapping("/consumer/login/select/{username}")
    public CommonResult selectUser(@PathVariable("username") String username){
        return restTemplate.getForObject(serverURL+"/login/select/"+username,CommonResult.class);
    }

    /**
     * 登录
     * @return
     */
    @GetMapping("/consumer/login/signIn")
    public CommonResult signIn(User user){
       /* Map<String,String> request = new HashMap<>();
        request.put("tel",tel);
        request.put("password",password);*/
        return restTemplate.postForObject(serverURL+"/login/signIn",user,CommonResult.class);
    }
}