package Lab_2_1_2.Lab2;

import Collections.MyArrayList;
import Collections.MyList;
import Lab_2_1_2.Sorts.Sort;

import java.util.Random;

public class utils<T> {
    /*public utils (MyList<T> arr, int size){
        randomArr((MyList<Integer>) arr, size);
    }*/
    public static void randomArr(MyList<Integer> arr, int size){
        final Random random = new Random();
        for (int i = 0; i<size; i++){
            arr.add(random.nextInt(size));
        }
    }

    public static long timeSort(Sort<Integer> sortedList){
        long start = System.currentTimeMillis();
        sortedList.sort();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static MyList<MyList<Long>> creat2DLongArr(int size1, int size2){

        MyList<MyList<Long>> arr = new MyArrayList<>(size1);
        for (int i = 0; i < size1; i++) arr.set(i, new MyArrayList<>(size2));
        return arr;
    }

}
