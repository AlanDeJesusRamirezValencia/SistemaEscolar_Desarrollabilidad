package sample.calificaciones;

import java.util.HashMap;

public class Grupo {
    private int id;
    private int grado;
    private char letra;
    private Profesor profesor;

    public Grupo(int id, int grado, char letra) {
        this.id = id;
        this.grado = grado;
        this.letra = letra;
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

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    /** Obtiene los atribudos de un objeto {@link Grupo} para transformarlos en un objeto de tipo {@link HashMap} */
    public HashMap<String,String> toHashMap(){
        HashMap<String,String> hashMap = new HashMap<String, String>();
        //Componentes de la clase Grupo
        hashMap.put("id", id + "");
        hashMap.put("grado", grado + "");
        hashMap.put("letra", letra + "");
        return hashMap;
    }

    /** Permite añadir los atributos de un {@link Grupo} a un HashMap */
    public static HashMap<String,String> añadirGrupo(HashMap<String,String> mensaje, Grupo grupo){
        mensaje.put("id", grupo.getId() + "");
        mensaje.put("grado", grupo.getGrado() + "");
        mensaje.put("letra", grupo.getLetra() + "");
        return mensaje;
    }

    // TODO ocrregir porque tiene un atributo profesor ahora
    /** A partir de un objeto {@link HashMap} crea un objeto de tipo {@link Grupo}*/
    public static Grupo obtenerGrupo(HashMap<String, String> hashMap){
        return new Grupo(
            Integer.parseInt(hashMap.get("id")),
            Integer.parseInt(hashMap.get("grado")),
            hashMap.get("letra").charAt(0)
        );
    }

    @Override
    public String toString() {
        return grado + letra + "";
    }
}
