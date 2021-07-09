package com.satyamg025.dunzo_assignment.beverage;

import java.util.HashMap;
import java.util.Map;

import com.satyamg025.dunzo_assignment.exception.IngredientNotAvailableException;
import com.satyamg025.dunzo_assignment.exception.IngredientQuantityNotSufficientException;
import com.satyamg025.dunzo_assignment.interfaces.AbstractBeverage;
import com.satyamg025.dunzo_assignment.interfaces.AbstractCoffeeMachineIngredient;
import com.satyamg025.dunzo_assignment.interfaces.Drink;
import com.satyamg025.dunzo_assignment.interfaces.Ingredient;
import com.satyamg025.dunzo_assignment.main.AllAvailableIngredients;

// concrete beverage which a coffee machine can prepare
// it is a Drink as it will a name and ingredients used to prepare this Beverage
// this can be served to customers to implementing the behavior of beverage which is to serve
public class Beverage extends Drink implements AbstractBeverage {

	public Beverage(String name) {
		super(name);
	}

	// serve the drink to customer
	@Override
	public void serveDrink() {

		// making a record of ingredients, in case some process gets terminated in
		// between because of non availability or not sufficient quantity, the till used
		// ingredients can be added back to coffee machine ingredient
		Map<AbstractCoffeeMachineIngredient, Integer> rollbackMap = new HashMap<>();

		String output = this.getName() + " is prepared";

		try {

			processDrink(rollbackMap);

		} catch (IngredientNotAvailableException | IngredientQuantityNotSufficientException e) {

			// if exception, add back the ingredients to coffee machine ingredients
			for (Map.Entry<AbstractCoffeeMachineIngredient, Integer> entry : rollbackMap.entrySet()) {

				entry.getKey().refill(entry.getValue());
			}

			output = this.getName() + " cannot be prepared because item " + e.getLocalizedMessage();
		}

		System.out.println();
		System.out.println(output);
		System.out.println();
	}

	private synchronized void processDrink(Map<AbstractCoffeeMachineIngredient, Integer> rollbackMap) {

		// get all ingredients available in the coffee machine
		Map<String, AbstractCoffeeMachineIngredient> systemIngredients = AllAvailableIngredients.getInstance()
				.getIngredients();

		// iterate over ingredients required to prepare the Drink
		for (Map.Entry<String, Ingredient> entry : this.getDrinkIngredients().entrySet()) {

			// if ingredient is not available in the coffee machine, throw exception
			if (!systemIngredients.containsKey(entry.getKey()))
				throw new IngredientNotAvailableException(entry.getKey());

			// update the quantity of coffee machine ingredient
			systemIngredients.get(entry.getKey()).addIngredientToBeverage(entry.getValue().getQuantity());

			// add to rollback list as well
			rollbackMap.put(systemIngredients.get(entry.getKey()), entry.getValue().getQuantity());
		}

	}
}
