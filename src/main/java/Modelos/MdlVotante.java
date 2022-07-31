package Modelos;

import Clases.ClsJdbc;
import Clases.ClsMensaje;
import Clases.ClsVotante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author MIRABAI
 */
public class MdlVotante {

    ClsJdbc jdbc;

    public MdlVotante() {
        this.jdbc = new ClsJdbc();
        this.jdbc.CrearConexion();
    }

    public ClsMensaje RegistrarVotante(ClsVotante votante) {
        ClsMensaje mensaje = new ClsMensaje();
        try {

            String sql = "INSERT INTO tbl_votantes VALUES( ?, ?, ?, ?, ?)";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);

            sentencia.setString(1, votante.getNumeroDocumento());
            sentencia.setString(2, votante.getNombre());
            sentencia.setString(3, votante.getTelefono());
            sentencia.setString(4, votante.getCorreo());
            sentencia.setString(5, votante.getDireccion());
            int resultado = sentencia.executeUpdate();
            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has registrado un nuevo votante");
            } else {
                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }
            return mensaje;
        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }
    }
    public ClsMensaje ActualizarVotante(ClsVotante votante){
        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "UPDATE tbl_votantes SET nombre = ?, telefono = ?, correo= ?, direccion= ? WHERE id_votantes= ?";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, votante.getNombre());
            sentencia.setString(2, votante.getTelefono());
            sentencia.setString(3, votante.getCorreo());
            sentencia.setString(4, votante.getDireccion());
            sentencia.setString(5, votante.getNumeroDocumento());

            int resultado = sentencia.executeUpdate();

            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has editado al votante");
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }
    }
    
    public ClsMensaje EliminarVotante(String idVotante) {

        ClsMensaje mensaje = new ClsMensaje();

        try {

            String sql = "DELETE FROM tbl_votantes WHERE id_votantes = ?";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            sentencia.setString(1, idVotante);

            int resultado = sentencia.executeUpdate();

            if (resultado == 1) {
                mensaje.CambiarMensaje(mensaje.OK, "Has eliminado al votante: " + idVotante);
            } else {

                mensaje.CambiarMensaje(mensaje.ERROR, "Error no encontrado");
            }

            return mensaje;

        } catch (Exception e) {
            mensaje.CambiarMensaje(mensaje.ERROR, "Excepción: " + e.getMessage());
            return mensaje;
        }

    }

    public LinkedList<ClsVotante> ObtenerVotantes() {

        try {

            LinkedList<ClsVotante> listaVotantes = new LinkedList<>();

            String sql = "SELECT * FROM tbl_votantes";

            PreparedStatement sentencia = this.jdbc.conexion.prepareStatement(sql);
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()) {
                String numeroDocumento = resultados.getString("id_votantes");
                String nombre = resultados.getString("nombre");
                String telefono = resultados.getString("telefono");
                String correo = resultados.getString("correo");
                String direccion = resultados.getString("direccion");

                ClsVotante votante = new ClsVotante(direccion, numeroDocumento, nombre, telefono, correo);

                listaVotantes.add(votante);
            }
            return listaVotantes;
        } catch (Exception e) {
            System.out.println("Ocurrió un error" + e.getMessage());

            return null;
        }
    }
}
