package com.example.order.service.impl

import com.example.order.entity.AcgOrder
import com.example.order.http.CreateOrderRequest
import com.example.order.repository.AcgOrderRepository
import com.example.order.service.AcgOrderService
import org.springframework.stereotype.Service

@Service
class AcgOrderServiceImpl(private val acgOrderRepository: AcgOrderRepository) : AcgOrderService {

    override fun createOrder(request: CreateOrderRequest): AcgOrder {
        val order = AcgOrder(goods = request.goods)
        order.goods.forEach { good -> good.acgOrder = order }
        return acgOrderRepository.save(order)
    }
}
