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
            int s = 0;
            while (x.charAt(n-s-1) == y.charAt(s)) ++s;
            int t = 0;
            while (y.charAt(m-t-1) == x.charAt(t)) ++t;
            if (t == 0 && s == 0){
                out.println(x+y);
            }else{
                if (t < s){
                    out.println(x.substring(0, n-s)+y);
                }else out.println(y.substring(0, m-s)+x);
            }
        }
    }                             
    public static void main(String[] args) throws Exception {
        test = 1; //ni(in.readLine());
        while (test-- > 0){ 
            solve();
            //testCase();
        }
        out.flush();
    }
    static void testCase() throws IOException{
        String x = in.readLine(), y = in.readLine();
        n = x.length();
        m = y.length();
        if (x.contains(y)) out.println(x);
        else if (y.contains(x)) out.println(y);
        else{
            int s = 0;
            while (x.charAt(n-s-1) == y.charAt(s)) ++s;
            int t = 0;
            while (y.charAt(m-t-1) == x.charAt(t)) ++t;
            if (t == 0 && s == 0){
                out.println(x+y);
            }else{
                if (t < s){
                    out.println(x.substring(0, n-s)+y);
                }else out.println(y.substring(0, m-s)+x);
            }
        }
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