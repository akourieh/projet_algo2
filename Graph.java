import java.util.*;

public class Graph {
    private ArrayList<ArrayList<Vertex>> adj;
    private ArrayList<Vertex> vertices;
    private int k;
    private int n;

    public Graph(int n) {
        this.n = n;                                         // # de sommets
        this.vertices = new ArrayList<Vertex>();
        this.adj = new ArrayList<ArrayList<Vertex>>();      // vecteur de vecteur des sommets adjacents a chaque sommet

        for (int v = 0; v < n; v++) {
            vertices.add(new Vertex(v));
        }

        for (int v = 0; v < n; v++) {
            adj.add(new ArrayList<Vertex>());
        }
    }


    public void addEdge(int u, int v)
    {
        adj.get(u).add(vertices.get(v));
        adj.get(v).add(vertices.get(u));
        n++;
    }

    public int n() {
        return n;
    }

    public void printGraph()
    {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("\n Adjacency list of vertex " + i);
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> " + adj.get(i).get(j).number());
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        int n = 5;
        Graph g = new Graph(n);

        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        g.printGraph();
    }
}
