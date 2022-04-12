
public class Vertex {
    int number;
    int degree;

    Vertex(int number) {
        this.number = number;
        this.degree = 0;
    }

    public int number() {
        return number;
    }

    public int degree() {
        return degree;
    }

    public void addDegree() {
        degree++;
    }

    public void substractDegree() {
        degree--;
    }

}
