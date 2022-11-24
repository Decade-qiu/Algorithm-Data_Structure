import java.util.*;

import java.io.*;
import java.lang.reflect.Array;

public class Main {
    static String ss, io[];
    static int test, N = 10010, M = 10000007;
    static int n, m, k;
    static int a[] = new int[N];
    static void solve() throws IOException{
        n = ni();
        int z = -1, o = -1, ans = 0;
        for (int i = 0;i < n;i++){
            a[i] = ni();
            if (a[i] == 0){
                ans += (i-o) + (o+1)*2;
                z = i;
            }else{
                ans += (z+1)*2;
                o = i;
            }
        }
        out.println(ans);
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