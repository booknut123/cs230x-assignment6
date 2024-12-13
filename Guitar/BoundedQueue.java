import javafoundations.*;

/**
 * BoundedQueue.java.
 * A bounded queue is a queue with a maximum capacity: no elements can be enqueued when the queue is full to its capacity. 
 *
 * @author Kailyn Lau
 * @version v1.0
 */
public class BoundedQueue<T> extends CircularArrayQueue<T> {
    private final int capacity;

    /**
     * Constructor that initializes the bounded queue with a specified capacity.
     *
     * @param capacity the maximum number of elements in the bounded queue
     */
    public BoundedQueue(int capacity) {
        super(); // CircularArrayQueue
        this.capacity = capacity;
    }

    /**
     * @return true if the queue is at capacity, false otherwise
     */
    public boolean isFull() {
        return size() == capacity;
    }

    /**
     * Overrides the enqueue method to prevent adding elements when full.
     *
     * @param element the element to be enqueued into the queue
     * @throws IllegalStateException if the queue is full
     */
    @Override
    public void enqueue(T element) {
        if (isFull()) {
            throw new IllegalStateException("Enqueue operation failed. The queue is full.");
        }
        super.enqueue(element); // Call the original enqueue method
    }

    /**
     * Main method to test this class.
     */
    public static void main(String[] args) {
        // Testing the BoundedQueue
        BoundedQueue<Integer> boundedQueue = new BoundedQueue<>(3);

        System.out.println("Enqueueing 1...");
        boundedQueue.enqueue(1);
        System.out.println(boundedQueue);

        System.out.println("Enqueueing 2...");
        boundedQueue.enqueue(2);
        System.out.println(boundedQueue);

        System.out.println("Enqueueing 3...");
        boundedQueue.enqueue(3);
        System.out.println(boundedQueue);

        // Attempt to enqueue when the queue is full
        try {
            System.out.println("Attempting to enqueue 4...");
            boundedQueue.enqueue(4);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Dequeue an element
        System.out.println("Dequeueing...");
        boundedQueue.dequeue();
        System.out.println(boundedQueue);

        // Enqueue another element after dequeue
        System.out.println("Enqueueing 4...");
        boundedQueue.enqueue(4);
        System.out.println(boundedQueue);
    }
}