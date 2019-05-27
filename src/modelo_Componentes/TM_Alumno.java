/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo_Componentes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Alumno;

/**
 *
 * @author lvazquezdorna
 */
public class TM_Alumno extends AbstractTableModel {

    private String nombreColumna[] = {"NALUMNO", "NOMBRE","DIRECCION", "TELEFONO","DNI",
        "CURSO"};
    private List<Alumno> listaAlumnos;

    public TM_Alumno() {
        listaAlumnos = new ArrayList<>();
    }

    public TM_Alumno(List<Alumno> listaAlumno) {
        this.listaAlumnos = listaAlumno;
    }

    @Override
    public int getRowCount() {
        return listaAlumnos.size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumna.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listaAlumnos.get(rowIndex).getnAlumno();
            case 1:
                return listaAlumnos.get(rowIndex).getNombre();
            case 2:
                return listaAlumnos.get(rowIndex).getDireccion();
            case 3:
                return listaAlumnos.get(rowIndex).getTelefono();
            case 4:
                return listaAlumnos.get(rowIndex).getDni();
            case 5:
                return listaAlumnos.get(rowIndex).getIdCurso();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return nombreColumna[column];

    }
    public void addAlumno(Alumno a){
        listaAlumnos.add(getRowCount(),a);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }
    
    public Alumno getAlumno(int fila){
        return listaAlumnos.get(fila);
    }
    
     public void setListaAlumnos(List<Alumno> listaAlumno) {
        this.listaAlumnos = listaAlumno;
        fireTableDataChanged();
    }
     
    public void eliminarAlumno( int fila){
        listaAlumnos.remove(fila);
        fireTableRowsDeleted(fila, fila);
    }

 
    
}
