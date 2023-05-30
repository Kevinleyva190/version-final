package Modelo;
import org.example.persistencia.InstruDao;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;

public class ModeloTablaInstrumento implements TableModel {
    public static final int columnas = 6;
    private ArrayList<Instrumento> datos;
    private InstruDao ldao;

    public ModeloTablaInstrumento() {
        ldao = new InstruDao();
        datos = new ArrayList<>();
    }

    public ModeloTablaInstrumento(ArrayList<Instrumento> datos) {
        ldao = new InstruDao();
        this.datos = datos;

    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas;
    }

    @Override
    public String getColumnName(int RowIndex) {
        switch (RowIndex) {
            case 0:
                return "Id";
            case 1:
                return "Nombre";
            case 2:
                return "clasificacion";
            case 3:
                return "Tipo";
            case 4:
                return "Instrumentista Famoso";
            case 5:
                return "Url";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int RowIndex) {
        switch (RowIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Instrumento tmp = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tmp.getId();
            case 1:
                return tmp.getNombre();
            case 2:
                return tmp.getClasificacion();
            case 3:
                return tmp.getTipo();
            case 4:
                return tmp.getFamoso();
            case 5:
                return tmp.getUrl();
        }
        return null;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                //datos.get(rowIndex).setId();
                break;
            case 1:
                datos.get(rowIndex).setNombre((String) o);
                break;
            case 2:
                datos.get(rowIndex).setClasificacion((String) o);
            case 3:
                datos.get(rowIndex).setTipo((String) o);
            case 4:
                datos.get(rowIndex).setFamoso((String) o);
            case 5:
                datos.get(rowIndex).setUrl((String) o);
                break;
            default:
                System.out.printf("No se modifica");
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }


    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public void cargardatos() {
        try {
            ArrayList<Instrumento> tirar = ldao.obtenerTodo();
            System.out.println(tirar);
            datos = ldao.obtenerTodo();
        } catch (SQLException sqle) {
            System.out.println("error" + sqle.getMessage());
        }

    }
    public Instrumento getInstroindex(int idx){
       return datos.get(idx);
    }

    public boolean agregarInstrumento(Instrumento insto) {
        boolean resultado = false;
        try {
            if (ldao.insertar(insto)) {
                datos.add(insto);
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (SQLException sqle) {
            System.out.println("Error" + sqle.getMessage());
        }
        return resultado;
    }

    public boolean actualizar(Instrumento insto) {
        boolean resultado = false;
        try {
            if (ldao.update(insto)) {
                datos.add(insto);
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (SQLException sqle) {
            System.out.println("Error" + sqle.getMessage());
        }
        return resultado;
    }

    public boolean eliminar(Instrumento insto) {
        boolean resultado = false;
        try {
            if (ldao.delete(String.valueOf(insto))) {
                datos.add(insto);
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (SQLException sqle) {
            System.out.println("Error" + sqle.getMessage());
        }
        return resultado;
    }

}