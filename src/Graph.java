import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Graph {
    private ArrayList<ArrayList<Vertex>> adj;
    private ArrayList<Vertex> vertices;
    private int n;
    private Vertex maxDegreeVertex;

    private PriorityQueue<Vertex> pq;



    public Graph(int n)
    {
        this.n = n;                                         // # de sommets
        this.maxDegreeVertex = new Vertex(0);
        this.vertices = new ArrayList<>();
        this.adj = new ArrayList<>();      // vecteur de vecteur des sommets adjacents a chaque sommet
        this.pq = new PriorityQueue<>(
                new Comparator<Vertex>(){
                    @Override
                    public int compare(Vertex v1,
                                       Vertex v2)
                    {
                        if (v1.saturation() < v2.saturation()) return 1;
                        else if (v1.saturation() > v2.saturation()) return -1;
                        if (v1.degree() < v2.degree()) return 1;
                        else if (v1.degree() > v2.degree()) return -1;
                        return 0;
                    }
                });

        for (int v = 0; v < n; v++) {
            vertices.add(new Vertex(v));
        }

        for (int v = 0; v < n; v++) {
            adj.add(new ArrayList<>());
        }


        for (int i = 0; i < n; i++) {
            pq.add(vertices.get(i));
        }
    }

    public void addEdge(int u, int v)
    {
        adj.get(u).add(vertices.get(v));
        adj.get(v).add(vertices.get(u));
        vertices.get(v).addDegree();
        vertices.get(u).addDegree();

        vertices.get(v).setSaturation(vertices.get(v).degree());
        vertices.get(u).setSaturation(vertices.get(u).degree());
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

    public void printColors()
    {
        for (Vertex vertex : vertices) {
            System.out.print("\n Vertex " + vertex.number() + " of color : " + vertex.color());
        }
    }

    public void coloring()
    {
        long startTime = System.currentTimeMillis();

        boolean used[] = new boolean[n];
        Arrays.fill(used, false);
        int color;

        while (! pq.isEmpty())
        {
            Vertex max = pq.poll();
            pq.remove(max);
            int maxInd = max.number();

            for (int i = 0; i < adj.get(maxInd).size(); i++) {
                if (adj.get(maxInd).get(i).color() != -1) {
                    used[adj.get(maxInd).get(i).color()] = true;
                }
            }

            for (color = 0; color < n; color++) {
                if (!used[color])
                    break;
            }
            for (int i = 0; i < adj.get(maxInd).size(); i++) {
                if (adj.get(maxInd).get(i).color() != -1) {
                    used[adj.get(maxInd).get(i).color()] = false;
                }
            }

            max.setColor(color);

            for (int i = 0; i < adj.get(maxInd).size(); i++) {
                adj.get(maxInd).get(i).substractSat();
            }
        }

        System.out.println("Time elapsed (sec) = " + (System.currentTimeMillis() - startTime)/1000.0);
    }

      //https://www.baeldung.com/reading-file-in-java
      //https://stackoverflow.com/questions/38270388/reading-the-values-for-the-graph-from-text-file
    public void loadGraph(String basename, int edges) throws Exception {
       try {
            Scanner file = new Scanner(new File(basename));
            for(int i = 0; i < edges; i++) {
                    System.out.println("Entered");

                    int v1 = file.nextInt();
                    int v2 = file.nextInt();
                    addEdge(v1, v2);
            }
        } catch (FileNotFoundException e) {
        }
        }
}
