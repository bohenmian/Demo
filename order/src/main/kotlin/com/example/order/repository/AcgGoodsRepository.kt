package com.example.order.repository

import com.example.order.entity.AcgGoods
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AcgGoodsRepository : JpaRepository<AcgGoods, Long> {

    fun getAcgGoodsByAcgOrderId(id: Long): List<AcgGoods>

}
