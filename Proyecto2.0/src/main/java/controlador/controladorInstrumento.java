package controlador;

import Modelo.Instrumento;
import Modelo.ModeloTablaInstrumento;
import Vista.Ventana;
import org.example.persistencia.ConexionSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class controladorInstrumento extends MouseAdapter {
    private Ventana view;
    private ModeloTablaInstrumento modelo;

    public controladorInstrumento(Ventana view) {
        this.view = view;
        modelo= new ModeloTablaInstrumento();
        this.view.gettblinstrumento().setModel(modelo);
        this.view.getBtncargar().addMouseListener(this);
        this.view.getBtnagregar().addMouseListener(this);
        this.view.getBtnActualizar().addMouseListener(this);
        this.view.getBtnEliminar().addMouseListener(this);
        this.view.getTblinstrumento().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.view.getBtncargar()) {
            modelo.cargardatos();
            this.view.gettblinstrumento().setModel(modelo);
            this.view.gettblinstrumento().updateUI();
        }
        if (e.getSource() == this.view.getBtnagregar()) {
            Instrumento instrumento = new Instrumento();
            instrumento.setId(0);
            instrumento.setNombre(this.view.getTxtnombre().getText());
            instrumento.setClasificacion(this.view.getTxtclasificacion().getText());
            instrumento.setTipo(this.view.getTxtTipo().getText());
            instrumento.setFamoso(this.view.getTxtFamoso().getText());
            instrumento.setUrl(this.view.getTxtUrl().getText());
            this.view.gettblinstrumento().updateUI();
            if (modelo.agregarInstrumento(instrumento)) {
                JOptionPane.showMessageDialog(view, "Se agrego correctamente", "aviso", JOptionPane.INFORMATION_MESSAGE);
                this.view.gettblinstrumento().updateUI();
            } else {
                JOptionPane.showMessageDialog(view, "No se pudo agregar a la base de datos. por favor revise su conexion", "Erro", JOptionPane.ERROR_MESSAGE);

            }
            this.view.limpiar();
           }
        if (e.getSource() == this.view.getBtnActualizar()) {
            int respuesta = JOptionPane.showConfirmDialog(view, "Estas seguro que se actualice el registro?",
                    "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {
                Instrumento instrumento = new Instrumento();
                instrumento.setId(Integer.parseInt((String) this.view.getTxtIdA().getText()));
                instrumento.setNombre(this.view.getTxtNombreA().getText());
                instrumento.setClasificacion(this.view.getTxtCalasificacionA().getText());
                instrumento.setTipo(this.view.getTxtTipoA().getText());
                instrumento.setFamoso(this.view.getTxtFamosoA().getText());
                instrumento.setUrl(this.view.getTxtUrlA().getText());
                this.view.gettblinstrumento().updateUI();
                if (modelo.actualizar(instrumento)) {
                    JOptionPane.showMessageDialog(view, "Se actualizo correctamente", "aviso", JOptionPane.INFORMATION_MESSAGE);
                    this.view.gettblinstrumento().updateUI();
                } else {
                    JOptionPane.showMessageDialog(view, "No se pudo actualizo en la base de datos. por favor revise su conexion", "Erro", JOptionPane.ERROR_MESSAGE);

                }
                this.view.limpiar4();
            }else {this.view.limpiar4();}
        }
        if (e.getSource() == this.view.getBtnEliminar()) {
            int respuesta = JOptionPane.showConfirmDialog(view, "Estas seguro de borrar el registro?",
                    "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {
                String sqlDelete = "DELETE FROM instrumentos WHERE id=?;";
                PreparedStatement pstm = null;
                try {
                    pstm = ConexionSingleton.getInstance("instrumentos.db").getConnection().prepareStatement(sqlDelete);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    pstm.setInt(1, Integer.parseInt(this.view.getTxtElminarId().getText()));
                    this.view.gettblinstrumento().updateUI();
                    JOptionPane.showMessageDialog(view, "Se elimino correctamente", "aviso", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException a) {
                    JOptionPane.showMessageDialog(view, "No se elimino correctamente", "aviso", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                try {
                    pstm.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error en Id", "aviso", JOptionPane.INFORMATION_MESSAGE);
                    throw new RuntimeException(ex);
                }finally {
                    this.view.limpiar4();
                }
            }
        }
        if (e.getSource()==view.gettblinstrumento()) {
            int index=this.view.gettblinstrumento().getSelectedRow();
            Instrumento tmp= modelo.getInstroindex(index);
            try {
                this.view.getImagenInstru().setIcon(tmp.getImagen());
            }catch (MalformedURLException mfue){
                System.out.println(e.toString());
            }

        }
}
}