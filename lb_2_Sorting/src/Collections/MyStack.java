package Collections;

public interface MyStack<T>{
    T push(T item);

    T pop();

    T peek();

    boolean isEmpty();

    int getSize();



    //void copy(MyLinkedList<T> copyList);
}
