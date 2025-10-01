<<<<<<< HEAD
package modelo;

public class Cita {

    private int id;
    private Paciente paciente;
    private Doctor doctor;
    private String fecha;  // puedes cambiar a LocalDate si quieres
    private String motivo;
    private String estado;

    public Cita() {
    }

    public Cita(int id, Paciente paciente, Doctor doctor, String fecha, String motivo, String estado) {
        this.id = id;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = estado;
    }

    public Cita(Paciente paciente, Doctor doctor, String fecha, String motivo, String estado) {
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = estado;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
=======
package modelo;

public class Cita {

    private int id;
    private Paciente paciente;
    private Doctor doctor;
    private String fecha;  // puedes cambiar a LocalDate si quieres
    private String motivo;
    private String estado;

    public Cita() {
    }

    public Cita(int id, Paciente paciente, Doctor doctor, String fecha, String motivo, String estado) {
        this.id = id;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = estado;
    }

    public Cita(Paciente paciente, Doctor doctor, String fecha, String motivo, String estado) {
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = estado;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
>>>>>>> 9df1de2cd2ca02ad56345db6ae4a26cb7fb75833
