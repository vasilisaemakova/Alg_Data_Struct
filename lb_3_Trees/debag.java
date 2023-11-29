package Lab_2_1_3;

import Collections.MyArrayList;
import Collections.MyStack;

public class debag {
    public static void viewStack(MyStack<Node> stackNode){
        MyArrayList my = new MyArrayList<>();
        while (!stackNode.isEmpty()){
            my.add((int) stackNode.pop().data);
        }
        for(int i = my.getSize()-1; i >=0; i--){
            stackNode.push(new Node(my.get(i)));
        }
        System.out.println(my.toString());
    }
}
