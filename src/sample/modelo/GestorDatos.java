package sample.modelo;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Angel Daniel
 * @version 11/03/2021
 */
public class GestorDatos {

    private static Connection conexion;

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

    public static ArrayList<Grupo> obtenerGrupos() throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = "SELECT * FROM grupos ORDER BY grado, letra;";
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Grupo> grupos = new ArrayList<>();
        while(resultados.next()){
            int id = resultados.getInt("id_grupo");
            int grado = resultados.getInt("grado");
            char letra = resultados.getString("letra").charAt(0);
            Grupo grupo = new Grupo(id,grado,letra);
            grupos.add(grupo);
        }
        conexion.close();
        return grupos;
    }

    public static Profesor obtenerProfesor(Grupo grupo) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("SELECT * FROM profesores p, grupos g " +
                "WHERE p.numero_personal = g.fk_profesor AND g.id_grupo = %d;", grupo.getId());
        ResultSet resultados = declaracion.executeQuery(consulta);
        Profesor profesor = null;
        while(resultados.next()){
            int nPersonal = resultados.getInt("numero_personal");
            String nombre = resultados.getString("nombre").toUpperCase();
            String apellidoPaterno = resultados.getString("apellido_paterno").toUpperCase();
            String apellidoMaterno = resultados.getString("apellido_materno").toUpperCase();
            profesor = new Profesor(nPersonal, nombre, apellidoPaterno, apellidoMaterno);
        }
        conexion.close();
        if (profesor == null){
            profesor = new Profesor(0,"Ninguno","","");
        }
        grupo.setProfesor(profesor);
        return profesor;
    }

    public static ArrayList<Profesor> obtenerProfesores() throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = "SELECT * FROM profesores;";
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Profesor> profesores = new ArrayList<>();
        while(resultados.next()){
            int nPersonal = resultados.getInt("numero_personal");
            String nombre = resultados.getString("nombre").toUpperCase();
            String apellidoPaterno = resultados.getString("apellido_paterno").toUpperCase();
            String apellidoMaterno = resultados.getString("apellido_materno").toUpperCase();
            Profesor profesor = new Profesor(nPersonal, nombre, apellidoPaterno, apellidoMaterno);
            profesores.add(profesor);
        }
        conexion.close();
        return profesores;
    }

    public static ArrayList<Materia> obtenerMaterias(Grupo grupo) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("SELECT * FROM materias WHERE fk_grupo = '%d';", grupo.getId());
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Materia> materias = new ArrayList<>();
        while(resultados.next()){
            int nrc = resultados.getInt("nrc");
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
                "WHERE c.fk_estudiante = e.matricula AND c.fk_materia = m.nrc AND m.nrc = %d " +
                "ORDER BY e.apellido_paterno, e.apellido_materno, e.nombre;", materia.getNrc());
        ResultSet resultados = declaracion.executeQuery(consulta);
        ArrayList<Calificacion> calificaciones = new ArrayList<>();
        ArrayList<Estudiante> estudiantes = obtenerEstudiantes(materia.getGrupo());
        while(resultados.next()){
            int nota = resultados.getInt("nota");
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
            int nota = resultados.getInt("nota");
            int nrc = resultados.getInt("fk_materia");
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

    public static void subirCalificaciones(HashMap<Estudiante,Integer> calificaciones, Materia materia, boolean subidas){
        try {
            if (subidas) actualizarCalificaciones(calificaciones, materia);
            else insertarCalificaciones(calificaciones, materia);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarCalificaciones(HashMap<Estudiante,Integer> calificaciones, Materia materia) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        for (Map.Entry<Estudiante,Integer> entry: calificaciones.entrySet()){
            Estudiante estudiante = entry.getKey();
            int nota = entry.getValue();
            String consulta = String.format("INSERT INTO calificaciones (nota, fk_estudiante, fk_materia) " +
                    "VALUES(%d, '%s', %d);", nota, estudiante.getMatricula(), materia.getNrc());
            declaracion.executeUpdate(consulta);
        }
        conexion.close();
    }

    public static void actualizarCalificaciones(HashMap<Estudiante,Integer> calificaciones, Materia materia) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        for (Map.Entry<Estudiante,Integer> entry: calificaciones.entrySet()){
            Estudiante estudiante = entry.getKey();
            int nota = entry.getValue();
            String consulta = String.format("UPDATE calificaciones SET nota = %d WHERE fk_estudiante = '%s' AND fk_materia = %d;",
                    nota, estudiante.getMatricula(), materia.getNrc());
            declaracion.executeUpdate(consulta);
        }
        conexion.close();
    }

    public static void insertarEstudiante(Estudiante estudiante) throws SQLException {
        conexion = Conexion.getConexion();
        estudiante.setMatricula(generarMatricula());
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("INSERT INTO estudiantes VALUES ('%s', '%s', '%s', '%s', %d);",
                estudiante.getMatricula(),
                estudiante.getNombre(),
                estudiante.getApellidoPaterno(),
                estudiante.getApellidoMaterno(),
                estudiante.getGrupo().getId());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }

    private static String generarMatricula(){
        conexion = Conexion.getConexion();
        String matricula = "";
        Statement declaracion = null;
        try {
            declaracion = conexion.createStatement();
            String consulta = "SELECT COUNT(*) AS numero_estudiantes FROM estudiantes;";
            ResultSet resultados = declaracion.executeQuery(consulta);
            while (resultados.next()){
                int numeroEstudiantes = resultados.getInt("numero_estudiantes");
                matricula = "EP00" + (1002 + numeroEstudiantes);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return matricula;
    }

    public static void insertarProfesor(Profesor profesor) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("INSERT INTO profesores (nombre, apellido_paterno, apellido_materno) " +
                "VALUES ('%s', '%s', '%s');", profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }

    public static void insertarGrupo(Grupo grupo, Profesor profesor) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = (profesor != null)?
                String.format("INSERT INTO grupos (letra, grado, fk_profesor)  VALUES ('%c', %d, %d);", grupo.getLetra(), grupo.getGrado(), profesor.getnPersonal()):
                String.format("INSERT INTO grupos (letra, grado)  VALUES ('%c', %d);", grupo.getLetra(), grupo.getGrado());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }

    public static void insertarMateria(Materia materia) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("INSERT INTO materias (nombre, fk_grupo)  VALUES ('%s', %d);",
                materia.getNombre(), materia.getGrupo().getId());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }

    public static void eliminarEstudiante(Estudiante estudiante) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("DELETE FROM estudiantes WHERE matricula = '%s';", estudiante.getMatricula());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }

    public static void eliminarProfesor(Profesor profesor) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("DELETE FROM profesores WHERE numero_personal = %d;", profesor.getnPersonal());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }

    public static void eliminarGrupo(Grupo grupo) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("DELETE FROM grupos WHERE id_grupo = %d;", grupo.getId());
        declaracion.executeUpdate(consulta);
        conexion.close();

    }

    public static void eliminarMateria(Materia materia) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("DELETE FROM materias WHERE nrc = %d;", materia.getNrc());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }

    public static void actualizarEstudiante(Estudiante estudiante) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("UPDATE estudiantes SET fk_grupo = %d, nombre = '%s', apellido_paterno = '%s', apellido_materno = '%s' WHERE matricula = '%s';",
                estudiante.getGrupo().getId(), estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoMaterno(), estudiante.getMatricula());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }

    public static void actualizarProfesor(Profesor profesor) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("UPDATE profesores SET nombre = '%s', apellido_paterno = '%s', apellido_materno = '%s' WHERE numero_personal = %d;",
                profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno(), profesor.getnPersonal());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }

    public static void actualizarGrupo(Grupo grupo) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("UPDATE grupos SET grado = %d, letra = '%c', fk_profesor = %d WHERE id_grupo = %d;",
                grupo.getGrado(), grupo.getLetra(), grupo.getProfesor().getnPersonal(), grupo.getId());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }

    public static void actualizarMateria(Materia materia) throws SQLException {
        conexion = Conexion.getConexion();
        Statement declaracion = conexion.createStatement();
        String consulta = String.format("UPDATE materias SET nombre = '%s', fk_grupo = %d WHERE nrc = %d;",
                materia.getNombre(), materia.getGrupo().getId(), materia.getNrc());
        declaracion.executeUpdate(consulta);
        conexion.close();
    }
}
