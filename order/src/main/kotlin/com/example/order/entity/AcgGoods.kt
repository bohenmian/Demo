package com.example.order.entity


import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "acg_goods")
data class AcgGoods(

        var name: String = "",

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "acg_order_id")
        var acgOrder: AcgOrder? = null,

        var price: BigDecimal = BigDecimal.ONE,

        var quantity: Int = 0,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)
