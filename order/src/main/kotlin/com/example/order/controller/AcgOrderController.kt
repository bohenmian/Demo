package com.example.order.controller

import com.example.order.entity.AcgGoods
import com.example.order.entity.AcgOrder
import com.example.order.enums.MessageEnum
import com.example.order.http.CreateOrderRequest
import com.example.order.http.SuccessMessage
import com.example.order.service.AcgOrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/order")
class AcgOrderController(val acgOrderService: AcgOrderService) {

    @PostMapping("/create")
    fun createOrder(@RequestBody @Valid request: CreateOrderRequest): ResponseEntity<SuccessMessage> {
        val savedOrder = acgOrderService.createOrder(request)
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("location", "/order/${savedOrder.id}")
                .body(SuccessMessage(MessageEnum.CREATE_ORDER_SUCCESS.code, MessageEnum.CREATE_ORDER_SUCCESS.message))
    }

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Long): ResponseEntity<AcgOrder> =
            ResponseEntity.status(HttpStatus.OK)
                    .body(acgOrderService.getOrder(id))


    @GetMapping("/{id}/goods")
    fun getGoods(@PathVariable id: Long): ResponseEntity<List<AcgGoods>> =
            ResponseEntity.ok(acgOrderService.getGoods(id))
}
