package sample.controlador;

import javafx.scene.Node;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.modelo.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class GeneradorExcel {

    private final Node componenteUI;

    public GeneradorExcel(Node componenteUI) {
        this.componenteUI = componenteUI;
    }

    public boolean crearExcel(Materia materia) {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet(materia.getNombre() + " " +  materia.getNrc());
        Row filaCampo = hoja.createRow(0);
        filaCampo.createCell(0).setCellValue("Matricula");
        filaCampo.createCell(1).setCellValue("Estudiante");
        filaCampo.createCell(2).setCellValue("Calificacion");

        ArrayList<Calificacion> calificaciones = null;
        try {
            calificaciones = GestorDatos.obtenerCalificaciones(materia);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (calificaciones != null) {
            for (int i=0; i<calificaciones.size(); i++){
                Row filaInformacion = hoja.createRow(i+1);
                Cell celdaMaticula = filaInformacion.createCell(0);
                celdaMaticula.setCellValue(calificaciones.get(i).getEstudiante().getMatricula());
                Cell celdaEstudiante = filaInformacion.createCell(1);
                celdaEstudiante.setCellValue(calificaciones.get(i).getEstudiante().toString());
                Cell celdaCalificacion = filaInformacion.createCell(2);
                celdaCalificacion.setCellValue(calificaciones.get(i).getNota());
            }
        }
        return guardarArchivo(libro);
    }

    public boolean crearExcel(Estudiante estudiante){
        ArrayList<Calificacion> calificaciones = null;
        try {
            calificaciones = GestorDatos.obtenerCalificaciones(estudiante);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet(estudiante + " " +  estudiante.getMatricula());
        Row filaCampo = hoja.createRow(0);
        filaCampo.createCell(0).setCellValue("NRC");
        filaCampo.createCell(1).setCellValue("Materia");
        filaCampo.createCell(2).setCellValue("Calificacion");

        if (calificaciones != null) {
            for (int i=0; i<calificaciones.size(); i++){
                Row filaInformacion = hoja.createRow(i+1);
                Cell celdaNrc = filaInformacion.createCell(0);
                celdaNrc.setCellValue(calificaciones.get(i).getMateria().getNrc());
                Cell celdaMateria = filaInformacion.createCell(1);
                celdaMateria.setCellValue(calificaciones.get(i).getMateria().getNombre());
                Cell celdaCalificacion = filaInformacion.createCell(2);
                celdaCalificacion.setCellValue(calificaciones.get(i).getNota());
            }
        }
        return guardarArchivo(libro);
    }

    public boolean guardarArchivo(Workbook libro){
        boolean bandera = false;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exportar Calificaciones");
        fileChooser.setInitialFileName("Calificaciones.xlsx");
        File archivo = fileChooser.showSaveDialog(componenteUI.getScene().getWindow());
        if (archivo != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(archivo);
                libro.write(fileOutputStream);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                bandera = true;
            }
        }
        return bandera;
    }
}