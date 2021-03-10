package sample.calificaciones;

import java.util.HashMap;

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
                        hashMap.get("letra").charAt(0)
                )
        );
    }
}
