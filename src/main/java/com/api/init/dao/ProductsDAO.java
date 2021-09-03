package com.api.init.dao;

import com.api.init.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsDAO extends JpaRepository<Product,Long> {


}
