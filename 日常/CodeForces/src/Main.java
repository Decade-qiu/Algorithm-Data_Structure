import java.util.*;
import java.io.*;

public class Main {
    static String ss, io[];
    static int test, N = 200010, M = 10000007;
    static void solve() throws IOException{
        long n = ni(), m = ni();
        long x = 1;
        while ((n*x)%10 != 0) x++;
        if (x > m){
            out.println(n*m);
            return;
        }
        long res = m/x;
        String tp = String.valueOf(res);
        res = (tp.charAt(0)-'0')*(long)Math.pow(10, tp.length()-1);
        out.println(n*x*res);
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