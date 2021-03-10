import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 
 */
public class MergeSortedArray {


    /**
     * You may assume that nums1 has a size equal to m + n 
     * such that it has enough space to hold additional 
     * elements from nums2.
     * 
     * Execution time: O(n + m)
     * 
     * Runtime: 2 ms, faster than 6.98% of Java online submissions for Merge Sorted Array.
     * Memory Usage: 39 MB, less than 81.89% of Java online submissions for Merge Sorted Array.
     */
    static void merge1(int[] nums1, int m, int[] nums2, int n) {

        // **** sanity check(s) ****
        if (m == 0 && n == 0)
            return;
        if (n == 0)
            return;

        // **** initialization ****
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(n + m, (a,b) -> (a - b) );

        // **** add elements from nums1 - O(m) ****
        for (int i = 0; i < m; i++)
            pq.add(nums1[i]);

        // **** add elements from nums2 - O(n)****
        for (int i = 0; i < n; i++)
            pq.add(nums2[i]);

        // **** populate nums1 from the pq - O(m + n)****
        int i = 0;
        while (!pq.isEmpty())
            nums1[i++] = pq.remove();
    }


    /**
     * You may assume that nums1 has a size equal to m + n 
     * such that it has enough space to hold additional 
     * elements from nums2.
     * 
     * Execution time: O(n + m)
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 39 MB, less than 71.39% of Java online submissions.
     */
    static void merge(int[] nums1, int m, int[] nums2, int n) {

        // ***** initialization ****
        m--;                                // last element in nums1
        n--;                                // last element in nums2
        int i = nums1.length - 1;           // current element in nums2

        // **** copy sorted integers from nums2 or nums1 to nums1 (right to left) ****
        while (i >= 0 && m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n])
                nums1[i--] = nums1[m--];
            else
                nums1[i--] = nums2[n--];
        }

        // **** copy remaining sorted integers from nums2 to nums1 (right to left) ****
        while (n >= 0)
            nums1[i--] = nums2[n--];
    }


    /**
     * Test scaffolding.
     * 
     * !!!! NOT PART OF SOLUTION !!!!
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read data for the first array ****
        int[] nums1 = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // *** read the number of elements in the first array ****
        int m = Integer.parseInt(br.readLine().trim());
    
        // **** read data for the second array ****
        String str = br.readLine().trim();

        // ???? ????
        System.out.println("main <<< str ==>" + str + "<==");

        // **** check if empty string ****
        int[] nums2 = null;
        if (str.equals(""))
            nums2 = new int[0];
        else
            nums2 = Arrays.stream(str.split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // *** read the number of elements in the second array ****
        int n = Integer.parseInt(br.readLine().trim());

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<<     m: " + m);
        System.out.println("main <<<     n: " + n);
        System.out.println("main <<< nums1: " + Arrays.toString(nums1));
        System.out.println("main <<< nums2: " + Arrays.toString(nums2));


        // **** make a copy of nums1 for later use ****
        int[] nums3 = nums1.clone();

        // **** merge the two arrays into the first array ****
        merge1(nums1, m, nums2, n);

        // ???? display merged array ????
        System.out.println("main <<< nums1: " + Arrays.toString(nums1) + "\n");


        // **** restore nums1 ****
        nums1 = Arrays.copyOf(nums3, nums3.length);

        // ???? ????
        System.out.println("main <<< nums1: " + Arrays.toString(nums1));
        System.out.println("main <<< nums2: " + Arrays.toString(nums2));

        // **** merge the two arrays into the first array ****
        merge(nums1, m, nums2, n);

        // ???? display merged array ????
        System.out.println("main <<< nums1: " + Arrays.toString(nums1));
    }
}