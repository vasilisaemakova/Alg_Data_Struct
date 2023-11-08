package Lab_2_1_2.Sorts;

import Collections.MyArrayList;
import Collections.MyList;

public class MergeSort <T extends Comparable<T>> implements Sort<T>{

    private final MyList<T> arr;
    public MergeSort (MyList<T> arr ){
        this.arr = arr;
    }
    void merge(int left, int mid, int right)
    {
        // Find sizes of two subarrays to be merged
        int lenLiftList = mid - left + 1;
        int lenRightList = right - mid;


        /* Create temp arrays */
        MyList<T> leftList = new MyArrayList<>(lenLiftList);
        MyList<T> rightList = new MyArrayList<>(lenRightList);

        /*Copy data to temp arrays*/
        for (int i = 0; i < lenLiftList; ++i)
            leftList.set(i, arr.get(left+i));
        for (int i = 0; i < lenRightList; ++i)
            rightList.set(i, arr.get(mid + 1 + i));

        /* Merge the temp arrays */

        // Initial indexes of left, right subarrays and array
        int leftPoint = 0, rightPoint = 0, arrPoint = left;


        while (leftPoint < lenLiftList && rightPoint < lenRightList) {

            if (leftList.get(leftPoint).compareTo(rightList.get(rightPoint)) <= 0) {
                arr.set(arrPoint, leftList.get(leftPoint));
                leftPoint++;
            }
            else {
                arr.set(arrPoint, rightList.get(rightPoint));
                rightPoint++;
            }
            arrPoint++;
        }

        // copy to arr
        while (leftPoint < lenLiftList) {
            arr.set(arrPoint, leftList.get(leftPoint));
            leftPoint++;
            arrPoint++;
        }

        /* Copy remaining elements of R[] if any */
        while (rightPoint < lenRightList) {
            arr.set(arrPoint, rightList.get(rightPoint));
            rightPoint++;
            arrPoint++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()

    public void sort() {
        sortLR(0, arr.getSize() - 1);
    }
    public void sortLR(int left, int right)
    {
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            sortLR(left, mid);
            sortLR(mid + 1, right);

            // Merge the sorted halves
            merge(left, mid, right);
        }
    }


    // demonstrated running mergeSort;
    /*public static void main(String args[])
    {
        int l = 1000;
        MyList<Integer> arr = new MyArrayList<>();
        utils.randomArr(arr, l);
        //System.out.println(arr.toString());

        Sort<Integer> mergeSort = new MergeSort<>(arr);
        long timeMerge = utils.timeSort(mergeSort);
        //System.out.println(arr.toString());

        System.out.println(timeMerge);
    }*/



}
