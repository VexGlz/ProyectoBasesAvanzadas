package Control;

import Dao.PacienteDAO;
import modelo.Paciente;
import java.util.List;

public class ControlPacientes {

    private final PacienteDAO pacienteDAO;

    public ControlPacientes() {
        this.pacienteDAO = new PacienteDAO();
    }

    // Insertar un nuevo paciente con validaciones
    public boolean agregarPaciente(Paciente paciente) {
        if (paciente.getNombre() == null || paciente.getNombre().trim().isEmpty()) {
            System.err.println("El nombre del paciente no puede estar vacío.");
            return false;
        }
        if (paciente.getApellido() == null || paciente.getApellido().trim().isEmpty()) {
            System.err.println("El apellido del paciente no puede estar vacío.");
            return false;
        }
        if (paciente.getEdad() <= 0) {
            System.err.println("La edad debe ser mayor a 0.");
            return false;
        }

        pacienteDAO.agregar(paciente);
        
        return true;
    }

    // Obtener un paciente por ID
    public Paciente obtenerPaciente(int idPaciente) {
        if (idPaciente <= 0) {
            System.err.println("ID de paciente inválido.");
            return null;
        }
        return pacienteDAO.obtenerPorId(idPaciente);
    }

    // Obtener todos los pacientes
    public List<Paciente> listarPacientes() {
        return pacienteDAO.listar();
    }

    // Actualizar paciente con validaciones
    public boolean actualizarPaciente(Paciente paciente) {
        if (paciente.getId() <= 0) {
            System.err.println("ID de paciente inválido.");
            return false;
        }
        if (paciente.getNombre() == null || paciente.getNombre().trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío.");
            return false;
        }
        if (paciente.getApellido() == null || paciente.getApellido().trim().isEmpty()) {
            System.err.println("El apellido no puede estar vacío.");
            return false;
        }
        if (paciente.getEdad() <= 0) {
            System.err.println("La edad debe ser mayor a 0.");
            return false;
        }

        pacienteDAO.actualizar(paciente);
        return true;
    }

    // Eliminar paciente con validación de ID
    public boolean eliminarPaciente(int idPaciente) {
        if (idPaciente <= 0) {
            System.err.println("ID de paciente inválido.");
            return false;
        }
        pacienteDAO.eliminar(idPaciente);
        return true;
    }
}