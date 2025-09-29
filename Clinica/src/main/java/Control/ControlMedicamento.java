package Control;

import Dao.MedicamentoDAO;
import Interfaces.IMedicamentoDAO;
import java.util.List;
import modelo.Medicamento;
import modelo.Tratamiento;

/**
 *
 * @author Josel
 */
public class ControlMedicamento {

    private final IMedicamentoDAO medicamentoDAO;

    public ControlMedicamento() {
        this.medicamentoDAO = new MedicamentoDAO();
    }

    // Insertar un nuevo medicamento con validaciones
    public boolean agregarMedicamento(Tratamiento tratamiento, String descripcion, String duracion, String cantidad) {
        if (tratamiento == null || tratamiento.getId_tratamiento() <= 0) {
            System.err.println("El tratamiento asociado es inválido.");
            return false;
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.err.println("La descripción del medicamento no puede estar vacía.");
            return false;
        }
        if (duracion == null) {
            duracion = "";
        }
        if (cantidad == null) {
            cantidad = "";
        }

        Medicamento medicamento = new Medicamento();
        medicamento.setTratamiento(tratamiento);
        medicamento.setDescripcion(descripcion.trim());
        medicamento.setDuracion(duracion.trim());
        medicamento.setCantidad(cantidad.trim());

        medicamentoDAO.agregar(medicamento);
        return true;
    }

    // Obtener un medicamento por ID (extra si lo necesitas, aunque no está en tu DAO)
    // public Medicamento obtenerMedicamento(int idMedicamento) { ... }

    // Obtener todos los medicamentos
    public List<Medicamento> listarMedicamentos() {
        return medicamentoDAO.listar();
    }

    // Obtener medicamentos por tratamiento
    public List<Medicamento> listarMedicamentosPorTratamiento(int idTratamiento) {
        if (idTratamiento <= 0) {
            System.err.println("ID de tratamiento inválido.");
            return null;
        }
        return medicamentoDAO.listarPorTratamiento(idTratamiento);
    }

    // Actualizar medicamento con validaciones
    public boolean actualizarMedicamento(int idMedicamento, Tratamiento tratamiento, String descripcion, String duracion, String cantidad) {
        if (idMedicamento <= 0) {
            System.err.println("ID de medicamento inválido.");
            return false;
        }
        if (tratamiento == null || tratamiento.getId_tratamiento() <= 0) {
            System.err.println("El tratamiento asociado es inválido.");
            return false;
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.err.println("La descripción del medicamento no puede estar vacía.");
            return false;
        }
        if (duracion == null) {
            duracion = "";
        }
        if (cantidad == null) {
            cantidad = "";
        }

        Medicamento medicamento = new Medicamento();
        medicamento.setId(idMedicamento);
        medicamento.setTratamiento(tratamiento);
        medicamento.setDescripcion(descripcion.trim());
        medicamento.setDuracion(duracion.trim());
        medicamento.setCantidad(cantidad.trim());

        medicamentoDAO.actualizar(medicamento);
        return true;
    }

    // Eliminar medicamento con validación de ID
    public boolean eliminarMedicamento(int idMedicamento) {
        if (idMedicamento <= 0) {
            System.err.println("ID de medicamento inválido.");
            return false;
        }
        medicamentoDAO.eliminar(idMedicamento);
        return true;
    }
}