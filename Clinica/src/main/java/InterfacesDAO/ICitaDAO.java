/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;
import modelo.Cita;

/**
 *
 * @author Josel
 */
public interface ICitaDAO {

    void agregar(Cita cita);

    void actualizar(Cita cita);

    void eliminar(int id);

    Cita obtenerPorId(int id);

    List<Cita> listar();
}
