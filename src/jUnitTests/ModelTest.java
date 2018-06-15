package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import application.Model;
import application.Recipe;
import application.Recipe.Ingredients;
import application.Recipe.Ingredients.Ingredient;

class ModelTest {
	private Recipe.Ingredients.Ingredient ingredient = new Recipe.Ingredients.Ingredient();
	private Recipe rec;

	@Test
	void testSecondsToHHMMSS() {
		Model testTime = new Model();
		String test1 = testTime.secondsToHHMMSS(2000);
		String test2 = "00:33:20";
		assertEquals(test2,test1);
	}
	
	@Test
	void testSecondsToCollapsingHHMMSS() {
		Model test = new Model();
		String test1 = test.secondsToCollapsingHHMMSS(120);
		String test2 = "2:00";
		assertEquals(test2, test1);
	}
	
	@Test
	void testGetTime() { //works if you comment line 60-70 in Model, NPE otherwise
		Model test = new Model();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String str = sdf.format(new Date());
		String test1 = test.getTime();
		assertEquals(str, test1);
	}
	
//	@Test
//	void testIngredientQuantityMultiplier() {
//		ingredient.setIname("bread");
//		ingredient.setQuantity("100");
//		ingredient.setUnit("g");
//		Model.ingredientsQuantityMultiplier(2);
//		assertEquals("200",Model.recipe.getIngredients().getIngredient());
//	}
	
	

}
