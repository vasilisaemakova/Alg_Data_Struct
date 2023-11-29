package Lab_2_1_3.AVLTree;

import Lab_2_1_3.Node;

public class NodeAVL<T> {
    T key;
    int height;
    NodeAVL left, right;
    public NodeAVL(T key){
        this.key = key;
        left = null;
        right = null;
        height = 1;
    }

}
