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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Diagnostico {
    
    protected String fecha;
    protected int id_paciente, semanas;
    protected float edadCronologica, peso, imc;
    protected String nombre_diag, nombre_emp, apellido_emp, notas, diagnostico;
    protected float altura,circun_abdomen,circun_brazo;
    protected int id_tablaD, id_emp, id_diag;
    protected float min,max,edad,ganancia;
    
    //Constructor
    
    public Diagnostico() {
        circun_brazo = 0;
        circun_abdomen = 0;
        notas = "vacío";
        imc = 0;
        semanas = 0;
    }
    
    //Metodos Set y Get

    public int getId_emp() {
        return id_emp;
    }

    public void setId_emp(int id_emp) {
        this.id_emp = id_emp;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_diag() {
        return id_diag;
    }

    public void setId_diag(int id_diag) {
        this.id_diag = id_diag;
    }
    
    public void setSemanas(int semanas) {
        this.semanas = semanas;
    }

    public void setEdadCronologica(float edadCronologica) {
        this.edadCronologica = edadCronologica;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }

    public float getGanancia() {
        return ganancia;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public void setEdad(float edad) {
        this.edad = edad;
    }

    public float getEdad() {
        return edad;
    }

    public void setNombre_diag(String nombre_diag) {
        this.nombre_diag = nombre_diag;
    }

    public void setNombre_emp(String nombre_emp) {
        this.nombre_emp = nombre_emp;
    }

    public void setApellido_emp(String apellido_emp) {
        this.apellido_emp = apellido_emp;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setCircun_abdomen(float circun_abdomen) {
        this.circun_abdomen = circun_abdomen;
    }

    //Metodos set y get.
    public void setCircun_brazo(float circun_brazo) {
        this.circun_brazo = circun_brazo;
    }

    public String getFecha() {
        return fecha;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public int getSemanas() {
        return semanas;
    }

    public float getEdadCronologica() {
        return edadCronologica;
    }

    public float getPeso() {
        return peso;
    }

    public float getImc() {
        return imc;
    }

    public String getNombre_diag() {
        return nombre_diag;
    }

    public String getNombre_emp() {
        return nombre_emp;
    }

    public String getApellido_emp() {
        return apellido_emp;
    }

    public String getNotas() {
        return notas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setId_tablaD(int id_tablaD) {
        this.id_tablaD = id_tablaD;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public int getId_tablaD() {
        return id_tablaD;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getAltura() {
        return altura;
    }

    public float getCircun_abdomen() {
        return circun_abdomen;
    }

    public float getCircun_brazo() {
        return circun_brazo;
    }
    
    
    
    //Metodos de la clase
    
    //Metodo para obtener diagnosticos de un paciente
    public ArrayList<Diagnostico> getDiagnosticosPaciente(int id_paciente, int id_empleado){
    
        ArrayList<Diagnostico> lista = new ArrayList<Diagnostico>();
        Diagnostico diagnostico = new Diagnostico();
        Manejador manejador = new Manejador();
        
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
         }catch (Exception e) {
		System.out.println ("ERROR 1");
         }
        
        String sql = "SELECT DISTINCT fecha_diagnostico, id_diag, cod_tabla_diag, cod_paciente, edad_cronologica, peso, nombre_diag, altura, nombre_emp, apellido_emp,circun_brazo, circun_abdomen, semanas_gestacion, imc, diagnostico, notas "
                     + "FROM fa_diagnostico, fa_empleado, fa_tabla_diag "
                     + "WHERE cod_emp=id_empleado AND cod_paciente='"+id_paciente+"'";
         
        ResultSet rst;
        
        try{
            
            rst = s.executeQuery(sql);     
            
            while (rst.next()) {
                
                diagnostico.setId_diag(Integer.parseInt(rst.getString("id_diag")));
                
                diagnostico.setId_tablaD(Integer.parseInt(rst.getString("cod_tabla_diag")));
                
                diagnostico.setId_emp(id_empleado);
                
                diagnostico.setFecha(rst.getString("fecha_diagnostico"));
                
                diagnostico.setId_paciente(Integer.parseInt(rst.getString("cod_paciente")));
                
                if(!Objects.isNull(rst.getString("semanas_gestacion")))
                    diagnostico.setSemanas(Integer.parseInt(rst.getString("semanas_gestacion")));
               
                diagnostico.setEdadCronologica(Float.parseFloat(rst.getString("edad_cronologica")));
                
                diagnostico.setPeso(Float.parseFloat(rst.getString("peso")));
                
                if(!Objects.isNull(rst.getString("imc")))
                    diagnostico.setImc(Float.parseFloat(rst.getString("imc")));
                
                diagnostico.setAltura(Float.parseFloat(rst.getString("altura")));
                
                if(!Objects.isNull(rst.getString("circun_brazo")))
                    diagnostico.setCircun_brazo(Float.parseFloat(rst.getString("circun_brazo")));
                
                if(!Objects.isNull(rst.getString("circun_abdomen")))
                    diagnostico.setCircun_abdomen(Float.parseFloat(rst.getString("circun_abdomen")));
                
                diagnostico.setNombre_diag(rst.getString("nombre_diag"));
                
                diagnostico.setNombre_emp(rst.getString("nombre_emp"));
                
                diagnostico.setApellido_emp(rst.getString("apellido_emp"));
                
                if(!Objects.isNull(rst.getString("notas")))
                    diagnostico.setNotas(rst.getString("notas"));

                diagnostico.setDiagnostico(rst.getString("diagnostico"));
                
                lista.add(diagnostico);
                
                diagnostico = new Diagnostico();
            }
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        }
        
        return lista;
        
    }
    
    //Metodo para calcular imc
    public float calcularIMC(){
        
        float resultado = 0;
        
        resultado = peso/(altura*altura);
        
        return resultado;
    }
    
    
    //Metodo que determina el maximo y minimo para los rangos para IMC
    public String diagnosticoIMC(float resultado){
        
        String cadena;
        
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "SELECT id_tabla_diag, min_normal_peso_imc_ganancia, max_normal_peso_imc_ganancia "
                + "FROM fa_tabla_diag WHERE sexo_diag=(SELECT sexo FROM fa_paciente WHERE id_paciente="+id_paciente+" )"
                + "AND peso_imc_ganancia='"+"IMC"+"' AND edad_talla_semana='"+" "+"' "
                + "AND cant_edad_talla_semana= (TRUNC(months_between(CURRENT_DATE, (SELECT fecha_naci_paciente FROM fa_paciente WHERE id_paciente= "+id_paciente+"))/12,0))";
        
        ResultSet rst;
        
        try {
            rst = s.executeQuery(sql);
            
            if(rst.next()){
               id_tablaD = Integer.parseInt(rst.getString("id_tabla_diag"));
               min = Float.parseFloat(rst.getString("min_normal_peso_imc_ganancia"));
               max = Float.parseFloat(rst.getString("max_normal_peso_imc_ganancia"));
               
               cadena = rangos(min,max,resultado);
               
               return cadena;
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "";
    }
    
    //Metodo que determina la edad en meses de un paciente
    public void calcularEdad(){
        
        
        
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "SELECT (TRUNC(months_between(CURRENT_DATE, fecha_naci_paciente),0))FROM fa_paciente WHERE id_paciente="+id_paciente; 
                
        ResultSet rst;
        
        try {
            rst = s.executeQuery(sql);
            
            if(rst.next()){
               edad = Float.parseFloat(rst.getString("trunc"));
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    //Metodo que determina el maximo y minimo para los rangos para Peso/Edad
    public String diagnosticoPesoEdad(){
        
        String cadena;
        
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "SELECT id_tabla_diag, min_normal_peso_imc_ganancia, max_normal_peso_imc_ganancia "
                + "FROM fa_tabla_diag WHERE sexo_diag=(SELECT sexo FROM fa_paciente WHERE id_paciente="+id_paciente+" )"
                + "AND peso_imc_ganancia='"+"PESO"+"' AND edad_talla_semana='"+"EDAD"+"' "
                + "AND cant_edad_talla_semana= (TRUNC(months_between(CURRENT_DATE, (SELECT fecha_naci_paciente FROM fa_paciente WHERE id_paciente= "+id_paciente+")),0))";
        
        ResultSet rst;
        
        try {
            rst = s.executeQuery(sql);
            
            if(rst.next()){
               id_tablaD = Integer.parseInt(rst.getString("id_tabla_diag"));
               min = Float.parseFloat(rst.getString("min_normal_peso_imc_ganancia"));
               max = Float.parseFloat(rst.getString("max_normal_peso_imc_ganancia"));
               
               cadena = rangos(min,max,peso);
               
               return cadena;
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "";
    }
    
    //Metodo que determina el maximo y minimo para los rangos para Peso/Talla
    public String diagnosticoPesoTalla(){
        
        String cadena;
        
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "SELECT id_tabla_diag, min_normal_peso_imc_ganancia, max_normal_peso_imc_ganancia "
                + "FROM fa_tabla_diag WHERE sexo_diag=(SELECT sexo FROM fa_paciente WHERE id_paciente="+id_paciente+" )"
                + "AND peso_imc_ganancia='"+"PESO"+"' AND edad_talla_semana='"+"TALLA"+"' "
                + "AND cant_edad_talla_semana="+altura;
        
        ResultSet rst;
        
        try {
            rst = s.executeQuery(sql);
            
            if(rst.next()){
               id_tablaD = Integer.parseInt(rst.getString("id_tabla_diag"));
               min = Float.parseFloat(rst.getString("min_normal_peso_imc_ganancia"));
               max = Float.parseFloat(rst.getString("max_normal_peso_imc_ganancia"));
               
               cadena = rangos(min,max,peso);
               
               return cadena;
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "";
    }
    
    //Metodo que determina el maximo y minimo para los rangos para Ganancia de Peso
    public String diagnosticoEmbarazada(){
        
        String cadena;
        
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "SELECT id_tabla_diag, min_normal_peso_imc_ganancia, max_normal_peso_imc_ganancia "
                + "FROM fa_tabla_diag WHERE sexo_diag=(SELECT sexo FROM fa_paciente WHERE id_paciente="+id_paciente+" )"
                + "AND peso_imc_ganancia='"+"GANANCIA"+"' AND edad_talla_semana='"+"SEMANAS DE GESTACION"+"' "
                + "AND cant_edad_talla_semana="+semanas;
        
        ResultSet rst;
        
        try {
            rst = s.executeQuery(sql);
            
            if(rst.next()){
               id_tablaD = Integer.parseInt(rst.getString("id_tabla_diag"));
               min = Float.parseFloat(rst.getString("min_normal_peso_imc_ganancia"));
               max = Float.parseFloat(rst.getString("max_normal_peso_imc_ganancia"));
               
               cadena = rangos(min,max,ganancia);
               
               return cadena;
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "";
    }
    
    //Metodo para determinar diagnostico para cualquier calculo
    public String rangos(float minimo, float maximo, float resultado){
        
        if(resultado >= minimo && resultado <= maximo)
            return "NORMOPESO";
        
        if(resultado < minimo)
            return "DEFICIT";
        
        if(resultado > maximo)
            return "SOBREPESO";
        
        return "";
  
    }
    
    //Metodo para registrar IMC
    public void registrarIMC(String diagnosticoF, int id_emp, String resultado, String nombreD){
        
        resultado = resultado.replace(",",".");
        
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "INSERT INTO fa_diagnostico(fecha_diagnostico, cod_paciente, cod_tabla_diag, cod_emp, edad_cronologica, "
                + "nombre_diag, peso, altura, imc, diagnostico, notas) "
                + "VALUES (CURRENT_DATE,"+id_paciente+","+id_tablaD+","+id_emp+","
                + "(TRUNC(months_between(CURRENT_DATE, (SELECT fecha_naci_paciente FROM fa_paciente WHERE id_paciente="+id_paciente+" ))/12,1)), "
                + "'"+nombreD+"',"+peso+","+altura+","+resultado+",'"+diagnosticoF+"','"+notas+"')";
        
        int rst;
        
        
        try{
            rst = s.executeUpdate(sql);
            if (rst == 1)
                JOptionPane.showMessageDialog(null,"Diagnóstico registrado con exito.");
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        } 
    }
    
    //Metodo para registrar Peso Edad
    public void registrarPesoEdad(String diagnosticoF, int id_emp, String nombreD){
         
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "INSERT INTO fa_diagnostico(fecha_diagnostico, cod_paciente, cod_tabla_diag, cod_emp, edad_cronologica, "
                + "nombre_diag, peso, altura, diagnostico, notas) "
                + "VALUES (CURRENT_DATE,"+id_paciente+","+id_tablaD+","+id_emp+","
                + "(TRUNC(months_between(CURRENT_DATE, (SELECT fecha_naci_paciente FROM fa_paciente WHERE id_paciente="+id_paciente+" )),1)), "
                + "'"+nombreD+"',"+peso+","+altura+",'"+diagnosticoF+"','"+notas+"')";
        
        int rst;
        
        
        try{
            rst = s.executeUpdate(sql);
            if (rst == 1)
                JOptionPane.showMessageDialog(null,"Diagnóstico registrado con exito.");
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        } 
    }
    
    //Metodo para registrar Peso/Talla
    public void registrarPesoTalla(String diagnosticoF, int id_emp, String nombreD){
         
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "INSERT INTO fa_diagnostico(fecha_diagnostico, cod_paciente, cod_tabla_diag, cod_emp, edad_cronologica, "
                + "nombre_diag, peso, altura, diagnostico, notas) "
                + "VALUES (CURRENT_DATE,"+id_paciente+","+id_tablaD+","+id_emp+","
                + "(TRUNC(months_between(CURRENT_DATE, (SELECT fecha_naci_paciente FROM fa_paciente WHERE id_paciente="+id_paciente+" ))/12,1)), "
                + "'"+nombreD+"',"+peso+","+altura+",'"+diagnosticoF+"','"+notas+"')";
        
        int rst;
        
        try{
            rst = s.executeUpdate(sql);
            if (rst == 1)
                JOptionPane.showMessageDialog(null,"Diagnóstico registrado con exito.");
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        } 
    }
    
    //Metodo para registrar Ganancia de Peso
    public void registrarEmbarazada(String diagnosticoF, int id_emp, String nombreD){
         
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "INSERT INTO fa_diagnostico(fecha_diagnostico, cod_paciente, cod_tabla_diag, cod_emp, edad_cronologica, "
                + "nombre_diag, peso, altura, diagnostico, notas, semanas_gestacion) "
                + "VALUES (CURRENT_DATE,"+id_paciente+","+id_tablaD+","+id_emp+","
                + "(TRUNC(months_between(CURRENT_DATE, (SELECT fecha_naci_paciente FROM fa_paciente WHERE id_paciente="+id_paciente+" ))/12,1)), "
                + "'"+nombreD+"',"+peso+","+altura+",'"+diagnosticoF+"','"+notas+"', '"+semanas+"')";
        
        int rst;
        
        try{
            rst = s.executeUpdate(sql);
            if (rst == 1)
                JOptionPane.showMessageDialog(null,"Diagnóstico registrado con exito.");
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        } 
    }
    
    //Eliminar un diagnostico
    public void ElimiarDiagnostico(Diagnostico diagnostico){
        
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "DELETE FROM fa_diagnostico WHERE cod_paciente = "+diagnostico.getId_paciente()+" AND id_diag = "+diagnostico.getId_diag()+"";
    
        int rst;
                
        try{
            rst = s.executeUpdate(sql);
            if (rst == 1)
                JOptionPane.showMessageDialog(null,"Diagnóstico eliminado con exito.");
            else
                JOptionPane.showMessageDialog(null,"No se encontro el diagnóstico solicitado.");
        }catch (Exception e) {
		System.out.println ("ERROR 2");
        } 
        
    }
    
    //Metodo que devuelve la cantidad de diagnosticos en NORMOPESO
    public float normopesoCant(){
        
        float cantidad = 0;
    
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "SELECT COUNT(*) FROM ult_diag WHERE diagnostico='NORMOPESO' GROUP BY diagnostico";
        
    
        ResultSet rst;
        
        try {
            rst = s.executeQuery(sql);
            
            if(rst.next()){
               
                cantidad = Integer.parseInt(rst.getString("count"));
                        
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cantidad;
    }
    
    //Metodo que devuelve la cantidad de diagnosticos en DEFICIT
    public float deficitCant(){
        
        float cantidad = 0;
    
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "SELECT COUNT(*) FROM ult_diag WHERE diagnostico='DEFICIT' GROUP BY diagnostico";
        
    
        ResultSet rst;
        
        try {
            rst = s.executeQuery(sql);
            
            if(rst.next()){
               
                cantidad = Integer.parseInt(rst.getString("count"));
                        
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cantidad;
    }
    
    //Metodo que devuelve la cantidad de diagnosticos en SOBREPESO
    public float sobrepesoCant(){
        
        float cantidad = 0;
    
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "SELECT COUNT(*) FROM ult_diag WHERE diagnostico='SOBREPESO' GROUP BY diagnostico";
        
    
        ResultSet rst;
        
        try {
            rst = s.executeQuery(sql);
            
            if(rst.next()){
               
                cantidad = Integer.parseInt(rst.getString("count"));

            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cantidad;
    }
    
    //Metodo que devuelve la cantidad de diagnosticos
    public float diagnosticoCant(){
        
        float cantidad = 0;
    
        Manejador manejador = new Manejador();
        manejador.conexion();
        
        Statement s = null;
        
        try{
            s = manejador.getConnection().createStatement();
        }catch (Exception e) {
		System.out.println ("ERROR");
        }
        
        String sql = "SELECT COUNT(*) FROM ult_diag";
        
    
        ResultSet rst;
        
        try {
            rst = s.executeQuery(sql);
            
            if(rst.next()){
               
                cantidad = Integer.parseInt(rst.getString("count"));
                        
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cantidad;
    }
    
    //Metodo para determinar los promedios por diagnosticos
    public float [] promedioDiag(){
        
        float promedios[];
        promedios = new float[3];
    
        float cantidadNormopeso = normopesoCant();
        
        float cantidadSobrepeso = sobrepesoCant();
        
        float cantidadDeficit = deficitCant();
        
        float cantidadDiagnostico = diagnosticoCant();
        
        
        if(cantidadDiagnostico > 0){
        
            promedios[0] = (cantidadNormopeso*100)/cantidadDiagnostico;
            promedios[1] = (cantidadSobrepeso*100)/cantidadDiagnostico;
            promedios[2] = (cantidadDeficit*100)/cantidadDiagnostico;
                    
        }else{
            
            promedios[0]=0;
            promedios[1]=0;
            promedios[2]=0;
            
        }
        
    
        return promedios;
    }
    
}
