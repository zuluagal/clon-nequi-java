
package reglanegocio;

import java.util.ArrayList;
import accesodatos.BaseDeDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Usuario {
    private String username;
    private String password;
    private String nombre;
    private int idTipoUsuario;
    private int idEstado;
    private int idOperador;
    private double saldo;
    private String celular;
    private String operador;
    private double monto;
    
    BaseDeDatos bd = new BaseDeDatos();

    public Usuario(String username, String password, String nombre, int idTipoUsuario, int idEstado, int idOperador,double saldo) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.idTipoUsuario = idTipoUsuario;
        this.idEstado = idEstado;
        this.idOperador = idOperador;
        this.saldo = saldo;
    }

    public Usuario() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }
    
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
    
    
    
    //Para login
    public boolean permitirAcceso(String pUsername, String pPassword) throws SQLException{
        var permitir = false;
        BaseDeDatos bd = new BaseDeDatos();
        var vSql = "SELECT username, nombre, IdTipoUsuario, IdEstado, saldo FROM usuario WHERE username=? and password=?";
        bd.conectar();
        bd.crearComando(vSql);
        bd.asignarParametro(1, pUsername);
        bd.asignarParametro(2, pPassword);
        ResultSet rs = bd.ejecutarConsulta();
        
        if(rs.next()){
            username = rs.getString("username");
            password = pPassword;
            nombre = rs.getString("nombre");
            idTipoUsuario = rs.getInt("IdTipoUsuario");
            idEstado = rs.getInt("IdEstado");
            saldo = rs.getDouble("saldo");
            
            if(idEstado == 1){
                permitir = true;
            } 
        }
        
        rs.close();
        bd.desconectar();        
        return permitir;
    }
    
    //Para crear usuario
    public int insertar(String username, String password, String nombre, int idTipoUsuario, int idEstado) throws SQLException{
        int numReg = 0;
        String vSql = "INSERT INTO usuario (username, password, nombre, IdTipoUsuario, IdEstado) VALUES (?,?,?,?,?)";
        bd.conectar();
        bd.crearComando(vSql);
        bd.asignarParametro(1, username);
        bd.asignarParametro(2, password);
        bd.asignarParametro(3, nombre);
	bd.asignarParametro(4, idTipoUsuario);
        bd.asignarParametro(5, idEstado);
        numReg = bd.ejecutarComando();
        bd.desconectar();
        return numReg;        
    }
    
    //Para modificar usuario
    public int modificar(String username, String password, String nombre, int idTipoUsuario, int idEstado) throws SQLException{
        int numReg = 0;
        String vSql = "UPDATE usuario SET password=?, nombre=?, IdTipoUsuario=?, IdEstado=? WHERE username = ?";
        bd.conectar();
        bd.crearComando(vSql);
        bd.asignarParametro(1, password);
        bd.asignarParametro(2, nombre);
	bd.asignarParametro(3, idTipoUsuario);
        bd.asignarParametro(4, idEstado);
	bd.asignarParametro(5, username);
        numReg = bd.ejecutarComando();
        bd.desconectar();
        return numReg;        
    }
    
    //Para modificar saldo
    public int modificarsaldo(String username, double saldo) throws SQLException{
        int numReg = 0;
        String vSql = "UPDATE usuario SET saldo=? WHERE username = ?";
        bd.conectar();
        bd.crearComando(vSql);
        bd.asignarParametro(1, saldo);
	bd.asignarParametro(2, username);
        numReg = bd.ejecutarComando();
        bd.desconectar();
        return numReg;        
    }
    
    //Para consultar usuario
    public boolean consultar(String username) throws SQLException{
        boolean consulta = false;
        String vSql = "SELECT password, nombre, IdTipoUsuario, IdEstado, saldo FROM usuario WHERE username = ?";
        bd.conectar();
        bd.crearComando(vSql);
        bd.asignarParametro(1, username);
        ResultSet rs = bd.ejecutarConsulta();
        
        if(rs.next()){
            this.username = username;
	    password = rs.getString("password");
            nombre = rs.getString("nombre");
	    idTipoUsuario = rs.getInt("IdTipoUsuario");
	    idEstado = rs.getInt("IdEstado");
            saldo = rs.getInt("saldo");
            consulta = true;
        }
        
        bd.cerrarResultSet();
        bd.desconectar();
        
        return consulta;        
    }
    
    //Para consultar saldo del usuario
    public boolean consultarSaldo(String username) throws SQLException{
        boolean consultasl = false;
        String vSql = "SELECT saldo FROM usuario WHERE username = ?";
        bd.conectar();
        bd.crearComando(vSql);
        bd.asignarParametro(1, username);
        ResultSet rs = bd.ejecutarConsulta();
        
        if(rs.next()){
            this.username = username;
            saldo = rs.getDouble("saldo");
            consultasl = true;
        }
        
        bd.cerrarResultSet();
        bd.desconectar();
        
        return consultasl;        
    }
    
    //Para insertar recargas en la tabla historial de la BD
    public int insertarHistorial(String username, String celular, int operador, double monto) throws SQLException{
        int numReg = 0;
        String vSql = "INSERT INTO historial (username, celular, idOperador, monto) VALUES (?,?,?,?)";
        bd.conectar();
        bd.crearComando(vSql);
        bd.asignarParametro(1, username);
        bd.asignarParametro(2, celular);
        bd.asignarParametro(3, operador);
	bd.asignarParametro(4, monto);
        numReg = bd.ejecutarComando();
        bd.desconectar();
        return numReg;        
    }
    
    //Para insertar recargas en la tabla historial de la BD
//    public int insertarHistorialConMsj(String username, double monto, String usernameR, String mensaje) throws SQLException{
//        int numReg = 0;
//        String vSql = "INSERT INTO historial (username, monto, usernameRecibe, mensaje) VALUES (?,?,?,?)";
//        bd.conectar();
//        bd.crearComando(vSql);
//        bd.asignarParametro(1, username);
//        bd.asignarParametro(2, monto);
//        bd.asignarParametro(3, usernameR);
//	bd.asignarParametro(4, mensaje);
//        numReg = bd.ejecutarComando();
//        bd.desconectar();
//        return numReg;        
//    }
    
    //Para consultar historial de recargas
    public boolean ConsultarHistorial(String username) throws SQLException {
        boolean consulta = false;
        String vSql = "select * from historial";

        var bd = new BaseDeDatos();
        bd.conectar();  // Devuelve un boolean 
        bd.crearComando(vSql);

        bd.asignarParametro(1, username);
        ResultSet rs = bd.ejecutarConsulta();

        if (rs.next()) {
            celular = rs.getString("celular");
            operador = rs.getString("operador");
            monto = rs.getDouble("monto");
            consulta = true;
        }
        bd.cerrarResultSet();
        bd.desconectar();
        return (consulta);
    }
    
    //Para consultar historial de recargas PRO
    public boolean consultarhr(String username) throws SQLException{
        boolean consulta = false;
        String vSql = "SELECT h.username, h.celular, opr.nombre, h.monto FROM historial h INNER JOIN operador opr ON h.idOperador = opr.id WHERE username = ?";
        bd.conectar();
        bd.crearComando(vSql);
        bd.asignarParametro(1, username);
        ResultSet rs = bd.ejecutarConsulta();
        while(rs.next()){
            this.username = rs.getString("username");
	    celular = rs.getString("celular");
            operador = rs.getString("nombre");
	    monto = rs.getDouble("monto");
            consulta = true;
        }
        bd.cerrarResultSet();
        bd.desconectar();
        return consulta;        
    }
    
    //Sobreescritura
   @Override
    public String toString() {
	return this.username;
    }
    
    //No recuerdo por
    public static ResultSet getTabla(String consulta) throws SQLException{
        var bd = new BaseDeDatos();
        bd.conectar();
        bd.crearComando(consulta);
        ResultSet datos = bd.ejecutarConsulta();
        return datos;
    }
    
    
  
    
}


