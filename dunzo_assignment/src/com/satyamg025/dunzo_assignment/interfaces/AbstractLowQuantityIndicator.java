package com.satyamg025.dunzo_assignment.interfaces;

// low quantity indicator
public interface AbstractLowQuantityIndicator {

	// add the ingredient having low quantity
	public void addIngredient(AbstractCoffeeMachineIngredient ingredient);

	// remove the ingredient if it gets refilled
	public void removeIngredient(AbstractCoffeeMachineIngredient ingredient);

	// display all the ingredients having low quantity
	public void display();
}
