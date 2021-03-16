package sample.modelo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Excel {

    private JFileChooser jFileChooser;
    private FileOutputStream fileOutputStream;

    public boolean crearExcel(Materia materia) {
        ArrayList<Calificacion> calificaciones = null;
        try {
            calificaciones = GestorDatos.obtenerCalificaciones(materia);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet(materia.getNombre() + " " +  materia.getNrc());
        Row filaCampo = hoja.createRow(0);
        filaCampo.createCell(0).setCellValue("Matricula");
        filaCampo.createCell(1).setCellValue("Estudiante");
        filaCampo.createCell(2).setCellValue("Calificacion");

        for (int i=0; i<=calificaciones.size(); i++){
            Row filaInformacion = hoja.createRow(i+1);
            Cell celdaMaticula = filaInformacion.createCell(0);
            celdaMaticula.setCellValue(calificaciones.get(i).getEstudiante().getMatricula());
            Cell celdaEstudiante = filaInformacion.createCell(1);
            celdaEstudiante.setCellValue(calificaciones.get(i).getEstudiante().toString());
            Cell celdaCalificacion = filaInformacion.createCell(2);
            celdaCalificacion.setCellValue(calificaciones.get(i).getNota());
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
        Sheet hoja = libro.createSheet(estudiante.toString() + " " +  estudiante.getMatricula());
        Row filaCampo = hoja.createRow(0);
        filaCampo.createCell(0).setCellValue("NRC");
        filaCampo.createCell(1).setCellValue("Materia");
        filaCampo.createCell(2).setCellValue("Calificacion");

        for (int i=0; i<=calificaciones.size(); i++){
            Row filaInformacion = hoja.createRow(i+1);
            Cell celdaNrc = filaInformacion.createCell(0);
            celdaNrc.setCellValue(calificaciones.get(i).getMateria().getNrc());
            Cell celdaMateria = filaInformacion.createCell(1);
            celdaMateria.setCellValue(calificaciones.get(i).getMateria().getNombre());
            Cell celdaCalificacion = filaInformacion.createCell(2);
            celdaCalificacion.setCellValue(calificaciones.get(i).getNota());
        }
        return guardarArchivo(libro);
    }

    public boolean guardarArchivo(Workbook libro){
        boolean bandera = false;
        jFileChooser = new JFileChooser();
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Excel", "xlsx");
        jFileChooser.setFileFilter(fileNameExtensionFilter);
        int opcion = jFileChooser.showSaveDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION){
            if (jFileChooser.getSelectedFile()!= null){
                try {
                    fileOutputStream = new FileOutputStream( jFileChooser.getSelectedFile() + "Calificaciones.xlsx");
                    libro.write(fileOutputStream);
                    fileOutputStream.close();
                    JOptionPane.showMessageDialog(null,"El archivo se a guardado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
                    bandera = true;
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Su archivo no se ha guardado","Advertencia",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        return bandera;
    }
}