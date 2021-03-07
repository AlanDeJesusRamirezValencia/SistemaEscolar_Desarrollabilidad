package sample.calificaciones;

import java.sql.SQLException;

public class Administrativo {

    public boolean validarUsuarioContraseña(String usuario, String contraseña){
        Usuario usuarioObtenido = null;
        try {
            usuarioObtenido = Gestor.obtenerUsuario(usuario);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contraseña.equals(usuarioObtenido.getContraseña());
    }

    public void cerrarSesion(){

    }

}
