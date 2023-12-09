package com.es.core.dao;

public interface StockDao {
    Integer availableStock(Long phoneId);
    void reserve(Long phoneId, Long quantity);
}
