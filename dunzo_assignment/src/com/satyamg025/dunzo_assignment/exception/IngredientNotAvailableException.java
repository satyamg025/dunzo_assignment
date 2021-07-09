package com.satyamg025.dunzo_assignment.exception;

public class IngredientNotAvailableException extends RuntimeException {

	public IngredientNotAvailableException(String ingredientName) {
		super(ingredientName + " is not available");
	}
}
