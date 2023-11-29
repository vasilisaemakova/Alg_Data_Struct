package Collections;

public class MyArrayList<T> implements MyList<T> {

    private final int defCapacity = 10;
    private int size, capacity = defCapacity;
    private Object[] arr;

    public MyArrayList(int size) {
        this.size = size;
        arr = new Object[size];
    }

    public MyArrayList() {
        size = 0;
        arr = new Object[capacity];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /* public boolean contains(T data){  /
         for (int i = 0; i < size; i++){
             if ((T)arr[i] == data) return true;
         }
         return false;
     };*/
    public boolean contains(Object obj) {  //
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) return true;
        }
        return false;
    }

    ;

    private void reCapacity() {
        capacity *= 1.5;
        Object[] newArr = new Object[(int) (capacity)];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    public boolean add(T data) {
        if (size >= capacity) reCapacity();
        arr[size++] = data;
        return true;
    }

    public void insert(T data, int index) {
        if (size >= capacity) reCapacity();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        System.arraycopy(arr, index, arr, index + 1, ++size - index);
        arr[index] = data;
    }

    public void insertBack(int indexFrom, int indexTo) {
        T temp = (T) arr[indexFrom];
        for (int i = indexFrom; i > indexTo; i--) {
            arr[i] = arr[i - 1];
        }
        arr[indexTo] = temp;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);//
        return (T) arr[index];
    }

    public T set(int index, T data) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);

        arr[index] = data;
        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);
        System.arraycopy(arr, index + 1, arr, index, (--size) - index + 1);
        return (T) arr[index];
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) return i;
        }
        return -1;
    }

    public boolean remove(Object obj) {
        int count = 0, index;
        do {
            index = indexOf(obj);
            if (index == -1) {
                if (count == 0) return false;
                return true;
            }

            removeAt(index);
            count += 1;

        } while (true);
    }

    public void clear() {
        arr = new Object[defCapacity];
        size = 0;
    }

    public String toString() {
        if (size == 0)
            return "empty";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(arr[i]).append(", ");
        }
        return sb.append(arr[size - 1]).append("]").toString();
    }


}
