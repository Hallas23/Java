package arrayExercise1;

import opgave1_2.KædetListeQueue;
import opgave2.DequeListe;
import queues.CircularArrayQueue;
import queues.Queue;

public class QueueDemo {
	public static void main(String[] args) {
		Queue q = new KædetListeQueue();
		q.enqueue("Arnold");
		q.enqueue("Sylvester");
		q.enqueue("Clint");
		q.enqueue("Bruce");
		q.enqueue("Dolph");
		q.enqueue("Chuck");
		System.out.println(q.getFront());
		System.out.println(q.isEmpty() + " " + q.size());
		while (!q.isEmpty()) {
			System.out.println(q.dequeue());
		}
//		System.out.println();
//		System.out.println(q.isEmpty() + " " + q.size());
//		System.out.println();
//		
//		Deque q2 = new DequeListe();
//		q2.addFirst("Arnold");
//		q2.addFirst("Sylvester");
//		q2.addFirst("Clint");
//		System.out.println(q2.getFirst());
//		System.out.println(q2.getLast());
		
		
	}
}

