package Control;

import Dao.DoctorDAO;
import Interfaces.IDoctorDAO;
import modelo.Doctor;
import java.util.List;

/**
 *
 * @author Josel
 */
public class ControlDoctores {

    private final IDoctorDAO doctorDAO;

    public ControlDoctores() {
        this.doctorDAO = new DoctorDAO();
    }

    // Insertar un nuevo doctor con validaciones
    public boolean agregarDoctor(String nombre, String apellido, String correo, int telefono, String especialidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del doctor no puede estar vacío.");
            return false;
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            System.err.println("El apellido del doctor no puede estar vacío.");
            return false;
        }
        if (correo == null) {
            correo = "";
        }
        if (especialidad == null) {
            especialidad = "";
        }

        Doctor doctor = new Doctor();
        doctor.setNombre(nombre.trim());
        doctor.setApellido(apellido.trim());
        doctor.setCorreo(correo.trim());
        doctor.setTelefono(telefono);
        doctor.setEspecialidad(especialidad.trim());

        doctorDAO.agregar(doctor);
        return true;
    }

    // Obtener un doctor por ID
    public Doctor obtenerDoctor(int idDoctor) {
        if (idDoctor <= 0) {
            System.err.println("ID de doctor inválido.");
            return null;
        }
        return doctorDAO.obtenerPorId(idDoctor);
    }

    // Obtener todos los doctores
    public List<Doctor> listarDoctores() {
        return doctorDAO.listar();
    }

    // Actualizar doctor con validaciones
    public boolean actualizarDoctor(int idDoctor, String nombre, String apellido, String correo, int telefono, String especialidad) {
        if (idDoctor <= 0) {
            System.err.println("ID de doctor inválido.");
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre del doctor no puede estar vacío.");
            return false;
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            System.err.println("El apellido del doctor no puede estar vacío.");
            return false;
        }
        if (correo == null) {
            correo = "";
        }
        if (especialidad == null) {
            especialidad = "";
        }

        Doctor doctor = new Doctor();
        doctor.setId(idDoctor);
        doctor.setNombre(nombre.trim());
        doctor.setApellido(apellido.trim());
        doctor.setCorreo(correo.trim());
        doctor.setTelefono(telefono);
        doctor.setEspecialidad(especialidad.trim());

        doctorDAO.actualizar(doctor);
        return true;
    }

    // Eliminar doctor con validación de ID
    public boolean eliminarDoctor(int idDoctor) {
        if (idDoctor <= 0) {
            System.err.println("ID de doctor inválido.");
            return false;
        }
        doctorDAO.eliminar(idDoctor);
        return true;
    }
}