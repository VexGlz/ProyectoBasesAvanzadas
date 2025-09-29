/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;
import modelo.Medicamento;
import modelo.Tratamiento;

/**
 *
 * @author Josel
 */
public interface IMedicamentoDAO {

    void agregar(Medicamento m);

    void actualizar(Medicamento m);

    void eliminar(int id);

    List<Medicamento> listar();

    List<Medicamento> listarPorTratamiento(int id_tratamiento);
}
