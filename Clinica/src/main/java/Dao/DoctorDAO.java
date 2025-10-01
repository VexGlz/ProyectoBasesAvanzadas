/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import modelo.Doctor;
import Conexion.ConexionDB;
import Interfaces.IDoctorDAO;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author Josel
 */
public class DoctorDAO implements IDoctorDAO {

    @Override
    public void agregar(Doctor d) {
        String sql = "INSERT INTO Doctor(nombre, apellido, especialidad, telefono, correo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getApellido());
            ps.setString(3, d.getEspecialidad());
            ps.setInt(4, d.getTelefono());
            ps.setString(5, d.getCorreo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                d.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar doctor: " + e.getMessage());
        }
    }

    public boolean actualizar(Doctor d) {
        String sql = "UPDATE Doctor SET nombre=?, apellido=?, especialidad=?, telefono=?, correo=? WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getApellido());
            ps.setString(3, d.getEspecialidad());
            ps.setInt(4, d.getTelefono());
            ps.setString(5, d.getCorreo());
            ps.setInt(6, d.getId());

            int filas = ps.executeUpdate();
            return filas > 0; // devuelve true si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al actualizar doctor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Doctor WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar doctor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Doctor obtenerPorId(int id) {
        String sql = "SELECT * FROM Doctor WHERE id = ?";
        Doctor d = null;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setApellido(rs.getString("apellido"));
                d.setEspecialidad(rs.getString("especialidad"));
                d.setTelefono(rs.getInt("telefono"));
                d.setCorreo(rs.getString("correo"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener doctor por ID: " + e.getMessage());
        }
        return d;
    }

    @Override
    public List<Doctor> listar() {
        List<Doctor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Doctor";
        try (Connection conn = ConexionDB.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setApellido(rs.getString("apellido"));
                d.setEspecialidad(rs.getString("especialidad"));
                d.setTelefono(rs.getInt("telefono"));
                d.setCorreo(rs.getString("correo"));
                lista.add(d);
            }

        } catch (SQLException e) {
            System.err.println("Error al ver los doctores: " + e.getMessage());
        }
        return lista;
    }
}
