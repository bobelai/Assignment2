public class StackUnderflowException extends RuntimeException {
    public StackUnderflowException() {
        super("Stack is empty, cannot perform the operation.");
    }
}
