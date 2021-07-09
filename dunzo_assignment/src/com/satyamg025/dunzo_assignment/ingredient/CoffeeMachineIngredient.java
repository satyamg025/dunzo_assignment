package com.satyamg025.dunzo_assignment.ingredient;

import com.satyamg025.dunzo_assignment.exception.IngredientQuantityNotSufficientException;
import com.satyamg025.dunzo_assignment.interfaces.AbstractCoffeeMachineIngredient;
import com.satyamg025.dunzo_assignment.interfaces.AbstractLowQuantityIndicator;
import com.satyamg025.dunzo_assignment.interfaces.Ingredient;

// ingredient available in the coffee machine. It will a ingredient and will also implement the AbstractCoffeeMachineIngredient behaviors
public class CoffeeMachineIngredient extends Ingredient implements AbstractCoffeeMachineIngredient {

	// low quantity indicator which will be notified if coffee machine ingredient is
	// low on quantity
	AbstractLowQuantityIndicator lowQuantityIndicator;

	int lowerLimit = 10;

	public CoffeeMachineIngredient(String name, int availableQuantity,
			AbstractLowQuantityIndicator lowQuantityIndicator) {
		super(name, availableQuantity);

		this.lowQuantityIndicator = lowQuantityIndicator;
		checkForLowQuantity();
	}

	// refilling the ingredient
	@Override
	public void refill(int addQuantity) {

		// synchronized because multiple beverages can be served parallely and they may
		// have same ingredient to process
		synchronized (this) {
			this.setQuantity(this.getQuantity() + addQuantity);
			// checking for low quantity
			checkForLowQuantity();
		}
	}

	// checking if quantity is sufficient to prepare a Beverage
	@Override
	public boolean hasSufficientQuantity(int quantity) {

		// if requestedQuantity is sufficient
		if (quantity <= this.getQuantity())
			return true;

		// checking for low quantity
		checkForLowQuantity();

		return false;
	}

	// add ingredient while processing the beverage
	@Override
	public void addIngredientToBeverage(int quantity) {

		// synchronized because multiple beverages can be served parallely and they may
		// have same ingredient to process

		synchronized (this) {
			
			// if not sufficient quantity is present, throw exception
			if (!hasSufficientQuantity(quantity))
				throw new IngredientQuantityNotSufficientException(this.getName());

			// update the quantity of coffee machine ingredient
			this.setQuantity(this.getQuantity() - quantity);
		}
	}

	@Override
	public void checkForLowQuantity() {

		// if low on quantity, add to indicator list, else remove from list
		if (this.getQuantity() <= lowerLimit)
			this.lowQuantityIndicator.addIngredient(this);
		else
			this.lowQuantityIndicator.removeIngredient(this);
	}

	@Override
	public String toString() {
		return super.getName();
	}
}
