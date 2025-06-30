package com.salman.ecommerce.service;


import com.salman.ecommerce.dto.ProductDto;
import com.salman.ecommerce.dto.ProductRequestDto;
import com.salman.ecommerce.dto.ProductsResponseDto;
import com.salman.ecommerce.dto.ResponseDto;
import com.salman.ecommerce.entity.ProductCategory;

public interface ProductService {

    ProductsResponseDto getProducts();
    ProductDto getProductById(long productId);
    ProductsResponseDto getProductsByCategory(ProductCategory category);
    ProductsResponseDto getProductsBySellerId(long sellerId);
    ProductDto addProduct(ProductRequestDto productRequestDto);
    ProductDto updateProduct(long productId, ProductRequestDto productRequestDto);
    ResponseDto deleteProductById(long productId);
}
