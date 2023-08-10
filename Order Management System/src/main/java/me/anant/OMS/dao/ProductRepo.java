package me.anant.OMS.dao;

import org.springframework.data.repository.CrudRepository;

import me.anant.OMS.model.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {

}
