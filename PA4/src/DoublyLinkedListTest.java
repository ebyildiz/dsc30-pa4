import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.AbstractList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    DoublyLinkedList<Integer> abs1 = new DoublyLinkedList<>();
    DoublyLinkedList<Integer> abs2 = new DoublyLinkedList<>();
    DoublyLinkedList<Integer> abs3 = new DoublyLinkedList<>();

    AbstractList<Character> abs4 = new DoublyLinkedList<>();
     void add100(AbstractList list){
        for(int i=0; i<100; i++) list.add(i);
    }
    void remove100(AbstractList list){
        for(int i=99; i>=0; i--) list.remove(i);
    }

    @org.junit.jupiter.api.Test
    void addTest() {
        boolean check1 = abs1.add(0);
        boolean check2 = abs1.add(1);
        abs1.add(1, 2);
        boolean check3 = abs1.add(3);

        assertEquals(check1, true);
        assertEquals(check2, true);
        assertEquals(check3, true);

        assertEquals(4, abs1.size());
        assertEquals(3, abs1.get(3));
        assertEquals(2, abs1.get(1));


        add100(abs2);
        assertEquals(100, abs2.size());
        assertEquals(0, abs2.get(0));
        assertEquals(99, abs2.get(99));

        abs2.add(100);
        abs2.add(101);
        assertEquals(100, abs2.get(100));
        assertEquals(101, abs2.get(101));

        remove100(abs2);
        add100(abs2);
        assertEquals(100, abs2.get(0));
        assertEquals(101, abs2.get(1));
        assertEquals(102, abs2.size());



        Assertions.assertThrows(NullPointerException.class, () -> {
            abs1.add(null);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            abs2.add(null);
        });

        abs2.clear();
        abs1.clear();

    }

    @Test
    void addIndexTest() {
         add100(abs1);
         abs1.add(0, -1);
         assertEquals(101, abs1.size());
         assertEquals(-1, abs1.get(0));

         abs1.add(60, 605);
         assertEquals(605, abs1.get(60)); // was 59
        assertEquals(102, abs1.size());

        assertEquals(58, abs1.get(59));
        assertEquals(60, abs1.get(62));

        abs1.add(102, 102);
        assertEquals(102, abs1.get(102));
        assertEquals(103, abs1.size());

        abs1.clear();

        add100(abs1);

        Assertions.assertThrows(NullPointerException.class, () -> {
            abs1.add(0, null);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            abs1.add(50, null);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.add(101, 101);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.add(-1, 101);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.add(-20, 101);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.add(105, 101);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.add(400, 101);
        });

        abs1.clear();

    }
    @org.junit.jupiter.api.Test
    void clearTest() {
        add100(abs2);
        abs2.clear();
        assertEquals(0, abs2.size());
        abs2.add(0);
        abs2.add(0, 1);
        abs2.clear();
        assertEquals(0, abs2.size());
        abs2.clear();
        assertEquals(0, abs2.size());


    }



    @org.junit.jupiter.api.Test
    void containsTest() {
        boolean check1 = abs2.contains(0);
        assertEquals(false, check1);

        abs2.add(3);
        boolean check2 = abs2.contains(3);
        assertEquals(true, check2);
        assertEquals(false, check1);

        abs2.clear();
        check2 = abs2.contains(3);
        assertEquals(false, check2);

        abs2.add(2);
        abs2.add(3);
        abs2.add(4);

        boolean check3 = abs2.contains(3);
        assertEquals(true, check3);

        boolean check4 = abs2.contains(3);
        abs2.clear();
        check3 = abs2.contains(3);
        assertEquals(true, check4);
        assertEquals(false, check3);
        abs2.clear();

    }

    @org.junit.jupiter.api.Test
    void isEmptyTest() {
        abs2.add(3);
        abs2.add(4);
        assertEquals(false, abs2.isEmpty());
        abs2.clear();
        assertEquals(true, abs2.isEmpty());
        assertEquals(true, abs3.isEmpty());
        add100(abs3);
        assertEquals(false, abs3.isEmpty());
        abs3.clear();
        assertEquals(true, abs3.isEmpty());


    }

    @org.junit.jupiter.api.Test
    void removeTest() {
         add100(abs3);
         remove100(abs3); //removes the first 100
         assertEquals(0, abs3.size());
         add100(abs3);
         abs3.remove(0);
         assertEquals(false, abs3.contains(0));
         abs3.remove(2);
         assertEquals(false, abs3.contains(3));
         assertEquals(98, abs3.size());


        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.remove(400);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.remove(105);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.remove(-1);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.remove(-5);
        });

        abs3.clear();

    }

    @org.junit.jupiter.api.Test
    void setTest() {
         add100(abs1);
         Integer previous = abs1.set(0, 100);
         assertEquals(0, previous);
         assertEquals(false, abs1.contains(0));
         assertEquals(true, abs1.contains(100));
         assertEquals(100, abs1.get(0));

        Integer previous2 = abs1.set(4, 5000);
        assertEquals(4, previous2);
        assertEquals(false, abs1.contains(4));
        assertEquals(true, abs1.contains(5000));
        assertEquals(5000, abs1.get(4));

        Integer previous3 = abs1.set(6, 455);
        assertEquals(6, previous3);
        assertEquals(false, abs1.contains(6));
        assertEquals(true, abs1.contains(455));
        assertEquals(455, abs1.get(6));

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.set(105, 5);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.set(-1, 5);
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            abs1.set(0, null);
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            abs1.set(15, null);
        });

        abs1.clear();

    }

    @org.junit.jupiter.api.Test
    void testToString() {
         abs1.add(1);
         abs1.add(2);
         abs1.add(3);
         String expected = "[(head) -> 1 -> 2 -> 3 -> (tail)]";
         assertEquals(expected, abs1.toString());

         String expected2 = "[(head) -> (tail)]";
         assertEquals(expected2, abs2.toString());

         abs2.add(1);
        String expected3 = "[(head) -> 1 -> (tail)]";
        assertEquals(expected3, abs2.toString());

        abs1.clear();
        abs2.clear();
    }

    @Test
    void getTest() {
         add100(abs1);
         assertEquals(99, abs1.get(99));
         assertEquals(100, abs1.size());

         abs1.set(99, 1000);
        assertEquals(1000, abs1.get(99));
        assertEquals(100, abs1.size());

        Integer first = abs1.get(3);
        Integer second = abs1.get(4);
        Integer third = abs1.get(0);

        abs2.add(first); abs2.add(second); abs2.add(third);
        assertEquals(3, abs2.get(0));
        assertEquals(4, abs2.get(1));
        assertEquals(0, abs2.get(2));
        assertEquals(3, abs2.size());

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.get(1000);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.get(-1);
        });
        abs1.clear();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.get(0);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            abs1.get(100);
        });

        abs1.clear();


    }

    @Test
    void removeMultipleOfTest() {
         add100(abs1);
         abs1.removeMultipleOf(5);
         assertEquals(80, abs1.size());
         assertEquals(abs1.get(0), 1);
        assertEquals(abs1.get(5), 7);
        assertEquals(abs1.get(10), 13);
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            abs1.removeMultipleOf(0);
        });
        abs1.removeMultipleOf(89);
        assertEquals(79, abs1.size());
        assertEquals(abs1.get(0), 2);
        abs1.removeMultipleOf(1);
        assertEquals(0, abs1.size());

        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            abs1.removeMultipleOf(-3);
        });

        abs1.add(23);
        abs1.add(45);
        abs1.add(19);
        abs1.add(90);

        abs1.removeMultipleOf(4);
        assertEquals("[(head) -> 45 -> 19 -> 90 -> (tail)]", abs1.toString());
        abs1.removeMultipleOf(2);
        assertEquals("[(head) -> 19 -> (tail)]", abs1.toString());

        assertEquals(1, abs1.size());

        abs1.removeMultipleOf(1);
        assertEquals(0, abs1.size());
        assertEquals("[(head) -> (tail)]", abs1.toString());



    }

    @Test
    void swapSegmentTest() {
         abs1.add(50); //5 //50
         abs1.add(60); //4 //60
         abs1.add(3); //10 //3
         abs1.add(4); //0
         abs1.add(6);
         abs1.add(8);

        abs2.add(5); //50 //5
        abs2.add(4); //60 //4
        abs2.add(10); //3 //10
        abs2.add(0);  //4
        abs2.add(9);
        abs2.add(2);

        abs1.swapSegment(abs2, 3);
        assertEquals(50, abs2.get(0));
        assertEquals(60, abs2.get(1));
        assertEquals(3, abs2.get(2));
        assertEquals(4, abs2.get(3));
        assertEquals(9, abs2.get(4));
        assertEquals(2, abs2.get(5));

        abs2.swapSegment(abs1, 2);
        assertEquals(5, abs2.get(0));
        assertEquals(4, abs2.get(1));
        assertEquals(10, abs2.get(2));
        assertEquals(4, abs2.get(3));
        assertEquals(9, abs2.get(4));
        assertEquals(2, abs2.get(5));

        abs1.clear();
        abs2.clear();



    }
}
