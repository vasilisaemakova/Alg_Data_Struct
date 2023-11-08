package Collections;

public interface MyList<T> {





    int getSize();

    boolean isEmpty();

    boolean add(T data);


    void insert(T data, int index);

    void insertBack(int indexFrom, int indexTo);

    boolean remove(Object o);

    T removeAt(int index);

    T get(int index);

    T set(int index, T element);

    boolean contains(Object obj);

    int indexOf(Object o);

    void clear();


    //void sort();

}

