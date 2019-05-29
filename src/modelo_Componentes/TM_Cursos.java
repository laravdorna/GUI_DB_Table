/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo_Componentes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Curso;

/**
 *
 * @author EliteDesk
 */
public class TM_Cursos extends AbstractTableModel {

    private String nombreColumna[] = {"IDCURSO", "NOMBRE"};
    private List<Curso> listaCursos;

    public TM_Cursos() {
        listaCursos = new ArrayList<>();
    }

    public TM_Cursos(List<Curso> listaCurso) {
        this.listaCursos = listaCurso;
    }

    @Override
    public int getRowCount() {
        return listaCursos.size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumna.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listaCursos.get(rowIndex).getIdCurso();
            case 1:
                return listaCursos.get(rowIndex).getNombre();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return nombreColumna[column];

    }

    public void addCurso(Curso c) {
        listaCursos.add(getRowCount(), c);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public Curso getCurso(int fila) {
        return listaCursos.get(fila);
    }

    public void setListaCurso(List<Curso> listaCurso) {
        this.listaCursos = listaCurso;
        fireTableDataChanged();
    }

    public void eliminarCurso(int fila) {
        listaCursos.remove(fila);
        fireTableRowsDeleted(fila, fila);
    }
}
