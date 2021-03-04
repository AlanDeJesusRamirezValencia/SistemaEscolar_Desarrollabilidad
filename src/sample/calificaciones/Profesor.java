package sample.calificaciones;

public class Profesor extends Persona {
    private int nPersonal;

    public Profesor(int nPersonal, String nombre, String apellidoPaterno, String apellidoMaterno, Grupo grupo) {
        super(nombre, apellidoPaterno, apellidoMaterno, grupo);
        this.nPersonal = nPersonal;
    }

    public int getnPersonal() {
        return nPersonal;
    }

    public void setnPersonal(int nPersonal) {
        this.nPersonal = nPersonal;
    }
}
