package Lab_2_1_2.Lab2;


import Collections.MyArrayList;
import Collections.MyList;
import Lab_2_1_2.Sorts.InsertionSort;
import Lab_2_1_2.Sorts.MergeSort;
import Lab_2_1_2.Sorts.Sort;
import Lab_2_1_2.Sorts.TimSort;

public class Main {
    public static void main(String[] args) {

        MyList<Integer> willSortList = null;
        Sort<Integer> sortingList = null;
        //long[][] dataForGraph = new long[3][4];
        MyList<MyList<Long>> dataForGraph = utils.creat2DLongArr(3, 4);
        long sortTime;

        for (int l = 1000, choiceLen = 0; l <= 1000000; l *= 10, choiceLen++) {
            for (int i = 0; i < 3; i++) {
                //mix list
                willSortList = new MyArrayList<>();
                utils.randomArr(willSortList, l);

                //choice type sort (Tim/Merge/Insert);
                if (i == 0) sortingList = new TimSort<>(willSortList);
                else if (i == 1) sortingList = new MergeSort<>(willSortList);
                else sortingList = new InsertionSort<>(willSortList);

                //add time sorts
                if (i == 2 && l >= 100000)
                    dataForGraph.get(i).set(choiceLen, (long) l);
                else
                    dataForGraph.get(i).set(choiceLen, utils.timeSort(sortingList));
            }

        }




        System.out.println("TimSort\t\t\t" + dataForGraph.get(0).toString());
        System.out.println("MergeSort\t\t" + dataForGraph.get(1).toString());
        System.out.println("InsertSort\t\t" + dataForGraph.get(2).toString());


    }
}