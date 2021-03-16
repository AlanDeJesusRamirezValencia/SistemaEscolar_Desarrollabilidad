package sample.controlador.arquitectura;

/**
 * Patrón singleton para acceder al nombre del usuario actual.
 *
 * @author alexter
 * @since 09/03/2021
 * @version 1.0
 */
public class UsuarioSingleton {

    private static UsuarioSingleton usuario;

    /** El nombre del usuario será el único dato en almacenarse en este singleton */
    private String nombreUsuario;

    /** Constructor por defecto privado */
    private UsuarioSingleton(){ }

    /**Unica forma de instanciar un objeto de tipo UsuarioSingleton*/
    public static UsuarioSingleton getInstance(){
        if (usuario == null)
            usuario = new UsuarioSingleton();
        return usuario;
    }

    /** Obtiene el nombre del usuario */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /** Establece el nombre del usuario */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
