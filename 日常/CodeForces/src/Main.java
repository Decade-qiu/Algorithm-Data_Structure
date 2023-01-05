import java.util.*;import java.io.*;
import java.util.stream.IntStream;

public class Main {
    static String ss, io[];
    static int test, N = 200010, M = 10000007;
    static int c[] = new int[N], sum = 0;
    static void solve() throws Exception{
        Object[] tp = {1,2,3};
        System.out.println(Arrays.toString(tp));
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