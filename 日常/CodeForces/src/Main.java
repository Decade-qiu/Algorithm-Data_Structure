import java.util.*;

import java.io.*;
import java.lang.reflect.Array;

public class Main {
    static String ss, io[];
    static int test, N = 10010, M = 10000007;
    static int t, n, m, k;
    static int a[] = new int[N];
    static void solve() throws IOException{
        String x = in.readLine(), y = in.readLine();
        n = x.length();
        m = y.length();
        if (x.contains(y)) out.println(x);
        else if (y.contains(x)) out.println(y);
        else{
            long M = 0x60000005, P = 171;
            long[] h1 = new long[n+1], h2 = new long[m+1];
            long[] p = new long[Math.max(n, m)+1];
            p[0] = 1;
            for (int i = 1;i <= n;i++){
                p[i] = (p[i-1]*P) % M;
                h1[i] = (h1[i-1]*P % M + (x.charAt(i-1)-'a'+1)) % M;
            }
            for (int i = 1;i <= m;i++){
                p[i] = (p[i-1]*P) % M;
                h2[i] = (h2[i-1]*P % M + (y.charAt(i-1)-'a'+1)) % M;
            }
            int a1 = 0, a2 = 0;
            for (int i = 1;i <= min(n, m);i++){
                long xl = (h1[i]-h1[0]*p[i]%M+M)%M;
                long yr = (h2[m]-h2[m-i]*p[i]%M+M)%M;
                if (xl == yr) a1 = i;
            }
            for (int i = 1;i <= min(n, m);i++){
                long xr = (h1[m]-h1[m-i]*p[i]%M+M)%M;
                long yl = (h2[i]-h2[0]*p[i]%M+M)%M;
                if (xr == yl) a2 = i;
            }
            if (a1 > a2) out.println(y.substring(0, m-a1)+x);
            else out.println(x.substring(0, m-a2)+y);
        }
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