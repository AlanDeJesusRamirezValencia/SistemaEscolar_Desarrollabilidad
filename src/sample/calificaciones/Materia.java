package sample.calificaciones;

import java.util.HashMap;

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

    /** Obtiene los atribudos de un objeto {@link Materia} para transformarlos en un objeto de tipo {@link HashMap} */
    public HashMap<String,String> toHashMap(){
        HashMap<String,String> hashMap = new HashMap<String, String>();

        //Componentes de la clase Materia
        hashMap.put("nrc", this.nrc + "");
        hashMap.put("nombre", this.nombre);

        //Componentes de la clase Grupo
        hashMap.put("id", grupo.getId() + "");
        hashMap.put("grado", grupo.getGrado() + "");
        hashMap.put("letra", grupo.getLetra() + "");
        return hashMap;
    }

    /** A partir de un objeto {@link HashMap} crea un objeto de tipo {@link Estudiante}*/
    public static Materia obtenerMateria(HashMap<String, String> hashMap){
        return new Materia(
                Integer.parseInt(hashMap.get("nrc")),
                hashMap.get("nombre"),
                new Grupo(
                        Integer.parseInt(hashMap.get("id")),
                        Integer.parseInt(hashMap.get("grado")),
                        hashMap.get("letra").charAt(0)
                )
        );
    }
}
