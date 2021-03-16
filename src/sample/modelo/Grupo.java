package sample.modelo;

import java.util.HashMap;

public class Grupo implements Comparable<Grupo>{
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

    /** Obtiene los atribudos de un objeto {@link Grupo} para transformarlos en un objeto de tipo {@link HashMap} */
    public HashMap<String,String> toHashMap(){
        HashMap<String,String> hashMap = new HashMap<String, String>();
        //Componentes de la clase Grupo
        hashMap.put("id", id + "");
        hashMap.put("grado", grado + "");
        hashMap.put("letra", letra + "");
        hashMap.put("nPersonalProfesor", profesor.getnPersonal() + "");
        hashMap.put("nombreProfesor", profesor.getNombre());
        hashMap.put("apellidoPaternoProfesor", profesor.getApellidoPaterno());
        hashMap.put("apellidoMaternoProfesor", profesor.getApellidoMaterno());
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
    }

    @Override
    public String toString() {
        return grado  + ""  + letra + "";
    }


    @Override
    public int compareTo(Grupo o) {

        HashMap<Character, Integer> alfabeto= new HashMap<Character, Integer>();
        alfabeto.put('A',  100);
        alfabeto.put('B',   99);
        alfabeto.put('C',   98);
        alfabeto.put('D',   97);
        alfabeto.put('E',   96);
        alfabeto.put('F',   95);
        alfabeto.put('G',   94);
        alfabeto.put('H',   93);
        alfabeto.put('I',   92);
        alfabeto.put('J',   91);
        alfabeto.put('K',   90);
        alfabeto.put('L',   89);
        alfabeto.put('M',   88);
        alfabeto.put('O',   87);
        alfabeto.put('P',   86);
        alfabeto.put('Q',   85);
        alfabeto.put('R',   84);
        alfabeto.put('S',   83);
        alfabeto.put('T',   82);
        alfabeto.put('U',   81);
        alfabeto.put('V',   80);
        alfabeto.put('X',   79);
        alfabeto.put('Y',   78);
        alfabeto.put('Z',   77);

        if (this.getGrado() != o.getGrado())
            return this.getGrado() > o.getGrado()? 1 : -1;
        else if (!(alfabeto.get(this.letra).equals(alfabeto.get(o.letra))))
            return alfabeto.get(this.letra) > alfabeto.get(o.letra)? -1 : 1;
        return 0;
    }
}
