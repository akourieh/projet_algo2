
public class Main {


    // Building the graph shown in the project's pdf

    public static void main(String[] args) throws Exception
    {
        if (args.length != 3) {
            System.err.println("Usage: java Main filename nodes edges");
            System.exit(1);
        }

        Graph g = new Graph(Integer.parseInt(args[1]));

        try {
            String file = args[0];
            System.out.println(file);
            int edges = Integer.parseInt(args[2]);
            g.loadGraph(file, edges);
        }
        catch (Exception e) {
            System.out.println(e);
            return;
        }

        System.out.println();
        g.coloring();
        g.printColors();
        //g.printColors();
    }
}