package MindStore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/test")
    public void test() {
        System.out.println("hello");
    }
}
//172.22.128.1
//        10.21.226.130