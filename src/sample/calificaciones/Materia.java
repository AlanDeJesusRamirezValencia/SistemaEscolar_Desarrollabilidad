package sample.calificaciones;

public class Materia {
    private int nrc;
    private String nombre;
    private Grupo grupo;

    public Materia(int nrc, String nombre, Grupo grupo) {
        this.nrc = nrc;
        this.nombre = nombre;
        this.grupo = grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
