package com.xdj.robot.controller;


import com.xdj.robot.model.auto.Users;
import com.xdj.robot.service.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xdj
 * @since 2021-04-16
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {

    @Resource
    private IUsersService usersService;

//    @Value("${test.port}")
//    private String myvalue;

//    @NacosValue(value = "test:port:",autoRefreshed = true)
//    private String port;

    @GetMapping("/user")
    public Users getUsers() {
//        System.out.println(myvalue);

//        System.out.println(port);
        log.error("test logback");
        return usersService.getUsers();
    }

    // test httpclient
    public static void main(String[] args) throws URISyntaxException, IOException {
        // test httpclient
        URI uri = new URI("http://42.192.207.31:8090/users/user");
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept","application/json");
        connection.connect();
        if (200 == connection.getResponseCode()) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ( (line = bufferedReader.readLine()) != null) {
                sb.append(line);

            }
            System.out.println(sb.toString());
        }
    }
}
