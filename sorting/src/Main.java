import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static long swaps = 0;
    static long comparisons = 0;

    static double roundToTwo(double number) {
        number *= 100;
        number = Math.round(number);
        number /= 100;
        return number;
    }

    static int[] recreateArray(int[] array) {
        int[] newArray = new int[array.length];
        for(int i = 0; i < array.length; i++){
            newArray[i] = array[i];
        }
        return newArray;
    }

    static void insertionSortAsc(int array[]) {
        int key = 0;
        int i = 0;
        for(int j = 1; j < array.length; j++) {
            key = array[j];
            i = j - 1;
            System.err.println("Porownanie przed while " + array[i] + " z " + key);
            while(i >= 0 && array[i] > key) {
                System.err.println("Porownanie w while " + array[i] + " z " + key);
                System.err.println("Podstawienie " + array[i] + " pod " + array[i+1]);
                array[i+1] = array[i];
                swaps++;
                comparisons++;
                i--;
            }
            System.err.println("Podstawienie " + key + " pod " + array[i+1]);
            array[i+1] = key;
            swaps++;
            comparisons++;
        }
    }

    static void insertionSortDesc(int array[]) {
        int key = 0;
        int i = 0;
        for(int j = 1; j < array.length; j++) {
            key = array[j];
            i = j - 1;
            System.err.println("Porownanie " + array[i] + " z " + key);
            while(i >= 0 && array[i] < key) {
                System.err.println("Porownanie w while " + array[i] + " z " + key);
                System.err.println("Podstawienie " + array[i] + " pod " + array[i+1]);
                array[i+1] = array[i];
                swaps++;
                comparisons++;
                i--;
            }
            System.err.println("Podstawienie " + key + " pod " + array[i+1]);
            array[i+1] = key;
            swaps++;
            comparisons++;
        }
    }

    static void insertionSortAscNoErr(int array[]) {
        int key = 0;
        int i = 0;
        for(int j = 1; j < array.length; j++) {
            key = array[j];
            i = j - 1;
            while(i >= 0 && array[i] > key) {
                array[i+1] = array[i];
                swaps++;
                comparisons++;
                i--;
            }
            array[i+1] = key;
            swaps++;
            comparisons++;
        }
    }

    static void mergeSortAsc(int array[], int firstIndx, int lastIndx) {
        int divider = Math.floorDiv(firstIndx + lastIndx, 2);
        if(firstIndx < lastIndx){
            mergeSortAsc(array, firstIndx, divider);
            mergeSortAsc(array, divider + 1, lastIndx);
            mergeAsc(array, firstIndx, divider, lastIndx);
        }
    }

     static void mergeAsc(int array[], int firstIndx, int divider, int lastIndx) {
        int x1 = divider - firstIndx + 1;
        int x2 = lastIndx - divider;
        int temp1[] = new int[x1];
        int temp2[] = new int[x2];
        int temp1iterator = 0;
        int temp2iterator = 0;
        for(int i = 0; i < temp1.length; i++) {
            swaps++;
            System.err.println("Wpisuje " + array[firstIndx + i] + " na " + i + " miejscu w lewej tablicy");
            temp1[i] = array[firstIndx + i];
        }
        for(int i = 0; i < temp2.length; i++) {
            swaps++;
            System.err.println("Wpisuje " + array[divider + i + 1] + " na " + i + " miejscu w prawej tablicy");
            temp2[i] = array[divider + i + 1];
        }
        for(int i = firstIndx; i <= lastIndx; i++) {
            System.err.println("Sprawdzenie czy lewa tablica jest pusta");
            if(temp1iterator == temp1.length) {
                System.err.println("Podstawiam " + temp2[temp2iterator] + " za " + i + " element w tablicy");
                array[i] = temp2[temp2iterator];
                swaps++;
                temp2iterator++;
                comparisons++;
            }
            else if(temp2iterator == temp2.length) {
                System.err.println("Sprawdzenie czy prawa tablica jest pusta");
                System.err.println("Podstawiam " + temp1[temp1iterator] + " za " + i + " element w tablicy");
                array[i] = temp1[temp1iterator];
                swaps++;
                temp1iterator++;
                comparisons+=2;
            }
            else {
                System.err.println("Sprawdzenie czy prawa tablica jest pusta");
                System.err.println("Porownanie " + temp1[temp1iterator] + " z " + temp2[temp2iterator]);
                if (temp1[temp1iterator] <= temp2[temp2iterator]) {
                    System.err.println("Wstawiam " + temp1[temp1iterator] + " na " + i + " miejsce w tablicy.");
                    array[i] = temp1[temp1iterator];
                    swaps++;
                    temp1iterator++;
                } else {
                    System.err.println("Wstawiam " + temp2[temp2iterator] + " na " + i + " miejsce w tablicy.");
                    array[i] = temp2[temp2iterator];
                    swaps++;
                    temp2iterator++;
                }
                comparisons+=3;
            }
        }
    }

    static void mergeSortDesc(int array[], int firstIndx, int lastIndx) {
        int divider = Math.floorDiv(firstIndx + lastIndx, 2);
        if(firstIndx < lastIndx){
            mergeSortDesc(array, firstIndx, divider);
            mergeSortDesc(array, divider + 1, lastIndx);
            mergeDesc(array, firstIndx, divider, lastIndx);
        }
    }

    static void mergeDesc(int array[], int firstIndx, int divider, int lastIndx) {
        int x1 = divider - firstIndx + 1;
        int x2 = lastIndx - divider;
        int temp1[] = new int[x1];
        int temp2[] = new int[x2];
        int temp1iterator = 0;
        int temp2iterator = 0;
        for(int i = 0; i < temp1.length; i++) {
            System.err.println("Wpisuje " + array[firstIndx + i] + " na " + i + " miejscu w lewej tablicy");
            swaps++;
            temp1[i] = array[firstIndx + i];
        }
        for(int i = 0; i < temp2.length; i++) {
            System.err.println("Wpisuje " + array[divider + i + 1] + " na " + i + " miejscu w prawej tablicy");
            swaps++;
            temp2[i] = array[divider + i + 1];
        }
        for(int i = firstIndx; i <= lastIndx; i++) {
            System.err.println("Sprawdzenie czy lewa tablica jest pusta");
            if(temp1iterator == temp1.length) {
                array[i] = temp2[temp2iterator];
                temp2iterator++;
                swaps++;
                comparisons++;
            }
            else if(temp2iterator == temp2.length) {
                System.err.println("Sprawdzenie czy prawa tablica jest pusta");
                System.err.println("Podstawiam " + temp1[temp1iterator] + " za " + i + " element w tablicy");
                array[i] = temp1[temp1iterator];
                temp1iterator++;
                swaps++;
                comparisons += 2;
            }
            else {
                System.err.println("Sprawdzenie czy prawa tablica jest pusta");
                System.err.println("Porownanie " + temp1[temp1iterator] + " z " + temp2[temp2iterator]);
                if (temp1[temp1iterator] >= temp2[temp2iterator]) {
                    System.err.println("Wstawiam " + temp1[temp1iterator] + " na " + i + " miejsce w tablicy.");
                    array[i] = temp1[temp1iterator];
                    temp1iterator++;
                    swaps++;
                } else {
                    System.err.println("Wstawiam " + temp2[temp2iterator] + " na " + i + " miejsce w tablicy.");
                    array[i] = temp2[temp2iterator];
                    temp2iterator++;
                    swaps++;
                }
                comparisons += 3;
            }
        }
    }

    static void mergeSortAscNoErr(int array[], int firstIndx, int lastIndx) {
        int divider = Math.floorDiv(firstIndx + lastIndx, 2);
        if(firstIndx < lastIndx){
            mergeSortAscNoErr(array, firstIndx, divider);
            mergeSortAscNoErr(array, divider + 1, lastIndx);
            mergeAscNoErr(array, firstIndx, divider, lastIndx);
        }
    }

    static void mergeAscNoErr(int array[], int firstIndx, int divider, int lastIndx) {
        int x1 = divider - firstIndx + 1;
        int x2 = lastIndx - divider;
        int temp1[] = new int[x1];
        int temp2[] = new int[x2];
        int temp1iterator = 0;
        int temp2iterator = 0;
        for(int i = 0; i < temp1.length; i++) {
            swaps++;
            temp1[i] = array[firstIndx + i];
        }
        for(int i = 0; i < temp2.length; i++) {
            swaps++;
            temp2[i] = array[divider + i + 1];
        }
        for(int i = firstIndx; i <= lastIndx; i++) {
            if(temp1iterator == temp1.length) {
                array[i] = temp2[temp2iterator];
                swaps++;
                temp2iterator++;
                comparisons++;
            }
            else if(temp2iterator == temp2.length) {
                array[i] = temp1[temp1iterator];
                swaps++;
                temp1iterator++;
                comparisons+=2;
            }
            else {
                if (temp1[temp1iterator] <= temp2[temp2iterator]) {
                    array[i] = temp1[temp1iterator];
                    swaps++;
                    temp1iterator++;
                } else {
                    array[i] = temp2[temp2iterator];
                    swaps++;
                    temp2iterator++;
                }
                comparisons+=3;
            }
        }
    }

    static void quickSortAsc(int array[], int firstIndx, int lastIndx) {
        int divider = 0;
        if(firstIndx < lastIndx) {
          divider = partitionAsc(array, firstIndx, lastIndx);
          quickSortAsc(array, firstIndx, divider - 1);
          quickSortAsc(array, divider + 1, lastIndx);
        }
    }

    static int partitionAsc(int array[], int firstIndx, int lastIndx) {
        int x = array[lastIndx];
        int i = firstIndx - 1;
        int temp = 0;
        for(int j = firstIndx; j < lastIndx; j++) {
            System.err.println("Porownanie " + array[j] + " z " + x);
         if(array[j] <= x) {
             i++;
             System.err.println("Swap " + array[i] + " z " + array[j]);
             temp = array[i];
             array[i] = array[j];
             array[j] = temp;
             swaps++;
         }
         comparisons++;
        }
        System.err.println("Swap " + array[i+1] + " z " + array[lastIndx]);
        temp = array[i+1];
        array[i+1] = array[lastIndx];
        array[lastIndx] = temp;
        swaps++;
        return i + 1;
    }

    static void quickSortAscNoErr(int array[], int firstIndx, int lastIndx) {
        int divider = 0;
        if(firstIndx < lastIndx) {
            divider = partitionAscNoErr(array, firstIndx, lastIndx);
            quickSortAscNoErr(array, firstIndx, divider - 1);
            quickSortAscNoErr(array, divider + 1, lastIndx);
        }
    }

    static int partitionAscNoErr(int array[], int firstIndx, int lastIndx) {
        int x = array[lastIndx];
        int i = firstIndx - 1;
        int temp = 0;
        for(int j = firstIndx; j < lastIndx; j++) {
            if(array[j] <= x) {
                i++;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                swaps++;
            }
            comparisons++;
        }
        temp = array[i+1];
        array[i+1] = array[lastIndx];
        array[lastIndx] = temp;
        swaps++;
        return i + 1;
    }

    static void quickSortDesc(int array[], int firstIndx, int lastIndx) {
        int divider = 0;
        if(firstIndx < lastIndx) {
            divider = partitionDesc(array, firstIndx, lastIndx);
            quickSortDesc(array, firstIndx, divider - 1);
            quickSortDesc(array, divider + 1, lastIndx);
        }
    }

    static int partitionDesc(int array[], int firstIndx, int lastIndx) {
        int x = array[lastIndx];
        int i = firstIndx - 1;
        int temp = 0;
        for(int j = firstIndx; j < lastIndx; j++) {
            System.err.println("Porownanie " + array[j] + " z " + x);
            if(array[j] >= x) {
                i++;
                System.err.println("Swap " + array[i] + " z " + array[j]);
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                swaps++;
            }
            comparisons++;
        }
        System.err.println("Swap " + array[i+1] + " z " + array[lastIndx]);
        temp = array[i + 1];
        array[i + 1] = array[lastIndx];
        array[lastIndx] = temp;
        swaps++;
        return i + 1;
    }

    static void dpQuickSortAsc(int array[], int firstIndx, int lastIndx) {
        int divider[];
        if(firstIndx < lastIndx) {
            divider = partitionDpAsc(array, firstIndx, lastIndx);
            dpQuickSortAsc(array, firstIndx, divider[0] - 1);
            dpQuickSortAsc(array, divider[0] + 1, divider[1] - 1);
            dpQuickSortAsc(array, divider[1] + 1, lastIndx);
        }
    }

    static int[] partitionDpAsc(int array[], int firstIndx, int lastIndx) {
        int result[] = new int[2];
        int temp = 0;
        if(array[lastIndx] < array[firstIndx]){
            temp = array[lastIndx];
            array[lastIndx] = array[firstIndx];
            array[firstIndx] = temp;
        }
        comparisons++;
        int q = array[lastIndx]; // right pivot
        int p = array[firstIndx]; // left pivot

        int j = firstIndx + 1;
        int g = lastIndx - 1;

        int leftSum = 0, rightSum = 0;

        for(int k = firstIndx + 1; k <= g; k++) {
            leftSum = j;
            rightSum = lastIndx - g;
            if (leftSum <= rightSum) {
                if (array[k] < p) {
                    temp = array[j];
                    array[j] = array[k];
                    array[k] = temp;
                    j++;
                    swaps++;
                    comparisons++;
                }
                else if (array[k] >= q) {
                    while (array[g] > q && k < g) {
                        g--;
                        comparisons++;
                    }
                    temp = array[g];
                    array[g] = array[k];
                    array[k] = temp;
                    swaps++;
                    g--;
                    if (array[k] < p) {
                        temp = array[j];
                        array[j] = array[k];
                        array[k] = temp;
                        swaps++;
                        j++;
                    }
                    comparisons += 3; // 1. array[k] < p 2. array[k] >= q 3. array[k] < p
                }

            }
            else {
                if (array[k] >= q) {
                    while (array[g] > q && k < g) {
                        g--;
                        comparisons++;
                    }
                    temp = array[g];
                    array[g] = array[k];
                    array[k] = temp;
                    swaps++;
                    g--;
                    if (array[k] < p) {
                        temp = array[j];
                        array[j] = array[k];
                        array[k] = temp;
                        swaps++;
                        j++;
                    }
                    comparisons += 2;
                }
                else if (array[k] < p) {
                    temp = array[j];
                    array[j] = array[k];
                    array[k] = temp;
                    swaps++;
                    j++;
                    comparisons += 2;
                }

            }
            comparisons++; //leftSum <= rightSum
        }
        j--;
        g++;

        temp = array[firstIndx];
        array[firstIndx] = array[j];
        array[j] = temp;
        swaps++;

        temp = array[lastIndx];
        array[lastIndx] = array[g];
        array[g] = temp;
        swaps++;
        result[0] = j;
        result[1] = g;
        return result;
    }

    static void dpQuickSortDsc(int array[], int firstIndx, int lastIndx) {
        int divider[];
        if(firstIndx < lastIndx) {
            divider = partitionDpDsc(array, firstIndx, lastIndx);
            dpQuickSortDsc(array, firstIndx, divider[0] - 1);
            dpQuickSortDsc(array, divider[0] + 1, divider[1] - 1);
            dpQuickSortDsc(array, divider[1] + 1, lastIndx);
        }
    }

    static int[] partitionDpDsc(int array[], int firstIndx, int lastIndx) {
        int result[] = new int[2];
        int temp = 0;
        if(array[lastIndx] > array[firstIndx]){
            temp = array[lastIndx];
            array[lastIndx] = array[firstIndx];
            array[firstIndx] = temp;
        }
        int q = array[lastIndx]; // right pivot
        int p = array[firstIndx]; // left pivot

        int j = firstIndx + 1;
        int g = lastIndx - 1;

        int leftSum = 0, rightSum = 0;

        for(int k = firstIndx + 1; k <= g; k++) {
            leftSum = j;
            rightSum = lastIndx - g;
            if (leftSum <= rightSum) {
                if (array[k] > p) {
                    temp = array[j];
                    array[j] = array[k];
                    array[k] = temp;
                    j++;
                } else if (array[k] <= q) {
                    while (array[g] < q && k < g) {
                        g--;
                    }
                    temp = array[g];
                    array[g] = array[k];
                    array[k] = temp;
                    g--;
                    if (array[k] > p) {
                        temp = array[j];
                        array[j] = array[k];
                        array[k] = temp;
                        j++;
                    }
                }
            }
            else if(leftSum > rightSum) {
                if (array[k] <= q) {
                    while (array[g] < q && k < g) {
                        g--;
                    }
                    temp = array[g];
                    array[g] = array[k];
                    array[k] = temp;
                    g--;
                    if (array[k] > p) {
                        temp = array[j];
                        array[j] = array[k];
                        array[k] = temp;
                        j++;
                    }
                }
                else if (array[k] > p) {
                    temp = array[j];
                    array[j] = array[k];
                    array[k] = temp;
                    j++;
                }

            }
        }
        j--;
        g++;

        temp = array[firstIndx];
        array[firstIndx] = array[j];
        array[j] = temp;

        temp = array[lastIndx];
        array[lastIndx] = array[g];
        array[g] = temp;
        result[0] = j;
        result[1] = g;
        return result;
    }

    static int[] countSortUpdated(int array[], int divider){

        int tempArray[] = new int[array.length];
        int countArray[] = new int[10];

        for(int i = 0; i < countArray.length; i++){
            countArray[i] = 0;
        }

        for(int i = 0; i < array.length; i++) {
            countArray[(array[i] / divider) % 10] += 1;
            comparisons++;
        }
        comparisons++;

        for(int i = 1; i < countArray.length; i++) {
            swaps++;
            countArray[i] = countArray[i] + countArray[i-1];
            comparisons++;
        }
        comparisons++;

        for(int i = array.length - 1; i >= 0; i--){
            int firstStep = array[i]/divider;
            int secondStep = firstStep % 10;
            int thirdStep = countArray[secondStep];
            swaps++;
            tempArray[thirdStep - 1] = array[i];
            countArray[(array[i] / divider) % 10] -= 1;
            comparisons++;
        }
        comparisons++;

        return tempArray;
    }

    static int[] radixSort(int[] array, int max) {

        for(int i = 1; max/i > 1; i*=10) {

            array = countSortUpdated(array, i);
            comparisons++;
        }
        comparisons++;
        return array;
    }

    static int[] generateArray(int amount){
        int array[] = new int[amount];
        Random generator = new Random();
        for(int i = 0; i < amount; i++){
            array[i] = generator.nextInt(10000);
        }
        return array;
    }

    static void sort(int array[], String type, String comp) {
        switch(type) {
            case("merge"):
                switch(comp) {
                    case("'<='"):
                        mergeSortAsc(array, 0, array.length - 1);
                        break;
                    case("'>='"):
                        mergeSortDesc(array, 0, array.length - 1);
                        break;
                }
                break;
            case("insert"):
                switch(comp) {
                    case("'<='"):
                        insertionSortAsc(array);
                        break;
                    case("'>='"):
                        insertionSortDesc(array);
                        break;
                }
                break;
            case("quick"):
                switch(comp) {
                    case("'<='"):
                        quickSortAsc(array, 0, array.length - 1);
                        break;
                    case("'>='"):
                        quickSortDesc(array, 0, array.length - 1 );
                        break;
                }
                break;
        }
    }

    static void checkArray(int array[],String comp) {
        switch(comp) {
            case (">="):
                for (int i = 0; i < array.length - 1; i++) {
                    if(array[i] > array[i + 1]){
                        System.err.println("źle posortowane");
                        System.exit(0);
                    }
                }
                break;
            case ("<="):
                for (int i = 0; i < array.length - 1; i++) {
                    if(array[i] < array[i + 1]){
                        System.err.println("źle posortowane");
                        System.exit(0);
                    }
                }
                break;
        }
    }

    public static void main(String[] args){

        String type = "";
        String comp = "";
        String stat = "";
        int n = 0;
        int k = 0;
        int array[];
        String values[];
        Scanner odczyt = new Scanner(System.in);

        for(int i = 1; i < args.length; i++) {
            if(args[i-1].equals("--type")) {
                type = args[i];
            }
            else if(args[i-1].equals("--comp")) {
                if(args[i].contains("<="))
                 comp = "'<='";
                else if (args[i].contains(">="))
                 comp = "'>='";
            }
            else if(args[i-1].equals("--stat")) {
                stat = args[i];
                k = Integer.parseInt(args[i+1]);
            }
        }
        if(stat.equals("")) {
            long start, stop;
            double result;
           // System.out.println("Podaj liczbe n: ");
            n = Integer.parseInt(odczyt.nextLine());
           // System.out.println("Liczba n: " + n);
            array = new int[n];
           // System.out.println("W jednej linii, oddzielone spacjami, podaj argumenty tablicy: ");
            values = odczyt.nextLine().split(" ");
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(values[i]);
            }
            swaps = 0;
            comparisons = 0;
            start = System.nanoTime();
            sort(array, type, comp);
            checkArray(array, comp);
            stop = System.nanoTime();
            result = (stop - start)/(double)1000000;
            result = roundToTwo(result);
            System.err.println("Czas: "+result+"ms");
            System.err.println("Porownania: " + comparisons);
            System.err.println("Zamiany: " + swaps);


            for(int i = 0; i < array.length; i++) {
                System.out.println("Wartosc z tablicy posortowanej: " + array[i]);
            }
        }
        else {
            long start, stop;
            double result1, result2, result3, result4, result5;
            String sresult1, sresult2, sresult3, sresult4, sresult5;
            long swaps1, swaps2, swaps3, swaps4, swaps5;
            long comparisons1, comparisons2, comparisons3, comparisons4, comparisons5;
            long memory1, memory2, memory3, memory4, memory5;
            int[] ncount = {10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000};
            Runtime runtime = Runtime.getRuntime();
            PrintWriter out = null;
           /* PrintWriter out1 = null;
            PrintWriter out2 = null;
            PrintWriter out3 = null;
            PrintWriter out4 = null;
            PrintWriter out5 = null;
            PrintWriter out6 = null;
            PrintWriter out7 = null;
            PrintWriter out8 = null;
            PrintWriter out9 = null;
            PrintWriter out10 = null;
            PrintWriter out11 = null;
            PrintWriter out12 = null;*/
            try {
                out = new PrintWriter(stat);
                /*out1 = new PrintWriter("inserttime");
                out2 = new PrintWriter("insertswaps");
                out3 = new PrintWriter("inertcomp");
                out4 = new PrintWriter("mergetime");
                out5 = new PrintWriter("mergeswaps");
                out6 = new PrintWriter("mergecomp");
                out7 = new PrintWriter("qstime");
                out8 = new PrintWriter("qsswaps");
                out9 = new PrintWriter("qscomp");
                out10 = new PrintWriter("dpqstime");
                out11 = new PrintWriter("dpqsswaps");
                out12 = new PrintWriter("dpqscomp");*/

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }
            for (int i = 1; i <= ncount.length - 1; i++) {
                for(int j = 0; j < k; j++) {
                    array = generateArray(ncount[i]); //generating new i-element array
                    double temp;
                    //Insertion sort
                    int temparray1[] = recreateArray(array); //copying array
                    start = System.nanoTime();
                    swaps = 0;
                    comparisons = 0;
                    runtime = Runtime.getRuntime();
                    runtime.gc();
                    insertionSortAscNoErr(temparray1);
                    stop = System.nanoTime();
                    memory1 = runtime.totalMemory() - runtime.freeMemory();
                    checkArray(temparray1,comp);
                    result1 = (stop - start)/(double)1000000;
                    result1 = roundToTwo(result1);
                    sresult1 = Double.toString(result1).replace(".",",");
                    swaps1 = swaps;
                    comparisons1 = comparisons;
                    //MergeSort
                    swaps = 0;
                    comparisons = 0;
                    int temparray2[] = recreateArray(array);
                    start = System.nanoTime();
                    mergeSortAscNoErr(temparray2,0,temparray2.length - 1);
                    stop = System.nanoTime();
                    runtime = Runtime.getRuntime();
                    runtime.gc();
                    checkArray(temparray2,comp);
                    result2 = (stop - start)/(double)1000000;
                    result2 = roundToTwo(result2);
                    sresult2 = Double.toString(result2).replace(".",",");
                    memory2 = runtime.totalMemory() - runtime.freeMemory();
                    swaps2 = swaps;
                    comparisons2 = comparisons;
                    //QuickSort
                    swaps = 0;
                    comparisons = 0;
                    int temparray3[] = recreateArray(array);
                    start = System.nanoTime();
                    quickSortAscNoErr(temparray3,0,temparray3.length - 1);
                    stop = System.nanoTime();
                    runtime = Runtime.getRuntime();
                    runtime.gc();
                    checkArray(temparray3,comp);
                    result3 = (stop - start)/(double)1000000;
                    result3 = roundToTwo(result3);
                    sresult3 = Double.toString(result3).replace(".",",");
                    memory3 = runtime.totalMemory() - runtime.freeMemory();
                    swaps3 = swaps;
                    comparisons3 = comparisons;
                    // DualPivot Quicksort
                    swaps = 0;
                    comparisons = 0;
                    int temparray4[] = recreateArray(array);
                    start = System.nanoTime();
                    dpQuickSortAsc(temparray4, 0, temparray4.length - 1);
                    stop = System.nanoTime();
                    runtime = Runtime.getRuntime();
                    runtime.gc();
                    checkArray(array, comp);
                    result4 = (stop - start)/(float)1000000;
                    result4 = roundToTwo(result4);
                    sresult4 = Double.toString(result4).replace(".",",");
                    memory4 = runtime.totalMemory() - runtime.freeMemory();
                    swaps4 = swaps;
                    comparisons4 = comparisons;
                    //RadixSort
                    swaps = 0;
                    comparisons = 0;
                    int temparray5[] = recreateArray(array);

                    int max = temparray5[0];
                    for(int l = 0; l < temparray5.length; l++) {
                        if(max < array[l])
                            max = array[l];
                    }
                    start = System.nanoTime();
                    runtime = Runtime.getRuntime();
                    runtime.gc();
                    radixSort(temparray5,max);
                    stop = System.nanoTime();
                    checkArray(array, comp);
                    result5 = (stop - start)/(float)1000000;
                    result5 = roundToTwo(result4);
                    sresult5 = Double.toString(result4).replace(".",",");
                    memory5 = runtime.totalMemory() - runtime.freeMemory();
                    swaps5 = swaps;
                    comparisons5 = comparisons;

                    System.out.println("Czas dzialania dla tablicy " + ncount[i] + " elementowej w insert: "+ result1 + "ms, merge: " + result2 + "ms, QS: " + result3 + "ms, DPQS: " + result4+"ms");
                    System.out.println("Ilosc zamian w insercie: " + swaps1 + ", w merge: "+ swaps2 + ", w QS: " + swaps3 + ", w DPQS: " + swaps4);
                    System.out.println("Ilosc porownan w insercie: " + comparisons1 + ", w merge: "+ comparisons2 + ", w QS: " + comparisons3 + ", w DPQS: " + comparisons4);
                    out.println("n: " + ncount[i] + "\r\n" +
                            "Insert | time: " + sresult1 +"ms, swaps: "+ swaps1  + " comp: " + comparisons1 + " memory: " + memory1 + "\r\n"+
                            "Merge | time: " + sresult2 + "ms, swaps: " + swaps2 + " comp: " + comparisons2 + " memory: " + memory2 + "\r\n" +
                            "Quicksort | time: " + sresult3 + "ms, swaps: " + swaps3 + " comp: " + comparisons3 + " memory: " + memory3 + "\r\n"+
                            "DPQuicksort | time: " + sresult4 + "ms, swaps: " + swaps4 + " comp: " + comparisons4 + " memory: " + memory4 + "\r\n"+
                            "Radixsort | time : " + sresult5 + "ms, swaps: " + swaps5 + " comp: " + comparisons5 + " memory: " + memory5 + "\r\n");
                    /*out1.println(sresult1);
                    out2.println(swaps1);
                    out3.println(comparisons1); //OSOBNE PLIKI DO LATWIEJSZEGO PRZENOSZENIA DANYCH DO EXCEL
                    out4.println(sresult2);
                    out5.println(swaps2);
                    out6.println(comparisons2);
                    out7.println(sresult3);
                    out8.println(swaps3);
                    out9.println(comparisons3);
                    out10.println(sresult4);
                    out11.println(swaps4);
                    out12.println(comparisons4);*/
                }
            }
            out.close();
            /*out1.close();
            out2.close();
            out3.close();
            out4.close();
            out5.close();
            out6.close();
            out7.close();
            out8.close();
            out9.close();
            out10.close();
            out11.close();
            out12.close();*/

        }
    }
}
