package BagpackTopick;

//动态规划法求0-1背包问题
public class Bag {
    public static void main(String[] args) {
        goods[] goods = new goods[]{new goods(2,6),new goods(2,3),
                new goods(6,5),new goods(5,4),new goods(4,6)};
        //dp[i][j]的含义：从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少
        int[][] dp = new int[5][11];
        for(int j = 1;j < 11;j++){
            for(int i = 0;i < 5;i++){
                //确定递推公式
                //思想是：①考虑物品[0...i-1]在背包容量为j时的最大价值（物品i不放入背包） ②将物品i加入背包时的最大价值 取①、②两者最大值
                //情况②计算方法是：物品[0...i-1]在背包容量为j-物品i的重量时的最大价值 与 物品i的价值 之和，即刚刚好将物品i塞进去
                //dp[i][j] = Math.max(dp[i - 1][j],dp[i - 1][j - goods[i].weight] + goods[i].value);
                //只能选第一物品时
                if(i == 0){
                    //当背包比物品1的容量大或等时才能放进去
                    dp[i][j] = j >= goods[i].weight ? goods[i].value : 0;
                }
                else {
                    //物品重量不大于背包重量时
                    if(j - goods[i].weight >= 0)
                        dp[i][j] = Math.max(dp[i - 1][j],dp[i - 1][j - goods[i].weight] + goods[i].value);
                    else
                    //物品根本放不进背包
                        dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int i = 4;
        int j = 10;
        while (i >= 0){
            if(i != 0) {
                //物品i没有放入背包
                if (dp[i][j] == dp[i - 1][j]) {
                    i--;
                }
                //如果不等 放入了背包
                else if (j - goods[i].weight >= 0) {
                    j -= goods[i].weight;
                    System.out.println("重量：" + goods[i].weight + "  价值：" + goods[i].value);
                    i = i - 1;
                }
            } else {
                if(dp[i][j] == goods[i].value)
                    System.out.println("重量：" + goods[i].weight + "  价值：" + goods[i].value);
                i--;
            }
        }

        System.out.println("最大价值为：" + dp[4][10]);
    }
}
class goods{
    int weight;
    int value;
    public goods(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}
