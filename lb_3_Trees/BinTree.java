package Lab_2_1_3;

import Collections.MyArrayList;

public class BinTree <T>{
    Node root = null;

    public Node getRoot() {
        return root;
    }

    MyArrayList<T> arrayTree = new MyArrayList<>();
    public BinTree(Node root){
        this.root = root;
    }

    public void preOrder(Node node)
    {
        if (node == null)
            return;
        arrayTree.add((T) node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public MyArrayList<T> getTree(){
        preOrder(root);
        //System.out.println(arrayTree.toString());
        return arrayTree;
    }

}
