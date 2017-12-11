package com.kaishengit.service;

import com.kaishengit.entity.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ProductService {
    /**
     * 新增商品,
     * @param product 商品信息
     * @param inputStream 商品图片
     */
    void addNewProduct(Product product, InputStream inputStream) throws RuntimeException;

    /**
     * 查询所有商品
     * @return
     */
    List<Product> findAll();

    /**
     * 根据ID查找用户
     * @param id
     * @return
     */
    Product findById(Integer id);

    void killProduct(Integer id);
}
