import java.util.*;import java.io.*;

public class Main {
    static String ss, io[];
    static int test, N = 100010, M = 10000007;
    static Map<Character, List<String>> mp = new HashMap<>();
    static Set<String> vis = new HashSet<>();
    static int ok = 0;
    static void solve() throws IOException{
        int n = ni(in.readLine());
        Set<String> g = new HashSet<>();
        for (int i = 0;i < n;i++){
            ss = in.readLine();
            if (ss.length() == 1) ss = ss + ss.charAt(0);
            String cur = ""+ss.charAt(0)+ss.charAt(ss.length()-1);
            mp.computeIfAbsent(ss.charAt(0), k->new ArrayList<>()).add(cur);
            g.add(cur);
        }
        for (String s : g){
            vis.clear();
            vis.add(s);
            dfs(s, 1);
            if (ok == 1) break;
        }
        out.println(ok==1?"First":"Second");
    }
    static void dfs(String s, int len){
        //out.print(s+" ");
        int x = 0;
        for (String ne : mp.get(s.charAt(s.length()-1))){
            if (ok == 1) break;
            if (vis.contains(ne)) continue;
            x = 1;
            vis.add(ne);
            dfs(ne, len+1);
            vis.remove(ne);
        }
        if (x == 0 && len%2==1) ok = 1; 
        //if (x == 0) out.println();
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