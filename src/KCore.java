 
/**
 * K-core decomposition algorithm.
 * This is an implementation of the algorithm given in:
 * V. Batagelj and M. Zaversnik. An o(m) algorithm for cores decomposition of networks. CoRR, 2003.
 *
 * Outputs: array "int[] res" containing the core values for each vertex.
 * The graph is stored using Webgraph
 * (see P. Boldi and S. Vigna. The Webgraph framework I: compression techniques. WWW'04.)
 *
 * @author Alex Thomo, thomo@uvic.ca, 2015
 */

import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

import it.unimi.dsi.webgraph.ImmutableGraph;

public class KCore {
	int[] cores;
	ImmutableGraph G;
	int n;
	int md; //max degré

	public KCore(String basename) throws Exception {
		G = ImmutableGraph.load(basename);

		n = G.numNodes();	//Taille du graphe

		md = 0;
		for(int v=0; v<n; v++) {
			int v_deg = G.outdegree(v);
			if(md < v_deg)
				md = v_deg;
		}
	}

    public int[] KCoreCompute () {

		//Initialisation des vecteurs
    	int[] vert = new int[n];
    	int[] pos = new int[n];
    	int[] deg = new int[n];
    	int[] bin = new int[md+1];

    	for(int d=0; d<=md; d++)
    		bin[d] = 0;

		// On initialise le vecteur deg et on augmente la taille de chaque paquet
    	for(int v=0; v<n; v++) {
    		deg[v] = G.outdegree(v);
    		bin[ deg[v] ]++;
    	}

    	int start = 0; //On incremente la position initiale de chaque paquet
    	for(int d=0; d<=md; d++) {
    		int num = bin[d];
    		bin[d] = start;
    		start += num;
    	}

		//On insére chaque sommet dans le paquet qui correspond à son degré
    	for(int v=0; v<n; v++) {
    		pos[v] = bin[ deg[v] ];
    		vert[ pos[v] ] = v;
    		bin[ deg[v] ]++;
		}

		//Dans l'étape precedente, nous avons augmenté la taille du paquet qui suive le degré du sommet, ici, nous devons le decrementer
    	for(int d=md; d>=1; d--)
    		bin[d] = bin[d-1];
    	bin[0] = 0; //1 in original

    	// Procedure
		for(int i=0; i<n; i++) {

    		int v = vert[i]; //On procede par ordre de degré

    		int v_deg = G.outdegree(v);
    		int[] N_v = G.successorArray(v);
    		for(int j=0; j<v_deg; j++) {
    			int u = N_v[j];

    			if(deg[u] > deg[v]) {
					// Le sommet u n'appartient pas au Δu-core
					// on doit decrementer son degré et le mettre au debut du
					//paquet qui correspond à Δneighbour - 1
    				int du = deg[u]; int pu = pos[u];
    				int pw = bin[du]; int w = vert[pw];
    				//L'étape suivante n'est effectué que quand le sommet en debut du paquet
    				//Est different de u
    				if(u!=w) {

    					pos[u] = pw; vert[pu] = w;
    					pos[w] = pu; vert[pw] = u;
    				}
    				//Si on augmente le debut du paquet Δu, le sommet u n'appartient plus à ce paquet
    				bin[du]++;
    				deg[u]--;
				}
			}

		}
		cores = deg;
    	return deg;
	}

	//https://www.w3schools.com/java/java_files_create.asp
	public void coreness(int vertexes, String filename) {

		try {
			FileWriter writer = new FileWriter(filename + ".cores.txt");

			for(int v = 0; v < vertexes; v++) {
				writer.write(v + " 	: " + cores[v] + "\n");
			}
			System.out.println("Wrote vertexes coreness in " + filename + ".cores.txt");
		} catch (IOException e) {
		}

	}

	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();

		if(args.length != 1) {
			System.err.println("Usage: java KCore basename");
			System.exit(1);
		}

		String basename = args[0];
		KCore kc = new KCore(basename);
		System.out.println("Starting procedure for graph " + basename);
		int[] res = kc.KCoreCompute();
		int kmax = -1;

		int vertexes = 0;

		for(int i=0; i<res.length; i++) {
			if(res[i] > kmax)
				kmax = res[i];
			if(res[i] > 0) vertexes++;
		}
		System.out.println("dmax	kmax");
		System.out.println(kc.md + "\t" + kmax);
		kc.coreness(vertexes, basename);

	}

}
