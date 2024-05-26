package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.*;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.exceptions.NoSuchGroceryOrderException;
import com.example.groceryorderapp.model.GroceryOrderWrapper;
import com.example.groceryorderapp.model.ItemToOrder;
import com.example.groceryorderapp.repositories.GroceryOrderRepo;
import com.example.groceryorderapp.repositories.MealPlanRepo;
import com.example.groceryorderapp.repositories.StockItemRepo;
import com.example.groceryorderapp.repositories.StoreItemRepo;
import com.example.groceryorderapp.services.GroceryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroceryOrderServiceImpl implements GroceryOrderService {
    @Autowired
    GroceryOrderRepo groceryOrderRepo;

    @Autowired
    StockItemRepo stockItemRepo;

    @Autowired
    MealPlanRepo mealPlanRepo;

    @Autowired
    StoreItemRepo storeItemRepo;

    @Override
    public GroceryOrder getGroceryOrder() throws NoSuchGroceryOrderException {
        return groceryOrderRepo.findById(1L)
                .orElseThrow(() ->  new NoSuchGroceryOrderException("A grocery order has not been created yet."));
    }

    @Override
    public List<ItemToOrder> getGroceryItemsToOrder() throws NoMealPlanException {
        MealPlan mealPlan = mealPlanRepo.findById(1L)
                .orElseThrow(() -> new NoMealPlanException("No meal plan created."));
        List<ItemToOrder> groceryItems = new ArrayList<>();
        for (Meal meal : mealPlan.getMeals()) {
            for (Ingredient ingredient : meal.getIngredients()) {
                groceryItems.add(new ItemToOrder(ingredient.getLocation(), ingredient.getDescription()));
            }
        }
        for (StockItem stockItem : stockItemRepo.findAll()) {
            groceryItems.add(new ItemToOrder(stockItem.getLocation(), stockItem.getName()));
        }
        return groceryItems
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public GroceryOrder createGroceryOrder(GroceryOrderWrapper groceryOrderWrapper) {
        groceryOrderRepo.deleteAll();

        List<StoreItem> itemsToOrder = new ArrayList<>();
        for (String itemToOrder : groceryOrderWrapper.getItemsToOrder()) {
            itemsToOrder.add(new StoreItem(itemToOrder));
        }
        GroceryOrder groceryOrderToPersist = new GroceryOrder();
        groceryOrderToPersist.setId(1L);
        groceryOrderToPersist.setItemsToOrder(storeItemRepo.saveAll(itemsToOrder
                .stream()
                .distinct()
                .collect(Collectors.toList())));
        return groceryOrderRepo.save(groceryOrderToPersist);
    }

    @Override
    public GroceryOrder updateGroceryOrder(GroceryOrder groceryOrder) {
        groceryOrderRepo.deleteAll();
        groceryOrder.setId(1L);
        return groceryOrderRepo.save(groceryOrder);
    }
}
