package sample.controlador.arquitectura;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Optional;

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
public abstract class Comunicador implements Navegador {

    @FXML
    protected AnchorPane contenedorPrincipal;

    @FXML
    protected Button btnUsuario;

    @FXML
    protected Button btnCerrarSesion;

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
    public void inicializarComponentes(){
    }

    @FXML
    private void mostrarBtnCerrarSesion(){
        btnCerrarSesion.setVisible(!btnCerrarSesion.isVisible());
    }

    @FXML
    private void cerrarSesion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(NOMBRE_SISTEMA);
        alert.setHeaderText("Cerrar Sesión");
        alert.setContentText("Está seguro de que quiere cerrar sesión?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            UsuarioSingleton.getInstance().limpiar();
            navegar(btnUsuario, "Login.fxml");
        } else btnCerrarSesion.setVisible(false);
    }
}
