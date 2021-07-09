package com.satyamg025.dunzo_assignment.main;

import java.util.HashMap;
import java.util.Map;

import com.satyamg025.dunzo_assignment.interfaces.AbstractCoffeeMachineIngredient;

// singleton class having all the ingredients available in the coffee machine
// singleton because it will be shared by all the drinks while their preparation
public class AllAvailableIngredients {

	private static AllAvailableIngredients instance;

	private Map<String, AbstractCoffeeMachineIngredient> ingredients;

	private AllAvailableIngredients() {
		this.ingredients = new HashMap<>();
	}

	public static AllAvailableIngredients getInstance() {

		if (instance == null)
			instance = new AllAvailableIngredients();

		return instance;
	}

	public void addIngredient(String name, AbstractCoffeeMachineIngredient ingredient) {

		this.ingredients.put(name, ingredient);
	}
	
	public void removeIngredient(String name) {

		this.ingredients.remove(name);
	}

	public Map<String, AbstractCoffeeMachineIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Map<String, AbstractCoffeeMachineIngredient> ingredients) {
		this.ingredients = ingredients;
	}

}
