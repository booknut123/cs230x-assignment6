import java.util.Random;

/**
 * Models a vibrating guitar string.
 *
 * @author Kailyn Lau
 * @version v1.0
 */
public class GuitarString {
    private static final int SAMPLING_RATE = 44100;
    private static final double ENERGY_DECAY_FACTOR = 0.994;

    private BoundedQueue<Double> queue;

    /**
     * Constructor that creates a guitar string of the given frequency. Bounded queue of capacity N, filled with zeros.
     *
     * @param frequency the frequency of the guitar string
     */
    public GuitarString(double frequency) {
        int capacity = (int) Math.ceil(SAMPLING_RATE / frequency);
        queue = new BoundedQueue<>(capacity);

        // Fill the queue with N zeros
        for (int i = 0; i < capacity; i++) {
            queue.enqueue(0.0);
        }
    }

    /**
     * Replaces the samples in the bounded queue with N random values between -0.5 and +0.5.
     */
    public void pluck() {
        Random rand = new Random();
        for (int i = 0; i < queue.size(); i++) {
            double randomValue = rand.nextDouble() - 0.5;
            queue.dequeue(); // Remove the front element
            queue.enqueue(randomValue); // Add the random value to the end
        }
    }

    /**
     * Returns the value of the item at the front of the bounded queue.
     *
     * @return the sample value at the front of the queue
     */
    public double sample() {
        return queue.first();
    }

    /**
     * Applies the Karplus-Strong algorithm. (delete sample at front of bounded queue and add the average of the deleted sample and the next sample, multiplied by the energy decay factor)
     */
    public void tic() {
        double firstSample = queue.dequeue();
        double nextSample = queue.first(); // Peek at the next sample
        double newSample = (firstSample + nextSample) * 0.5 * ENERGY_DECAY_FACTOR;
        queue.enqueue(newSample); // Add the new sample to the end of the queue
    }
}
