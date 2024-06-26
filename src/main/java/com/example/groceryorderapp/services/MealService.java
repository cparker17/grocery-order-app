package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.model.RecipeWrapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealService {
    Meal saveMeal(Meal meal, RecipeWrapper recipeWrapper);

    void updateMeal(Meal meal);

    void deleteMeal(Long id) throws NoSuchMealException;

    Meal getMeal(Long id) throws NoSuchMealException;

    List<Meal> getAllMeals() throws NoSuchMealException;

    Meal getTonightsMeal() throws NoMealPlanException;
}
