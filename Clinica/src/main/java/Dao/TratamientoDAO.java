<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import modelo.Tratamiento;
import modelo.Cita;
import modelo.Medicamento;
import Conexion.ConexionDB;
import InterfacesDAO.ITratamientoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josel
 */
public class TratamientoDAO implements ITratamientoDAO {

    private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    private CitaDAO citaDAO = new CitaDAO();

    @Override
    public void agregar(Tratamiento t) {
        String sql = "INSERT INTO Tratamientos(id_citas, id_doctor, descripcion) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, t.getCita().getId());
            ps.setInt(2, t.getCita().getDoctor().getId());
            ps.setString(3, t.getDescripcion());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                t.setId_tratamiento(rs.getInt(1));
            }

            if (t.getMedicamentos() != null) {
                for (Medicamento m : t.getMedicamentos()) {
                    m.setTratamiento(t);
                    medicamentoDAO.agregar(m);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar tratamiento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Tratamiento t) {
        String sql = "UPDATE Tratamientos SET id_citas=?, descripcion=? WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            if (t.getCita() != null && t.getCita().getId() > 0) {
                ps.setInt(1, t.getCita().getId());
            } else {
                
                System.err.println("Error: La cita asociada al tratamiento no tiene un ID válido para la actualización.");
                return; 
            }
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, t.getId_tratamiento()); 
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar tratamiento: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {

        medicamentoDAO.eliminarPorTratamiento(id);

        String sql = "DELETE FROM Tratamientos WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar tratamiento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Tratamiento> listar() {
        List<Tratamiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tratamientos";
        try (Connection conn = ConexionDB.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Tratamiento t = new Tratamiento();
                t.setId_tratamiento(rs.getInt("id"));
                Cita c = citaDAO.obtenerPorId(rs.getInt("id_citas"));
                t.setCita(c);
                t.setDescripcion(rs.getString("descripcion")); // <-- Asegúrate de que esto sea la descripción de la DB
                t.setMedicamentos(medicamentoDAO.listarPorTratamiento(t.getId_tratamiento()));
                lista.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar tratamientos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Tratamiento> listarPorCita(int id_cita) {
        List<Tratamiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tratamientos WHERE id_citas = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_cita);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tratamiento t = new Tratamiento();
                t.setId_tratamiento(rs.getInt("id"));
                Cita c = citaDAO.obtenerPorId(rs.getInt("id_citas"));
                t.setCita(c);
                t.setDescripcion(rs.getString("descripcion"));

                lista.add(t);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar tratamientos por cita: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public Tratamiento obtenerPorId(int id) {
        String sql = "SELECT * FROM Tratamientos WHERE id = ?";
        Tratamiento t = null;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = new Tratamiento();
                t.setId_tratamiento(rs.getInt("id"));
                Cita c = citaDAO.obtenerPorId(rs.getInt("id_citas"));
                t.setCita(c);
                t.setDescripcion(rs.getString("descripcion"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener tratamiento por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return t;
    }
}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import modelo.Tratamiento;
import modelo.Cita;
import modelo.Medicamento;
import Conexion.ConexionDB;
import Interfaces.ITratamientoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josel
 */
public class TratamientoDAO implements ITratamientoDAO {

    private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    private CitaDAO citaDAO = new CitaDAO();

    @Override
    public void agregar(Tratamiento t) {
        String sql = "INSERT INTO Tratamientos(id_citas, descripcion) VALUES (?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, t.getCita().getId());
            ps.setString(2, t.getDescripcion());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                t.setId_tratamiento(rs.getInt(1));
            }

            if (t.getMedicamentos() != null) {
                for (Medicamento m : t.getMedicamentos()) {
                    m.setTratamiento(t);
                    medicamentoDAO.agregar(m);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar tratamiento: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Tratamiento t) {
        String sql = "UPDATE Tratamientos SET id_citas=?, descripcion=? WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, t.getCita().getId());
            ps.setString(2, t.getDescripcion());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar tratamiento: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Tratamientos WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar tratamiento: " + e.getMessage());
        }
    }

    @Override
    public List<Tratamiento> listar() {
        List<Tratamiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tratamientos";

        try (Connection conn = ConexionDB.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Tratamiento t = new Tratamiento();
                t.setId_tratamiento(rs.getInt("id"));
                Cita c = citaDAO.obtenerPorId(rs.getInt("id_citas"));
                t.setCita(c);

                t.setDescripcion(rs.getString("descripcion"));

                t.setMedicamentos(medicamentoDAO.listarPorTratamiento(t.getId_tratamiento()));

                lista.add(t);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar tratamientos: " + e.getMessage());
        }

        return lista;
    }

    public List<Tratamiento> listarPorCita(int id_cita) {
        List<Tratamiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tratamientos WHERE id_citas = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_cita);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tratamiento t = new Tratamiento();
                t.setId_tratamiento(rs.getInt("id"));
                t.setDescripcion(rs.getString("descripcion"));

                // Si tienes medicamentos asociados
                t.setMedicamentos(medicamentoDAO.listarPorTratamiento(t.getId_tratamiento()));

                lista.add(t);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar tratamientos por cita: " + e.getMessage());
        }

        return lista;
    }

}
>>>>>>> 9df1de2cd2ca02ad56345db6ae4a26cb7fb75833
