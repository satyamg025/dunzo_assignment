package com.satyamg025.dunzo_assignment.interfaces;

// behavior of the Ingredients available in the coffee machine
public interface AbstractCoffeeMachineIngredient {

	// we can refill an ingredient
	public void refill(int addQuantity);

	// we can check whether there is sufficient quantity to prepare a particular
	// Beverage
	public boolean hasSufficientQuantity(int quantity);

	// add the Ingredient (quantity) while preparing a beverage. This quantity will
	// be subtracted from the quantity available in the coffee machine
	public void addIngredientToBeverage(int quantity);

	public void checkForLowQuantity();
}
