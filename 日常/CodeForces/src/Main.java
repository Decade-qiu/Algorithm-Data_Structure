import java.util.*;
import java.io.*;

public class Main {
    static String ss, io[];
    static int test, N = 200010, M = 10000007;
    static void solve() throws IOException{
        int n = ni(), f = 0;
        int[] a = new int[n+1], b = new int[n+1];
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 1;i <= n;i++) ts.add(i);
        for (int i = 1;i <= n/2;i++) {
            a[i] = ni();
            if (!ts.contains(a[i])) f = 1;
            ts.remove(a[i]);
        }
        if (f == 1) {out.println(-1);return;}
        for (int i = n/2;i >= 1;i--){
            Integer c = ts.lower(a[i]);
            if (c == null) {out.println(-1);return;}
            else {
                b[i] = c;
                ts.remove(c);
            }
        }
        for (int i = 1;i <= n/2;i++) out.print(b[i]+" "+a[i]+" ");
        out.println();
    }
    public static void main(String[] args) throws Exception {
        //test = 1;
        test = ni(in.readLine());
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