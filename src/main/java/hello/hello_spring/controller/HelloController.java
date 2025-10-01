package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    static class Hello {
      private String name;

      public String getName() {
        return this.name;
      }

      public void setName(String name) {
        this.name = name;
      }
    }


    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // data라는 key에 hello!!를 추가
        return "hello"; // 파일명 : hello.html을 찾아서 렌더링
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { 
      model.addAttribute("name", name);
      return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
      return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
      Hello hello = new Hello();
      hello.setName(name);
      return hello;
    }
}