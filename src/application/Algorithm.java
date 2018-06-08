package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;

public class Algorithm extends CookingController {

	public int[] calculateTaskSequence() {
		
		int totalTime = Integer.parseInt(Model.recipe.duration.totaltime);
		
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		int count = 1;
		
		// Checks if attReq = true and if it has children where attReq = false. If true then move to front of array. 
		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {
			if(Model.recipe.tasks.task.get(i).attentionRequired == true && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0")) {
				if(Model.recipe.tasks.task.get(i+count).attentionRequired == false && ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).children, "0")) {
					sequence.add(0, Model.recipe.tasks.task.get(i).ID);
				}
				count++;
			}
		}
		
		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {

//			String x = Model.recipe.tasks.task.get(i).children[0];
//			String[] newChildren = x.replaceAll("\"", "").split(","); 
//			System.out.println(newChildren.length);
			
			// Checks if attReq = false and children = 0 and parent = 0 (which means that the node is alone), then it should be moved to front of array to reduce time used. 
			if(i != Model.recipe.tasks.task.size()-1) {
				if(Model.recipe.tasks.task.get(i).attentionRequired == false && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") &&
						ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")){
					sequence.add(0, Model.recipe.tasks.task.get(i).ID);
					totalTime = totalTime - Model.recipe.tasks.task.get(i).time;
				}
				else {
					sequence.add(Model.recipe.tasks.task.get(i).ID);	
				}
			}
			else {
				// Checks for the same as before but with last index in array.
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
		
		// Should this be moved to for-loop above? Look into later (Mads).
		// Checks if element has a child and attReq is false. If that's the case then it should have a higher priority than other elements. 
		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {
						if (Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0")) {	
							sequence.remove(i);
							sequence.add(0, Model.recipe.tasks.task.get(i).ID);
							totalTime = totalTime - Model.recipe.tasks.task.get(i).time;
						}	
			// Note: this piece is not necessary. The child shouldn't move ahead in the array - this could be discussed but I decided not to.
			//			 if(Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
			//				System.out.println(Model.recipe.tasks.task.get(i).ID);
			//				sequence.remove(i);
			//				sequence.add(0, Model.recipe.tasks.task.get(i).ID);	
			//			}

		}
		
		
		if(totalTime < 0) {
			System.out.println("Will fix later ");
			// Hvis totaltime < 0, returner den samlede tid for de tasks hvor attReq er true
		}
		
		
		System.out.println(totalTime);
		// Stream converts List<integer> to int[].

		int[] taskSequence = sequence.stream().mapToInt(i->i).toArray();
		// System.out.println(java.util.Arrays.toString(taskSequence));
		return taskSequence;
	} 

	public int[] longestPath() {

		int test = 0;
		
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		ArrayList<Integer> tests = new ArrayList<Integer>();
		// If a node has a child and that child has a child, then it has to be the index after the node. Note: Last index can't have a child.
		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {
			if(!ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") && !ArrayUtils.contains(Model.recipe.tasks.task.get(i+test).children, "0")) {
				tests.add(Model.recipe.tasks.task.get(i+test).ID);
			}
		}
		for(int i = 0; i < tests.size(); i++) {		
			if(tests.get(i) == Collections.max(tests)) {
				System.out.println("End " + tests.get(i));
				break;
			}
			else if(tests.get(i+1) - tests.get(i) == 1) {
				System.out.println("1 diff: " + tests.get(i));

			} else {
				System.out.println("!1 diff: " + tests.get(i));

				//break;
			}
			//System.out.println(tests);
		}
		
		//		if(!ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
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
		
		
		int[] taskSequence = sequence.stream().mapToInt(i->i).toArray();
		System.out.println(java.util.Arrays.toString(taskSequence));
		return taskSequence;
	}

}
