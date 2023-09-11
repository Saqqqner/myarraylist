package ru.adel;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;


/**
 * Класс MyArrayList представляет собой реализацию динамического массива в Java.
 * Он позволяет хранить и управлять элементами списка.
 *
 * @param <E> Тип элементов, которые будут храниться в списке.
 */
public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    /**
     * Конструктор без параметров создает список с начальной емкостью по умолчанию.
     */
    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Конструктор создает список с указанной начальной емкостью.
     *
     * @param initCapacity Начальная емкость списка (не может быть отрицательной).
     * @throws IllegalArgumentException Если начальная емкость отрицательна.
     */
    public MyArrayList(int initCapacity) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("Initial  Capacity cannot be negative");
        }
        data = new Object[initCapacity];
        size = 0;
    }

    /**
     * Возвращает текущее количество элементов в списке.
     *
     * @return Количество элементов.
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return true, если список пуст, в противном случае false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Получает элемент по индексу.
     *
     * @param index Индекс элемента.
     * @return Элемент по указанному индексу.
     * @throws IndexOutOfBoundsException Если индекс находится вне допустимого диапазона.
     */
    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element Элемент для добавления.
     */
    public void add(E element) {
        ensureCapasity(size + 1);
        data[size++] = element;

    }
    /**
     * Добавляет элемент в указанную позицию списка.
     *
     * @param index   Индекс, в который нужно добавить элемент.
     * @param element Элемент для добавления.
     * @throws IllegalArgumentException Если индекс находится вне допустимого диапазона.
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        ensureCapasity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;

    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index Индекс элемента для удаления.
     * @throws IndexOutOfBoundsException Если индекс находится вне допустимого диапазона.
     */
    public void remove(int index) {
        checkIndex(index);
        int numToMove = size - index - 1;
        if (index >= 0) {
            System.arraycopy(data, index + 1, data, index, numToMove);
        }
        data[size--] = null;
    }


    /**
     * Очищает список, удаляя все элементы.
     */
    public void clear() {
        Arrays.fill(data, 0, size, null);
        size = 0;
    }
    /**
     * Заменяет элемент по указанному индексу новым элементом.
     *
     * @param index   Индекс элемента для замены.
     * @param element Новый элемент для замены.
     * @throws IndexOutOfBoundsException Если индекс находится вне допустимого диапазона.
     */
    public void replace(int index, E element) {
        checkIndex(index);
        data[index] = element;
    }

    /**
     * Выполняет действие над каждым элементом списка.
     *
     * @param action Действие, выполняемое над каждым элементом.
     */
    public void forEach(Consumer<? super E> action) {
        for (int i = 0; i < size; i++) {
            action.accept((E) data[i]);

        }

    }
    /**
     * Сортирует элементы списка с использованием быстрой сортировки.
     *
     * @param comparator Компаратор для сравнения элементов.
     */
    public void quickSort(Comparator<? super E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Сортирует элементы списка с использованием быстрой сортировки.
     */
    public void quickSort() {
        quickSort(0, size - 1);
    }


    private void quickSort(int low, int high, Comparator<? super E> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }


    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high, Comparator<? super E> comparator) {
        E pivot = get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(get(j), pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private int partition(int low, int high) {
        E pivot = get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(get(j), pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }


    private int compare(E a, E b) {
        if (a instanceof Comparable) {
            return ((Comparable<E>) a).compareTo(b);
        } else {
            throw new IllegalArgumentException("Elements must implement Comparable");
        }
    }

    private void swap(int i, int j) {
        E temp = get(i);
        replace(i, get(j));
        replace(j, temp);
    }

    private void ensureCapasity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length * 2, minCapacity);
            data = Arrays.copyOf(data, newCapacity);

        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }


    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyArrayList<?> that = (MyArrayList<?>) o;

        if (size != that.size) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(data);
        result = 31 * result + size;
        return result;
    }
}


