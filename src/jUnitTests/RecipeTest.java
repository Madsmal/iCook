package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Recipe;
import application.Recipe.Ingredients.Ingredient;

class RecipeTest {
	private Recipe rec;
	private Ingredient ing;

	@BeforeEach
	void setUp() throws Exception {
		rec = new Recipe();
		ing = new Recipe.Ingredients.Ingredient();
		rec.setTitle("asciuta");
		rec.setID(Byte.parseByte("1"));
		rec.setStartdate("12.04.2014");
		rec.setChangedate("14.06.2018");
		rec.setTarget("For 4-5 people");
		
		rec.setIngredients(new Recipe.Ingredients());
		ing.setIname("spaghetti");
		ing.setQuantity("300");
		ing.setUnit("g");
		
		
		
	}

	@Test
	void test() {
		String title = "asciuta";
		String ID = "1";
		String sd = "12.04.2014";
		String cd = "14.06.2018"; 
		String tar = "For 4-5 people";
		
		assertEquals(title, rec.getTitle());
		assertEquals(Byte.parseByte(ID), rec.getID());
		assertEquals(sd, rec.getStartdate());
		assertEquals(cd, rec.getChangedate());
		assertEquals(tar, rec.getTarget());
		
		
	}

}
