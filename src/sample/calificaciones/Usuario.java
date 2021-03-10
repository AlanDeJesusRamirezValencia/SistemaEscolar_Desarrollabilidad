package sample.calificaciones;

import java.util.HashMap;

public class Usuario {
    private String usuario, contraseña;

    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContraseña() { return contraseña; }

    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    /** Obtiene los atribudos de un objeto {@link Usuario} para transformarlos en un objeto de tipo {@link HashMap} */
    public HashMap<String,String> toHashMap(){
        HashMap<String,String> hashMap = new HashMap<String, String>();
        //Componentes de usuario
        hashMap.put("usuario", usuario);
        return hashMap;
    }

    /** A partir de un objeto {@link HashMap} crea un objeto de tipo {@link Usuario}*/
    public static String obtenerUsuario(HashMap<String, String> hashMap){
        return hashMap.get("usuario");
    }
}