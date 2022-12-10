import java.util.*;
import java.io.*;

public class Main {
    static String ss, io[];
    static int test, N = 100010, M = 10000007;
    static long[] f = new long[N*4];
    static long[] g = new long[N*4];
    static int[] a = new int[N];
    static void bd(int k, int l, int r){
        if (l == r){
            f[k] = a[l];
            return;
        }
        int m = l+r >> 1;
        bd(k*2, l, m);
        bd(k*2+1, m+1, r);
        f[k] = f[k*2]+f[k*2+1];
    }
    static void down(int k, int l, int m, int r){
        f[k*2] += g[k]*(m-l+1);
        f[k*2+1] += g[k]*(r-m);
        g[k*2] += g[k];
        g[k*2+1] += g[k];
        g[k] = 0;
    }
    static void upd(int k, int l, int r, int s, int t, int v){
        if (s <= l && r <= t){
            f[k] += v*(r-l+1);
            g[k] += v;
            return;
        }
        int m = l+r >> 1;
        down(k, l, m, r);
        if (s <= m) upd(k*2, l, m, s, t, v);
        if (m+1 <= t) upd(k*2+1, m+1, r, s, t, v);
        f[k] = f[k*2]+f[k*2+1];
    }
    static long qry(int k, int l, int r, int s, int t){
        if (s <= l && r <= t) return f[k];
        int m = l+r >> 1;
        down(k, l, m, r);
        long ans = 0;
        if (s <= m) ans += qry(k*2, l, m, s, t);
        if (m+1 <= t) ans += qry(k*2+1, m+1, r, s, t);
        return ans;
    }
    static void solve() throws IOException{
        int n = ni(), m = ni();
        for (int i = 1;i <= n;i++) a[i] = ni();
        bd(1, 1, n);
        while (m-- > 0){
            int op = ni();
            if (op == 1) upd(1, 1, n, ni(), ni(), ni());
            else out.println(qry(1, 1, n, ni(), ni()));
        }
    }
    public static void main(String[] args) throws Exception {
        test = 1;
        //test = ni(in.readLine());
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
    static long max(long a, long b) {return a > b ? a : b;}
    static int min(int a, int b) {return a < b ? a : b;}
    static long min(long a, long b) {return a < b ? a : b;}
    static int abs(int a) {return a > 0?a:-a;}
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer input = new StreamTokenizer(in);
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
}  