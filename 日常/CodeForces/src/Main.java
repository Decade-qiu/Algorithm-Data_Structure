import java.util.*;
import java.io.*;

public class Main {
    static String ss, io[];
    static int test, N = 200010, M = 10000007;
    static void solve() throws IOException{
        int n = ni(), p = ni();
        int[] a = new int[N];
        for (int i = n;i >= 1;i--) a[i] = ni();
        int l = 0, r = p-1;
        while (l <= r){
            int m = l+r >> 1;
            if (ck(a, n, m, p)) r = m-1;
            else l = m+1;
        }
        out.println(l);
    }
    static boolean ck(int[] a, int n, int m, int p){
        List<int[]> x = new ArrayList<>();
        int pre = 0;
        for (int i = 1;i <= n;i++){
            int l = a[i];
            int r = a[i]+(i==1?m:pre);
            if (r >= p){
                pre = 1;
                x.add(new int[]{0, r-p});
                r = p-1;
            }else pre = 0;
            x.add(new int[]{l, r});
        }
        if (pre == 1){
            x.add(new int[]{1, 1});
        }
        Collections.sort(x, (s, t)->{
            if (s[0] == t[0]) return s[1]-t[1];
            return s[0]-t[0];
        });;
        int st = x.get(0)[0], ed = x.get(0)[1];
        for (int i = 1;i < x.size();i++){
            int l = x.get(i)[0], r = x.get(i)[1];
            if (l >= ed+2) return false;
            ed = Math.max(ed, r);
        }
        return st==0 && ed==p-1;
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