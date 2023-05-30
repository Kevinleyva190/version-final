package org.example.persistencia;

import Modelo.Instrumento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class InstruDao implements InterfazDao{
    public InstruDao() {
    }

    @Override
    public boolean insertar(Object obj) throws SQLException {
        String sqlInsert="INSERT INTO instrumentos(Nombre,Clasificacion,Tipo,Instrumentista,url) VALUES(?,?,?,?,?)";
        int rowCount=0;
        PreparedStatement pstm=ConexionSingleton.getInstance("instrumentos.db").getConnection().prepareStatement(sqlInsert);
        pstm.setString(1,((Instrumento)obj).getNombre());
        pstm.setString(2,((Instrumento)obj).getClasificacion());
        pstm.setString(3,((Instrumento)obj).getTipo());
        pstm.setString(4,((Instrumento)obj).getFamoso());
        pstm.setString(5,((Instrumento)obj).getUrl());
        rowCount= pstm.executeUpdate();
        return rowCount>0;
    }

    @Override
    public boolean update(Object obj) throws SQLException {
        String sqlUpdate="UPDATE instrumentos SET Nombre=?,Clasificacion=?,Tipo=?,Instrumentista=?, url=? WHERE id=?;";
        int rowCount=0;
        PreparedStatement pstm=ConexionSingleton.getInstance("instrumentos.db").getConnection().prepareStatement(sqlUpdate);
        pstm.setString(1,((Instrumento)obj).getNombre());
        pstm.setString(2,((Instrumento)obj).getClasificacion());
        pstm.setString(3,((Instrumento)obj).getTipo());
        pstm.setString(4,((Instrumento)obj).getFamoso());
        pstm.setString(5,((Instrumento)obj).getUrl());
        pstm.setInt(6,((Instrumento)obj).getId());
        rowCount= pstm.executeUpdate();
        return rowCount>0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sqlDelete="DELETE FROM instrumentos WHERE id=?;";
        int rowCout=0;
        PreparedStatement pstm= ConexionSingleton.getInstance("instrumentos.db").getConnection().prepareStatement(sqlDelete);
        pstm.setInt(1,Integer.parseInt(id));
        rowCout =pstm.executeUpdate();
        return rowCout>0;
    }


    @Override
    public ArrayList<Instrumento> obtenerTodo() throws SQLException {
        String sql = "SELECT * FROM instrumentos";
        ArrayList<Instrumento> resultado = new ArrayList<>();
        String dbPath = "D:/df/final/Proyecto2.0/src/main/resources/instrumentos.db";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                resultado.add(new Instrumento(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public Object buscarPorId(String id) throws SQLException {
        String sql ="SELECT * FROM libros WHERE id=?";
        Instrumento instrumento=null;

        PreparedStatement pstm = ConexionSingleton.getInstance("instrumentos.db").getConnection().prepareStatement(sql);
        pstm.setInt(1,Integer.parseInt(id));
        ResultSet rst= pstm.executeQuery();
        if (rst.next()) {
            instrumento = new Instrumento(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
            return instrumento;
        }
        return null;
    }
}