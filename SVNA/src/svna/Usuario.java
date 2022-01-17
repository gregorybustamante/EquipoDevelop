/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package svna;

import BBDD.Manejador;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Usuario {
    
    protected int id;
    protected String user;
    protected String password;
    protected Manejador manejador;

    //Constructores
    
    public Usuario() {
    }

    public Usuario(int id, String user, String password, Manejador manejador) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.manejador = manejador;
    }

    //Metodos Set y Get
    
    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Manejador getManejador() {
        return manejador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setManejador(Manejador manejador) {
        this.manejador = manejador;
    }
    
    //Metodos de la clase
    
    //Metodo que verifica si un usuario se encuentra en la base de datos
    public boolean verificarUsuario(String user, String password) throws NoSuchAlgorithmException{
    
        boolean flag = false;
        manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "Select * From fa_acceso Where username = '"+user+"'";
        
        ResultSet rst;
    
    
        try{
            rst = s.executeQuery(sql);
            while (rst.next()) {
                if(user.equals(rst.getString("username")) && manejador.encriptarMD5(password).equals(rst.getString("passw"))){
                    flag = true;
                    id = Integer.parseInt(rst.getString("cod_empleado"));
                }
            }
        }catch (Exception e) {
		System.out.println ("ERROR");
        }    
        
        return flag;
    }
    
    //Metodo para actualizar passwords
    public void updatePassword(int id_usuario, String password){
        
        manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "UPDATE fa_acceso SET passw='"+password+"' WHERE cod_empleado='"+id_usuario+"'";
        
        int rst;
        
        try{
            
            rst = s.executeUpdate(sql);
            if (rst == 1)
                JOptionPane.showMessageDialog(null,"Actualizado.");
            else
                JOptionPane.showMessageDialog(null,"No se pudo actualizar.");
            
        }catch (Exception e) {
		System.out.println ("ERROR");
        } 
    }
}
