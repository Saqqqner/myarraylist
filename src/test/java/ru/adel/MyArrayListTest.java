package ru.adel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new MyArrayList<>();

    }

    @Test
    void testAddAndGet() {
        list.add(31);
        assertEquals(31, list.get(0));
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        list.add(31);
        list.add(31);
        assertEquals(2, list.size());
    }

    @Test()
    void tesIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(11);
        assertFalse(list.isEmpty());
    }

    @Test
    void testAddByIndex() {
        list.add(31);
        list.add(22);
        assertEquals(22, list.get(1));
        list.add(1, 55);
        assertEquals(55, list.get(1));
        assertEquals(22, list.get(2));
    }

    @Test
    void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(0);
        assertEquals(2, list.get(0));
        assertEquals(2, list.size());
    }

    @Test
    void testReplace() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.replace(2, 4);
        assertEquals(4, list.get(2));
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void testQuickSortByComparator() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.quickSort((a, b) -> b - a);
        assertEquals(4, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(1, list.get(3));
    }

    @Test
    void testQuickSort() {
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        list.quickSort();
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));

    }

    @Test
    void testForEach() {
        list.add(1);
        list.add(2);
        final int[] sum = {0};
        list.forEach(item -> sum[0] += item);
        assertEquals(3, sum[0]);
    }

    @Test
    void testGetOutOfBounds() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void testAddNegativeIndex() {
        assertThrows(IllegalArgumentException.class, () -> list.add(-1, 42));
    }

    @Test
    void testRemoveOutOfBounds() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void testReplaceOutOfBounds() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.replace(1, 42));
    }

    @Test
    void testEquals() {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        assertTrue(list.equals(list1));

    }
}