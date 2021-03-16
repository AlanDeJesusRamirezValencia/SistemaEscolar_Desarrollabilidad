package sample.modelo;

public class Calificacion {

    private int nota;
    private Materia materia;
    private Estudiante estudiante;

    public Calificacion(int nota, Materia materia, Estudiante estudiante) {
        this.nota = nota;
        this.materia = materia;
        this.estudiante = estudiante;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
