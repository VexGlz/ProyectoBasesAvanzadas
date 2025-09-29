package Control;

import Dao.TratamientoDAO;
import modelo.Tratamiento;
import modelo.Cita;
import java.util.List;

public class ControlTratamiento {

    private final TratamientoDAO tratamientoDAO;

    public ControlTratamiento() {
        this.tratamientoDAO = new TratamientoDAO();
    }

    public boolean agregarTratamiento(Cita cita, String descripcion) {
        if (cita == null || cita.getId() <= 0) {
            System.err.println("El tratamiento debe estar asociado a una cita válida.");
            return false;
        }
        if (descripcion == null) {
            descripcion = "";
        }

        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setCita(cita);
        tratamiento.setDescripcion(descripcion.trim());

        tratamientoDAO.agregar(tratamiento);
        return true;
    }
    
    public List<Tratamiento> listarTratamientos() {
        return tratamientoDAO.listar();
    }

    public boolean actualizarTratamiento(int idTratamiento, Cita cita, String descripcion) {
        if (idTratamiento <= 0) {
            System.err.println("ID de tratamiento inválido.");
            return false;
        }
        if (cita == null || cita.getId() <= 0) {
            System.err.println("El tratamiento debe estar asociado a una cita válida.");
            return false;
        }
        if (descripcion == null) {
            descripcion = "";
        }

        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setId_tratamiento(idTratamiento);
        tratamiento.setCita(cita);
        tratamiento.setDescripcion(descripcion.trim());

        tratamientoDAO.actualizar(tratamiento);
        return true;
    }

    public boolean eliminarTratamiento(int idTratamiento) {
        if (idTratamiento <= 0) {
            System.err.println("ID de tratamiento inválido.");
            return false;
        }
        tratamientoDAO.eliminar(idTratamiento);
        return true;
    }
}