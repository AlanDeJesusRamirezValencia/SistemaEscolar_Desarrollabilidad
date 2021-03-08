package sample.calificaciones;

import com.mysql.jdbc.Connection;
import java.util.ArrayList;
import java.sql.*;

public class Gestor {

    public static Usuario obtenerUsuario(String nombre) throws SQLException {
        Connection conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = "SELECT * FROM administrativos WHERE usuario = '" + nombre + "';";
        ResultSet resultados = declaracion.executeQuery(consulta);
        while(resultados.next()){
            String usuario = resultados.getString("usuario");
            String contraseña = resultados.getString("contrasena");
            return new Usuario(usuario, contraseña);
        }
        return new Usuario("", "");
    }

    public ArrayList<Grupo> obtenerGrupos() throws SQLException {
        Connection conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = "SELECT * FROM grupos;";
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Grupo> grupos = new ArrayList<>();
        while(resultados.next()){
            int id = Integer.parseInt(resultados.getString("id_grupo"));
            int grado = Integer.parseInt(resultados.getString("grado"));
            char letra = resultados.getString("letra").charAt(0);
            Grupo grupo = new Grupo(id,grado,letra);
            grupos.add(grupo);
        }
        return grupos;
    }

    public ArrayList<Materia> obtenerMaterias(Grupo grupo) throws SQLException {
        Connection conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = "SELECT * FROM materias WHERE fk_grupo = " + grupo.getId() + ";";
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Materia> materias = new ArrayList<>();
        while(resultados.next()){
            int nrc = Integer.parseInt(resultados.getString("nrc"));
            String nombre = resultados.getString("grado");
            Materia materia = new Materia(nrc, nombre, grupo);
            materias.add(materia);
        }
        return materias;
    }

    public ArrayList<Estudiante> obtenerEstudiantes(Grupo grupo) throws SQLException {
        Connection conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = "SELECT * FROM estudiantes WHERE fk_grupo = " + grupo.getId() + ";";
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        while(resultados.next()){
            String matricula = resultados.getString("matricula");
            String nombre = resultados.getString("nombre");
            String apellidoPaterno = resultados.getString("apellido_paterno");
            String apellidoMaterno = resultados.getString("apellido_materno");
            Estudiante estudiante = new Estudiante(matricula, nombre, apellidoPaterno, apellidoMaterno, grupo);
            estudiantes.add(estudiante);
        }
        return estudiantes;
    }

    public ArrayList<Calificacion> obtenerCalificaciones(Materia materia) throws SQLException {
        Connection conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = "SELECT * FROM calificaciones WHERE fk_materia = " + materia.getNrc() + ";";
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Calificacion> calificaciones = new ArrayList<>();
        ArrayList<Estudiante> estudiantes = obtenerEstudiantes(materia.getGrupo());
        while(resultados.next()){
            int nota = Integer.parseInt(resultados.getString("nota"));
            String matricula = resultados.getString("fk_estudiante");
            Estudiante estudiante = buscarEstudiante(estudiantes, matricula);
            Calificacion calificacion = new Calificacion(nota, materia, estudiante);
        }
        return calificaciones;
    }

    private Estudiante buscarEstudiante (ArrayList<Estudiante> estudiantes, String matricula){
        for (Estudiante estudiante: estudiantes) {
            if (estudiante.getMatricula().equals(matricula)){
                return estudiante;
            }
        }
        return null;
    }

    public ArrayList<Calificacion> obtenerCalificaciones(Estudiante estudiante){
        ArrayList<Calificacion> calificaciones = new ArrayList<>();
        return calificaciones;
    }

    public void mostrarGrupos(ArrayList<Grupo> grupos){

    }

    public  void mostrarGrupo(Grupo grupo){

    }

    public void mostarMaterias(ArrayList<Materia> materias){

    }

    public void mostrarMateria(Materia materia){

    }

    public void mostrarEstudiantes(ArrayList<Estudiante> estudiantes){

    }

    public void  mostrarEstudiante(Estudiante estudiante){

    }

    public void asignarCalificaciones(Materia materia){

    }

}
