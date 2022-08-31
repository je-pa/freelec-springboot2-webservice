package com.jojoldu.book.freelecspringboot2webservice.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
//스프링 테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션
//@Controller, @ControllerAdvice 등 사용 가능(@Service, @Component, @Repository 는 사용 불가능)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;
    //웹 API 테스트할 때 사용
    //스프링 MVC 테스트의 시작점
    //MockMvc 클래스를 통해 HTTP GET, POST등에 대한 API 테스트 가능

    @Test
    public void helloReturn() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDtoReturn() throws Exception {
        String name = "hello";
        int amount = 10000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))) // String만 허용되기 때문에 문자열로 변경 필요
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
