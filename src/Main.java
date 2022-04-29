
public class Main {


    // Building the graph shown in the project's pdf

    public static void main(String[] args)
    {
        int n = 29;
        Graph g = new Graph(n);

        g.addEdge(0, 1);

        g.addEdge(1, 2);

        g.addEdge(2, 3);
        g.addEdge(2, 14);

        g.addEdge(3, 4);
        g.addEdge(3, 13);

        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(4, 11);
        g.addEdge(4, 12);

        g.addEdge(5, 6);

        g.addEdge(6, 11);
        g.addEdge(6, 10);
        g.addEdge(6, 22);
        g.addEdge(6, 7);

        g.addEdge(7, 8);

        g.addEdge(8, 9);

        g.addEdge(10, 11);
        g.addEdge(10, 12);
        g.addEdge(10, 25);
        g.addEdge(10, 18);

        g.addEdge(11, 12);
        
        g.addEdge(12, 13);
        g.addEdge(12, 17);

        g.addEdge(13, 14);

        g.addEdge(14, 15);

        g.addEdge(15, 16);

        g.addEdge(17, 18);
        g.addEdge(17, 19);
        g.addEdge(17, 21);

        g.addEdge(18, 19);

        g.addEdge(19, 20);

        g.addEdge(22, 23);
        g.addEdge(22, 24);

        g.addEdge(23, 25);

        g.addEdge(24, 25);
        g.addEdge(24, 27);
        
        g.addEdge(26, 27);

        g.addEdge(27, 28);

        System.out.println();
        g.coloring();
        //g.printColors();
    }
}