
public class Main {


    // Building the graph shown in the project's pdf

    public static void main(String[] args) throws Exception
    {
        if (args.length != 2) {
            System.err.println("Usage: java Main filename nodes ");
            System.exit(1);
        }
        long startTime = System.currentTimeMillis();

        Graph g = new Graph(Integer.parseInt(args[1]));

        try {
            String file = args[0];
            System.out.println(file);
            g.loadGraph(file);
        }
        catch (Exception e) {
            System.out.println(e);
            return;
        }

        System.out.println();
        g.coloring();
        System.out.println("Time elapsed (sec) = " + (System.currentTimeMillis() - startTime)/1000.0);
        System.out.println("Nombre de coloration pour le graphe " + args[0] + " " + g.getMaxColoring());

      //g.printColors();
    }
}
