package com.example.order.service.impl

import com.example.order.entity.AcgGoods
import com.example.order.entity.AcgOrder
import com.example.order.http.CreateOrderRequest
import com.example.order.repository.AcgGoodsRepository
import com.example.order.repository.AcgOrderRepository
import com.example.order.service.AcgOrderService
import org.springframework.stereotype.Service

@Service
class AcgOrderServiceImpl(val acgOrderRepository: AcgOrderRepository, val acgGoodsRepository: AcgGoodsRepository) : AcgOrderService {

    override fun createOrder(request: CreateOrderRequest): AcgOrder {
        val order = AcgOrder(goods = request.goods)
        order.goods.forEach { good -> good.acgOrder = order }
        return acgOrderRepository.save(order)
    }

    override fun getOrder(id: Long): AcgOrder {
        return acgOrderRepository.findById(id).get()
    }

    override fun getGoods(id: Long): List<AcgGoods> {
        return acgGoodsRepository.getAcgGoodsByAcgOrderId(id)
    }
}
