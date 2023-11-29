package Lab_2_1_3.AVLTree;

import Collections.MyArrayList;
import Collections.MyLinkedList;
import Collections.MyQueue;
import Collections.MyStack;
import Lab_2_1_3.Node;

public class TreeAVL <T extends Comparable<T>>{
    private NodeAVL<T> root = new NodeAVL<>(null);

    /*public TreeAVL(T key){
        root = new NodeAVL<T>(key);
    }*/
    int hight(NodeAVL<T> p){
        return p==null ? 0 : p.height;
    }

    void fixHight (NodeAVL<T> p){
        p.height = 1 + Math.max(hight(p.left), hight(p.right));
    }

    int difHight (NodeAVL<T> p){
        return hight(p.right) - hight(p.left);
    }

    NodeAVL<T> rotateRight(NodeAVL<T> p){
        NodeAVL<T> left = p.left;
        p.left = left.right;
        left.right = p;
        fixHight(p);
        fixHight(left);
        return left;
    }

    NodeAVL<T> rotateLeft(NodeAVL<T> p){
        NodeAVL<T> right = p.right;
        p.right = right.left;
        right.left = p;
        fixHight(p);
        fixHight(right);
        return right;
    }

    NodeAVL<T> balance(NodeAVL<T> p){
        fixHight(p); // dell
        if(difHight(p) == 2){
            if (difHight(p.right) < 0){
                p.right = rotateRight(p.right);
            }
            return rotateLeft(p);
        }
        if(difHight(p) == -2){ //else if
            if (difHight(p.left) >0){
                p.left = rotateLeft(p.left);
            }
            return rotateRight(p);
        }
        return p;
    }

    NodeAVL<T> insert(NodeAVL<T> p, T key){
        if (p == null){
            return new NodeAVL<T>(key);
        }
        if (key.compareTo(p.key) < 0)
            p.left = insert(p.left, key);

        else
            p.right = insert(p.right, key);


        return balance(p);
    }

    public void add(T key){
        if (root.key == null)
            root.key = key;
        else
            root = insert(root, key);
    }

    NodeAVL<T> findMin(NodeAVL<T> p){
        return p.left == null ? p : findMin(p.left);
    }

    NodeAVL<T> removeMin(NodeAVL<T> p){
        if(p.left == null) return p.right;
        p.left = removeMin(p.left);
        return balance(p);
    }

    NodeAVL<T> remove(NodeAVL<T> p, T key){
        if (p == null) return null;
        if (key.compareTo(p.key) < 0){
            p.left = remove(p.left, key);
        }
        if (key.compareTo(p.key) > 0){
            p.right = remove(p.right, key);
        }

        else{
            NodeAVL<T> left = p.left;
            NodeAVL<T> right = p.right;
            if (right == null) return left;
            NodeAVL<T> min = findMin(right);
            min.right = removeMin(right);
            min.left = left;
            return balance(min);
        }
        return balance(p);
    }

    public void removeAt(T key){
        remove(root, key);
    }


    public MyArrayList<T> contLevelOrder(){
        MyQueue<NodeAVL> queue = new MyLinkedList<>();
        MyArrayList<T> list = new MyArrayList<>();
        NodeAVL<T> cur = root;
        do{

            list.add(cur.key);
            if (cur.left!=null) queue.add(cur.left);
            if (cur.right!=null) queue.add(cur.right);
            if (!queue.isEmpty()) cur=queue.poll();
        }while (!queue.isEmpty());
        list.add(cur.key);
        return list;
    }

    public MyArrayList<T> contPreOrder(){
        MyStack<NodeAVL> stack  = new MyLinkedList<>();
        MyArrayList<T> list = new MyArrayList<>();
        NodeAVL<T> cur = root;
        while (!stack.isEmpty() || cur != null){
            while (cur != null){
                list.add(cur.key);
                if (cur.right != null) stack.push(cur.right);
                cur = cur.left;
            }
            if (!stack.isEmpty()){
                cur = stack.pop();
            }
        }

        return list;
    }

    public MyArrayList<T> contInOrder(){
        MyStack<NodeAVL> stack  = new MyLinkedList<>();
        MyArrayList<T> list = new MyArrayList<>();
        NodeAVL<T> cur = root;
        while (!stack.isEmpty() || cur != null){
            if (!stack.isEmpty()){
                cur = stack.pop();
                list.add(cur.key);
                if (cur.right != null) cur = cur.right;
                else cur = null;
            }
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
        }

        return list;
    }

    public MyArrayList<T> contPostOrder(){
        MyStack<NodeAVL> stack  = new MyLinkedList<>();
        MyArrayList<T> list = new MyArrayList<>();
        NodeAVL<T> cur = root;
        while (!stack.isEmpty() || cur != null){
            if (!stack.isEmpty()){
                cur = stack.pop();
                if (!stack.isEmpty() && cur.right == stack.peek()){
                    cur = stack.pop();
                }
                else {
                    list.add(cur.key);
                    cur = null;
                }
            }
            while (cur != null){
                stack.push(cur);
                if(cur.right != null){
                    stack.push(cur.right);
                    stack.push(cur);
                }
                cur = cur.left;
            }
        }

        return list;
    }


    private void doPreOrder(NodeAVL<T> cur,  MyArrayList<T> list) {
        if (cur == null)
            return;
        list.add(cur.key);
        doPreOrder(cur.left,  list);
        doPreOrder(cur.right,  list);
    }

    public MyArrayList<T> preOrder() {
        MyArrayList<T> list = new MyArrayList<>();
        doPreOrder(root, list);
        return list;
    }
}

