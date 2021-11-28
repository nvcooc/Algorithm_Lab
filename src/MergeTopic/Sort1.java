package MergeTopic;

public class Sort1 {

    public static void main(String[] args) {
        int[] A = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        Sort1 sort = new Sort1();
        System.out.println("快速排序过程：");
        sort.QuickSort(A,0,A.length-1);
    }

    public void QuickSort(int A[],int p,int r){
        if(p < r){
            int q = Partition(A,p,r);
            QuickSort(A,p,q-1);
            QuickSort(A,q+1,r);
        }
    }

    public int Partition(int A[],int p,int r){
        int x = A[r];
        int i = p - 1;
        int j = p;
        for(;j < r;j++){
            if(A[j] < x){
                i++;
                Exchange(A,i,j);
            }
        }
        Exchange(A,++i,r);
        for (int a:A){
            System.out.print(a+"  ");
        }
        System.out.println("归位元素：" + A[i]);
        return i;
    }

    public void Exchange(int A[],int a,int b){
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }
}
