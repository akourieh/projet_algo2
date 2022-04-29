import java.util.*;

public class Graph {
    private ArrayList<ArrayList<Vertex>> adj;
    private ArrayList<Vertex> vertices;
    private int n;
    private int k;
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

    public Vertex getVertex(int i) 
    {
        if (0 <= i <= n) return vertices.get(i);
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


    public void DSatur() 
    {
        ArrayList<Integer> colors = new ArrayList<>(k + 1);
        for(int i = 0; i < (k+1); i++)
        {
            colors.add(i);
        }
        ArrayList<Vertex> alreadyColored = new ArrayList<>();
        
        Vertex maxSatVertex = maxDegreeVertex;
        int currentColor = 0;
        maxSatVertex.color = colors(currentColor);
        alreadyColored.add(maxSatVertex);

        int i = maxSatVertex.number();
        while (alreadyColored.size() = n) 
        {
            currentColor = 0;
            for(int j = 0; j < adj[i].size(); j++)
            {
                Vertex neighbor = adj.get(i).get(j);
                int indNeighbor = adj.get(i).get(j).number();
                for(int k = 0; k < adj[indNeighbor].size(); k++)
                {
                    if (!alreadyColored.contains(adj[indNeighbor][k]))
                    {
                        adj[indNeighbor][k].addSat();
                    }
                }
                
                if (neighbor.saturation > maxSatVertex.saturation)
                {
                    maxSatVertex = neighbor;
                }
            }

            i = maxSatVertex.number();

            for(int j = 0; j < adj[i].size(); j++)
            {
                if (adj[j][i].color >= currentColor) 
                {
                    currentColor++;
                }
            }

            maxSatVertex.color = currentColor;
            alreadyColored.add(maxSatVertex);
        }
    }
}
