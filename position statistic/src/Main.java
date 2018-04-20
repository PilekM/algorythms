import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static long swaps = 0;
    static long comparisons = 0;

    static int[] recreateArray(int[] array) {
        int[] newArray = new int[array.length];
        for(int i = 0; i < array.length; i++){
            newArray[i] = array[i];
        }
        return newArray;
    }

    static void displayErr(int[] array) {

        for(int i = 0; i < array.length; i++) {
                System.err.print(array[i] +" ");
        }

    }

    static int[] generateRandValueArray(int amount){
        int array[] = new int[amount];
        Random generator = new Random();
        for(int i = 0; i < amount; i++){
            array[i] = generator.nextInt(10000);
        }
        return array;
    }


    static int[] generateSortedArray(int amount) {

        int array[] = new int[amount];

        ArrayList<Integer> tempList = new ArrayList<>();

        Random generator = new Random();

        for(int i = 0; i < amount; i++) {
            tempList.add(i+1);
        }

        for(int i = 0; i < amount; i++){
            int temp = generator.nextInt(tempList.size());
            array[i] = tempList.get(temp);
            tempList.remove(temp);
        }

        return array;
    }

    static void insertionSort(int array[], int firstIndex, int lastIndex) {
        int key;
        int i;
        int end = lastIndex + firstIndex - 1;
        for(int j = firstIndex + 1; j <= end; j++) {
            key = array[j];
            i = j - 1;
            System.err.println("Porownanie " + array[i] + " z " + key + " przed petla while w insertSort");
            while(i >= firstIndex && array[i] > key) {
                System.err.println("Porownanie " + array[i] + " z " + key + " w petli while w insertSort");
                System.err.println("Podstawienie " + array[i] + " pod " + array[i + 1] + " w while w insertSort" );
                array[i+1] = array[i];
                i--;
                comparisons++;
                swaps++;
            }
            System.err.println("Podstawienie " + array[key] + " pod " + array[i + 1] + " za while w insertSort" );
            array[i+1] = key;
        }
    }

    static int partition(int array[], int firstIndx, int lastIndx) {
        int x = array[lastIndx];
        int i = firstIndx - 1;
        int temp = 0;
        for(int j = firstIndx; j < lastIndx; j++) {
            System.err.println("Wykonuje porownanie " + array[j] + " z " + x + " w partition." );
            if(array[j] <= x) {
                i++;
                System.err.println("Swapuje " + array[i] + " z " + array[j] + " w partition w petli.");
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                swaps++;
            }
            comparisons++;
        }
        System.err.println("Swapuje " + array[i + 1] + " z " + array[lastIndx] + " w partition za petla.");
        temp = array[i+1];
        array[i+1] = array[lastIndx];
        array[lastIndx] = temp;
        swaps++;
        return i + 1;
    }

    static int randPartition(int array[], int firstIndex, int lastIndex) {
        Random generator = new Random();
        int i = generator.nextInt(lastIndex - firstIndex) + firstIndex;
        System.err.println("Po wylosowniu pivota " + array[i] + "swapuje go z " + array[lastIndex]);
        int temp = array[lastIndex];
        array[lastIndex] = array[i];
        array[i] = temp;
        swaps++;
        return partition(array, firstIndex, lastIndex);
     }

    static int randomSelect(int[] array, int firstIndex, int lastIndex, int i) {

        System.err.println("Zaczynam randomSelect szukając " + i + " stat. pozyc. dla tablicy : ");
        displayErr(array);
        System.err.println("W zakresie od " + firstIndex + " do " + lastIndex);

        int q;
        int k;
        System.err.println("Porownuje " + firstIndex + " z " + lastIndex + " na poczatku petli randSelect");
        comparisons++;
        if(firstIndex == lastIndex) {
            return array[firstIndex];
        }

        q = randPartition(array, firstIndex, lastIndex);
        System.err.println("Wybrany pivot: " + q);

        k = q - firstIndex + 1;

        if (i == k) {
            System.out.println("Sprawdzam czy " + i + " == " + k);
            comparisons ++;
            return array[q];
        }
        else if (i < k) {
            System.out.println("Porownuje " + i + " z " + k +" dwukrotnie");
            comparisons += 2;
            return randomSelect(array, firstIndex, q - 1, i);
        }
        else {
            System.out.println("Porownuje " + i + " z " + k + " dwukrotnie");
            comparisons += 2;
            return randomSelect(array, q + 1, lastIndex, i - k);
        }
    }

    static int select(int[] array, int p, int r, int i) {

        System.err.println("Zaczynam select szukając " + i + " stat. pozyc. dla tablicy : ");
        displayErr(array);
        System.err.println("W zakresie od " + p + " do " + r);

        int n = r - p + 1;    // number of elements in the subarray

        if (n == 1)
            return array[p];    // base case: return the only element
        else {
            // Divide the subarray into ceil(n / GROUP_SIZE) groups,
            // and find the median of each group by insertion sorting
            // the group and picking the median from the sorted list.
            final int GROUP_SIZE = 5; // size of each group
            int groups;        // how many groups
            if (n % GROUP_SIZE == 0)
                groups = n / GROUP_SIZE;
            else
                groups = (n / GROUP_SIZE) + 1;

            // Create an array of medians.
            int[] medians = new int[groups];

            // Fill in medians to contain the medians of the groups.
            for (int groupStart = p, groupNumber = 0;
                 groupStart <= r;
                 groupStart += GROUP_SIZE, groupNumber++) {
                int thisGroupSize = Math.min(r - groupStart + 1, GROUP_SIZE);
                insertionSort(array, groupStart, thisGroupSize);
                for(int f = groupStart; f < groupStart+thisGroupSize; f++) {
                        System.out.print(array[f] +" ");
                }
                System.out.println(" ");
                medians[groupNumber] =
                        array[groupStart + ((thisGroupSize - 1) / 2)];
            }

            // Recursively find the median of the medians.
            int theMedian = select(medians, 0, groups - 1, (groups + 1) / 2);

            int medianIndex = p;
            while (theMedian - array[medianIndex] != 0)
                medianIndex++;

            System.err.println("Swapuje + " + array[r] + " z " + array[medianIndex] + " w select." );
            swaps++;
            int temp = array[r];
            array[r] = array[medianIndex];
            array[medianIndex] = temp;
            int q = partition(array, p, r);
            System.err.println("Pivotem jest " + q);

            // The low side of the partition is array[p..q-1].  Set k
            // to the number of elements in array[p..q], so that we
                   // include the median of the medians in our count.
            int k = q - p + 1;

            if (i == k) {
                System.out.println("Sprawdzam czy " + i + " == " + k);
                comparisons++;
                return array[q];
            }
            else if (i < k) {
                System.out.println("Porownuje " + i + " z " + k + " dwukrotnie");
                comparisons += 2;
                return select(array, p, q - 1, i); // ith smallest is ith
            }
            else {
                System.out.println("Porownuje " + i + " z " + k + " dwukrotnie");
                comparisons += 2;
                return select(array, q + 1, r, i - k); // ith smallest is (i-k)th
            }
        }
    }


    public static void main (String[] args) {

        String type = args[0];
        int n = 0;
        int k = 0;
        int ith = 0;
        int ith2 = 0;
        long swaps1 = 0;
        long swaps2 = 0;
        long comp1 = 0;
        long comp2 = 0;
        int array[];
        String values[];
        Scanner odczyt = new Scanner(System.in);

        n = Integer.parseInt(odczyt.nextLine());

        k = Integer.parseInt(odczyt.nextLine());
        while(k > n || k < 0) {
            System.out.println("Podano błędne k (powinno byc z zakresu od 1 do n)");
            k = Integer.parseInt(odczyt.nextLine());
        }

        switch(type) {

            case "-p":
                    array = generateSortedArray(n);
                for(int i = 0; i < array.length; i++) {
                    if(array[i] == ith)
                        System.out.print("[ " + array[i] +" ] ");
                    else
                        System.out.print(array[i] +" ");
                }
                System.out.println(" ");
                break;
            case "-r":
                    array = generateRandValueArray(n);
                break;
            default:
                array = new int[n];
                System.out.println("Błędnie wprowadzono parametr");
                System.exit(0);

        }
        int[] arr1 = recreateArray(array);
        int[] arr2 = recreateArray(array);


        swaps = 0;
        comparisons = 0;
        ith = select(arr1,0, array.length - 1, k);
        swaps1 = swaps;
        comp1 = comparisons;

        swaps = 0;
        comparisons = 0;
        ith2 = randomSelect(arr2, 0, array.length - 1, k);
        swaps2 = swaps;
        comp2 = comparisons;

        for(int i = 0; i < array.length; i++) {
            if(array[i] == ith)
                System.out.print("[ " + array[i] +" ] ");
            else
                System.out.print(array[i] +" ");
        }
     }
}
