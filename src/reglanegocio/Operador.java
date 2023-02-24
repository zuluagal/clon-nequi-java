
package reglanegocio;
import java.util.ArrayList;
import accesodatos.BaseDeDatos;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class Operador {
    private int id;
    private String nombre;

    public Operador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Operador() {
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
    
    public ArrayList consultarListaOperador() throws SQLException{
	ArrayList operadorList = new ArrayList<>();
	Operador opr;
	var vSql = "SELECT id, nombre FROM operador";
	var bd = new BaseDeDatos();
	bd.conectar();
	
	bd.crearComando(vSql);
	var rs = bd.ejecutarConsulta();
	
	while(rs.next()){
	    opr = new Operador();
	    opr.setId(rs.getInt("id"));
	    opr.setNombre(rs.getString("nombre"));
	    
	    operadorList.add(opr);
	}
	rs.close();
	bd.desconectar();
	return operadorList;
    }

    @Override
    public String toString() {
	return this.nombre;
    }
    
    public static void cargarCombo(JComboBox<Operador> cmb) throws SQLException{
	
	var opr = new Operador();
	ArrayList<Operador> listaOperador = opr.consultarListaOperador();
	
	for (int i = 0; i < listaOperador.size(); i++) {
	   cmb.addItem(new Operador(listaOperador.get(i).getId(), listaOperador.get(i).getNombre()));
	}
	cmb.setSelectedIndex(-1);
    }
    
    public static void cargarCombo(JComboBox<Operador> cmb, int id) throws SQLException{
	cargarCombo(cmb);
	
	for (int i = 0; i < cmb.getItemCount(); i++) {
	    if(cmb.getItemAt(i).getId() == id){
		cmb.setSelectedIndex(i);
		break;
	    }
	}
    }
    
}
