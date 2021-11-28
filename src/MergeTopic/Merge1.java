package MergeTopic;


public class Merge1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int total = len1 + len2;

        //分奇数和偶数
        if(total % 2 == 1){
            //找到两个数组中第 total/2 + 1小的数
            return find(nums1,0,nums2,0,total/2 + 1);
        }else {
            int a = find(nums1,0,nums2,0,total/2);
            int b = find(nums1,0,nums2,0,total/2 + 1);
            return (a+b)/2.0;
        }

    }
    /**
     * @param i nums1 从下标 i 开始
     * @param j nums2 从下标 j 开始
     * @param k nums1、nums2中第k小的数
     */
    public int find(int[] nums1, int i, int[] nums2, int j, int k) {
         //让nums1长度为最小的那个，nums1会先于nums2判断完
        if(nums1.length - i > nums2.length - j){
            return find(nums2,j,nums1,i,k);
        }
        //若num1判断完毕，直接去nums2寻找
        if(i == nums1.length){
            return nums2[j + k - 1];
        }
        //如果只需要查找最小的数
        if(k == 1){
            return Math.min(nums1[i],nums2[j]);
        }
        /**
         * si: 递归寻找时nums1的起始位置
         * sj: 递归寻找时nums2的起始位置
         * nums1为长度较小的那一个，需要越界判断
         */
        int si = Math.min(i + k/2, nums1.length),sj = j + k/2;
        if(nums1[si-1] > nums2[sj-1]){
            return find(nums1,i,nums2,sj,k-(sj-j));
        }else {
            return find(nums1,si,nums2,j,k-(si-i));
        }
    }
}
