package application;

public class Recipe {
	//class for recipes
	//test
	private int id;
	private String name;
	private int numberOfServes;
	private String ingredients;
	private String steps;
	private String notes;
	
	public Recipe(int id, String name, int numberOfServes, String ingredients, String steps, String remarks, String notes) {
		this.id = id;
		this.name = name;
		this.numberOfServes = numberOfServes;
		this.ingredients = ingredients;
		this.steps = steps;
		this.notes = notes;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumberOfServes() {
		return numberOfServes;
	}
	
	public void setNumberOfServes(int numberOfServes) {
		this.numberOfServes = numberOfServes;
	}
	
	public String getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	public String getSteps() {
		return steps;
	}
	
	public void setSteps(String steps) {
		this.steps = steps;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public void updateFields(String name, int serves, String ingredients, String steps, String notes, int numberOfServes) {
		this.name = name;
		this.numberOfServes = numberOfServes;
		this.ingredients = ingredients;
		this.steps = steps;
		this.notes = notes;
	}
	
}
