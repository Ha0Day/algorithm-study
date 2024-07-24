import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G3_1939_중량제한_sol2 {
    public static int N, M;
    public static List<Edge> edges;
    public static int island1, island2;
    public static int[] rank, parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<Edge>();
        rank = new int[N + 1];
        parent = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        st = new StringTokenizer(br.readLine());
        island1 = Integer.parseInt(st.nextToken());
        island2 = Integer.parseInt(st.nextToken());


        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o2.w < o1.w) {
                    return -1;
                }
                if (o2.w > o1.w) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        int answer = 0;
        makeSet();

        for (Edge cur : edges) {
            union(cur.s, cur.e);
            if (find(island1) == find(island2)) {
                answer = cur.w;
                break;
            }
        }
        System.out.println(answer);
    }

    public static void makeSet() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return false;
        }

        if (rank[a] < rank[b]) {
            rank[b] += rank[a];
            parent[a] = b;
        } else {
            rank[a] += rank[b];
            parent[b] = a;
        }
        return true;
    }

    public static class Edge {
        int s; //노드1
        int e; //노드2
        int w; //가중치

        Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

}
