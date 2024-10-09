/**
 * Notation class converts infix expressions to postfix,
 * postfix to infix, and evaluates postfix expressions.
 *
 * Infix notation: operators are between operands (2 + 3).
 * Postfix notation: operators come after operands (2 3 +).
 *@ Leul Belay 
 * This class uses custom stack and queue
 */
public class Notation {

    /**
     * Default constructor
     */
    public Notation() {
        // Default constructor
    }

    /**
     * Converts an infix expression to postfix.
     *
     * @param infix The infix expression to convert.
     * @return The postfix expression as a string.
     * @throws InvalidNotationFormatException if the infix expression is invalid.
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
        StackInterface<Character> stack = new MyStack<>(infix.length()); // Stack for operators
        QueueInterface<Character> queue = new MyQueue<>(infix.length()); // Queue for result
        StringBuilder postfix = new StringBuilder();

        for (char ch : infix.toCharArray()) {
            if (Character.isDigit(ch)) {  // If it's a number
                try {
                    queue.enqueue(ch); // Add to the queue
                } catch (QueueOverflowException e) {
                    throw new InvalidNotationFormatException();
                }
            } else if (ch == '(') {  // Open parenthesis
                try {
                    stack.push(ch);
                } catch (StackOverflowException e) {
                    throw new InvalidNotationFormatException();
                }
            } else if (ch == ')') {  // Close parenthesis
                try {
                    // Pop from stack to queue until '('
                    while (!stack.isEmpty() && stack.top() != '(') {
                        queue.enqueue(stack.pop());
                    }
                    stack.pop(); // Pop the '('
                } catch (Exception e) {
                    throw new InvalidNotationFormatException();
                }
            } else if (isOperator(ch)) {  // It's an operator
                try {
                    // Pop from stack to queue based on precedence
                    while (!stack.isEmpty() && precedence(stack.top()) >= precedence(ch)) {
                        queue.enqueue(stack.pop());
                    }
                    stack.push(ch); // Push the current operator
                } catch (Exception e) {
                    throw new InvalidNotationFormatException();
                }
            }
        }

        // Add remaining operators to the queue
        try {
            while (!stack.isEmpty()) {
                queue.enqueue(stack.pop());
            }
        } catch (Exception e) {
            throw new InvalidNotationFormatException();
        }

        return queue.toString(); // Return postfix as a string
    }

    /**
     * Converts a postfix expression to an infix expression.
     *
     * @param postfix The postfix expression to convert.
     * @return The infix expression as a string.
     * @throws InvalidNotationFormatException if the postfix expression is invalid.
     */
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
        StackInterface<String> stack = new MyStack<>(postfix.length()); // Stack for operands

        for (char ch : postfix.toCharArray()) {
            if (Character.isDigit(ch)) {  // It's an operand (number)
                try {
                    stack.push(Character.toString(ch)); // Push it to the stack
                } catch (StackOverflowException e) {
                    throw new InvalidNotationFormatException();
                }
            } else if (isOperator(ch)) {  // It's an operator
                try {
                    // Pop two operands
                    String operand2 = stack.pop();
                    String operand1 = stack.pop();
                    // Create infix expression
                    String result = "(" + operand1 + ch + operand2 + ")";
                    stack.push(result); // Push it back to the stack
                } catch (StackUnderflowException e) {
                    throw new InvalidNotationFormatException();
                }
            }
        }

        if (stack.size() != 1) {
            throw new InvalidNotationFormatException(); // Something went wrong
        }

        try {
            return stack.pop(); // Return the final infix expression
        } catch (StackUnderflowException e) {
            throw new InvalidNotationFormatException();
        }
    }

    /**
     * Evaluates a postfix expression and returns the result as a double.
     *
     * @param postfixExpr The postfix expression to evaluate.
     * @return The result as a double.
     * @throws InvalidNotationFormatException if the expression is invalid.
     */
    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
        StackInterface<Double> stack = new MyStack<>(postfixExpr.length()); // Stack for values

        for (char ch : postfixExpr.toCharArray()) {
            if (Character.isDigit(ch)) {  // It's a number
                try {
                    stack.push((double) (ch - '0')); // Convert char to number
                } catch (StackOverflowException e) {
                    throw new InvalidNotationFormatException();
                }
            } else if (isOperator(ch)) {  // It's an operator
                try {
                    double operand2 = stack.pop(); // Pop two numbers
                    double operand1 = stack.pop();
                    // Perform the operation and push the result
                    double result = performOperation(operand1, operand2, ch);
                    stack.push(result);
                } catch (StackUnderflowException e) {
                    throw new InvalidNotationFormatException();
                }
            }
        }

        if (stack.size() != 1) {
            throw new InvalidNotationFormatException(); // More than one value left
        }

        try {
            return stack.pop(); // Final result
        } catch (StackUnderflowException e) {
            throw new InvalidNotationFormatException();
        }
    }

    /**
     * Checks if a character is an operator.
     *
     * @param ch The character to check.
     * @return True if it's an operator, otherwise false.
     */
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    /**
     * Determines the precedence of an operator.
     *
     * @param operator The operator to check.
     * @return Higher value means higher precedence.
     */
    private static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return -1; // Invalid operator
    }

    /**
     * Performs arithmetic operations.
     *
     * @param operand1 The first number.
     * @param operand2 The second number.
     * @param operator The operator.
     * @return The result of the operation.
     */
    private static double performOperation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                return 0;
        }
    }
}
