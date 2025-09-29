/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import modelo.Cita;
import modelo.Paciente;
import modelo.Doctor;
import Conexion.ConexionDB;
import Interfaces.ICitaDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josel
 */
public class CitaDAO implements ICitaDAO {

    private PacienteDAO pacienteDAO = new PacienteDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    public void agregar(Cita c) {
        String sql = "INSERT INTO Citas(id_paciente, id_doctor, fecha, motivo, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getPaciente().getId());
            ps.setInt(2, c.getDoctor().getId());
            ps.setString(3, c.getFecha());
            ps.setString(4, c.getMotivo());
            ps.setString(5, c.getEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                c.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar cita: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Cita c) {
        String sql = "UPDATE Citas SET id_paciente=?, id_doctor=?, fecha=?, motivo=?, estado=? WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getPaciente().getId());
            ps.setInt(2, c.getDoctor().getId());
            ps.setString(3, c.getFecha());
            ps.setString(4, c.getMotivo());
            ps.setString(5, c.getEstado());
            ps.setInt(6, c.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar cita: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Citas WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar cita: " + e.getMessage());
        }
    }

    public Cita obtenerPorId(int id) {
        String sql = "SELECT * FROM Citas WHERE id = ?";
        Cita c = null;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Cita();
                c.setId(rs.getInt("id"));

                Paciente p = pacienteDAO.obtenerPorId(rs.getInt("id_paciente"));
                Doctor d = doctorDAO.obtenerPorId(rs.getInt("id_doctor"));

                c.setPaciente(p);
                c.setDoctor(d);
                c.setFecha(rs.getString("fecha"));
                c.setMotivo(rs.getString("motivo"));
                c.setEstado(rs.getString("estado"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener cita por id: " + e.getMessage());
        }

        return c;
    }

    @Override
    public List<Cita> listar() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM Citas";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("id"));

                Paciente p = pacienteDAO.obtenerPorId(rs.getInt("id_paciente"));
                Doctor d = doctorDAO.obtenerPorId(rs.getInt("id_doctor"));

                c.setPaciente(p);
                c.setDoctor(d);
                c.setFecha(rs.getString("fecha"));
                c.setMotivo(rs.getString("motivo"));
                c.setEstado(rs.getString("estado"));

                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar citas: " + e.getMessage());
        }

        return lista;
    }

    public List<Cita> listarPorPaciente(int id_paciente) {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM Citas WHERE id_paciente = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_paciente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("id"));

                // Recuperar paciente y doctor usando tus DAOs
                Paciente p = pacienteDAO.obtenerPorId(rs.getInt("id_paciente"));
                Doctor d = doctorDAO.obtenerPorId(rs.getInt("id_doctor"));

                c.setPaciente(p);
                c.setDoctor(d);
                c.setFecha(rs.getString("fecha"));
                c.setMotivo(rs.getString("motivo"));
                c.setEstado(rs.getString("estado"));

                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar citas por paciente: " + e.getMessage());
        }

        return lista;
    }

}
