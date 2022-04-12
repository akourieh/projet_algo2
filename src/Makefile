JC = javac
.SUFFIXES: .java .class
.java.class: $(JC) $*.java

CLASSES = \
        Graph.java \
        Vertex.java \
        Main.java 

default: 
	classes

classes: 
	$(CLASSES:.java=.class)

run: 
	java Main

clean: 
	$(RM) *.class
