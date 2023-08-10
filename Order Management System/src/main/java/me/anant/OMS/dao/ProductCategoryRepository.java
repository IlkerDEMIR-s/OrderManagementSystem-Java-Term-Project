package me.anant.OMS.dao;

import org.springframework.data.repository.CrudRepository;

import me.anant.OMS.model.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

}
