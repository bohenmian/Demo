package com.example.order.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name = "acg_order")
data class AcgOrder(
        var userId: Long = 0,

        @OneToMany(mappedBy = "acgOrder", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var goods: List<AcgGoods> = emptyList(),

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)
