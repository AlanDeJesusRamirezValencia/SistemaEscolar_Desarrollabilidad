package sample.controlador.arquitectura;

import javafx.scene.Node;

import java.util.HashMap;

/**
 * Clase Comunicador
 *
 * Esta clase permite a los 'controladores de escenas' comunicarse entre sí.
 * Al implementar la clase {@link Navegador}, permite navegar de un escenario
 * a otro y enviar mensajes de una clase a otra cuando se navega.
 *
 * @author alexter
 * @since 09/03/2021
 * @version 1.0
 */
public class Comunicador implements Navegador {

    /** Almacena los datos que se quieren pasar de un escenario a otro */
    private HashMap<String, String> mensaje;

    /**
     * Cuando se navega de un escenario a otro, el escenario de destino (destinatario)
     * recibirá un mensaje de tipo {@link HashMap} del escenario de origen (remitente).
     *
     * @see Navegador#navegar(Node, String, HashMap)
     * @param mensajeRecibido es el mensaje que ha sido recibido
     */
    void recibirMensaje(HashMap<String, String> mensajeRecibido){
        //Nos aseguramos de que todos los mensajes contengan el nombre del usuario actual
        UsuarioSingleton usuario = UsuarioSingleton.getInstance();
        mensajeRecibido.put("usuario", usuario.getNombreUsuario());
        this.mensaje = mensajeRecibido;
    }

    /** Obtiene el mensaje recibido del anterior escenario */
    public HashMap<String, String> getMensaje() {
        return mensaje;
    }

    /** Perite a los componentes del nuevo escenario inicializarse justo después de hacer el cambio de escenario. */
    public void inicializarComponentes(){}
}
