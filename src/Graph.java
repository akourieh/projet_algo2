import java.util.*;

public class Graph {
    private ArrayList<ArrayList<Vertex>> adj;
    private ArrayList<Vertex> vertices;
    private int k;
    private int n;
    private Vertex maxDegreeVertex;

    public Graph(int n) 
    {
        this.n = n;                                         // # de sommets
        this.maxDegreeVertex = new Vertex(0);
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
        vertices.get(v).addDegree();
        vertices.get(u).addDegree();

        if (vertices.get(v).degree() > maxDegreeVertex.degree()) maxDegreeVertex = vertices.get(v);
        else if (vertices.get(u).degree() > maxDegreeVertex.degree()) maxDegreeVertex = vertices.get(u);
    }

    public void removeVertex(int i)
    {
        Vertex v = vertices.get(i);
        for (int j = 0; j < adj.get(i).size(); j++) {
            int neighbor = adj.get(i).get(j).number();
            int ind = adj.get(neighbor).indexOf(v);
            adj.get(neighbor).remove(ind);
            vertices.get(neighbor).substractDegree();
        }
        vertices.remove(i);
        adj.remove(i);
        n--;
    }

    public int n() 
    {
        return n;
    }

    public Vertex maxDegree() 
    {
        return maxDegreeVertex;
    }

    public void printAdjacencyList()
    {
        for (int i = 0; i < adj.size(); i++) {
            System.out.print("\n Adjacency list of vertex " + vertices.get(i).number() + " of degree " + vertices.get(i).degree() + " : ");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(adj.get(i).get(j).number() + " ");
            }
            System.out.println();
        }
    }

    public static boolean iskDegenerate(int k)
    {
        
        return true;
    } 

    public static int isDegenerate()
    {
        // should we try for all "k" possible knowing what is the max degree of the graph
        return 0;
    } 


}
