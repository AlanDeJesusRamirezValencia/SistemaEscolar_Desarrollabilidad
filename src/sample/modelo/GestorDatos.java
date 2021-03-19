package sample.modelo;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Angel Daniel
 * @version 11/03/2021
 */
public class GestorDatos {

    private static Connection conexion;

    /**
     * Este metodo realiza una consulta a la base de datos para obtener el usuario que se busca
     * @param nombre es el nombre del usuario a buscar
     * @return un usuario encontrado
     * @throws SQLException
     */
    public static Usuario obtenerUsuario(String nombre) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("SELECT * FROM administrativos WHERE usuario = '%s';", nombre);
        ResultSet resultados = declaracion.executeQuery(consulta);
        while(resultados.next()){
            String usuario = resultados.getString("usuario");
            String contraseña = resultados.getString("contrasena");
            return new Usuario(usuario, contraseña);
        }
        conexion.close();
        return new Usuario("", "");
    }

    /**
     * Este metodo realiza una consulta a la base de datos para obtener una lista de todos los grupos
     * @see #obtenerProfesor(Grupo)
     * @return una lista con todos los grupos
     * @throws SQLException
     */
    public static ArrayList<Grupo> obtenerGrupos() throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = "SELECT * FROM grupos;";
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Grupo> grupos = new ArrayList<>();
        while(resultados.next()){
            int id = Integer.parseInt(resultados.getString("id_grupo"));
            int grado = Integer.parseInt(resultados.getString("grado"));
            char letra = resultados.getString("letra").charAt(0);
            Grupo grupo = new Grupo(id,grado,letra);
            Profesor profesor = obtenerProfesor(grupo);
            grupo.setProfesor(profesor);
            grupos.add(grupo);
        }
        conexion.close();
        return grupos;
    }

    /**
     * Este metodo realiza una consulta a la base de datos para obtener el profesor de un grupo
     * @param grupo
     * @return un profesor buscado
     * @throws SQLException
     */
    public static Profesor obtenerProfesor(Grupo grupo) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("SELECT * FROM profesores p, grupos g " +
                "WHERE p.numero_personal = g.fk_profesor AND g.id_grupo = %d;", grupo.getId());
        ResultSet resultados = declaracion.executeQuery(consulta);
        Profesor profesor = null;
        while(resultados.next()){
            int nPersonal = Integer.parseInt(resultados.getString("numero_personal"));
            String nombre = resultados.getString("nombre").toUpperCase();
            String apellidoPaterno = resultados.getString("apellido_paterno").toUpperCase();
            String apellidoMaterno = resultados.getString("apellido_materno").toUpperCase();
            profesor = new Profesor(nPersonal, nombre, apellidoPaterno, apellidoMaterno);
        }
        conexion.close();
        return profesor;
    }

    public static ArrayList<Materia> obtenerMaterias(Grupo grupo) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("SELECT * FROM materias WHERE fk_grupo = '%d';", grupo.getId());
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Materia> materias = new ArrayList<>();
        while(resultados.next()){
            int nrc = Integer.parseInt(resultados.getString("nrc"));
            String nombre = resultados.getString("nombre");
            Materia materia = new Materia(nrc, nombre, grupo);
            materias.add(materia);
        }
        conexion.close();
        return materias;
    }

    public static ArrayList<Estudiante> obtenerEstudiantes(Grupo grupo) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("SELECT * FROM estudiantes WHERE fk_grupo = %d " +
                "ORDER BY apellido_paterno, apellido_materno, nombre;", grupo.getId());
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        while(resultados.next()){
            String matricula = resultados.getString("matricula");
            String nombre = resultados.getString("nombre").toUpperCase();
            String apellidoPaterno = resultados.getString("apellido_paterno").toUpperCase();
            String apellidoMaterno = resultados.getString("apellido_materno").toUpperCase();
            Estudiante estudiante = new Estudiante(matricula, nombre, apellidoPaterno, apellidoMaterno, grupo);
            estudiantes.add(estudiante);
        }
        conexion.close();
        return estudiantes;
    }

    public static ArrayList<Calificacion> obtenerCalificaciones(Materia materia) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("SELECT c.id_calificacion, c.nota, c.fk_estudiante " +
                "FROM calificaciones c, estudiantes e, materias m " +
                "WHERE c.fk_estudiante = e.matricula AND m.nrc = %d " +
                "ORDER BY e.apellido_paterno, e.apellido_materno, e.nombre;", materia.getNrc());
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Calificacion> calificaciones = new ArrayList<>();
        ArrayList<Estudiante> estudiantes = obtenerEstudiantes(materia.getGrupo());
        while(resultados.next()){
            int nota = Integer.parseInt(resultados.getString("nota"));
            String matricula = resultados.getString("fk_estudiante");
            Estudiante estudiante = buscarEstudiante(estudiantes, matricula);
            Calificacion calificacion = new Calificacion(nota, materia, estudiante);
            calificaciones.add(calificacion);
        }
        conexion.close();
        return calificaciones;
    }

    private static Estudiante buscarEstudiante (ArrayList<Estudiante> estudiantes, String matricula){
        for (Estudiante estudiante: estudiantes) {
            if (estudiante.getMatricula().equals(matricula)){
                return estudiante;
            }
        }
        return null;
    }

    public static ArrayList<Calificacion> obtenerCalificaciones(Estudiante estudiante) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("SELECT * FROM calificaciones WHERE fk_estudiante = '%s';", estudiante.getMatricula());
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Calificacion> calificaciones = new ArrayList<>();
        ArrayList<Materia> materias = obtenerMaterias(estudiante.getGrupo());
        while(resultados.next()){
            int nota = Integer.parseInt(resultados.getString("nota"));
            int nrc = Integer.parseInt(resultados.getString("fk_materia"));
            Materia materia = buscarMateria(materias, nrc);
            Calificacion calificacion = new Calificacion(nota, materia, estudiante);
            calificaciones.add(calificacion);
        }
        conexion.close();
        return calificaciones;
    }

    private static Materia buscarMateria(ArrayList<Materia> materias, int nrc){
        for (Materia materia: materias){
            if (materia.getNrc() == nrc){
                return materia;
            }
        }
        return null;
    }

    public static void subirCalificaciones(HashMap<Estudiante,Integer> calificaciones, Materia materia) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        for (Map.Entry<Estudiante,Integer> entry: calificaciones.entrySet()){
            Estudiante estudiante = entry.getKey();
            int nota = entry.getValue();
            String consulta = String.format("INSERT INTO calificaciones (nota, fk_estudiante, fk_materia) " +
                    "VALUES(%d, '%s', %d)", nota, estudiante.getMatricula(), materia.getNrc());
            declaracion.executeUpdate(consulta);
        }
        conexion.close();
    }

    public static void actualizarCalificacion(Estudiante estudiante, int nota, Materia materia) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("UPDATE calificaciones SET nota = %d " +
                "WHERE fk_estudiante = '%s' AND fk_materia = %d", nota, estudiante.getMatricula(), materia.getNrc());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }
}
