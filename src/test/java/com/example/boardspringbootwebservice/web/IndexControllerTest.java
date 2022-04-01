package com.example.boardspringbootwebservice.web;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebMvcTest(controllers = IndexController.class)
class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    @DisplayName("IndexController_TEST")
    void IndexControllerTest1() {
        // given && when
        String body = restTemplate.getForObject("/", String.class);

        // then
        assertThat(body).contains("스프링");
    }

//    @Test
//    @DisplayName("IndexController_TEST2")
//    void IndexControllerTest2() throws Exception {
//        // given && when && then
//        mockMvc.perform(get("/")
//                        .accept(MediaType.TEXT_HTML))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"));
//
//    }

}