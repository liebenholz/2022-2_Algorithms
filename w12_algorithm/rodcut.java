package univ_lecture.knu22_algorithm.w12_algorithm;

public class rodcut {
    
    public static int max(int i, int j) {
        if (i > j)
            return i;
        else
            return j;
    }

    public static int rodcut_rec(int n, int[] p) {
        int r;
        if (n == 0)
            return 0;
        else
            r = -1;
            for(int i=0; i<n; n++) {
                r = max(r, p[i] + rodcut_rec(n - i, p));
            }
            return r;
    }

    public static int rodcut_mem(int n, int[] p, int[] r) {
        if(r[n] < 0) {
            if (n == 0)
                r[n] = 0;
            else {
                r[n] = -1;
                for(int i=0; i<n; i++) {
                    r[n] = max(r[n], p[i] + rodcut_mem(n - i, p, r));
                }
            }
        }
        return r[n];
    }

    public static int rodcut_tab(int n, int[] p) {
        int[] r = new int[n+1];
        for(int i=0; i<n; n++) {
            r[i] = -1;
            for(int j=0; j<i; j++) {
                r[i] = max(r[i], r[i - j] + p[j]);
            }
        }
        return r[n];
    }

    public static void main(String args[]) {
        int[] profit = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int leng = 4;
        int[] cut_profit = new int[leng+1];
        
        System.out.println("Recurrence : " + rodcut_rec(leng, profit));
        System.out.println("Memoization : " + rodcut_mem(leng, profit, cut_profit));
        System.out.println("Tabulization : " + rodcut_tab(leng, cut_profit));

    }
}
