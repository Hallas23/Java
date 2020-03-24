package opgave2;

import java.util.Scanner;

/**
 * This program demonstrates a decision tree for an animal guessing game.
 */
public class DecisionTreeDemo {
	public static void main(String[] args) {

		BinaryTreeOpgave2<Integer> n15 = new BinaryTreeOpgave2<Integer>(15);

		BinaryTreeOpgave2<Integer> n25 = new BinaryTreeOpgave2<Integer>(25);
		
		BinaryTreeOpgave2<Integer> n88 = new BinaryTreeOpgave2<Integer>(88);

		BinaryTreeOpgave2<Integer> n90 = new BinaryTreeOpgave2<Integer>(90, n88, null);
		
		BinaryTreeOpgave2<Integer> n77 = new BinaryTreeOpgave2<Integer>(77, null, n90);
		
		BinaryTreeOpgave2<Integer> n30 = new BinaryTreeOpgave2<Integer>(30, n25, null);
		
		BinaryTreeOpgave2<Integer> n11 = new BinaryTreeOpgave2<Integer>(11, null, n15);
		
		BinaryTreeOpgave2<Integer> n22 = new BinaryTreeOpgave2<Integer>(22, n11, n30);

		BinaryTreeOpgave2<Integer> n45 = new BinaryTreeOpgave2<Integer>(45, n22, n77);
		
		System.out.println(n45.sum());
		
		
		System.out.println(n77.right().data());
		System.out.println(n77.size());
		

	}
}
