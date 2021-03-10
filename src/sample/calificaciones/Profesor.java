package sample.calificaciones;

import java.util.HashMap;

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

    /** Obtiene los atribudos de un objeto {@link Profesor} para transformarlos en un objeto de tipo {@link HashMap} */
    public HashMap<String,String> toHashMap(){
        HashMap<String,String> hashMap = new HashMap<String, String>();

        //Componentes de la clase alumno
        hashMap.put("nPersonal", this.nPersonal + "");
        hashMap.put("nombre", this.nombre);
        hashMap.put("apellidoPaterno", this.apellidoPaterno);
        hashMap.put("apellidoMaterno", this.apellidoMaterno);

        //Componentes de la clase Grupo
        hashMap.put("id", grupo.getId() + "");
        hashMap.put("grado", grupo.getGrado() + "");
        hashMap.put("letra", grupo.getLetra() + "");
        return hashMap;
    }

    /** A partir de un objeto {@link HashMap} crea un objeto de tipo {@link Profesor}*/
    public static Profesor obtenerProfesor(HashMap<String, String> hashMap){
        return new Profesor(
                Integer.parseInt(hashMap.get("nPersonal")),
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
