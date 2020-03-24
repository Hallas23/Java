package application.controller;

/**
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class Pre {
	public static void require(boolean precondition) {
		if (!precondition)
			throw new RuntimeException("Pre condition violated");
	}
}
