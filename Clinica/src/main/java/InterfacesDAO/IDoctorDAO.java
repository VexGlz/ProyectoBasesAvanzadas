/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;
import modelo.*;

/**
 *
 * @author Josel
 */
public interface IDoctorDAO {

    void agregar(Doctor doctor);

    void actualizar(Doctor doctor);

    void eliminar(int id);

    Doctor obtenerPorId(int id);

    List<Doctor> listar();
}
