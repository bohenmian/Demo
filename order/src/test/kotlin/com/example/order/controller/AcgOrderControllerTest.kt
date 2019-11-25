package com.example.order.controller

import com.example.order.entity.AcgGoods
import com.example.order.http.CreateOrderRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AcgOrderControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    lateinit var iphone: AcgGoods
    lateinit var mac: AcgGoods


    @BeforeEach
    fun setUp() {
        iphone = AcgGoods(name = "iphone", price = BigDecimal.valueOf(7999), quantity = 10)
        mac = AcgGoods(name = "mac", price = BigDecimal.valueOf(18600), quantity = 10)
    }

    @Test
    fun should_return_create_order_success_when_given_a_valid_order() {
        val createOrderRequest = CreateOrderRequest(listOf(iphone, mac))
        createOrder(createOrderRequest)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.code").value(10000))
                .andExpect(jsonPath("$.message").value("create order success"))
    }

    @Test
    fun should_return_save_order_when_create_order_success() {
        val createOrderRequest = CreateOrderRequest(listOf(iphone, mac))
        val savedOrder = createOrder(createOrderRequest)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.code").value(10000))
                .andExpect(jsonPath("$.message").value("create order success"))
                .andReturn()
        val location = savedOrder.response.getHeader("location")
        mockMvc.perform(get(location!!))
                .andExpect(status().isOk)
    }

    @Test
    fun should_return_save_goods_when_create_order_success() {
        val createOrderRequest = CreateOrderRequest(listOf(iphone, mac))
        val savedOrder = createOrder(createOrderRequest)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.code").value(10000))
                .andExpect(jsonPath("$.message").value("create order success"))
                .andReturn()
        val location = savedOrder.response.getHeader("location")
        mockMvc.perform(get("$location/goods"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("iphone"))
                .andExpect(jsonPath("$[1].name").value("mac"))

    }

    private fun createOrder(createOrderRequest: CreateOrderRequest): ResultActions {
        return mockMvc.perform(post("/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(createOrderRequest)))
    }
}
