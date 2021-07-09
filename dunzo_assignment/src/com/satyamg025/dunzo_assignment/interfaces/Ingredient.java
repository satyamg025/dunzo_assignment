package com.satyamg025.dunzo_assignment.interfaces;

// basic Ingredient, it has name and quantity
public class Ingredient {

	private String name;

	private int quantity;

	public Ingredient(String name, int availableQuantity) {
		super();
		this.name = name;
		this.quantity = availableQuantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	// protected, because we do not want object to directly set the value, it should
	// be set from refill method only
	protected void setQuantity(int availableQuantity) {
		this.quantity = availableQuantity;
	}
}
