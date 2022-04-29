JC = javac
.SUFFIXES: .java .class
.java.class: $(JC) $*.java

CLASSES = \
        Graph.java \
        Vertex.java \
        Main.java 

build:
	javac $(CLASSES)

run:
	java Main

clean:
	$(RM) *.class

default: 
	java Main

