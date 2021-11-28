package TreeTopic;

import java.util.Scanner;

public class RBTreeTest {
    public static void main(String[] args) {
        //新增节点
        //insertOpt();
        //删除节点
        deleteOpt();
    }

    /**
     * 插入操作
     */
    public static void insertOpt(){
        Scanner scanner=new Scanner(System.in);
        RedBlackTree rbt=new RedBlackTree();
        while (true){
            System.out.println("请输入你要插入的节点:");
            String key=scanner.next();
            System.out.println();
            //这里代码最多支持3位数，3位以上的话红黑树显示太错位了，这里就不重构代码了,大家可自行重构
            rbt.put(Integer.parseInt(key));
            TreeOperation.show(rbt.getRoot());
        }
    }

    /**
     * 删除操作
     */
    public static void deleteOpt(){
        RedBlackTree rbt = new RedBlackTree();
        //测试1：预先造10个节点（1-10）

        //测试2：包含2位数和3位数的测试代码 41 38 31 12 19 8 77 88 99 100 101
        rbt.put(41);
        rbt.put(38);
        rbt.put(31);
        rbt.put(12);
        rbt.put(19);
        rbt.put(8);
        rbt.put(77);
        rbt.put(88);
        rbt.put(99);
        rbt.put(100);
        rbt.put(101);

        TreeOperation.show(rbt.getRoot());
        //以下开始删除
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("请输入你要删除的节点:");
            String key=scanner.next();
            //1 2 3 88 66 77 100 5 4 101
            rbt.delete(Integer.parseInt(key));
            TreeOperation.show(rbt.getRoot());
        }
    }
}

