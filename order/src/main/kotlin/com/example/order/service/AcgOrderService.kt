package com.example.order.service

import com.example.order.entity.AcgOrder
import com.example.order.http.CreateOrderRequest

interface AcgOrderService {

    fun createOrder(request: CreateOrderRequest): AcgOrder

}
