package me.anant.OMS.dao;

import org.springframework.data.repository.CrudRepository;

import me.anant.OMS.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
