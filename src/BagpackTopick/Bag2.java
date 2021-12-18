package BagpackTopick;

//带记忆的递归法求0-1背包问题
public class Bag2 {
    static int[] weight = {2, 2, 6, 5, 4};//重量表
    static int[] value = {6, 3, 5, 4, 6};//价值表
    static int n = 5;//物品数量
    static int maxWeight = 10;//背包的承重极限
    static int[][] r = new int[n][maxWeight + 1];//记录表

    public static void main(String[] args) {
        //将记录表初始化为-1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < maxWeight + 1; j++) {
                r[i][j] = -1;
            }
        }
        System.out.println(dfs(0, maxWeight));
    }

    /**
     *
     * @param i 判断物品i
     * @param weightLeft 此时背包空余容量
     * @return 将物品i到物品maxLength放入背包的最大价值
     */
    private static int dfs(int i, int weightLeft) {
        if (i == n)
            return 0;//没有物品选择了
        if (weightLeft <= 0)
            return 0;//容量不足了
        if (r[i][weightLeft] >= 0) {
            //计算前先检查是否有记录
            return r[i][weightLeft];
        }
        //value1 不选物品i
        //value2 选择物品i
        int value1 = dfs(i + 1, weightLeft);//不选当前物品，容量不变
        if (weightLeft >= weight[i]) {
            int value2 = value[i] + dfs(i + 1, weightLeft - weight[i]);//选择当前物品
            r[i][weightLeft] = Math.max(value1, value2);//返回前，先记录子问题的解
            return Math.max(value1, value2);
        }
        r[i][weightLeft] = value1;//返回前，先记录子问题的解
        return value1;
    }
}
