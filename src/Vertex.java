
public class Vertex {
    private int number;
    private int degree;
    private int saturation;
    private int color;

    Vertex(int number) {
        this.number = number;
        this.degree = 0;
        this.saturation = 0;
        this.color = -1;
    }

    public int number() {
        return number;
    }

    public int degree() {
        return degree;
    }

    public int saturation() {
        return saturation;
    }

    public int color() {
        return color;
    }

    public void setColor(int c) {
        color = c;
    }

    public void setSaturation(int s) {
        saturation = s;
    }

    public void addDegree() {
        degree++;
    }

    public void substractDegree() {
        degree--;
    }

    public void addSat() {
        saturation++;
    }

    public void substractSat() {
        saturation--;
    }
}
