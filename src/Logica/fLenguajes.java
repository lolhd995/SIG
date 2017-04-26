package Logica;

import Datos.dLenguajes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fLenguajes {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.Conectar();
    private String sSQL = "";

    public int total_registros;
    
    //Metodo para llenar la tabla con el id_reliigon a buscar
    public DefaultTableModel mostrar(String buscar){

        DefaultTableModel modelo;
        String [] Titulos = {"Identificador", "Lenguaje"};
        String [] Registros = new String[2];
         
        total_registros = 0;
        
        modelo = new DefaultTableModel(null,Titulos);
        
        sSQL = "SELECT * FROM lenguajes WHERE id_lengua LIKE '%" + buscar + "%' ORDER BY id_lengua;";
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                Registros[0] = rs.getString("id_lengua");
                Registros[1] = rs.getString("nombre_lengua");
                
                total_registros = total_registros + 1;
                
                modelo.addRow(Registros);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            
            return null;
            
        }
    
    }
    
   
    
    //Metodo Insertar Lenguaje
    public boolean insertar(dLenguajes dts){
        
        sSQL = "INSERT INTO lenguajes (id_lengua, nombre_lengua) values(?,?)";
        
        
        
        try {
            PreparedStatement pat = cn.prepareStatement(sSQL);
            pat.setString(1, dts.getId_Lenguaje());
            pat.setString(2, dts.getNombre_Lenguaje());
            
            int n = pat.executeUpdate();
            
            if(n != 0){
                return true;
            
            }else{
                return false;
            }
             
            
        } catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e);
            return false;
            
        }
    
    }
    
    
    //Metodo Modificar Religion
    public boolean modificar(dLenguajes dts){
        
        sSQL = "UPDATE lenguajes set nombre_lengua = ? "+
                "WHERE id_lengua = ? ;";
        
        
        try {
            
            //
            PreparedStatement pat = cn.prepareStatement(sSQL);
            pat.setString(2, dts.getId_Lenguaje());
            pat.setString(1, dts.getNombre_Lenguaje());
            
            int n = pat.executeUpdate();
            
            if(n != 0){
                return true;
            
            }else{
                return false;
            }
            
            //
            
        } catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e);
            return false;
            
        }
    
    }
    
     
    //Metodo Eliminar Religion
    public boolean eliminar(dLenguajes dts){
        
        sSQL ="DELETE FROM lenguajes WHERE id_lengua = ?" ;
        
        try {
             //
            PreparedStatement pat = cn.prepareStatement(sSQL);
            pat.setString(1, dts.getId_Lenguaje());
            
            
            int n = pat.executeUpdate();
            
            if(n != 0){
                return true;
            
            }else{
                return false;
            }
            
            //
            
            
        } catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e);
            return false;
            
        }
    
    }
    
     public DefaultTableModel mostrarLengMuni(String buscar){

        DefaultTableModel modelo;
        String [] Titulos = {"Lenguajes"};
        String [] Registros = new String[1];
         
        total_registros = 0;
        
        modelo = new DefaultTableModel(null,Titulos);
        
        sSQL = "SELECT nombre_lengua FROM lenguajes, municipios_lenguajes " +
                "WHERE municipios_lenguajes.id_municipio = '"+ buscar
                +"' AND municipios_lenguajes.id_lengua = lenguajes.id_lengua;";
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                
                Registros[0] = rs.getString("nombre_lengua");
                
                total_registros = total_registros + 1;
                
                modelo.addRow(Registros);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            
            return null;
            
        }
    
    }
    
    
}
