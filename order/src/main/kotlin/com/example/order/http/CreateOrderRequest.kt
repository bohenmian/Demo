package com.example.order.http

import com.example.order.entity.AcgGoods
import javax.validation.constraints.Size

data class CreateOrderRequest(

        @Size(min = 1)
        val goods: List<AcgGoods>
)
