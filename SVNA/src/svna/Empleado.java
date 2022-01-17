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
public class Empleado {
    
    protected int id;
    protected String cargo;
    protected String nombres, apellidos;
    protected String fecha;
    protected String usuario, password;

    //Constructores
    
    public Empleado(String cargo, String nombres, String apellidos, String fecha, String usuario, String password) {
        this.cargo = cargo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha = fecha;
        this.usuario = usuario;
        this.password = password;
    }

    public Empleado() {
    }
    
    public String getUsuario() {
        return usuario;
    }

    //Metodos set y get

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getPassword() {    
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public int getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFecha() {
        return fecha;
    }
    
    //Metodos de la clase
    
    //Metodo que registra un empleado y su usuario
    public void registrarEmpleado() throws NoSuchAlgorithmException{
        
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "INSERT INTO fa_empleado(id_empleado, cargo, nombre_emp, apellido_emp, fecha_naci_emp, activo) "
                + "VALUES (DEFAULT, '"+cargo+"', '"+nombres+"', '"+apellidos+"', '"+fecha+"', TRUE); \n \n" 
                + "INSERT INTO fa_acceso(cod_empleado, username, passw) VALUES "
                + "((SELECT id_empleado FROM fa_empleado WHERE nombre_emp='"+nombres+"' AND apellido_emp='"+apellidos+"' AND fecha_naci_emp='"+fecha+"'), '"+usuario+"', '"+manejador.encriptarMD5(password)+"');";
        
        int rst;
    
        try{
            rst = s.executeUpdate(sql);
            if (rst == 1)
                JOptionPane.showMessageDialog(null,"Perfil registrado con exito.");
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        } 
        
    }
 
}
