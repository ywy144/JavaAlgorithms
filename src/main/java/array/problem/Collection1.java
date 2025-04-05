package array.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collection1 {

    /**
     * 两数之和 problemId = 1
     * @param nums
     * @param target
     * @return index
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 寻找两个数组的中位数 problemId = 4
     * @param nums1 升序
     * @param nums2 升序
     * @return 两个数组的中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int ptr1 = 0, ptr2 = 0;
        int step = 0;
        int nums = nums1.length + nums2.length;
        // 偶数，找第 n/2 和 (n/2)+1 个数
        if (nums % 2 == 0) {
            int a = 0, b = 0, cur = 0;
            while (step < nums / 2 + 1) {
                if (ptr1 < nums1.length && (ptr2 >= nums2.length || nums1[ptr1] < nums2[ptr2])) {
                    cur = nums1[ptr1++];
                } else if (ptr2 < nums2.length) {
                    cur = nums2[ptr2++];
                }
                step++;
                a = step == nums / 2 ? cur : a;
                b = step == nums / 2 + 1 ? cur : b;
            }
            return (double) (a + b) / 2;
        // 奇数个，找第 (n/2)+1 个数
        } else {
            int cur = 0;
            while (step < nums / 2 + 1) {
                if (ptr1 < nums1.length && (ptr2 >= nums2.length || nums1[ptr1] < nums2[ptr2])) {
                    cur = nums1[ptr1++];
                } else if (ptr2 < nums2.length) {
                    cur = nums2[ptr2++];
                }
                step++;
            }
            return cur;
        }
    }

    /**
     * 盛水最多的容器 problemId = 11
     * @param height
     * @return 容积
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }

    /**
     * 三数之和 problemId = 15
     * @param nums 无序
     * @return 三个数，没有返回空列表
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0) return ans;
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;
            while(l < r)
                if(nums[i] + nums[l] + nums[r] == 0){
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while(l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while(l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                }
                else if(nums[i] + nums[l] + nums[r] > 0) {
                    r--;
                }
                else {
                    l++;
                }
        }
        return ans;
    }

    /**
     * 最接近的三数之和 problemId = 16
     * @param nums 无序
     * @param target 目标值
     * @return 最接近的 target 的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {
        if(nums.length == 3)
            return nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        int ans = 0;
        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            int l = i + 1, r = nums.length - 1;
            while(l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if(sum == target)
                    return target;
                else if(sum > target){
                    if(Math.abs(sum - target) < minDist){
                        ans = sum;
                        minDist = Math.abs(sum - target);
                    }
                    r--;
                }
                else{
                    if(Math.abs(sum - target) < minDist) {
                        ans = sum;
                        minDist = Math.abs(sum - target);
                    }
                    l++;
                }
            }
        }
        return ans;
    }

    /**
     * 四数之和 problemId = 18
     * @param nums 无序
     * @param target 目标值
     * @return 满足条件的不重复四元组
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return null;
    }

    /**
     * 删除有序数组的重复项 problemId = 26
     * @param nums
     * @return 新数组长度
     */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    /**
     * 移除元素 problemId = 27
     * @param nums 数组
     * @param val 目标值
     * @return 新数组长度
     */
    public int removeElement(int[] nums, int val) {
        int rear = nums.length - 1;
        int head = 0;
        while (rear >= 0 && nums[rear] == val) {
            rear--;
        }
        while (head < rear) {
            if (nums[head] == val) {
                nums[head] = nums[rear--];
                while (head < rear && nums[rear] == val) {
                    rear--;
                }
            }
            head++;
        }
        return rear + 1;
    }

    /**
     * 下一个排列 problemId = 31
     * @param nums 数组
     */
    public void nextPermutation(int[] nums) {

    }

    /**
     * 搜索旋转排序数组 problemId = 34
     * @param nums 数组
     * @param target 目标值
     * @return 下标，不存在则为 -1
     */
    public int search(int[] nums, int target) {
        int pivot = 1;
        while (pivot < nums.length && nums[pivot] > nums[pivot - 1]) {
            pivot++;
        }
        int leftPos = Arrays.binarySearch(nums, 0, pivot, target);
        int rightPos = Arrays.binarySearch(nums, pivot, nums.length, target);
        return leftPos < 0 ? rightPos < 0 ? -1 : rightPos : leftPos;
    }
}
