import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Quang Nguyen
 * @version 1.0
 * @userid qnguyen305
 * @GTID 903770019
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null");
        }

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                T temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }

    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null");
        }

        int start = 0;
        int end = arr.length - 1;
        int swapped = start;
        while (start < end) {
           for (int i = start; i < end; i++) {
               if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                   T temp = arr[i];
                   arr[i] = arr[i + 1];
                   arr[i + 1] = temp;
                   swapped = i;
               }
           }
           end = swapped;
           for (int i = end; i > start; i--) {
               if (comparator.compare(arr[i], arr[i  -1]) < 0) {
                   T temp = arr[i];
                   arr[i] = arr[i - 1];
                   arr[i - 1] = temp;
                   swapped = i;
               }
           }
           start = swapped;
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null");
        }

        int len = arr.length;
        int midIndex = len/2;
        T[] leftArr = null;
        T[] rightArr = null;
        if (len > 1) {
            leftArr = (T[]) new Object[midIndex];
            rightArr = (T[]) new Object[len - midIndex];
            for (int i = 0; i < midIndex; i++) {
                leftArr[i] = arr[i];
            }
            for (int i = midIndex; i < arr.length; i++) {
                rightArr[i - midIndex] = arr[i];
            }
        }
        if (leftArr != null) {
            mergeSort(leftArr, comparator);
        }
        if (rightArr != null) {
            mergeSort(rightArr, comparator);
        }

        int leftIndex = 0, rightIndex = 0, currIndex = 0;
        if (leftArr != null && rightArr != null) {
            while (leftIndex < midIndex && rightIndex < len - midIndex) {
                if (comparator.compare(leftArr[leftIndex], rightArr[rightIndex]) <= 0) {
                    arr[currIndex] = leftArr[leftIndex];
                    leftIndex++;
                } else {
                    arr[currIndex] = rightArr[rightIndex];
                    rightIndex++;
                }
                currIndex++;
            }
            while (leftIndex < midIndex) {
                arr[currIndex] = leftArr[leftIndex];
                currIndex++;
                leftIndex++;
            }
            while (rightIndex < len - midIndex) {
                arr[currIndex] = rightArr[rightIndex];
                currIndex++;
                rightIndex++;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null");
        }
        if (rand == null) {
            throw new IllegalArgumentException("random is null");
        }
        qSort(arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     *
     * @param arr array
     * @param comparator comparator
     * @param rand random
     * @param left left bound
     * @param right right bound
     * @param <T>
     */
    private static <T> void qSort(T[] arr, Comparator comparator, Random rand, int left, int right) {
        if (right - left <= 0) {
            return;
        }
        int pivotIndex = rand.nextInt(right - left + 1) + left;
        T pivot = arr[pivotIndex];
        T temp = arr[left];
        arr[left] = pivot;
        arr[pivotIndex] = temp;
        int i = left + 1, j = right;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivot) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivot) >= 0) {
                j--;
            }
            if (i <= j) {
                T temp1 = arr[i];
                arr[i] = arr[j];
                arr[j] = temp1;
                i++;
                j--;
            }
        }
        T temp2 = arr[j];
        arr[j] = pivot;
        arr[left] = temp2;
        qSort(arr, comparator, rand, left, j - 1);
        qSort(arr, comparator, rand, j + 1, right);
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        int largestNumber = arr[0];
        int iterations = 0;
        int len = arr.length;

        for (int i = 1; i < len; i++) {
            if (Math.abs(arr[i]) > largestNumber) {
                largestNumber = Math.abs(arr[i]);
            }
        }
        while (largestNumber > 0) {
            largestNumber /= 10;
            iterations++;
        }
        int div = 1;
        for (int i = 1; i <= iterations; i++) {
            for (int j = 0; j < len; j++) {
                int bucket = ((arr[j] / div) % 10) + 9;
                if (buckets[bucket] == null) {
                    LinkedList<Integer> newLL = new LinkedList<>();
                    buckets[bucket] = newLL;
                }
                buckets[bucket].add(arr[j]);
            }
            int index = 0;
            for (int b = 0; b < buckets.length; b++) {
                while (buckets[b] != null && !buckets[b].isEmpty()) {
                    arr[index] = buckets[b].removeFirst();
                    index++;
                }
            }
            div *= 10;
        }
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        PriorityQueue<Integer> priorQueue = new PriorityQueue(data);
        System.out.println();
        int[] arr = new int[data.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = priorQueue.remove();
        }
        return arr;
    }
}
