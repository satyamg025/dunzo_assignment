package com.satyamg025.dunzo_assignment.interfaces;

import java.util.HashMap;
import java.util.Map;

// basic Drink entity, it has got name and ingredients
public class Drink {

	private String name;

	private Map<String, Ingredient> drinkIngredients;

	public Drink(String name) {
		this.name = name;
		this.drinkIngredients = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Ingredient> getDrinkIngredients() {
		return drinkIngredients;
	}

	public void setDrinkIngredients(Map<String, Ingredient> drinkIngredients) {
		this.drinkIngredients = drinkIngredients;
	}

	public void addIngredient(Ingredient ingredient) {
		this.drinkIngredients.put(ingredient.getName(), ingredient);
	}
}
