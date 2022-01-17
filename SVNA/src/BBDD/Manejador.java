/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BBDD;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;


/**
 *
 * @author gabri
 */
public class Manejador {

    Connection connection;
    
    public Manejador() {
        
        connection = null;
    }
    
    public void conexion(){
        
        if (connection != null){
		return;
	}

	String url = "jdbc:postgresql:SC";
	String password = "171215";

	try{
		Class.forName("org.postgresql.Driver");

		connection = DriverManager.getConnection(url, "postgres", password);
		
		if (connection != null){
			System.out.println("Conectando con la base de datos...");
		}
	} catch (Exception e){
	      System.out.println("Problemas de conexion");
	  }
        
    }
    
    public void getAllPaciente(){
        
        Statement s = null;
        
        try{
            s = connection.createStatement();
         }catch (Exception e) {
		System.out.println ("ERROR");
          }
         String sql = "Select * From fa_paciente";
         
        ResultSet rst;
    
    
        try{
            rst = s.executeQuery(sql);
            while (rst.next()) {
                System.out.println(rst.getString("nombre_paciente"));

            }
        }catch (Exception e) {
		System.out.println ("ERROR");
        }    
    }
    
    public String encriptarMD5(String str) throws NoSuchAlgorithmException{
        
        MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        md.update(str.getBytes());
        byte[] digest = md.digest();

        // Se escribe byte a byte en hexadecimal
       // for (byte b : digest) {
         //  System.out.print(Integer.toHexString(0xFF & b));
       // }
       // System.out.println();

        // Se escribe codificado base 64. Se necesita la librería
        // commons-codec-x.x.x.jar de Apache
        byte[] encoded = Base64.encodeBase64(digest);
                
        return new String(encoded);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
}
