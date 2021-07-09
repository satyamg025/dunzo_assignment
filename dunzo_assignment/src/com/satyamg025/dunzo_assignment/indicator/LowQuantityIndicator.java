package com.satyamg025.dunzo_assignment.indicator;

import java.util.ArrayList;
import java.util.List;

import com.satyamg025.dunzo_assignment.interfaces.AbstractCoffeeMachineIngredient;
import com.satyamg025.dunzo_assignment.interfaces.AbstractLowQuantityIndicator;

// indicator to display which ingredients are running low on quantity
public class LowQuantityIndicator implements AbstractLowQuantityIndicator {

	// create a list which will have all those ingredients which are running low on
	// quantity
	private List<AbstractCoffeeMachineIngredient> ingredientList;

	public LowQuantityIndicator() {
		ingredientList = new ArrayList<>();
	}

	// add ingredient to list and display
	@Override
	public void addIngredient(AbstractCoffeeMachineIngredient ingredient) {
		ingredientList.add(ingredient);
		display();
	}

	// remove ingredient from list and display
	@Override
	public void removeIngredient(AbstractCoffeeMachineIngredient ingredient) {
		ingredientList.remove(ingredient);
		display();
	}

	// display ingredients running low on quantity
	@Override
	public void display() {

//		if (ingredientList.size() > 0)
//			System.out.println(ingredientList + " low on quantity");
	
	}

}
