
package Modelos;

import Clases.ClsCandidato;
import Clases.ClsJdbc;
import Clases.ClsMensaje;
import Clases.ClsPropuesta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author MIRABAI
 */
public class MdlCandidato {
    
    ClsJdbc jdbc;

    public MdlCandidato() {
        this.jdbc = new ClsJdbc();
        this.jdbc.CrearConexion();
    }
    
    
    public ClsMensaje AgregarCandidato(ClsCandidato candidato){
        
        ClsMensaje mensaje = new ClsMensaje();
        try {
            
            String sql = "INSERT INTO tbl_candidatos VALUES (" + 
                    "'" + candidato.getNumeroDocumento()+ "', "+
                    "'" + candidato.getNombre()+ "', "+
                    "'" + candidato.getTelefono()+ "', "+
                    "'" + candidato.getCorreo()+ "', "+
                    "'" + candidato.getPartidoPolitico()+ "', "+
                    "'" + candidato.getCiudadOrigen()+ "', "+
                    "'" + candidato.getDescripcion()+ "', "+
                    "NULL"+
                    ")";
            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            int resultado = sentencia.executeUpdate();
            
            if (resultado==1){
                mensaje.CambiarMensaje(mensaje.OK, "Has creaod un nuevo candidato");
            }else{
                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }
            return mensaje;
            
        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: "+ e.getMessage());
            return mensaje;
        }
        
        
         
    }
    
    
    public ClsMensaje ActualizarCandidato(ClsCandidato candidato){
        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "UPDATE tbl_candidatos SET nombre = ?, telefono = ?, correo= ?, partido_politico= ?, ciudad_origen= ?, descripcion= ? WHERE id_candidato= ? ";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, candidato.getNombre());
            sentencia.setString(2, candidato.getTelefono());
            sentencia.setString(3, candidato.getCorreo());
            sentencia.setString(4, candidato.getPartidoPolitico());
            sentencia.setString(5, candidato.getCiudadOrigen());
            sentencia.setString(6, candidato.getDescripcion());
            sentencia.setString(7, candidato.getNumeroDocumento());

            int resultado = sentencia.executeUpdate();

            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has editado al candidato");
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }
    }
    
    public ClsMensaje EliminarCandidato(String idCandidato) {

        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "DELETE FROM tbl_candidatos WHERE id_candidato = ?";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, idCandidato);

            int resultado = sentencia.executeUpdate();

            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has eliminado al candidato: " + idCandidato);
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }

    }
    
    
    
    public LinkedList<ClsCandidato> ObtenerCandidatos(){
        try {
            LinkedList<ClsCandidato> listaCandidatos= new LinkedList<>();
            
            String sql = "SELECT * FROM tbl_candidatos";
            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            ResultSet resultados = sentencia.executeQuery();
            
            while(resultados.next()){
                String partidoPolitico = resultados.getString("partido_politico");
                String descripcion = resultados.getString("descripcion");
                String numeroDocumento = resultados.getString("id_candidato");
                String nombre = resultados.getString("nombre");
                String telefono = resultados.getString("telefono");
                String correo = resultados.getString("correo");
                String ciudadOrigen= resultados.getString("ciudad_origen");
                
                
                ClsCandidato candidato = new ClsCandidato(partidoPolitico, descripcion, ciudadOrigen, numeroDocumento, nombre, telefono, correo);
                
                listaCandidatos.add(candidato);
            }
            
            return listaCandidatos;
        } catch (Exception e) {
            System.out.println("Ocurrió un error" + e.getMessage());
            return null;
        }
    }
    
}
