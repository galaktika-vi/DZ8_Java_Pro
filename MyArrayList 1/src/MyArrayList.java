import java.util.*;

public class MyArrayList implements Iterable<Integer> {
    private static final int DEFAULT_CAPACITY = 10;
    private Integer[] elements;
    private int size = 0;

    public MyArrayList() {
        elements = new Integer[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Начальная емкость не может быть отрицательной.");
        }
        elements = new Integer[initialCapacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<Integer> iterator() {
        return new MyIterator();
    }

    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    public Integer get(int index) {
        checkIndex(index);
        return elements[index];
    }

    public Integer set(int index, Integer element) {
        checkIndex(index);
        Integer oldValue = elements[index];
        elements[index] = element;
        return oldValue;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void add(Integer element) {
        ensureCapacity();
        elements[size] = element;
        size++;
    }

    public void add(int index, Integer element) {
        checkIndexForAdd(index);
        ensureCapacity();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    public Integer remove(int index) {
        checkIndex(index);
        Integer oldValue = elements[index];
        removeAtIndex(index);
        return oldValue;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    removeAtIndex(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    removeAtIndex(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс находится вне допустимого диапазона.");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс находится вне допустимого диапазона.");
        }
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            Integer[] newElements = new Integer[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    private void removeAtIndex(int index) {
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
    }

    private class MyIterator implements Iterator<Integer> {
        private int cursor = 0;

        public boolean hasNext() {
            return cursor != size;
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Integer nextValue = elements[cursor];
            cursor++;
            return nextValue;
        }

        public void remove() {
            throw new UnsupportedOperationException("Метод remove() не поддерживается.");
        }
    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
