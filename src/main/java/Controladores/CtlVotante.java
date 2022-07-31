
package Controladores;

import Clases.ClsMensaje;
import Clases.ClsVotante;
import Modelos.MdlVotante;
import java.util.LinkedList;

/**
 *
 * @author MIRABAI
 */
public class CtlVotante {
    MdlVotante modelo;

    public CtlVotante() {
        this.modelo = new MdlVotante();
    }

    

        
    public ClsMensaje RegistrarVotante(ClsVotante votante){
        ClsMensaje respuesta = this.modelo.RegistrarVotante(votante);
        
        return respuesta;
    }
    public ClsMensaje EliminarVotante(String votante) {
        ClsMensaje respuesta = this.modelo.EliminarVotante(votante);
        return respuesta;
    }
    public ClsMensaje ActualizarVotante(ClsVotante votante){
        
        ClsMensaje respuesta = this.modelo.ActualizarVotante(votante);
        return respuesta;
    }
    
    public LinkedList<ClsVotante> ObtenerVotantes(){
        return this.modelo.ObtenerVotantes();
        
    }
}
