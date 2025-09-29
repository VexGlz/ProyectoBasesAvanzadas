/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;
import modelo.Paciente;
import modelo.Tratamiento;

/**
 *
 * @author Josel
 */
public interface IPacienteDAO {

    void agregar(Paciente paciente);

    void actualizar(Paciente paciente);

    void eliminar(int id);

    Paciente obtenerPorId(int id);

    List<Paciente> listar();
}
