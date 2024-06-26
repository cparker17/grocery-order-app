package com.example.groceryorderapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grocery_order")
public class GroceryOrder {

    @Id
    private Long id;

    @OneToMany
    private List<StoreItem> itemsToOrder;
}
