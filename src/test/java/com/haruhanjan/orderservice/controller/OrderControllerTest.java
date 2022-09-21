package com.haruhanjan.orderservice.controller;

import com.haruhanjan.orderservice.docs.RestDocTestConfiguration;
import com.haruhanjan.orderservice.dto.OrderResponseDto;
import com.haruhanjan.orderservice.dto.SlimOrderResponseDto;
import com.haruhanjan.orderservice.sample.SampleGenerator;
import com.haruhanjan.orderservice.service.InternalWebService;
import com.haruhanjan.orderservice.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(OrderController.class)
@Import(RestDocTestConfiguration.class)
@AutoConfigureRestDocs
class OrderControllerTest {

    private SampleGenerator sg = new SampleGenerator();

    @MockBean
    private OrderService orderService;
    @MockBean
    private InternalWebService internalWebService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("주문 전체 조회 테스트")
    void getAllTest() throws Exception {
        List<SlimOrderResponseDto> list = new ArrayList<>();
        for(int i=0;i<4;i++) {
            list.add(sg.slimOrderRequestDto());
        }
        given(orderService.getAll(anyLong(), any())).willReturn(list);

        ResultActions result = this.mockMvc.perform(get("/api/order")
                .param("page", "0").param("size", "3")
                .cookie(new Cookie("access_token", "value"))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andDo(document("order-get-all",
                        responseFields(fieldWithPath("[]").description("List of User's Slim Order"))
                                .andWithPrefix("[].", slimOrderFields))
                );
    }
    @Test
    @DisplayName("주문 단일 조회 테스트")
    void getOneTest() throws Exception{
        OrderResponseDto dto = sg.orderResponseDto();
        when(orderService.getOne(anyLong(),anyLong())).thenReturn(dto);

        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/order/{id}", anyLong())
                .param("page", "0").param("size", "3")
                .cookie(new Cookie("access_token", "value"))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andDo(document("order-get-one",
                        pathParameters(
                                parameterWithName("id").description("주문 고유 ID")
                        ),
                        responseFields(orderDetailFields).andWithPrefix("orderItemList[].", itemFields)
                ));
    }

    @Test
    @DisplayName("주문 저장 테스트")
    void postTest() throws Exception {
        String json = sg.createOrderRequestJson();
        OrderResponseDto responseDto = sg.orderResponseDto();
        when(orderService.save(anyLong(),any())).thenReturn(responseDto);

        // content에서 json 파싱 문제..
        ResultActions result = this.mockMvc.perform(post("/api/order")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .cookie(new Cookie("access_token", "value"))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated())
                .andDo(document("order-post",
                        requestFields(orderRequestFields).andWithPrefix("orderItemList[].",itemRequestFields)
                ));
    }


    @Test
    @DisplayName("주문 상태 수정 테스트")
    void patchStateTest() throws Exception {
        String json = sg.patchOrderStateJson();

        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.patch("/api/order/{id}",anyInt())
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .cookie(new Cookie("access_token", "value"))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andDo(document("order-patch-state",
                        pathParameters(
                                parameterWithName("id").description("주문 고유 ID")
                        ),
                        requestFields(fieldWithPath("orderState")
                                .description("COMPLETE_PAYMENT")
                                .description("DELIVERY_START")
                                .description("DELIVERY_FINISH")
                                .description("CANCEL"))
                ));
    }

    FieldDescriptor[] slimOrderFields = new FieldDescriptor[]{
            fieldWithPath("state").description("현재 주문 상태").type(JsonFieldType.STRING),
            fieldWithPath("totalPrice").description("주문한 총 금액").type(JsonFieldType.NUMBER),
            fieldWithPath("orderDate").description("주문 날짜").type(JsonFieldType.STRING),
            fieldWithPath("alcoholNameList[]").description("주문한 술 이름 리스트").type(JsonFieldType.ARRAY)
    };

    FieldDescriptor[] orderDetailFields = new FieldDescriptor[]{
            fieldWithPath("state").description("현재 주문 상태").type(JsonFieldType.STRING),
            fieldWithPath("orderDate").description("주문 날짜").type(JsonFieldType.STRING),
            fieldWithPath("totalPrice").description("주문한 총 금액").type(JsonFieldType.NUMBER),
            fieldWithPath("orderItemList[]").description("주문한 아이템 리스트")
    };

    FieldDescriptor[] itemFields = new FieldDescriptor[]{
            fieldWithPath("alcoholId").description("술 고유 아이디").type(JsonFieldType.NUMBER),
            fieldWithPath("alcoholName").description("술 이름").type(JsonFieldType.STRING),
            fieldWithPath("quantity").description("주문한 수량").type(JsonFieldType.NUMBER),
            fieldWithPath("price").description("술 가격").type(JsonFieldType.NUMBER)
    };

    FieldDescriptor[] orderRequestFields = new FieldDescriptor[]{
            fieldWithPath("orderDate").description("주문한 날짜").type(JsonFieldType.STRING),
            fieldWithPath("orderItemList[]").description("주문한 술 리스트").type(JsonFieldType.ARRAY)
    };

    FieldDescriptor[] itemRequestFields = new FieldDescriptor[]{
            fieldWithPath("alcoholId").description("술 고유 아이디").type(JsonFieldType.NUMBER),
            fieldWithPath("quantity").description("주문한 수량").type(JsonFieldType.NUMBER)
    };
}