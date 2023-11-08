package Lab_2_1_2.Sorts;


import Collections.MyArrayList;
import Collections.MyLinkedList;
import Collections.MyList;
import Collections.MyStack;
import Lab_2_1_2.Lab2.utils;


public class TimSort <T extends Comparable<T>> implements Sort<T>
{
    private final MyList<T> arr;

    public TimSort (MyList<T> arr ){
        this.arr = arr;
    }

    private static int getMinRun(int n) {
        int r = 0;
        while (n >= 64)
        {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }

    private static class Run {
        int index;
        int size;

        public Run(int index, int size){
            this.index = index;
            this.size = size;
        }

    }

    private void binInsert(int cur, int beg, int end) {
        while (end >= beg) {
            if (arr.get(cur).compareTo(arr.get((beg + end) / 2)) > 0) {
                beg = (beg + end) / 2 + 1;
            }
            else {
                end = (beg + end) / 2 - 1;
            }
        }
        arr.insertBack(cur, beg);
    }

    private void insertionSort(int left, int right, int curInd) {
        for (int i = curInd; i <= right; i++){
            if (arr.get(i).compareTo(arr.get(i-1)) < 0){
                binInsert(i, left, i-1);
            }
        }
    }

    private int doGallop(MyList <T> galopList, int point, T constDataPoint) {
        int gallop = 1;
        int size = galopList.getSize();
        while (true) {
            if ((point < size) && (galopList.get(point).compareTo(constDataPoint) <= 0)) {
                point += gallop;
            }
            else {
                point -= (gallop / 2);
                point++;
                gallop = 1;
                return point;
            }
            gallop *= 2;
        }
    }

    private void merge(Run left, Run right) {

        //copy left, right subarr
        MyList<T> leftList = new MyArrayList<>();
        MyList<T> rightList = new MyArrayList<>();
        for (int i = left.index; i < left.index + left.size; i++) leftList.add(arr.get(i));
        for (int i = right.index; i < right.index + right.size; i++) rightList.add(arr.get(i));

        int leftPoint = 0, rightPoint = 0;
        int pastPoint = 0; //need for copy elem. from left or right subarr to arr after galop
        int curInd = left.index;

        while (leftPoint < left.size && rightPoint < right.size) {
            if (leftList.get(leftPoint).compareTo(rightList.get(rightPoint)) <= 0) {
                pastPoint = leftPoint;
                leftPoint = doGallop(leftList, leftPoint, rightList.get(rightPoint));
                for (int i = pastPoint; i < leftPoint; i++, curInd++) {
                    arr.set(curInd, leftList.get(i));
                }
            }
            else {
                pastPoint = rightPoint;
                rightPoint = doGallop(rightList, rightPoint, leftList.get(leftPoint));
                for (int i = pastPoint; i < rightPoint; i++, curInd++) arr.set(curInd, rightList.get(i));
            }
        }
        for (int i = leftPoint; i < left.size; i++, curInd++){
            arr.set(curInd, leftList.get(i));
        }
        for (int i = rightPoint; i < right.size; i++, curInd++) {
            arr.set(curInd, rightList.get(i));
        }
        right.index = left.index;
        right.size += left.size;

        //mylist.view();
    }

    private void reverse(int left, int right){
        int sizeArrReverse = right- left + 1;
        MyList<T> arrReverse = new MyArrayList<>(sizeArrReverse);
        //boolean div2 = true;
        T temp;
        int limit = sizeArrReverse / 2;
        for (int i = 0; i < limit; i++){
            temp = arr.get(left + i);
            arr.set(left+i, arr.get(right-i));
            arr.set(right-i, temp);
        }
    }

    private int tryFindRun(int curInd){
        int temp = curInd;
        while ((curInd+1 < arr.getSize()) && (arr.get(curInd+1).compareTo(arr.get(curInd)) > 0)){
            curInd++;
        }
        if (curInd == temp){
            while ((curInd+1 < arr.getSize()) && (arr.get(curInd+1).compareTo(arr.get(curInd)) < 0)){
                curInd++;
            }
            reverse(temp, curInd);
        }

        return curInd;
    }

    private void mergeTop(MyStack<Run> stack) {

        while (stack.getSize() > 1) {
            Run X = stack.pop();

            Run Y = stack.pop();


            if (stack.getSize() > 0) {

                Run Z = stack.pop();
                if (Z.size <= Y.size + X.size) {
                    if (Z.size < X.size) {
                        merge(Z, Y);
                        stack.push(Y);
                        stack.push(X);
                    } else {
                        merge(Y, X);
                        stack.push(Z);
                        stack.push(X);
                    }
                } else if (Y.size <= X.size) {
                    merge(Y, X);
                    stack.push(Z);
                    stack.push(X);
                } else {
                    stack.push(Z);
                    stack.push(Y);
                    stack.push(X);
                    break;
                }
            } else if (Y.size <= X.size) {
                merge(Y, X);
                stack.push(X);
            } else {
                stack.push(Y);
                stack.push(X);
                break;
            }
        }
    }

    private void mergeAll(MyStack<Run> stack){

        while (stack.getSize() > 1) {
            Run X = stack.pop();
            Run Y = stack.pop();

            if (stack.getSize() > 0) {
                Run Z = stack.pop();
                if (Z.size < X.size) {
                    merge(Z, Y);
                    stack.push(Y);
                    stack.push(X);
                }
                else {
                    merge(Y, X);
                    stack.push(Z);
                    stack.push(X);
                }
            }
            else {
                merge(Y, X);
            }
        }
    }

    public void sort() {
        int size = arr.getSize();
        int minRun = getMinRun(arr.getSize());
        MyStack<Run> stackRun = new MyLinkedList<>();
        Run X = null, Y = null, Z = null;


        int curInd = 0, begRun = 0, endRun = 0;
        while (curInd < size){
            //get sorted run
            begRun = curInd;
            curInd = tryFindRun(curInd);
            if (curInd - begRun + 1 < minRun){
                endRun = Math.min((begRun + minRun - 1), (size - 1));
                insertionSort(begRun, endRun, curInd+1);
                curInd = endRun;
            }

            X = new Run(begRun, (++curInd - begRun));
            stackRun.push(X);
            mergeTop(stackRun);
        }
        mergeAll(stackRun);



    }



    /*public static void main(String args[]) {
        MyArrayList<Integer> list = new MyArrayList<>();
        utils.randomArr(list, 1000);
        for (int i = 0; i < 100; i++){
            list.add(i);
        }
        TimSort<Integer> sir = new TimSort<>(list);
        utils.timeSort(sir);
        System.out.println(list.toString());
    }*/
}


// This code has been contributed by 29AjayKumar
