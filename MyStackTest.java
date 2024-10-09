
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyStackTest {
    public MyStack<String> stringS;
    public String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
    public ArrayList<String> fill = new ArrayList<String>();

    // STUDENT: student tests will use the doubleS
    public MyStack<Double> doubleS;

    @BeforeEach
    public void setUp() throws Exception {
        stringS = new MyStack<>(5);
        stringS.push(a);
        stringS.push(b);
        stringS.push(c);

        // STUDENT: add setup for doubleS for student tests
        doubleS = new MyStack<>(5);
        doubleS.push(1.1);
        doubleS.push(2.2);
        doubleS.push(3.3);
    }

    @AfterEach
    public void tearDown() throws Exception {
        stringS = null;
        doubleS = null;
    }

    @Test
    public void testIsEmpty() {
        assertEquals(false, stringS.isEmpty());
        stringS.pop();
        stringS.pop();
        stringS.pop();
        assertEquals(true, stringS.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertEquals(false, stringS.isFull());
        stringS.push(d);
        stringS.push(e);
        assertEquals(true, stringS.isFull());
    }

    @Test
    public void testPop() {
        try {
            assertEquals(c, stringS.pop());
            assertEquals(b, stringS.pop());
            assertEquals(a, stringS.pop());
            // Stack is empty, next statement should cause com.example.javafoox.StackUnderflowException
            stringS.pop();
            assertTrue("This should have caused a com.example.javafoox.StackUnderflowException", false);
        } catch (StackUnderflowException e) {
            assertTrue("This should have caused a com.example.javafoox.StackUnderflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused a com.example.javafoox.StackUnderflowException", false);
        }
    }

    @Test
    public void testPopStudent() {
        try {
            assertEquals(3.3, doubleS.pop(), 0.001);
            assertEquals(2.2, doubleS.pop(), 0.001);
            assertEquals(1.1, doubleS.pop(), 0.001);
            // Stack should be empty now
            doubleS.pop();
            fail("This should have caused a com.example.javafoox.StackUnderflowException");
        } catch (StackUnderflowException e) {
            assertTrue("com.example.javafoox.StackUnderflowException correctly thrown", true);
        }
    }

    @Test
    public void testTop() {
        assertEquals(c, stringS.top());
        stringS.push(d);
        assertEquals(d, stringS.top());
        stringS.pop();
        stringS.pop();
        assertEquals(b, stringS.top());
    }

    @Test
    public void testSize() {
        assertEquals(3, stringS.size());
        stringS.push(d);
        assertEquals(4, stringS.size());
        stringS.pop();
        stringS.pop();
        assertEquals(2, stringS.size());
    }

    @Test
    public void testPush() {
        try {
            assertEquals(3, stringS.size());
            assertEquals(true, stringS.push(d));
            assertEquals(4, stringS.size());
            assertEquals(true, stringS.push(e));
            assertEquals(5, stringS.size());
            // Stack is full, next statement should cause com.example.javafoox.StackOverflowException
            stringS.push(f);
            assertTrue("This should have caused a com.example.javafoox.StackOverflowException", false);
        } catch (StackOverflowException e) {
            assertTrue("This should have caused a com.example.javafoox.StackOverflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused a com.example.javafoox.StackOverflowException", false);
        }
    }

    @Test
    public void testPushStudent() {
        try {
            doubleS.push(4.4);
            assertEquals(4, doubleS.size());
            doubleS.push(5.5);
            assertEquals(5, doubleS.size());
            // Stack should now be full
            doubleS.push(6.6);
            fail("This should have caused a com.example.javafoox.StackOverflowException");
        } catch (StackOverflowException e) {
            assertTrue("com.example.javafoox.StackOverflowException correctly thrown", true);
        }
    }

    @Test
    public void testToString() {
        assertEquals("abc", stringS.toString());
        stringS.push(d);
        assertEquals("abcd", stringS.toString());
        stringS.push(e);
        assertEquals("abcde", stringS.toString());
    }

    @Test
    public void testToStringStudent() {
        assertEquals("1.12.23.3", doubleS.toString());
        doubleS.pop();
        assertEquals("1.12.2", doubleS.toString());
    }

    @Test
    public void testToStringDelimiter() {
        assertEquals("a%b%c", stringS.toString("%"));
        stringS.push(d);
        assertEquals("a&b&c&d", stringS.toString("&"));
        stringS.push(e);
        assertEquals("a/b/c/d/e", stringS.toString("/"));
    }

    @Test
    public void testFill() {
        fill.add("apple");
        fill.add("banana");
        fill.add("carrot");
        // start with an empty stack
        stringS = new MyStack<>(5);
        // fill with an ArrayList
        stringS.fill(fill);
        assertEquals(3, stringS.size());
        assertEquals("carrot", stringS.pop());
        assertEquals("banana", stringS.pop());
        assertEquals("apple", stringS.pop());
    }
}
