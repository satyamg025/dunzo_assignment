package com.satyamg025.dunzo_assignment.ingredient;

import com.satyamg025.dunzo_assignment.interfaces.Ingredient;

// ingredient which is used to prepare a Beverage. No additional behavior, hence simply extending the Ingredient class
public class DrinkIngredient extends Ingredient {

	public DrinkIngredient(String name, int quantity) {
		super(name, quantity);
	}
}
