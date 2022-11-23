
import java.util.*;
import java.io.*;

public class Main {
    static String ss, io[];
    static int test, n, N = 1010;
    static int[] a[] = new int[N][2];
    static int[] b[] = new int[N][2];
    static void solve() throws IOException{
        io = in.readLine().split(" ");
        n = ni(io[0]); int m = ni(io[1]);
        int n1 = 0, n2 = 0;
        for (int i = 0;i < n;i++){
            io = in.readLine().split(" ");
            int u = ni(io[0]), v = ni(io[1]);
            if (io[2].equals("A")){
                a[n1][0] = u;
                a[n1++][1] = v;
            }else{
                b[n2][0] = u;
                b[n2++][1] = v;
            }
        }
        while (m-- > 0){
            int C = ni(), A = ni(), B = ni();
            int u = a[0][0], v = a[0][1];
            int sum = C+A*u+B*v;
            if (sum == 0){
                out.println("No");
                continue;
            }
            int f = sum<0?-1:1, res = 0;
            for (int i = 1;i < n1;i++){
                u = a[i][0];v = a[i][1];
                sum = C+A*u+B*v;
                if (sum*f <= 0){
                    res = -1;
                    break;
                }
            }
            if (res == -1){
                out.println("No");
                continue;
            }
            for (int i = 0;i < n2;i++){
                u = b[i][0];v = b[i][1];
                sum = C+A*u+B*v;
                if (sum*f >= 0){
                    res = -1;
                    break;
                }
            }
            if (res == -1){
                out.println("No");
            }else{
                out.println("Yes");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        test = 1;
        while (test-- > 0){
            solve();
        }
        //out.println();
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