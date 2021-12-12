package GreedyTopic;

import java.util.*;

public class greedy {
    public static void main(String[] args) {
        int d[] = new int[]{4,2,4,3,1,4,6}; //截止时间
        int w[] = new int[]{70,60,50,40,30,20,10}; //惩罚系数
        int n[] = new int[d.length + 1]; //保存时间点之前已经安排的任务数量
        HashMap<Integer,Integer> mapOn = new HashMap<>();
        HashMap<Integer,Integer> mapOut = new HashMap<>();
        //tag时间之前不能再添加任务了:tag时间前任务已经安排满了
        int tag = 0;

        for(int i = 0;i < d.length;i++) {
            //任务i注定超时(截止时间前活动已经排满)
            if (d[i] <= tag) {
                //任务编号从1开始
                mapOut.put(i+1,d[i]);
            } else {
                mapOn.put(i+1,d[i]);
                for (int j = d[i]; j < n.length; j++) {

                    n[j]++;
                    if (n[j] >= j) {
                        if (j > tag)
                            tag = j;
                    }
                }
            }
        }

        int w_sum = 0;
        System.out.print("超时活动：");
        for(Map.Entry<Integer,Integer> e:mapOut.entrySet()){
            System.out.print(e.getKey()+"  ");
            w_sum += w[e.getKey() - 1];
        }
        System.out.println("\n总的惩罚：" + w_sum);

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(mapOn.entrySet()); //转换为list
        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for (Map.Entry<Integer,Integer> entry:mapOut.entrySet()){
            list.add(entry);
        }
        System.out.print("最终活动排序：");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).getKey() + "  ");
        }
    }
}
