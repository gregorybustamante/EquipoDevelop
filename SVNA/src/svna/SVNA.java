/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package svna;

import BBDD.Manejador;
import java.security.NoSuchAlgorithmException;
import svna.templates.*;

/**
 *
 * @author gabri
 */
public class SVNA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic 
        
       /* Usuario user = new Usuario();
        Manejador m = new Manejador();
        
        user.updatePassword(1, m.encriptarMD5("123"));
        user.updatePassword(2,m.encriptarMD5("123"));
        user.updatePassword(3,m.encriptarMD5("123")); */
        
        Home home = new Home();
        home.setVisible(true);
            
        
    }
    
}
