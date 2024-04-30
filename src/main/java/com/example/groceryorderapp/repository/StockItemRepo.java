package com.example.groceryorderapp.repository;

import com.example.groceryorderapp.domain.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepo extends JpaRepository<StockItem, Long> {
}
