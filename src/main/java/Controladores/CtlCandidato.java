
package Controladores;

import Clases.ClsCandidato;
import Clases.ClsMensaje;
import Clases.ClsPropuesta;
import Modelos.MdlCandidato;
import java.util.LinkedList;

/**
 *
 * @author MIRABAI
 */
public class CtlCandidato {
    
    MdlCandidato modelo;

    public CtlCandidato() {
        this.modelo = new MdlCandidato();
    }
    
    
    public ClsMensaje AgregarCandidato(ClsCandidato candidato){
        
        ClsMensaje respuesta = this.modelo.AgregarCandidato(candidato);
        return respuesta;
    }
    public ClsMensaje EliminarCandidato(String candidato) {
        ClsMensaje respuesta = this.modelo.EliminarCandidato(candidato);
        return respuesta;
    }
    public ClsMensaje ActualizarCandidato(ClsCandidato candidato){
        
        ClsMensaje respuesta = this.modelo.ActualizarCandidato(candidato);
        return respuesta;
    }
    
    public LinkedList<ClsCandidato> ObtenerCandidatos(){
        return this.modelo.ObtenerCandidatos();
    }
    
    
    
}
