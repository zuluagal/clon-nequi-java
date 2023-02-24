
package reglanegocio;
import java.util.ArrayList;
import accesodatos.BaseDeDatos;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class TipoUsuario {
    private int id;
    private String nombre;

    public TipoUsuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoUsuario() {
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
    
    public ArrayList consultarListaTipoUsuario() throws SQLException{
	ArrayList tipoUsuarioList = new ArrayList<>();
	TipoUsuario tu;
	var vSql = "SELECT id, nombre FROM TipoUsuario";
	var bd = new BaseDeDatos();
	bd.conectar();
	
	bd.crearComando(vSql);
	var rs = bd.ejecutarConsulta();
	
	while(rs.next()){
	    tu = new TipoUsuario();
	    tu.setId(rs.getInt("id"));
	    tu.setNombre(rs.getString("nombre"));
	    
	    tipoUsuarioList.add(tu);
	}
	rs.close();
	bd.desconectar();
	return tipoUsuarioList;
    }

    @Override
    public String toString() {
	return this.nombre;
    }
    
    public static void cargarCombo(JComboBox<TipoUsuario> cmb) throws SQLException{
	
	var tu = new TipoUsuario();
	ArrayList<TipoUsuario> listaTipoUsuario = tu.consultarListaTipoUsuario();
	
	for (int i = 0; i < listaTipoUsuario.size(); i++) {
	   cmb.addItem(new TipoUsuario(listaTipoUsuario.get(i).getId(), listaTipoUsuario.get(i).getNombre()));
	}
	cmb.setSelectedIndex(-1);
    }
    
    public static void cargarCombo(JComboBox<TipoUsuario> cmb, int id) throws SQLException{
	cargarCombo(cmb);
	
	for (int i = 0; i < cmb.getItemCount(); i++) {
	    if(cmb.getItemAt(i).getId() == id){
		cmb.setSelectedIndex(i);
		break;
	    }
	}
    }
    
    
    
}
