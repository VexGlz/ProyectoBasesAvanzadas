/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import modelo.Paciente;
import Conexion.ConexionDB;
import Interfaces.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josel
 */
public class PacienteDAO implements IPacienteDAO {

    @Override
    public void agregar(Paciente p) {
        String sql = "INSERT INTO Pacientes(nombre, apellido, edad, sexo, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setInt(3, p.getEdad());
            ps.setString(4, p.getSexo());
            ps.setString(5, p.getDireccion());
            ps.setInt(6, p.getTelefono());
            ps.setString(7, p.getCorreo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                p.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar paciente: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Paciente p) {
        String sql = "UPDATE Pacientes SET nombre=?, apellido=?, edad=?, sexo=?, direccion=?, telefono=?, correo=? WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setInt(3, p.getEdad());
            ps.setString(4, p.getSexo());
            ps.setString(5, p.getDireccion());
            ps.setInt(6, p.getTelefono());
            ps.setString(7, p.getCorreo());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Pacientes WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar paciente: " + e.getMessage());
        }
    }

    @Override
    public Paciente obtenerPorId(int id) {
        String sql = "SELECT * FROM Pacientes WHERE id = ?";
        Paciente p = null;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setEdad(rs.getInt("edad"));
                p.setSexo(rs.getString("sexo"));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getInt("telefono"));
                p.setCorreo(rs.getString("corrreo"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener paciente por ID: " + e.getMessage());
        }
        return p;
    }

    @Override
    public List<Paciente> listar() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pacientes";

        try {
            Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setEdad(rs.getInt("edad"));
                p.setSexo(rs.getString("sexo"));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getInt("telefono"));
                p.setCorreo(rs.getString("correo"));

                lista.add(p);
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("No se pudo consultar los pacientes: " + e);
            e.printStackTrace();
        }

        return lista;
    }

}
