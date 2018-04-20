import java.util.Scanner;

public class BinSearch {

    static long comparisons;
    static long swaps;

    static double roundToTwo(double number) {
        number *= 100;
        number = Math.round(number);
        number /= 100;
        return number;
    }


    static int[] generateSortedArray(int amount) {

        int array[] = new int[amount];
        for(int i = 0; i < array.length; i++){
            array[i] = i + 1;
        }

        return array;
    }


    public static int recursiveBinarySearch(int[] array, int firstIndex, int lastIndex, int key) {

        if (firstIndex < lastIndex) {
            int mid = firstIndex + (lastIndex - firstIndex) / 2;

            if (key < array[mid]) {
                comparisons++;
                return recursiveBinarySearch(array, firstIndex, mid, key);

            } else if (key > array[mid]) {
                comparisons += 2;
                return recursiveBinarySearch(array, mid+1, lastIndex , key);

            } else {
                comparisons +=2;
                return mid;
            }
        }
        return -(firstIndex + 1);
    }

    public static void main(String[] args){

        int n;
        int v;
        long comp, start, stop;
        int result;
        double tresult;
        int[] array;
        Scanner odczyt = new Scanner(System.in);

        n = Integer.parseInt(odczyt.nextLine());
        v = Integer.parseInt(odczyt.nextLine());

        for(int i = 1000; i <= 100000; i += 1000){

            array = generateSortedArray(i);

            comparisons = 0;
            start = System.nanoTime();
            result = recursiveBinarySearch(array, 0, array.length, v);
            stop = System.nanoTime();
            tresult = (stop - start)/(double)1000000;
            comp = comparisons;
            /*if (result >= 0)
                System.out.println("0");
            else
                System.out.println("1");*/
            System.out.println("Dla i = " + i + " porownania: " + comp + " czas: " + tresult);
        }

    }
}
