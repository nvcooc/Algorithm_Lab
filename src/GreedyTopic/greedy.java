package GreedyTopic;

import java.util.*;

public class greedy {
    public static void main(String[] args) {
        int d[] = new int[]{4,2,4,3,1,4,6}; //截止时间
        int w[] = new int[]{70,60,50,40,30,20,10}; //惩罚系数
        int n[] = new int[d.length + 1];
        HashMap<Integer,Integer> mapOn = new HashMap<>();
        HashMap<Integer,Integer> mapOut = new HashMap<>();
        //tag时间之前不能再添加任务了
        int tag = 0;

        for(int i = 0;i < d.length;i++) {
            //任务i注定超时
            if (d[i] <= tag) {
                //任务编号从1开始
                mapOut.put(i+1,d[i]);
            } else {
                for (int j = d[i]; j < n.length; j++) {
                    mapOn.put(i+1,d[i]);
                    n[j]++;
                    if (n[j] >= j) {
                        if (j > tag)
                            tag = j;
                    }
                }
            }
        }

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
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
    }
}
