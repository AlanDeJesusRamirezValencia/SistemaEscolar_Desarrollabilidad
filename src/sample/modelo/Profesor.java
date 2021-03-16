package sample.modelo;

import java.util.HashMap;

public class Profesor extends Persona {
    private int nPersonal;

    public Profesor(int nPersonal, String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.nPersonal = nPersonal;
    }

    public int getnPersonal() {
        return nPersonal;
    }

    public void setnPersonal(int nPersonal) {
        this.nPersonal = nPersonal;
    }

    // TODO corregir porque se cambio y ahora profesor no tiene atributo grupo
    /** Obtiene los atribudos de un objeto {@link Profesor} para transformarlos en un objeto de tipo {@link HashMap} */
    public HashMap<String,String> toHashMap(){
        HashMap<String,String> hashMap = new HashMap<String, String>();

        //Componentes de la clase alumno
        hashMap.put("nPersonalProfesor", this.nPersonal + "");
        hashMap.put("nombreProfesor", this.nombre);
        hashMap.put("apellidoPaternoProfesor", this.apellidoPaterno);
        hashMap.put("apellidoMaternoProfesor", this.apellidoMaterno);
        return hashMap;
    }

    /** A partir de un objeto {@link HashMap} crea un objeto de tipo {@link Profesor}*/
    public static Profesor obtenerProfesor(HashMap<String, String> hashMap){
        return new Profesor(
                Integer.parseInt(hashMap.get("nPersonalProfesor")),
                hashMap.get("nombreProfesor"),
                hashMap.get("apellidoPaternoProfesor"),
                hashMap.get("apellidoMaternoProfesor")
        );
    }
}
