package application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;

public class Algorithm extends CookingController {

	public int[] calculateTaskSequence() {

		// Compares the time for each element to the other elements and if the prereg for the element is empty - 
		// if so then add it to the front of the array.

		// Here it is added to the front of the array, since it doesn't require other tasks to be made.

		// To make a longest path tree (a way to optimize the algorhitm) use preReg. 
		// if a tree contains more preRegs than another, then it should prioritize this tree.


		// Better solution: add elements with no preReg to front of array and make those first.
		int test = 0;
		
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		ArrayList<Integer> tests = new ArrayList<Integer>();
		
		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {

//			String x = Model.recipe.tasks.task.get(i).children[0];
//			String[] newChildren = x.replaceAll("\"", "").split(","); 
//			System.out.println(newChildren.length);

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
			//			if (Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0")) {	
			//				sequence.remove(i);
			//				sequence.add(0, Model.recipe.tasks.task.get(i).ID);
			//			}	
			// Note: Not sure if this is necessary. Should the child to a parent be on the same index as before or should it be made right after the parent is done. 
			// This statement moves the child to the index after the parent. 
			//			 if(Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
			//				System.out.println(Model.recipe.tasks.task.get(i).ID);
			//				sequence.remove(i);
			//				sequence.add(0, Model.recipe.tasks.task.get(i).ID);	
			//			}

			// If a node has a child and that child has a child, then it has to be the index after the node. Note: Last index can't have a child.

			if(!ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") && !ArrayUtils.contains(Model.recipe.tasks.task.get(i+test).children, "0")) { // || 
				// (!ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0") && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0"))) {
				
				//System.out.println("out: " + Model.recipe.tasks.task.get(i+test).ID);
				tests.add(Model.recipe.tasks.task.get(i+test).ID);
				//sum = Model.recipe.tasks.task.get(i).time + sum;
				//System.out.println(sum);
			}
			//			if(!ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
			//				System.out.println("id: " + Model.recipe.tasks.task.get(i).ID);
			//
			//				System.out.println("Parent: " + Model.recipe.tasks.task.get(i-1).ID);
			//					// Case: if i+test = last index
			//					if(i+1 == Model.recipe.tasks.task.size()) {
			//						System.out.println("i = max value: " + Model.recipe.tasks.task.get(i).ID);
			//
			//						break;
			//					}
			//					else if(!ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0")) {
			//						System.out.println("ID: " + Model.recipe.tasks.task.get(i).ID);
			//
			//					}
			//				}
			//				if(ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, i+1)) {
			//					System.out.println("i: " + Model.recipe.tasks.task.get(i).ID);
		}

		System.out.println(tests);
		
		for(int i = 0; i < tests.size(); i++) {		
			if(tests.get(i) == 6) {
				System.out.println("End " + tests.get(i));
				break;
			}
			else if(tests.get(i+1) - tests.get(i) == 1) {
				System.out.println("1 diff: " + tests.get(i));

			} else {
				System.out.println("!1 diff: " + tests.get(i));

				break;
			}
			System.out.println(tests);
		}


		// Stream converts List<integer> to int[].

		int[] taskSequence = sequence.stream().mapToInt(i->i).toArray();
		// System.out.println(java.util.Arrays.toString(taskSequence));
		return taskSequence;
	} 

	public int[] longestPath() {

		ArrayList<Integer> sequence = new ArrayList<Integer>();

		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {
			if(!ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") || 
					(!ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0") && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0"))) {
				//				System.out.println("i: " + Model.recipe.tasks.task.get(i).ID);
				if(!ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
					System.out.println("id: " + Model.recipe.tasks.task.get(i).ID);
				}
			}
		}

		int[] taskSequence = sequence.stream().mapToInt(i->i).toArray();
		System.out.println(java.util.Arrays.toString(taskSequence));
		return taskSequence;
	}

}
