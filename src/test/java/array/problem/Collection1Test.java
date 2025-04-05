package array.problem;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Collection1Test {

    private Collection1 c = new Collection1();

    @Test
    public void findMedianSortedArraysTest() {
        int[] a = new int[]{2};
        int[] b = new int[]{1,3,5,7};
        System.out.println(c.findMedianSortedArrays(a, b));
    }

    @Test
    public void threeSumTest() {
        int[] arr = new int[]{-3, -3, 0, 0, 1, 1, 2, 2, 3, 3};
        System.out.println(c.threeSum(arr));
    }

    @Test
    public void threeSumClosestTest() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(c.threeSumClosest(arr, 1));
    }

    @Test
    public void removeDuplicatesTest() {
        int[] arr = {1, 1, 2, 2, 2, 3, 3, 4, 4, 4};
        System.out.println(c.removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
        int[] arr1 = {1, 1, 2};
        System.out.println(c.removeDuplicates(arr1));
        System.out.println(Arrays.toString(arr1));
    }

    @Test
    public void removeElementTest() {
        int[] arr = {1, 2, 2, 2, 1, 1, 2, 2, 1};
        System.out.printf("original array: %s%n", Arrays.toString(arr));
        int ans = c.removeElement(arr, 2);
        System.out.printf("new array: %s, ans = %d%n", Arrays.toString(arr), ans);
        arr = new int[]{1, 1, 1, 1, 1, 1};
        System.out.printf("original array: %s%n", Arrays.toString(arr));
        ans = c.removeElement(arr, 1);
        System.out.printf("new array: %s, ans = %d%n", Arrays.toString(arr), ans);
        arr = new int[]{2, 2, 2, 2, 1};
        System.out.printf("original array: %s%n", Arrays.toString(arr));
        ans = c.removeElement(arr, 2);
        System.out.printf("new array: %s, ans = %d%n", Arrays.toString(arr), ans);
    }

    @Test
    public void searchRotatedArrayTest() {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(c.search(arr, 5));
        System.out.println(c.search(arr, 7));
        System.out.println(c.search(arr, 2));
        System.out.println(c.search(arr, 0));
        System.out.println(c.search(arr, 100));
    }
}
