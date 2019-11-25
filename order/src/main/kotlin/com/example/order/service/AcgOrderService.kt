package com.example.order.service

import com.example.order.entity.AcgGoods
import com.example.order.entity.AcgOrder
import com.example.order.http.CreateOrderRequest

interface AcgOrderService {

    fun createOrder(request: CreateOrderRequest): AcgOrder

    fun getOrder(id: Long): AcgOrder

    fun getGoods(id: Long): List<AcgGoods>

}
