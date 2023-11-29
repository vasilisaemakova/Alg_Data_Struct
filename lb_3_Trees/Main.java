package Lab_2_1_3;

import Collections.MyArrayList;
import Collections.MyLinkedList;
import Collections.MyStack;
import Lab_2_1_3.AVLTree.TreeAVL;

import java.io.IOException;
import java.nio.file.*;





public class Main {


    public static void main(String[] args) throws Exception {


        String input = Files.readString(Paths.get("C:\\Users\\vasil\\IdeaProjects\\Lab_2_1_3\\fileInput.txt"));

        int curIndChar = 0;
        char curChar;
        int sizeInput = input.length();
        MyStack<Node> stackNode = new MyLinkedList<>();
        MyStack<Character> stackBrack = new MyLinkedList<>();
        BinTree<Integer> tree = null;


        while (curIndChar < sizeInput) {
            curChar = input.charAt(curIndChar);
            if (curChar == '('){
                stackBrack.push(curChar);
            }

            else if (curChar == ')'){
                stackBrack.pop();
                stackNode.pop();
            }

            else{
                //input num
                char nextChar = input.charAt(curIndChar + 1);
                int num = 0;
                while (nextChar >= '0' && nextChar <= '9') {
                    num *= 10;
                    num += (curChar - '0');
                    curChar = nextChar;
                    curIndChar++;
                    nextChar = input.charAt(curIndChar + 1);
                }
                num *= 10;
                num += (curChar - '0');
                Node curNode = new Node(num);
                if (stackNode.isEmpty()){
                    stackNode.push(curNode);
                    tree = new BinTree<>(curNode);
                }
                else{
                    Node last = stackNode.peek();
                    if (last.left == null) last.left = curNode;
                    else if (last.right == null) last.right = curNode;
                    else{
                        throw new Exception("It's not bin Tree");
                    }
                    stackNode.push(curNode);

                }

            }
            curIndChar++;
            //debag.viewStack(stackNode);


        }

        //TreeAVL<Integer> treeAVL = new TreeAVL( (Integer)tree.getRoot().data);
        TreeAVL<Integer> treeAVL = new TreeAVL();
        MyArrayList<Integer> treeArray = tree.getTree();


        for (int i = 0; i < treeArray.getSize(); i++){
            treeAVL.add(treeArray.get(i));
        }


        System.out.println("Input:\t\t\t\t" + input);
        System.out.println("Tree:\t\t\t\t" + treeArray.toString());
        System.out.println("AVL Tree:\t\t\t" + treeAVL.preOrder().toString());
        System.out.println("contLevelOrder:\t\t" + treeAVL.contLevelOrder().toString());//в ширину
        System.out.println("contPreOrder:\t\t" + treeAVL.contPreOrder().toString());//прямой
        System.out.println("contInOrder:\t\t" + treeAVL.contInOrder().toString());//центрированный
        System.out.println("contPostOrder:\t\t" + treeAVL.contPostOrder().toString());//обратный

    }
}
