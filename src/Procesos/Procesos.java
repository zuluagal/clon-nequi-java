
package Procesos;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.MOUSE_ENTERED;
import javax.swing.JOptionPane;
import presentacion.FrmLogin;
import presentacion.FrmPrincipal;


public class Procesos {

    FrmLogin fmlogin = new FrmLogin();
    FrmPrincipal fmp = new FrmPrincipal();
    
    
    //Proceso para validar caracteres
    public void validarCaracteres(java.awt.event.KeyEvent e) {
        int key = e.getKeyChar();
        
        if (       key >= 0   && key < 1
                || key >= 32  && key <= 64
                || key >= 91  && key <= 96
                || key >= 123 && key <= 208
                || key >= 210 && key <= 240
                || key >= 242 && key <= 255) {
            e.consume();
            JOptionPane.showMessageDialog(null, "No se permiten caracteres especiales.");
        }
    }

    //Proceso para validar numeros y el 8 corresponde al bs backspace o retroceso o borrar:V
    public void validarNumeros(java.awt.event.KeyEvent e) {
           int key = e.getKeyChar();
           boolean numeros = key >= 48 && key <= 57;
           boolean delete = key == 8;
           
           if (!(numeros || delete)) {
               e.consume();
               JOptionPane.showMessageDialog(null, "Solo se permiten numeros.");
           }
    }
    
    public void validarCaracteresNumeros(java.awt.event.KeyEvent e){
           int key = e.getKeyChar();

            boolean letras = 
                   key >= 65 && key <= 90
                || key >= 97 && key <= 122;
            boolean numeros = key >= 48 && key <= 57;
            boolean delete  = key == 8;
           
           if (!(letras || numeros || delete)) {
               e.consume();
               JOptionPane.showMessageDialog(null, "Solo se permiten letras y numeros.");
           }
    }


    
    public void colores(MouseEvent e){
        
    }

   
}
