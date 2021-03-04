package sample.calificaciones;

public class Estudiante extends Persona {
    private String matricula;

    public Estudiante(String matricula, String nombre, String apellidoPaterno, String apellidoMaterno, Grupo grupo) {
        super(nombre, apellidoPaterno, apellidoMaterno, grupo);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
