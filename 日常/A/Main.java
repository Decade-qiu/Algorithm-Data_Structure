import java.util.*;

import java.io.*;

public class Main {
    static String ss, io[];
    static int test, N = 30, M = 10000007;
    static int n, m;
    static int a[] = new int[N], b[][] = new int[N][N];
    static void solve() throws IOException{
        n = ni(in.readLine());
        int ans = 0;
        HashSet<String> hs = new HashSet<>();
        while (n-- > 0){
            io = in.readLine().split(" ");
            hs.add(io[0]+"!"+io[1]);
        }
        out.println(hs.size());
    }
    static int aa(int x, int y){
        return x == 0 ? y : aa(y, y % x);
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