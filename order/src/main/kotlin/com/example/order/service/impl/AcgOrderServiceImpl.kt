package com.example.order.service.impl

import com.example.order.entity.AcgOrder
import com.example.order.enums.MessageEnum
import com.example.order.http.CreateOrderRequest
import com.example.order.http.SuccessMessage
import com.example.order.repository.AcgOrderRepository
import com.example.order.service.AcgOrderService
import org.springframework.stereotype.Service

@Service
class AcgOrderServiceImpl(private val acgOrderRepository: AcgOrderRepository) : AcgOrderService {

    override fun createOrder(request: CreateOrderRequest): SuccessMessage {
        val order = AcgOrder(goods = request.goods)
        order.goods.forEach { good -> good.acgOrder = order }
        acgOrderRepository.save(order)
        return SuccessMessage(MessageEnum.CREATE_ORDER_SUCCESS.code, MessageEnum.CREATE_ORDER_SUCCESS.message)
    }
}
