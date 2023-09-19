package seminar_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Task2 {

    private ArrayList<Integer> subarray = new ArrayList<>();
    private ArrayList<Integer[]> resaltList = new ArrayList<>();


    public Integer[][] arrayPartition(int[] array, int border){
        int sack = 0;
        int idx = 0;

        for (int i = 0; i < array.length; i++) {
            if (sack + array[i] < border) {
                sack += array[i];
                subarray.add(array[i]);
            } else {
                if (array[i] > border) continue;
                Integer[] arr = subarray.toArray(new Integer[0]);
                resaltList.add(arr);
                subarray.clear();
                sack = array[i]; subarray.add(array[i]);
                idx++;
            }
        }
        Integer[] arr = subarray.toArray(new Integer[0]);
        resaltList.add(arr);

        Integer[][] result = new Integer[resaltList.size()][];
        for (int i = 0; i < resaltList.size(); i++) {
            result[i] = resaltList.get(i);
        }

        return result;
    }

    public int[] createArray(int arraySize){
        Random rnd = new Random();
        int[] result = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            result[i] = rnd.nextInt(0, 25);
        }
        System.out.println("Исходный массив: " + Arrays.toString(result));
        return result;
    }
}