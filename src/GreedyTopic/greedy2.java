package GreedyTopic;

import java.util.*;

public class greedy2 {
    public static void main(String[] args) {
        int d[] = new int[]{4, 2, 4, 3, 1, 4, 6}; //截止时间
        int w[] = new int[]{0, 10, 20, 30, 40, 50, 60}; //惩罚系数
        int n[] = new int[d.length + 1]; //保存时间点之前已经安排的任务数量
        ArrayList<Activity> activities = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            activities.add(new Activity(d[i], w[i],i + 1));
        }

        activities.sort(greedy2::compare1);
        ArrayList<Activity> actOut = new ArrayList<>();
        ArrayList<Activity> actIn = new ArrayList<>();

        //tag时间之前不能再添加任务了:tag时间前任务已经安排满了
        int tag = 0;
        for (Activity a : activities){
            //任务i注定超时(截止时间前活动已经排满)
            if(a.endTime <= tag){
                actOut.add(a);
            } else {
                actIn.add(a);
                for (int j = a.endTime;j < n.length;j++){
                    n[j]++;
                    if(n[j] >= j){
                        if(j > tag)
                            tag = j;
                    }
                }
            }
        }

        int w_sum = 0;
        System.out.print("超时活动：");
        for (Activity a:actOut) {
            System.out.print(a.number + "  ");
            w_sum += a.costTime;
        }
        System.out.println("\n总的惩罚：" + w_sum);

        actIn.sort(greedy2::compare2);
        for (Activity a:actOut) {
            actIn.add(a);
        }
        System.out.print("最终活动排序：");
        for (Activity a:actIn){
            System.out.print(a.number + " ");
        }
    }
    //按惩罚从大到小排序
    static int compare1(Activity a1, Activity a2) {
        if(a2.costTime < a1.costTime){
            return -1;
        }else if(a2.costTime == a1.costTime){
            return 0;
        }else {
            return 1;
        }
    }
    //按截至时间从小到大排序
    static int compare2(Activity a1,Activity a2){
        if(a1.endTime < a2.endTime){
            return -1;
        }else if(a1.endTime == a2.endTime){
            return 0;
        }else {
            return 1;
        }
    }
}
class Activity {
    int endTime;
    int costTime;
    int number;

    public Activity(int endTime, int costTime,int number) {
        this.endTime = endTime;
        this.costTime = costTime;
        this.number = number;
    }
}
