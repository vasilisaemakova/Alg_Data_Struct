package Lab_2_1_2.Sorts;

import Collections.MyList;

public class InsertionSort<T extends Comparable<T>> implements Sort<T> {

    private final MyList<T> arr;

    public InsertionSort(MyList<T> arr) {
        this.arr = arr;
    }

    public void sort() {
        T key;
        int size = arr.getSize();
        for (int i = 1; i < size; ++i) {
            key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j).compareTo(key) > 0) {
                arr.set(j + 1, arr.get(j));

                j = j - 1;
            }
            arr.set(j + 1, key);
        }

    }

    //demonstrated running InsertionSort
    /*public static void main(String args[]) {
        int l = 100000;
        MyList<Integer> arr = new MyArrayList<>();
        utils.randomArr(arr, l);
        //System.out.println(arr.toString());

        Sort<Integer> insertionSort = new InsertionSort<>(arr);
        long timeMerge = utils.timeSort(insertionSort);
        //System.out.println(arr.toString());

        System.out.println(timeMerge);
    }*/

}
