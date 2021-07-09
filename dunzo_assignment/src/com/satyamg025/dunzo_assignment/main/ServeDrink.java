package com.satyamg025.dunzo_assignment.main;

import com.satyamg025.dunzo_assignment.interfaces.AbstractBeverage;

// serving the drink to customer
public class ServeDrink implements Runnable {

	private AbstractBeverage beverage;

	public ServeDrink(AbstractBeverage beverage) {
		super();
		this.beverage = beverage;
	}

	@Override
	public void run() {
		this.beverage.serveDrink();
	}

}
