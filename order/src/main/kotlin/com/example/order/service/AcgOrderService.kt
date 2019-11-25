package com.example.order.service

import com.example.order.http.CreateOrderRequest
import com.example.order.http.SuccessMessage

interface AcgOrderService {

    fun createOrder(request: CreateOrderRequest): SuccessMessage

}
