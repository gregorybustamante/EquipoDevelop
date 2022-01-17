/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package svna;

import BBDD.Manejador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Paciente {
    
    protected int id;
    protected String cedula,representante;
    protected String nombres, nombresR;
    protected String apellidos, apellidosR;
    protected String fechaN;
    protected String sexo;
    protected String telefono;
    protected String correo;
    protected String zona, unidad;
    
    //Constructores

    public Paciente() {
    }
    
    //Constructor para tablas de pacientes
    public Paciente(int id, String cedula ,String nombres, String apellidos, String fechaN){
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaN = fechaN;
    }
    
    public String getNombresR() {
        return nombresR;
    }

    //Metodos Set y Get
    public String getApellidosR() {    
        return apellidosR;
    }

    public void setNombresR(String nombresR) {
        this.nombresR = nombresR;
    }

    public void setApellidosR(String apellidosR) {
        this.apellidosR = apellidosR;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaN(String fechaN) {
        this.fechaN = fechaN;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getCedula() {
        return cedula;
    }

    public String getRepresentante() {
        return representante;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaN() {
        return fechaN;
    }

    public String getSexo() {
        return sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getZona() {
        return zona;
    }
    
    //Metodos de la clase
    
    //Obtener la lista de pacientes
    public ArrayList<Paciente> getAllPaciente(){
        
        ArrayList<Paciente> lista = new ArrayList<Paciente>();
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
         }catch (Exception e) {
		System.out.println ("ERROR 1");
         }
         String sql = "Select * From fa_paciente";
         
        ResultSet rst;
    
        try{
            rst = s.executeQuery(sql);            
            while (rst.next()) {
                lista.add(new Paciente(Integer.parseInt(rst.getString("id_paciente")), rst.getString("cedula"), rst.getString("nombre_paciente"), rst.getString("apellido_paciente"),rst.getString("fecha_naci_paciente") ));
            }
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        }
        
        return lista;        
    }
    
    //Obtener la lista de pacientes para IMC
    public ArrayList<Paciente> getAllPacienteIMC(){
        
        ArrayList<Paciente> lista = new ArrayList<Paciente>();
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
         }catch (Exception e) {
		System.out.println ("ERROR 1");
         }
         String sql = "SELECT * FROM fa_paciente " +
                      "WHERE (TRUNC(months_between(CURRENT_DATE, fecha_naci_paciente)/12,0))>=10";
         
        ResultSet rst;
    
        try{
            rst = s.executeQuery(sql);            
            while (rst.next()) {
                lista.add(new Paciente(Integer.parseInt(rst.getString("id_paciente")), rst.getString("cedula"), rst.getString("nombre_paciente"), rst.getString("apellido_paciente"),rst.getString("fecha_naci_paciente") ));
            }
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        }
        
        return lista;        
    }
    
    //Obtener la lista de pacientes para Peso/Edad
    public ArrayList<Paciente> getAllPacientePesoEdad(){
        
        ArrayList<Paciente> lista = new ArrayList<Paciente>();
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
         }catch (Exception e) {
		System.out.println ("ERROR 1");
         }
         String sql = "SELECT * FROM fa_paciente\n" +
"WHERE (TRUNC(months_between(CURRENT_DATE, fecha_naci_paciente),0))>=0 AND  (TRUNC(months_between(CURRENT_DATE, fecha_naci_paciente),0))<=24 ";
         
        ResultSet rst;
    
        try{
            rst = s.executeQuery(sql);            
            while (rst.next()) {
                lista.add(new Paciente(Integer.parseInt(rst.getString("id_paciente")), rst.getString("cedula"), rst.getString("nombre_paciente"), rst.getString("apellido_paciente"),rst.getString("fecha_naci_paciente") ));
            }
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        }
        
        return lista;        
    }
    
    //Obtener la lista de pacientes para Peso/Edad
    public ArrayList<Paciente> getAllPacienteMujeres(){
        
        ArrayList<Paciente> lista = new ArrayList<Paciente>();
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
         }catch (Exception e) {
		System.out.println ("ERROR 1");
         }
         String sql = "SELECT * FROM fa_paciente WHERE (TRUNC(months_between(CURRENT_DATE, fecha_naci_paciente)/12,1))>=10 AND (TRUNC(months_between(CURRENT_DATE, fecha_naci_paciente)/12,1))<=54 AND sexo='F'";
         
        ResultSet rst;
    
        try{
            rst = s.executeQuery(sql);            
            while (rst.next()) {
                lista.add(new Paciente(Integer.parseInt(rst.getString("id_paciente")), rst.getString("cedula"), rst.getString("nombre_paciente"), rst.getString("apellido_paciente"),rst.getString("fecha_naci_paciente") ));
            }
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        }
        
        return lista;        
    }
    
    //Obtener la lista de pacientes para Peso/Talla
    public ArrayList<Paciente> getAllPacientePesoTalla(){
        
        ArrayList<Paciente> lista = new ArrayList<Paciente>();
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
         }catch (Exception e) {
		System.out.println ("ERROR 1");
         }
         String sql = "SELECT * FROM fa_paciente WHERE (TRUNC(months_between(CURRENT_DATE, fecha_naci_paciente)/12,1))>=5 AND  (TRUNC(months_between(CURRENT_DATE, fecha_naci_paciente)/12,1))<=9.9";
         
        ResultSet rst;
    
        try{
            rst = s.executeQuery(sql);            
            while (rst.next()) {
                lista.add(new Paciente(Integer.parseInt(rst.getString("id_paciente")), rst.getString("cedula"), rst.getString("nombre_paciente"), rst.getString("apellido_paciente"),rst.getString("fecha_naci_paciente") ));
            }
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        }
        
        return lista;        
    }
    
    //Obtener toda la informacion de un paciente
    public Paciente getPaciente(int id_paciente){
        
        Paciente paciente = new Paciente();
        
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
         }catch (Exception e) {
		System.out.println ("ERROR 1");
         }
        
         String sql1 = "SELECT cedula, nombre_paciente, apellido_paciente, fecha_naci_paciente, sexo "
                 + "FROM fa_paciente WHERE id_paciente='"+id_paciente+"'";
                  
        ResultSet rst1;
        
        try{
            
            rst1 = s.executeQuery(sql1);
            
            if (rst1.next()) {
                
                paciente.setCedula(rst1.getString("cedula"));
                paciente.setNombres(rst1.getString("nombre_paciente"));
                paciente.setApellidos(rst1.getString("apellido_paciente"));
                paciente.setFechaN(rst1.getString("fecha_naci_paciente"));
                paciente.setSexo(rst1.getString("sexo"));
                
                
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro al paciente");
            }
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        }
        return paciente;
    }
    
    //Obtener telefono de un paciente
    public String getPacienteTelefono(int id_paciente){
        
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
            System.out.println ("ERROR 1");
        }
        
        String sql2 = "SELECT num_tlf FROM fa_paciente, fa_tlf "
                 + "WHERE cod_tlf=id_tlf AND id_paciente='"+id_paciente+"'";
        
        ResultSet rst2;
        
        try {
            rst2 = s.executeQuery(sql2);
            
            if(rst2.next()){
               return rst2.getString("num_tlf");
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "no aplica";
    }
    
    //Obtener correo de un paciente
    public String getPacienteCorreo(int id_paciente){
        
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
            System.out.println ("ERROR 1");
        }
        
        String sql3 = "SELECT num_correo FROM fa_paciente, fa_correo "
                + "WHERE cod_correo=id_correo AND id_paciente='"+id_paciente+"'";
        
        ResultSet rst3;
        
        try {
            rst3 = s.executeQuery(sql3);
            
            if(rst3.next()){
               return rst3.getString("num_correo");
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "no aplica";
    }
    
    //Obtener datos de zona de un paciente
    public Paciente getZonaPaciente(int id_paciente){
        
        Paciente paciente = new Paciente();
    
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
            System.out.println ("ERROR 1");
        }
        
        String sql4 = "SELECT zona, unidad_educativa FROM fa_paciente, fa_zona "
                 + "WHERE cod_zona=id_zona AND id_paciente='"+id_paciente+"'";
        
        ResultSet rst4;
        
        try {
            rst4 = s.executeQuery(sql4);
            
            if(rst4.next()){
                paciente.setZona(rst4.getString("zona"));
                paciente.setUnidad(rst4.getString("unidad_educativa"));
            }else{
                paciente.setZona("no aplica");
                paciente.setUnidad("no aplica");
            }           
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return paciente;
    }
    
    //Obtener datos de representante de un paciente
    public Paciente getRepPaciente(int id_paciente){
    
        Paciente paciente = new Paciente();
    
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
            System.out.println ("ERROR 1");
        }
        
        String sql5 = "SELECT id_repre, nombre_repre, apellido_repre FROM fa_paciente, fa_repre "
                 + "WHERE cod_repre=id_repre AND id_paciente='"+id_paciente+"'";
        
        ResultSet rst5;
        
        try {
            rst5 = s.executeQuery(sql5);
            
            if(rst5.next()){
                paciente.setRepresentante(rst5.getString("id_repre"));
                paciente.setNombresR(rst5.getString("nombre_repre"));
                paciente.setApellidosR(rst5.getString("apellido_repre"));
            }else{
                paciente.setRepresentante("no aplica");
                paciente.setNombresR("no aplica");
                paciente.setApellidosR("no aplica");
            }          
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return paciente;
    }
    
    public void registrarPacienteMenor(){
        
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "INSERT INTO fa_repre(id_repre, nombre_repre, apellido_repre) VALUES ("+representante+", "+nombresR+", "+apellidosR+"); \n \n"
                + "INSERT INTO fa_tlf(id_tlf, num_tlf) VALUES (NEXTVAL('fa_tlff'), "+telefono+"); \n \n"
                + "INSERT INTO fa_correo(id_correo, num_correo) VALUES (NEXTVAL('fa_corr'), "+correo+"); \n \n"
                + "INSERT INTO fa_zona(id_zona, zona, unidad_educativa) VALUES (NEXTVAL('fa_zon'), "+zona+", "+unidad+"); \n \n"
                + "INSERT INTO fa_paciente(id_paciente, cedula, nombre_paciente, apellido_paciente, fecha_naci_paciente, sexo, cod_zona, cod_tlf, cod_correo, cod_repre) "
                + "VALUES (DEFAULT, "+cedula+", "+nombres+", "+apellidos+", "+fechaN+", "+sexo+", CURRVAL('fa_zon'), CURRVAL('fa_tlff'), CURRVAL('fa_corr'), "+representante+");";
        
        int rst;
        
        try{
            rst = s.executeUpdate(sql);
            if (rst == 1)
                JOptionPane.showMessageDialog(null,"Paciente registrado con exito.");
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        } 
    }
    
    public void registrarPacienteMayor(){
        
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = ""
                + "INSERT INTO fa_tlf(id_tlf, num_tlf) VALUES (NEXTVAL('fa_tlff'), "+telefono+"); \n \n"
                + "INSERT INTO fa_correo(id_correo, num_correo) VALUES (NEXTVAL('fa_corr'), "+correo+"); \n \n"
                + "INSERT INTO fa_zona(id_zona, zona, unidad_educativa) VALUES (NEXTVAL('fa_zon'), "+zona+", "+unidad+"); \n \n"
                + "INSERT INTO fa_paciente(id_paciente, cedula, nombre_paciente, apellido_paciente, fecha_naci_paciente, sexo, cod_zona, cod_tlf, cod_correo) "
                + "VALUES (DEFAULT, "+cedula+", "+nombres+", "+apellidos+", "+fechaN+", "+sexo+", CURRVAL('fa_zon'), CURRVAL('fa_tlff'), CURRVAL('fa_corr'));";
        
        int rst;
    
        System.out.println(sql);
        
        try{
            rst = s.executeUpdate(sql);
            if (rst == 1)
                JOptionPane.showMessageDialog(null,"Paciente registrado con exito.");
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        } 
    }
    
}
