
package reglanegocio;
import java.util.ArrayList;
import accesodatos.BaseDeDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class Estado {
    private int id;
    private String nombre;

    public Estado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Estado() {
        this.id = 0;
        this.nombre = "";
    } 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public ArrayList consultarListaEstado() throws SQLException{
	ArrayList EstadoList = new ArrayList<>();
	Estado tu;
	var vSql = "SELECT id, nombre FROM Estado";
	var bd = new BaseDeDatos();
	bd.conectar();
	
	bd.crearComando(vSql);
	var rs = bd.ejecutarConsulta();
	
	while(rs.next()){
	    tu = new Estado();
	    tu.setId(rs.getInt("id"));
	    tu.setNombre(rs.getString("nombre"));
	    
	    EstadoList.add(tu);
	}
	rs.close();
	bd.desconectar();
	return EstadoList;
    }

    @Override
    public String toString() {
	return this.nombre;
    }
    
     public static void cargarCombo(JComboBox<Estado> cmb) throws SQLException{
	
	var tu = new Estado();
	ArrayList<Estado> EstadoList = tu.consultarListaEstado();
	
	for (int i = 0; i < EstadoList.size(); i++) {
	   cmb.addItem(new Estado(EstadoList.get(i).getId(), EstadoList.get(i).getNombre()));
	}
	cmb.setSelectedIndex(-1);
    }
    
    public static void cargarCombo(JComboBox<Estado> cmb, int id) throws SQLException{
	cargarCombo(cmb);
	
	for (int i = 0; i < cmb.getItemCount(); i++) {
	    if(cmb.getItemAt(i).getId() == id){
		cmb.setSelectedIndex(i);
		break;
	    }
	}
    }
    
}
