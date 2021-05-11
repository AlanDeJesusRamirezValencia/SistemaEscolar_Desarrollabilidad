package sample.modelo;

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

    public Grupo(int id, int grado, char letra, Profesor profesor) {
        this.id = id;
        this.grado = grado;
        this.letra = letra;
        this.profesor = profesor;
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

    @Override
    public String toString() {
        return grado  + "" + letra;
    }

    /** Obtiene los atribudos de un objeto {@link Grupo} para transformarlos en un objeto de tipo {@link HashMap} */
    public HashMap<String,String> toHashMap(){
        HashMap<String,String> hashMap = new HashMap<String, String>();
        //Componentes de la clase Grupo
        hashMap.put("id", id + "");
        hashMap.put("grado", grado + "");
        hashMap.put("letra", letra + "");
        if (profesor != null){
            hashMap.put("nPersonalProfesor", profesor.getnPersonal() + "");
            hashMap.put("nombreProfesor", profesor.getNombre());
            hashMap.put("apellidoPaternoProfesor", profesor.getApellidoPaterno());
            hashMap.put("apellidoMaternoProfesor", profesor.getApellidoMaterno());
        }
        else{
            hashMap.put("nPersonalProfesor", "");
            hashMap.put("nombreProfesor", "");
            hashMap.put("apellidoPaternoProfesor", "");
            hashMap.put("apellidoMaternoProfesor", "");
        }
        return hashMap;
    }

    /** Permite añadir los atributos de un {@link Grupo} a un HashMap */
    public static HashMap<String,String> añadirGrupo(HashMap<String,String> mensaje, Grupo grupo){
        mensaje.put("id", grupo.getId() + "");
        mensaje.put("grado", grupo.getGrado() + "");
        mensaje.put("letra", grupo.getLetra() + "");
        mensaje.put("nPersonalProfesor", grupo.getProfesor().getnPersonal() + "");
        mensaje.put("nombreProfesor", grupo.getProfesor().getNombre());
        mensaje.put("apellidoPaternoProfesor", grupo.getProfesor().getApellidoPaterno());
        mensaje.put("apellidoMaternoProfesor", grupo.getProfesor().getApellidoMaterno());
        return mensaje;
    }

    /** A partir de un objeto {@link HashMap} crea un objeto de tipo {@link Grupo}*/
    public static Grupo obtenerGrupo(HashMap<String, String> hashMap){
        if (!hashMap.get("nPersonalProfesor").equals(""))
            return new Grupo(
                    Integer.parseInt(hashMap.get("id")),
                    Integer.parseInt(hashMap.get("grado")),
                    hashMap.get("letra").charAt(0),
                    new Profesor(
                            Integer.parseInt(hashMap.get("nPersonalProfesor")),
                            hashMap.get("nombreProfesor"),
                            hashMap.get("apellidoPaternoProfesor"),
                            hashMap.get("apellidoMaternoProfesor")
                    )
            );
        else {
            return new Grupo(
                    Integer.parseInt(hashMap.get("id")),
                    Integer.parseInt(hashMap.get("grado")),
                    hashMap.get("letra").charAt(0),
                    null
            );
        }
    }
}
