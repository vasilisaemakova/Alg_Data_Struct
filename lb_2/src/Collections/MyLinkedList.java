package Collections;
import java.util.EmptyStackException;

public class MyLinkedList<T> implements MyList<T>, MyStack<T>, MyQueue<T> {

    private int size = 0;
    private Node<T> head;


    public void copy(MyLinkedList<T> copyList){

        for (int i = 0; i < copyList.size; i++){
            this.push(copyList.get(i));
        }
    }


    public T push(T item) {
        add(item, 0);
        return item;
    }


    public T pop() {
        return removeAt(0);
    }


    public T peek() {
        if (head == null)
            throw new EmptyStackException();

        return head.value;
    }




    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean add(T value) {

        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = new Node<>(value, null);
        }

        size++;
        return true;
    }

    public T poll() {
        return removeAt(0);
    }

    public void insert(T data, int index) {

    }

    public void insertBack(int indexFrom, int indexTo) {

    }

    public boolean add(T value, int index) {

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(index);

        if (index == 0) {
            head = new Node<>(value, head);
            size++;
            return true;
        } else {
            Node<T> temp = head;
            int currIdx = 0;
            while (temp.next != null) {
                if (currIdx + 1 == index) {
                    Node<T> next = temp.next;
                    temp.next = new Node<>(value, next);
                    size++;
                    return true;
                }
                currIdx++;
                temp = temp.next;
            }
        }

        return false;
    }

    public boolean remove(Object o) {
        Node<T> temp = new Node<>(null, head);
        while (temp.next != null) {
            if (temp.next.value.equals(o)) {
                temp.next = temp.next.next;
                size--;
                return true;
            }
            temp = temp.next;
        }

        return false;
    }




    public T removeAt(int index) {
        if (index == 0) {
            T result = head.value;
            head = head.next;
            size--;
            return result;
        }

        Node<T> previous = null;
        int currIdx = 0;
        Node<T> temp = head;
        while (temp != null) {
            if (currIdx == index) {
                T result = temp.value;;
                previous.next = temp.next;
                size--;
                return result;
            }

            previous = temp;
            temp = temp.next;
            currIdx++;

        }

        throw new IndexOutOfBoundsException(index);
    }

    public T get(int index) {
        int currIdx = 0;
        Node<T> temp = head;

        while (temp != null) {
            if (currIdx == index) {
                return temp.value;
            }

            temp = temp.next;
            currIdx++;
        }

        throw new IndexOutOfBoundsException(index);
    }

    public T set(int index, T element) {
        int currIdx = 0;
        Node<T> temp = head;
        while (temp != null) {
            if (currIdx == index) {
                temp.value = element;
                return element;
            }

            currIdx++;
            temp = temp.next;
        }

        throw new IndexOutOfBoundsException(index);
    }


    public boolean contains(Object o) { return indexOf(o) != -1; }

    public int indexOf(Object o) {
        int currIdx = 0;
        Node<T> temp  = head;

        while (temp != null) {
            if (temp.value == o)
                return currIdx;

            temp = temp.next;
            currIdx++;
        }

        return -1;
    }

    public void clear() {
        Node<T> temp = head;
        while (temp != null) {
            Node<T> next = temp.next;
            temp.next = null;
            temp.value = null;
            temp = next;
        }
        head = null;
        size = 0;
    }

    public void sort() {

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> temp = head;
        while (temp.next != null) {
            sb.append(temp.value).append(", ");
            temp = temp.next;
        }
        return sb.append(temp.value).append("]").toString();
    }

//////////////////

///////////////////////////////////////
    private static class Node<T> {
        Node<T> next;
        T value;


        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

}
