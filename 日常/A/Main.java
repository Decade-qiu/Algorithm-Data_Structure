import java.util.*;

import java.io.*;

public class Main {
    static String ss, io[];
    static int test, N = 100010, M = 10000007;
    static int n, m;
    static int a[] = new int[N], tr[] = new int[N], ans[] = new int[N];
    static void solve() throws IOException{
        n = ni();
        for (int i = 0;i < n;i++) {
            a[i] = ni();
        }
        tr[n-1] = a[n-1];
        for (int i = n-2;i >= 0;i--) tr[i] = min(tr[i+1], a[i]);
        for (int i = 0;i < n;i++){
            int v = a[i];
            int l = i, r = n-1;
            while (l <= r){
                int m = l+r >> 1;
                if (tr[m] >= v) r = m-1;
                else l = m+1;
            }
            if (l == i) ans[i] = -1;
            else ans[i] = l-i-2;
        }
        for (int i = 0;i < n;i++) out.print(ans[i]+" ");
    }                             
    public static void main(String[] args) throws Exception {
        test = 1; //ni(in.readLine());
        while (test-- > 0){ 
            solve();
        }
        out.flush();
    }
    static int ni() throws IOException{input.nextToken();return (int) input.nval;}
    static long nl() throws IOException{input.nextToken();return (long) input.nval;}
    static int ni(String x) {return Integer.parseInt(x);}
    static long nl(String x) {return Long.parseLong(x);}
    static int max(int a, int b) {return a > b ? a : b;}    
    static int min(int a, int b) {return a < b ? a : b;}
    static int abs(int a) {return a > 0?a:-a;}
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer input = new StreamTokenizer(in);
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
}  