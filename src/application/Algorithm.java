package application;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

public class Algorithm extends CookingController {
	public int[] calculateTaskSequence() {

		// Compares the time for each element to the other elements and if the prereg for the element is empty - 
		// if so then add it to the front of the array.

		// Here it is added to the front of the array, since it doesn't require other tasks to be made.

		// To make a longest path tree (a way to optimize the algorhitm) use preReg. 
		// if a tree contains more preRegs than another, then it should prioritize this tree.


		// Better solution: add elements with no preReg to front of array and make those first.

		
		ArrayList<Integer> sequence = new ArrayList<Integer>();

		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {
			if(i != Model.recipe.tasks.task.size()-1) {
				if(Model.recipe.tasks.task.get(i).attentionRequired == false && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") &&
						ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")){
					sequence.add(0, Model.recipe.tasks.task.get(i).ID);
				}
				else {
					sequence.add(Model.recipe.tasks.task.get(i).ID);	
				}
			}
			else {
				if(Model.recipe.tasks.task.get(i).attentionRequired == false && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") &&
						ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
					sequence.add(0, Model.recipe.tasks.task.get(i).ID);
				}
				else {
					sequence.add(Model.recipe.tasks.task.get(i).ID);
				}
			}		
		}
		System.out.println(sequence);
		// Checks if element has a child and attReq is false. If that's the case then it should have a higher priority than other elements. 
		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {
			if (Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0")) {	
				sequence.remove(i);
				sequence.add(0, Model.recipe.tasks.task.get(i).ID);
			}	
			// Note: Not sure if this is necessary. Should the child to a parent be on the same index as before or should it be made right after the parent is done. 
			// This statement moves the child to the index after the parent. 
			 if(Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
				sequence.remove(i);
				sequence.add(0, Model.recipe.tasks.task.get(i).ID);	
			}
			 
			String x = Model.recipe.tasks.task.get(i).children[0];
			String[] newChildren = x.replaceAll("\"", "").split(","); 
			System.out.println("ID " + Model.recipe.tasks.task.get(i).ID + " længde : " +
			Model.recipe.tasks.task.get(i).children.length + " Barn " + java.util.Arrays.toString(Model.recipe.tasks.task.get(i).children));
			System.out.println(newChildren.length);
			
			
		}
		// Stream converts List<integer> to int[].
		
		int[] taskSequence = sequence.stream().mapToInt(i->i).toArray();
		
		System.out.println(java.util.Arrays.toString(taskSequence));
		return taskSequence;
	} 
}
