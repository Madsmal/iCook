/**
 * @author Mads Malmskov s153013
 */


package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

public class Algorithm extends CookingController {

	


	public int[] calculateTaskSequence() {
		
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		int[] taskSequence = new int[0];
		
		List<Recipe.Tasks.Task> tasks = Model.recipe.tasks.getTask();
		
		Collections.sort(tasks);
		
		Map<Integer, Recipe.Tasks.Task> tasksMap = new HashMap<>();
		for(Recipe.Tasks.Task t  :tasks) {
			tasksMap.put(t.getID(), t);
		}
		
		Set<Integer> visited = new HashSet<>();
		
		while(visited.size() != tasks.size()) {
			int poz = 0;
			Recipe.Tasks.Task task = null;
			do {
				task = tasks.get(poz);
				poz++;
			}while(visited.contains(task.getID()));
			
			Queue<Recipe.Tasks.Task> dfsQueue = new LinkedList<>();
			dfsQueue.add(tasks.get(poz-1));
			
			while(!dfsQueue.isEmpty()) {
				Recipe.Tasks.Task t = dfsQueue.poll();
				if(t != null) {
					visited.add(t.getID());
					sequence.add(t.getID());
					String[] children = t.getChildren();
					for(String c : children) {
						if(!visited.contains(Integer.parseInt(c))) {
							dfsQueue.add(tasksMap.get(Integer.parseInt(c)));
						}
					}
				}
			}
		}

//		ArrayList<Integer> sequence = new ArrayList<Integer>();
//		int[] taskSequence = new int[0];
//
//		// Checks if attReq = true and if it has children where attReq = false. If true then move to front of array. 
//
//		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {
//
//			int test = i-1;
//			//			String x = Model.recipe.tasks.task.get(i).children[0];
//			//			String[] newChildren = x.replaceAll("\"", "").split(","); 
//			//			System.out.println(newChildren.length);
//
//			// Checks if attReq = false and children = 0 and parent = 0 (which means that the node is alone), then it should be moved to front of array to reduce time used. 
//			if(i != Model.recipe.tasks.task.size()-1) {
//				if(Model.recipe.tasks.task.get(i).attentionRequired == false && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") &&
//						ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")){
//					sequence.add(0, Model.recipe.tasks.task.get(i).ID);
//				} 
//				else if (Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0")) {
//					sequence.add(0,  Model.recipe.tasks.task.get(i).ID);
//					
//					if(!ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents,"0")){
//						while (true) {
//							if(Model.recipe.tasks.task.get(i-test).attentionRequired == true && Arrays.asList(Model.recipe.tasks.task.get(i-test).children).contains(Integer.toString(Model.recipe.tasks.task.get(i).ID))) {
//								sequence.add(Model.recipe.tasks.task.get(i).ID);	
//								
//							} else { 
//								test--; 
//							}
//							
//							break;
//						}
//					} 
//					
//					
//				}
//				else {
//					sequence.add(Model.recipe.tasks.task.get(i).ID);	
//				}
//				
//			}
//			else {
//				// Checks for the same as before but with last index in array.
//				if(Model.recipe.tasks.task.get(i).attentionRequired == false && ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
//					sequence.add(0, Model.recipe.tasks.task.get(i).ID);
//				}
//				else {
//					sequence.add(Model.recipe.tasks.task.get(i).ID);
//				}
//
//			}
//			
//		}
//		// sequence constructed. 
//		//		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {		
//		//			int test = i-1;
//		//			if (Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0")) {
//		//				if(!ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents,"0")){
//		//					// ID = 4 && parents = 2
//		//					while (true) {
//		//						if(Model.recipe.tasks.task.get(i-test).attentionRequired == true && Arrays.asList(Model.recipe.tasks.task.get(i-test).children).contains(Integer.toString(Model.recipe.tasks.task.get(i).ID))) {
//		//							System.out.println(sequence);
//		//							System.out.println("Scoop " + Model.recipe.tasks.task.get(i).ID);
//		//							sequence.remove(Model.recipe.tasks.task.get(i).ID);
//		//							sequence.add(Model.recipe.tasks.task.get(i-test).ID, Model.recipe.tasks.task.get(i).ID);
//		//							System.out.println("after " + sequence);
//		//						} else { 
//		//							test--; 
//		//							}
//		//						break;
//		//					}
//		//				}
//		//				sequence.remove(i);
//		//				sequence.add(0, Model.recipe.tasks.task.get(i).ID);
//		//			}	
//		//		}
//		// Note: this piece is not necessary. The child shouldn't move ahead in the array - this could be discussed but I decided not to.
//		//			 if(Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
//		//				System.out.println(Model.recipe.tasks.task.get(i).ID);
//		//				sequence.remove(i);
//		//				sequence.add(0, Model.recipe.tasks.task.get(i).ID);	
//		//			}
//
//
//
//		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {
//			if(Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") && ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
//				//				// Should be added to front of array. Since it's a false node with children
//				//				// Print 3 og 5
//				sequence.add(0, Model.recipe.tasks.task.get(i).ID);
//				sequence.remove(Model.recipe.tasks.task.get(i).ID);
//				//				
//				//				
//				////				while (true) {
//				////					if(Model.recipe.tasks.task.get(i+count).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).children, "0") && 
//				////							!ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).parents, Integer.toString(i))) {
//				////						System.out.println("SIS " + Model.recipe.tasks.task.get(i+count).ID);
//				////						count++;
//				////							if (!ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).children, "0") && Model.recipe.tasks.task.get((i+count)+1).attentionRequired == true) {
//				////								System.out.println("SISsss " + Model.recipe.tasks.task.get(i).ID);
//				////							}
//				////						
//				////					} else { 
//				////						break; 
//				////						}
//				////				}
//				//				if(i+count == Model.recipe.tasks.task.size()) {
//				//					break;
//				//				}
//				//				else if(Model.recipe.tasks.task.get(i+count).attentionRequired == false && ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).children, "0")) {
//				//					sequence.add(0, Model.recipe.tasks.task.get(i).ID);
//				//					count++;
//				//				} 
//				//
//			}
//		}

		//System.out.println(sequence);
		// Checks if element has a child and attReq is false. If that's the case then it should have a higher priority than other elements. 
		//sequence.remove(Integer.valueOf(4));
		
		// Stream converts List<integer> to int[].
		taskSequence = sequence.stream().mapToInt(i->i).toArray();
		//System.out.println(java.util.Arrays.toString(taskSequence));

		return taskSequence;
	} 

	public String calculateWorktime() {

		int workTime = 0;

		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {

			//			String x = Model.recipe.tasks.task.get(i).children[0];
			//			String[] newChildren = x.replaceAll("\"", "").split(","); 
			//			System.out.println(newChildren.length);

			// Checks if attReq = false and children = 0 and parent = 0 (which means that the node is alone), then it should be moved to front of array to reduce time used. 
			if(i != Model.recipe.tasks.task.size()-1) {
				if(Model.recipe.tasks.task.get(i).attentionRequired == true) {
					workTime = workTime + Model.recipe.tasks.task.get(i).time;
				}
				else {
					workTime = workTime + 0;
				}
			}
			else {
				if(Model.recipe.tasks.task.get(i).attentionRequired == true) {
					workTime = workTime + Model.recipe.tasks.task.get(i).time;
				}
				else {
					workTime = workTime + 0;
				}			
			}
		}

		//		if(workTime < 0) {
		//			for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {
		//				if (Model.recipe.tasks.task.get(i).attentionRequired == true){
		//					workTime = workTime +  Model.recipe.tasks.task.get(i).time;
		//
		//				}
		//			}
		//
		//		}
		String str1 = Integer.toString(workTime);
		//System.out.println(str1);

		return str1;
	}

	public String calculateTotaltime() {

		int totalTime = 0;
		int count = 1;

		for(int i = 0; i < Model.recipe.tasks.task.size(); i++) {

			//			String x = Model.recipe.tasks.task.get(i).children[0];
			//			String[] newChildren = x.replaceAll("\"", "").split(","); 
			//			System.out.println(newChildren.length);

			// Checks if attReq = false and children = 0 and parent = 0 (which means that the node is alone), then it should be moved to front of array to reduce time used. 
			if(i == 0) {
				if(Model.recipe.tasks.task.get(i).attentionRequired == true && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0")) {
					totalTime = totalTime + Model.recipe.tasks.task.get(i).time;
				} 			
				else if (Model.recipe.tasks.task.get(i).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0")) {	
					while (true) {
						if (Model.recipe.tasks.task.get(i+count).attentionRequired == true && !ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).children, "0")  && 
								!ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).parents, "0")) {
							totalTime = totalTime + Model.recipe.tasks.task.get(i+count).time;
							count++;
						} else {
							totalTime = totalTime + Model.recipe.tasks.task.get(i+count).time;
							break; 
						} 
					}
				} else if (Model.recipe.tasks.task.get(i).attentionRequired == true && !ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0")) {
					totalTime = totalTime + Model.recipe.tasks.task.get(i).time;
					if(Model.recipe.tasks.task.get(i+count).attentionRequired == true && !ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).children, "0") && 
							!ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).parents, "0")) {
						count++;
						totalTime = totalTime + Model.recipe.tasks.task.get(i).time;
					}	
					else if(Model.recipe.tasks.task.get(i+count).attentionRequired == false && !ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).children, "0") && 
							!ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).parents, "0")) {

						while (true) {
							if(Model.recipe.tasks.task.get(i+count+1).attentionRequired == true && !ArrayUtils.contains(Model.recipe.tasks.task.get(i+count+1).children, "0") && 
									!ArrayUtils.contains(Model.recipe.tasks.task.get(i+count+1).parents, "0")) {
								totalTime = totalTime + Model.recipe.tasks.task.get(i+count).time - Model.recipe.tasks.task.get(i+count+1).time; 
								count++;
							} else {
								totalTime = totalTime - Model.recipe.tasks.task.get(i+count+1).time;
								break; 
							} 
						}

					} else if (Model.recipe.tasks.task.get(i+count).attentionRequired == false && ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).children, "0") &&
							!ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).parents, "0")) {

						totalTime = totalTime + Model.recipe.tasks.task.get(i+count).time;
					}
					else if (Model.recipe.tasks.task.get(i+count).attentionRequired == true && !ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).children, "0") && 
							!ArrayUtils.contains(Model.recipe.tasks.task.get(i+count).parents, "0")){
						count++;
						totalTime = totalTime + Model.recipe.tasks.task.get(i).time;
					}  
				} 

			}
			else if(i != Model.recipe.tasks.task.size() - 1 && i > 0) {
				if(Model.recipe.tasks.task.get(i).attentionRequired == true && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") &&
						ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")){
					totalTime = totalTime + Model.recipe.tasks.task.get(i).time;
				}
				else if(Model.recipe.tasks.task.get(i).attentionRequired == false && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") &&
						ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
					totalTime = totalTime + 0;
				}
			}
			else {
				if(Model.recipe.tasks.task.get(i).attentionRequired == true  && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") &&
						ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")) {
					totalTime = totalTime + Model.recipe.tasks.task.get(i).time;
				}
				else if (Model.recipe.tasks.task.get(i).attentionRequired == false  && ArrayUtils.contains(Model.recipe.tasks.task.get(i).children, "0") &&
						ArrayUtils.contains(Model.recipe.tasks.task.get(i).parents, "0")){
					totalTime = totalTime + 0;
				}			
			}
		}

		String str1 = Integer.toString(totalTime);
		//System.out.println(str1);
		return str1;
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
		//System.out.println(java.util.Arrays.toString(taskSequence));
		return taskSequence;
	}

}