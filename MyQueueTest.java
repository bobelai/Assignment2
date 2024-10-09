
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyQueueTest {
    public MyQueue<String> stringQ;
    public String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
    public ArrayList<String> fill = new ArrayList<String>();

    // STUDENT: student tests will use the doubleQ
    public MyQueue<Double> doubleQ;

    @BeforeEach
    public void setUp() throws Exception {
        stringQ = new MyQueue<>(5);
        stringQ.enqueue(a);
        stringQ.enqueue(b);
        stringQ.enqueue(c);

        // STUDENT: add setup for doubleQ for student tests
        doubleQ = new MyQueue<>(5);
        doubleQ.enqueue(1.1);
        doubleQ.enqueue(2.2);
        doubleQ.enqueue(3.3);
    }

    @AfterEach
    public void tearDown() throws Exception {
        stringQ = null;
        doubleQ = null;
    }

    @Test
    public void testIsEmpty() {
        assertEquals(false, stringQ.isEmpty());
        stringQ.dequeue();
        stringQ.dequeue();
        stringQ.dequeue();
        assertEquals(true, stringQ.isEmpty());
    }

    @Test
    public void testDequeue() {
        try {
            assertEquals(a, stringQ.dequeue());
            assertEquals(b, stringQ.dequeue());
            assertEquals(c, stringQ.dequeue());
            // Queue is empty, next statement should cause QueueUnderFlowException
            stringQ.dequeue();
            assertTrue("This should have caused a com.example.javafoox.QueueUnderflowException", false);
        } catch (QueueUnderflowException e) {
            assertTrue("This should have caused a com.example.javafoox.QueueUnderflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused a com.example.javafoox.QueueUnderflowException", false);
        }
    }

    @Test
    public void testDequeueStudent() {
        try {
            assertEquals(1.1, doubleQ.dequeue(), 0.001);
            assertEquals(2.2, doubleQ.dequeue(), 0.001);
            assertEquals(3.3, doubleQ.dequeue(), 0.001);
            // Queue should be empty now
            doubleQ.dequeue();
            fail("This should have caused a com.example.javafoox.QueueUnderflowException");
        } catch (QueueUnderflowException e) {
            assertTrue("com.example.javafoox.QueueUnderflowException correctly thrown", true);
        }
    }

    @Test
    public void testSize() {
        assertEquals(3, stringQ.size());
        stringQ.enqueue(d);
        assertEquals(4, stringQ.size());
        stringQ.dequeue();
        stringQ.dequeue();
        assertEquals(2, stringQ.size());
    }

    @Test
    public void testEnqueue() {
        try {
            assertEquals(3, stringQ.size());
            assertEquals(true, stringQ.enqueue(d));
            assertEquals(4, stringQ.size());
            assertEquals(true, stringQ.enqueue(e));
            assertEquals(5, stringQ.size());
            // Queue is full, next statement should cause com.example.javafoox.QueueOverflowException
            stringQ.enqueue(f);
            assertTrue("This should have caused a com.example.javafoox.QueueOverflowException", false);
        } catch (QueueOverflowException e) {
            assertTrue("This should have caused a com.example.javafoox.QueueOverflowException", true);
        } catch (Exception e) {
            assertTrue("This should have caused a com.example.javafoox.QueueOverflowException", false);
        }
    }

    @Test
    public void testEnqueueStudent() {
        try {
            doubleQ.enqueue(4.4);
            assertEquals(4, doubleQ.size());
            doubleQ.enqueue(5.5);
            assertEquals(5, doubleQ.size());
            // Queue should now be full
            doubleQ.enqueue(6.6);
            fail("This should have caused a com.example.javafoox.QueueOverflowException");
        } catch (QueueOverflowException e) {
            assertTrue("com.example.javafoox.QueueOverflowException correctly thrown", true);
        }
    }

    @Test
    public void testIsFull() {
        assertEquals(false, stringQ.isFull());
        stringQ.enqueue(d);
        stringQ.enqueue(e);
        assertEquals(true, stringQ.isFull());
    }

    @Test
    public void testToString() {
        assertEquals("abc", stringQ.toString());
        stringQ.enqueue(d);
        assertEquals("abcd", stringQ.toString());
        stringQ.enqueue(e);
        assertEquals("abcde", stringQ.toString());
    }

    @Test
    public void testToStringStudent() {
        assertEquals("1.12.23.3", doubleQ.toString());
        doubleQ.dequeue();
        assertEquals("2.23.3", doubleQ.toString());
    }

    @Test
    public void testToStringDelimiter() {
        assertEquals("a%b%c", stringQ.toString("%"));
        stringQ.enqueue(d);
        assertEquals("a&b&c&d", stringQ.toString("&"));
        stringQ.enqueue(e);
        assertEquals("a/b/c/d/e", stringQ.toString("/"));
    }

    @Test
    public void testFill() {
        fill.add("apple");
        fill.add("banana");
        fill.add("carrot");
        // start with an empty queue
        stringQ = new MyQueue<>(5);
        // fill with an ArrayList
        stringQ.fill(fill);
        assertEquals(3, stringQ.size());
        assertEquals("apple", stringQ.dequeue());
        assertEquals("banana", stringQ.dequeue());
        assertEquals("carrot", stringQ.dequeue());
    }
}
