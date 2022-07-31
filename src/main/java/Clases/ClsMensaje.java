/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author MIRABAI
 */
public class ClsMensaje {
    
    public final String OK = "OK";
    public final String ERROR = "ERROR";
    public final String ADVERTENCIA = "ADVERTENCIA";
   
    private String tipo;
    private String descripcion;
    private String data;

    public ClsMensaje() {
        
    }
    
    public void CambiarMensaje(String tipo, String descripcion){
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    
    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
    
    
    
    
    
}
