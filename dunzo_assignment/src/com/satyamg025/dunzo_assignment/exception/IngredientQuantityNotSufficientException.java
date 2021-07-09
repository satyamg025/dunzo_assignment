package com.satyamg025.dunzo_assignment.exception;

public class IngredientQuantityNotSufficientException extends RuntimeException {

	public IngredientQuantityNotSufficientException(String ingredientName) {
		super(ingredientName + " is not sufficient");
	}
}
