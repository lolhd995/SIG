package Logica;

import Datos.dTradiciones;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fTradiciones {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.Conectar();
    private String sSQL = "";
    
    
    int total_registros;
    
    
     
    
    
    public DefaultTableModel mostrar(String buscar){

        DefaultTableModel modelo;
        String [] Titulos = {"Identificador", "Tradicion","Descripcion","Fecha de Festejo","Imagen",
                             "Tipo"};
        String [] Registros = new String[6];
         
        total_registros = 0;
        
        modelo = new DefaultTableModel(null,Titulos);
        
        sSQL =  "SELECT tradiciones.id_tradicion,tradiciones.nombre_tradicion,tradiciones.descripcion_tradicion,"+
                "tradiciones.fecha_festejo,tipo_tradicion.nombre_tipo_tradicion "+
                "FROM tradiciones, municipios_tradiciones, tipo_tradicion WHERE municipios_tradiciones.id_municipio = '" + 
                buscar + "' AND municipios_tradiciones.id_tradicion = tradiciones.id_tradicion "+
                "AND tradiciones.id_tipo_tradicion=tipo_tradicion.id_tipo_tradicion ORDER BY tradiciones.nombre_tradicion;";
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                Registros[0] = rs.getString("tradiciones.id_tradicion");
                Registros[1] = rs.getString("tradiciones.nombre_tradicion");
                Registros[2] = rs.getString("tradiciones.descripcion_tradicion");
                Registros[3] = rs.getString("tradiones.fecha_festejo");
                Registros[4] = rs.getString("tipo_tradicion.nombre_tipo_tradicion");
            
           
                
                total_registros = total_registros + 1;
                
                modelo.addRow(Registros);
            }
            
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            
            return null;
            
        }
    
    }
    
    
        public DefaultTableModel mostrarNombreTradicion(String buscar){

        DefaultTableModel modelo;
        String [] Titulos = {"Tradiciones"};
        String [] Registros = new String[1];
         
        total_registros = 0;
        
        modelo = new DefaultTableModel(null,Titulos);
        
        sSQL =  "SELECT tradiciones.nombre_tradicion "+
                "FROM tradiciones, municipios_tradiciones WHERE municipios_tradiciones.id_municipio = '" + 
                buscar + "' AND municipios_tradiciones.id_tradicion = tradiciones.id_tradicion;";
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                
                Registros[0] = rs.getString("tradiciones.nombre_tradicion");
            
           
                
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
