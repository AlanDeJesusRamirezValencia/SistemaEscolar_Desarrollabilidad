package sample.calificaciones;

public class Grupo {
    private int id;
    private int grado;
    private char letra;

    public Grupo(int id, int grado, char letra) {
        this.id = id;
        this.grado = grado;
        this.letra = letra;
    }

    @Override
    public String toString() {
        return grado + " " + letra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }
}
