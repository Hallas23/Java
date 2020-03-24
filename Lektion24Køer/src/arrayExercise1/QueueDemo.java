package arrayExercise1;

import queues.Queue;

public class QueueDemo {
	public static void main(String[] args) {
		Queue q = new ArrayQueue();
		q.enqueue("Arnold");
		q.enqueue("Sylvester");
		q.enqueue("Clint");
		q.enqueue("Bruce");
		q.enqueue("Dolph");
		q.enqueue("Chuck");
		System.out.println(q.getFront());
		
	}
}
