package Control;

import Dao.CitaDAO;
import Interfaces.ICitaDAO;
import modelo.Cita;
import modelo.Paciente;
import modelo.Doctor;
import java.util.List;

/**
 *
 * @author
 */
public class ControlCita {

    private final ICitaDAO citaDAO;

    public ControlCita() {
        this.citaDAO = new CitaDAO();
    }

    // Insertar una nueva cita con validaciones
    public boolean agregarCita(Paciente paciente, Doctor doctor, String fecha, String motivo, String estado) {
        if (paciente == null) {
            System.err.println("El paciente es obligatorio.");
            return false;
        }
        if (doctor == null) {
            System.err.println("El doctor es obligatorio.");
            return false;
        }
        if (fecha == null || fecha.trim().isEmpty()) {
            System.err.println("La fecha es obligatoria.");
            return false;
        }
        if (estado == null || estado.trim().isEmpty()) {
            estado = "Pendiente";
        }
        if (motivo == null) {
            motivo = "";
        }

        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setDoctor(doctor);
        cita.setFecha(fecha.trim());
        cita.setMotivo(motivo.trim());
        cita.setEstado(estado.trim());

        citaDAO.agregar(cita);
        return true;
    }

    // Obtener una cita por ID
    public Cita obtenerCita(int idCita) {
        if (idCita <= 0) {
            System.err.println("ID de cita inválido.");
            return null;
        }
        return ((CitaDAO) citaDAO).obtenerPorId(idCita);
    }

    public List<Cita> listarCitas() {
        return citaDAO.listar();
    }

    public boolean actualizarCita(int idCita, Paciente paciente, Doctor doctor, String fecha, String motivo, String estado) {
        if (idCita <= 0) {
            System.err.println("ID de cita inválido.");
            return false;
        }
        if (paciente == null) {
            System.err.println("El paciente es obligatorio.");
            return false;
        }
        if (doctor == null) {
            System.err.println("El doctor es obligatorio.");
            return false;
        }
        if (fecha == null || fecha.trim().isEmpty()) {
            System.err.println("La fecha es obligatoria.");
            return false;
        }
        if (estado == null || estado.trim().isEmpty()) {
            estado = "Pendiente";
        }
        if (motivo == null) {
            motivo = "";
        }

        Cita cita = new Cita();
        cita.setId(idCita);
        cita.setPaciente(paciente);
        cita.setDoctor(doctor);
        cita.setFecha(fecha.trim());
        cita.setMotivo(motivo.trim());
        cita.setEstado(estado.trim());

        citaDAO.actualizar(cita);
        return true;
    }

    public boolean eliminarCita(int idCita) {
        if (idCita <= 0) {
            System.err.println("ID de cita inválido.");
            return false;
        }
        citaDAO.eliminar(idCita);
        return true;
    }
}
