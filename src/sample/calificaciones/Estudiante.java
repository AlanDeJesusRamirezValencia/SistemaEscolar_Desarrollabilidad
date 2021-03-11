package sample.calificaciones;

import java.util.HashMap;

public class Estudiante extends Persona {
    private String matricula;
    private Grupo grupo;

    public Estudiante(String matricula, String nombre, String apellidoPaterno, String apellidoMaterno, Grupo grupo) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.matricula = matricula;
        this.grupo = grupo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    // TODO corregir tambien porque estudiante tampoco tiene un atributo grupo ahora
    /** Obtiene los atribudos de un objeto {@link Estudiante} para transformarlos en un objeto de tipo {@link HashMap} */
    public HashMap<String,String> toHashMap(){
        HashMap<String,String> hashMap = new HashMap<String, String>();

        //Componentes de la clase alumno
        hashMap.put("matricula", this.matricula);
        hashMap.put("nombre", this.nombre);
        hashMap.put("apellidoPaterno", this.apellidoPaterno);
        hashMap.put("apellidoMaterno", this.apellidoMaterno);

        //Componentes de la clase Grupo
        hashMap.put("id", grupo.getId() + "");
        hashMap.put("grado", grupo.getGrado() + "");
        hashMap.put("letra", grupo.getLetra() + "");

        //Componentes del profesor del Grupo
        hashMap.put("nPersonalProfesor", grupo.getProfesor().getnPersonal() + "");
        hashMap.put("nombreProfesor", grupo.getProfesor().getNombre());
        hashMap.put("apellidoPaternoProfesor", grupo.getProfesor().getApellidoPaterno());
        hashMap.put("apellidoMaternoProfesor", grupo.getProfesor().getApellidoMaterno());
        return hashMap;
    }

    /** A partir de un objeto {@link HashMap} crea un objeto de tipo {@link Estudiante}*/
    public static Estudiante obtenerEstudiante(HashMap<String, String> hashMap){
        return new Estudiante(
                hashMap.get("matricula"),
                hashMap.get("nombre"),
                hashMap.get("apellidoPaterno"),
                hashMap.get("apellidoMaterno"),
                new Grupo(
                        Integer.parseInt(hashMap.get("id")),
                        Integer.parseInt(hashMap.get("grado")),
                        hashMap.get("letra").charAt(0),
                        new Profesor(
                                Integer.parseInt(hashMap.get("nPersonalProfesor")),
                                hashMap.get("nombreProfesor"),
                                hashMap.get("apellidoPaternoProfesor"),
                                hashMap.get("apellidoMaternoProfesor")
                        )
                )
        );
    }
}
