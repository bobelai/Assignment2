public class QueueOverflowException extends RuntimeException {
    public QueueOverflowException() {
        super("Queue is full, cannot add more elements.");
    }
}