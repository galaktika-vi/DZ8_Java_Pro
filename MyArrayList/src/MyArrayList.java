import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList {
    private static final int DEFAULT_CAPACITY = 10;
    private Integer[] array;
    private int size;

    public MyArrayList() {
        array = new Integer[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<Integer> iterator() {
        return new MyArrayListIterator();
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }

    public void set(int index, Integer value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        array[index] = value;
    }

    public boolean contains(Object obj) {
        for (int i = 0; i < size; i++) {
            if (obj == null) {
                if (array[i] == null) {
                    return true;
                }
            } else {
                if (obj.equals(array[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < size; i++) {
            if (obj == null) {
                if (array[i] == null) {
                    return i;
                }
            } else {
                if (obj.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        for (int i = size - 1; i >= 0; i--) {
            if (obj == null) {
                if (array[i] == null) {
                    return i;
                }
            } else {
                if (obj.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void add(Integer value) {
        if (size == array.length) {
            ensureCapacity();
        }
        array[size++] = value;
    }

    private void ensureCapacity() {
        int newCapacity = array.length * 2;
        Integer[] newArray = new Integer[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private class MyArrayListIterator implements Iterator<Integer> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[index++];
        }
    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        // Пример использования методов
        System.out.println("Size: " + list.size());
        System.out.println("Is Empty: " + list.isEmpty());

        System.out.println("Contains 2: " + list.contains(2));
        System.out.println("Index of 3: " + list.indexOf(3));
        System.out.println("Last Index of 2: " + list.lastIndexOf(2));

        list.clear();
        System.out.println("Cleared. Is Empty: " + list.isEmpty());
    }
}
