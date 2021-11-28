package MergeTopic;

public class Merge2 {
    class Solution {
        public int maxSubArray(int[] nums) {
            return find(nums,0,nums.length-1).max;
        }

        public State find(int[] nums,int start,int end){

            if(start == end){
                return new State(nums[start],nums[start],nums[start],nums[start]);
            }

            int middle = (start + end) / 2;
            State stateLeft = find(nums,start,middle);
            State stateRight = find(nums,middle + 1,end);
            int total = stateLeft.total + stateRight.total;
            int leftMax = Math.max(stateLeft.leftMax, stateLeft.total + stateRight.leftMax);
            int rightMax = Math.max(stateRight.rightMax, stateRight.total + stateLeft.rightMax);
            int max = Math.max(stateLeft.rightMax + stateRight.leftMax,Math.max(stateLeft.max,stateRight.max));

            return new State(leftMax,rightMax,max,total);

        }
    }



    class State {
        int leftMax;    //从最左端开始的最大字串和
        int rightMax;   //以最右端结束的最大子串和
        int max;        //
        int total;      //

        public State(int leftMax, int rightMax, int max, int total) {
            this.leftMax = leftMax;
            this.rightMax = rightMax;
            this.max = max;
            this.total = total;
        }
    }
}
