Projet d'Algorithmique 2 (INFO-F203) : Dégénérescence, Coloration et Coeurs [BAC2-2022] 

La compilation se fait sur une machine Linux, les directives suivantes se trouvent dans le README.md
du repo Github athomo/kcore.
Pour compiler les programmes, il suffit de lancer la commande suivante :

javac -cp "lib/*" -d bin src/*

Pour la partie concernant la recherche de la dégénérescence et de la profondeur de sommets, il existent
16
deux façons de tester le code avec des graphes, soit, en utilisant les graphes fourni par Stanford[10],
soit en utilisant les graphes fourni par l’”Università di Milano” Les seuls graphes à utiliser
sont les graphes non dirigés.
Stanford fournit les graphes sous forme de fichier .txt, il est alors nécessaire de les traduire vers des ImmutableGraph,
la classe que nous utilisons dans le code. De notre expérience, les 4 premières lignes de
certains fichiers contiennent des commentaires. Il est possible de vérifier cette éventualité en appelant
la commande suivante :

head inputfile.txt

Si le fichier contient des des commentaires, il est possible de les effacer avec l’outil sed

sed -i 1,4d inputfile.txt

Les arêtes doivent être maintenant triés, cette étape est obligatoire pour la suite :

sort -nk 1 inputfile.txt | uniq > outputfile.txt

La classe ImmutableGraph requiert un fichier executable pour pouvoir marcher :

java -cp ”lib/*” it.unimi.dsi.webgraph.BVGraph -1 -g ArcListASCIIGraph dummy output < outputfile.txt

Output peut etre remplacé par un nom à choix. Pour pouvoir lancer le programme, il suffira de lancer
la commande suivante :

java -cp "bin:lib/*" KCore output

Concernant les graphes fourni par l’”Università di Milano”, il faut pour chaque graphe télécharger le
fichier .graph et .properties de chaque graphe et de sa transposée, pour le graphe enwiki-2020, il faut
télécharger les 4 fichiers suivants :

enwiki-2020.graph
enwiki-2020.properties
enwiki-2020-t.graph
enwiki-2020-t.properties

Ensuite, il faut construire le fichier .offset pour les 2 fichiers

java -cp "lib/*" it.unimi.dsi.webgraph.BVGraph -o -O -L enwiki-2020
java -cp "lib/*" it.unimi.dsi.webgraph.BVGraph -o -O -L enwiki-2020-t

Et les symetriser, ici, nous utilisons de graphe non-dirigés, les graphes fournis sont dirigés.

java -cp "lib/*" it.unimi.dsi.webgraph.Transform union enwiki-2020 enwiki-2020-t enwiki-2020-sym

Comme pour les graphes de Stanford, il suffit de lancer la commande suivante

java -cp "bin:lib/*" KCore enwiki-2020-sym


Pour la compilation, lire la partie précedente 8 Pour lancer le programme, il suffit d’avoir un graphe
non dirigé fourni par Stanford et introduire la commande suivante depuis le dossier root :

java -cp "bin:lib/" Main graphname.txt NoOfVertexes

NoOfVertexes répresente le nombre de sommets du graphe
Les sommets doivent etre separés par un espace ou par un tab
