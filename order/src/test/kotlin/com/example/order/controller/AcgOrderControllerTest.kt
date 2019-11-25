package com.example.order.controller

import com.example.order.entity.AcgGoods
import com.example.order.http.CreateOrderRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureMockMvc
class AcgOrderControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun should_return_create_order_success_when_given_a_valid_order() {
        val iphone = AcgGoods(name = "iphone", price = BigDecimal.valueOf(7999), quantity = 10)
        val mac = AcgGoods(name = "mac", price = BigDecimal.valueOf(18600), quantity = 10)
        val createOrderRequest = CreateOrderRequest(listOf(iphone, mac))
        mockMvc.perform(post("/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(createOrderRequest)))
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.code").value(10000))
                .andExpect(jsonPath("$.message").value("create order success"))
    }
}
