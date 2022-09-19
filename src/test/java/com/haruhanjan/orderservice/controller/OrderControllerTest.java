package com.haruhanjan.orderservice.controller;

import com.haruhanjan.orderservice.docs.RestDocTestConfiguration;
import com.haruhanjan.orderservice.service.InternalWebService;
import com.haruhanjan.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(OrderController.class)
@Import(RestDocTestConfiguration.class)
@AutoConfigureRestDocs
class OrderControllerTest {

    @MockBean
    private OrderService orderService;
    @MockBean
    private InternalWebService internalWebService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() {
    }

    @Test
    void post() {
    }

    @Test
    void getOne() {
    }

    @Test
    void patchState() {
    }
}