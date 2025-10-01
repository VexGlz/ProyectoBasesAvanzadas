/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import java.util.List;
import modelo.*;

/**
 *
 * @author Josel
 */
public interface ITratamientoDAO {

    void agregar(Tratamiento tratamiento);

    void actualizar(Tratamiento tratamiento);

    void eliminar(int id);

    Tratamiento obtenerPorId(int id); 

    List<Tratamiento> listar();

    List<Tratamiento> listarPorCita(int idcita);
}
