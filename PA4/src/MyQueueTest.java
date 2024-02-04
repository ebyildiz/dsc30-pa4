import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    MyQueue ex1 = new MyQueue<Character>();
    MyQueue ex2 = new MyQueue<Character>();
    MyQueue ex3 = new MyQueue<Character>();

    @Test
    void isEmpty() {
        boolean result = ex1.isEmpty();
        assertEquals(true, result);

        ex1.enqueue('0');
        boolean result1 = ex1.isEmpty();
        assertEquals(false, result1);

        ex1.dequeue();
        assertEquals(false, result1);
        assertEquals(true, ex2.isEmpty());

        ex2.enqueue('0');
        ex2.enqueue('1');
        ex2.dequeue();
        assertEquals(false, ex2.isEmpty());

        ex2.dequeue();
        assertEquals(true, ex2.isEmpty());

    }

    @Test
    void size() {
        assertEquals(0, ex1.size());
        assertEquals(0, ex2.size());

        ex1.enqueue('0');
        ex1.enqueue('1');
        ex1.enqueue('2');
        assertEquals(3, ex1.size());

        ex2.enqueue('0');
        ex2.enqueue('1');
        ex2.enqueue('2');
        ex2.enqueue('3');
        ex2.enqueue('3');
        ex2.enqueue('4');
        ex2.enqueue('4');
        ex2.enqueue('0');
        ex2.enqueue('4');
        ex2.enqueue('0');
        ex2.enqueue('4');
        ex2.enqueue('4');
        assertEquals(12, ex2.size() );
    }

    @Test
    void enqueue() {
        ex2.enqueue('0');
        ex2.enqueue('1');
        ex2.dequeue();
        ex2.enqueue('2');
        ex2.enqueue('3');
        ex2.enqueue('3');
        ex2.enqueue('4');
        ex2.enqueue('4');
        ex2.enqueue('0');
        ex2.dequeue();
        ex2.enqueue('4');
        ex2.enqueue('0');
        ex2.enqueue('4');
        ex2.enqueue('4');
        assertEquals(10, ex2.size() );
        assertEquals('2', ex2.peek());

        ex2.enqueue('5');
        ex2.dequeue();
        assertEquals(10, ex2.size());

        for(int i=0; i<100; i++){
            ex1.enqueue((char) i );
        }
        assertEquals(100, ex1.size());

        for(int i=0; i<50; i++){
            ex1.dequeue();
        }
        assertEquals(50, ex1.size());

        for(int i=0; i<50; i++){
            ex1.dequeue();
        }
        assertEquals(0, ex1.size());
        assertEquals(true, ex1.isEmpty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ex1.enqueue(null);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ex2.enqueue(null);
        });

    }

    @Test
    void dequeue() {
        for(int i=0; i<8; i++) {
            ex2.enqueue((char) i);
        }
        ex2.dequeue();
        assertEquals(7, ex2.size());

        for(int i=0; i<3; i++) {
            ex2.dequeue();
        }
        assertEquals(4, ex2.size());

        for(int i=0; i<100; i++) {
            ex2.enqueue('0');
            ex2.dequeue();
        }
        assertEquals(4, ex2.size());
        assertEquals('0', ex2.peek());

        assertEquals(null, ex3.dequeue());
    }

    @Test
    void peek() {
        ex2.enqueue('0');
        ex2.enqueue('1');
        ex2.dequeue();
        assertEquals('1', ex2.peek());

        ex2.enqueue('2');
        ex2.dequeue();
        ex2.enqueue('3');
        assertEquals('2', ex2.peek());

        ex2.enqueue('4');
        ex2.enqueue('5');
        assertEquals('2', ex2.peek());

        assertEquals(null, ex3.peek());
    }
}