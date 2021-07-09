package com.satyamg025.dunzo_assignment.main;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.satyamg025.dunzo_assignment.beverage.Beverage;
import com.satyamg025.dunzo_assignment.indicator.LowQuantityIndicator;
import com.satyamg025.dunzo_assignment.ingredient.DrinkIngredient;
import com.satyamg025.dunzo_assignment.ingredient.CoffeeMachineIngredient;
import com.satyamg025.dunzo_assignment.interfaces.AbstractLowQuantityIndicator;

public class CoffeeMachine {

	public static void main(String[] args) {

		// json input
		String jsonString = "{\"machine\":{\"outlets\":{\"count_n\":4},\"beverages\":{\"hot_tea\":{\"hot_milk\":100,\"hot_water\":200,\"sugar_syrup\":10,\"ginger_syrup\":10,\"tea_leaves_syrup\":30},\"black_tea\":{\"hot_water\":300,\"sugar_syrup\":50,\"ginger_syrup\":30,\"tea_leaves_syrup\":30},\"green_tea\":{\"hot_water\":100,\"sugar_syrup\":50,\"ginger_syrup\":30,\"green_mixture\":30},\"hot_coffee\":{\"hot_milk\":400,\"hot_water\":100,\"sugar_syrup\":50,\"ginger_syrup\":30,\"tea_leaves_syrup\":30}},\"total_items_quantity\":{\"hot_milk\":500,\"hot_water\":500,\"sugar_syrup\":100,\"ginger_syrup\":100,\"tea_leaves_syrup\":100}}}";

		JSONObject jsonInput = new JSONObject(jsonString);

		AbstractLowQuantityIndicator indicator = new LowQuantityIndicator();

		// get total outlets
		int totalOutlets = jsonInput.getJSONObject("machine").getJSONObject("outlets").getInt("count_n");

		addSystemIngredients(jsonInput, indicator);

		// get all beverages
		List<Beverage> beveragesList = getAllBeverages(jsonInput);

		// serve drinks
		serveDrinks(totalOutlets, beveragesList);
	}

	public static void addSystemIngredients(JSONObject jsonInput, AbstractLowQuantityIndicator indicator) {

		// add all ingredients available in the coffee machine
		JSONObject totalItemsQuantity = jsonInput.getJSONObject("machine").getJSONObject("total_items_quantity");

		totalItemsQuantity.keySet().forEach(item -> {
			AllAvailableIngredients.getInstance().addIngredient(item,
					new CoffeeMachineIngredient(item, totalItemsQuantity.getInt(item), indicator));
		});
	}

	public static List<Beverage> getAllBeverages(JSONObject jsonInput) {

		// get all beverages
		JSONObject beverages = jsonInput.getJSONObject("machine").getJSONObject("beverages");
		List<Beverage> beveragesList = new ArrayList<>();

		beverages.keySet().forEach(beverageName -> {

			Beverage beverage = new Beverage(beverageName);

			JSONObject beverageItems = beverages.getJSONObject(beverageName);

			// add beverage ingredient
			beverageItems.keySet().forEach(item -> {
				beverage.addIngredient(new DrinkIngredient(item, beverageItems.getInt(item)));
			});

			beveragesList.add(beverage);
		});

		return beveragesList;
	}

	public static void serveDrinks(int outlets, List<Beverage> beverageList) {

		// serving the drinks in new thread as multiple beverages can be served
		// parallely
		for (int i = 0; i < outlets; i++) {

			new Thread(new ServeDrink(beverageList.get(i))).start();
		}

	}
}
