package accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
//
//public class BaseDeDatos {
//    public static final String URL = "jdbc:mysql://localhost/bdproyectofinal";
//    public static final String USER = "root";
//    public static final String CLAVE = "";
//    private String bdMsgError;
//    private Connection cn = null;
//    //Connection cn = null;
//    PreparedStatement cmd = null;
//    ResultSet rs = null;
//    
//    public String getBdMsgError(){
//        return bdMsgError;
//    }
//    
//    public BaseDeDatos(){
//        bdMsgError="";
//    }
//    
//    public boolean conectar(){
//        boolean conectado = false;
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            cn = DriverManager.getConnection(URL, USER, CLAVE);
//            conectado=!(cn.isClosed());
//        }catch(java.lang.ClassNotFoundException | SQLException e){
//            bdMsgError = e.getMessage();
//        }
//        return  conectado;
//    }
//    
//    public void desconectar(){
//        try{
//            cn.close();
//        }catch(SQLException e){
//            bdMsgError = e.getMessage();
//        }
//    }
//    
//    public void crearComando(String vSql){
//        try{
//            cmd = cn.prepareStatement(vSql);
//        }catch(SQLException e){
//            bdMsgError = e.getMessage();
//        }
//    }
//    
//    public int ejecutarComando(){
//        int reg=0;
//        try{
//            reg=cmd.executeUpdate();
//        }
//        catch(SQLException e){
//            bdMsgError = e.getMessage();
//        }
//        return reg;
//    }
//   
//    
//    public void asignarParametro(int orden, int valor)throws SQLException{
//        cmd.setInt(orden, valor);
//    }
//    
//    public void asignarParametro(int orden, long valor)throws SQLException{
//        cmd.setLong(orden, valor);
//    }
//    
//    public void asignarParametro(int orden, String valor)throws SQLException{
//        cmd.setString(orden, valor);
//    }
//    
//    public void asignarParametro(int orden, double valor)throws SQLException{
//        cmd.setDouble(orden, valor);
//    }
//    
//    public void asignarParametro(int orden, float valor)throws SQLException{
//        cmd.setDouble(orden, valor);
//    }
//    
//    public ResultSet ejecutarConsulta(){
//        try{
//            rs=cmd.executeQuery();
//        }catch(SQLException e){
//            bdMsgError=e.getMessage();
//        }
//        return rs;
//    }
//    
//    public void cerrarResultSet() throws SQLException{
//        rs.close();
//    }
//    
//}



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BaseDeDatos {

    private Connection conexion = null;
    public static final String URL = "jdbc:mysql://localhost/bdproyectofinal";
    public static final String USER = "root";
    public static final String CLAVE = "";
    private String bdMsgError;
    PreparedStatement cmd = null;
    ResultSet rs = null;
    
    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, CLAVE);
        }catch(java.lang.ClassNotFoundException | SQLException e){
            bdMsgError = e.getMessage();
        }
        return conexion;
    }

    public void desconectar() {
        try {
            conexion.close();
        } catch (SQLException e) {
      //      System.out.println("Error al desconectar " + e.getMessage());
            bdMsgError = e.getMessage();
        }
    }
    

//    public static void main(String[] args) {
//     BaseDeDatos c = new BaseDeDatos();
//     c.getConnection();
//    }
    
    
    public void crearComando(String vSql){
        try{
            cmd = conexion.prepareStatement(vSql);
        }catch(SQLException e){
            bdMsgError = e.getMessage();
            //System.out.println(e);
        }
    }
    
    public int ejecutarComando(){
        int reg=0;
        try{
            reg=cmd.executeUpdate();
        }
        catch(SQLException e){
            bdMsgError = e.getMessage();
            //System.out.println(e);
        }
        return reg;
    }
    
     public void asignarParametro(int orden, int valor)throws SQLException{
        cmd.setInt(orden, valor);
    }
    
    public void asignarParametro(int orden, long valor)throws SQLException{
        cmd.setLong(orden, valor);
    }
    
    public void asignarParametro(int orden, String valor)throws SQLException{
        cmd.setString(orden, valor);
    }
    
    public void asignarParametro(int orden, double valor)throws SQLException{
        cmd.setDouble(orden, valor);
    }
    
    public void asignarParametro(int orden, float valor)throws SQLException{
        cmd.setDouble(orden, valor);
    }
    
    public ResultSet ejecutarConsulta(){
        try{
            rs=cmd.executeQuery();
        }catch(SQLException e){
            bdMsgError=e.getMessage();
        }
        return rs;
    }
    
    public void cerrarResultSet() throws SQLException{
        rs.close();
    }
    
}
