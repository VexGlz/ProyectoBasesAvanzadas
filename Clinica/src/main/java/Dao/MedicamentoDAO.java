<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Medicamento;
import modelo.Tratamiento;
import InterfacesDAO.IMedicamentoDAO;

public class MedicamentoDAO implements IMedicamentoDAO {

    @Override
    public void agregar(Medicamento m) {
        String sql = "INSERT INTO Medicamentos(id_tratamiento, descripcion, duracion, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, m.getTratamiento().getId_tratamiento());
            ps.setString(2, m.getDescripcion());
            ps.setString(3, m.getDuracion());
            ps.setString(4, m.getCantidad());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                m.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar medicamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Medicamento m) {
        String sql = "UPDATE Medicamentos SET id_tratamiento=?, descripcion=?, duracion=?, cantidad=? WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getTratamiento().getId_tratamiento());
            ps.setString(2, m.getDescripcion());
            ps.setString(3, m.getDuracion());
            ps.setString(4, m.getCantidad());
            ps.setInt(5, m.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar medicamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Medicamentos WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar medicamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminarPorTratamiento(int idTratamiento) {
        String sql = "DELETE FROM Medicamentos WHERE id_tratamiento=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTratamiento);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar medicamentos por tratamiento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Medicamento> listar() {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medicamentos";

        try (Connection conn = ConexionDB.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Medicamento m = new Medicamento();
                m.setId(rs.getInt("id"));

                Tratamiento t = new Tratamiento();
                t.setId_tratamiento(rs.getInt("id_tratamiento"));
                m.setTratamiento(t);

                m.setDescripcion(rs.getString("descripcion"));
                m.setDuracion(rs.getString("duracion"));
                m.setCantidad(rs.getString("cantidad"));

                lista.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar medicamentos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<Medicamento> listarPorTratamiento(int tratamientoId) {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medicamentos WHERE id_tratamiento=?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, tratamientoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Medicamento m = new Medicamento();
                m.setId(rs.getInt("id"));

                Tratamiento t = new Tratamiento();
                t.setId_tratamiento(tratamientoId);
                m.setTratamiento(t);

                m.setDescripcion(rs.getString("descripcion"));
                m.setDuracion(rs.getString("duracion"));
                m.setCantidad(rs.getString("cantidad"));

                lista.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar medicamentos por tratamiento: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }
}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Medicamento;
import modelo.Tratamiento;
import Interfaces.IMedicamentoDAO;
/**
 *
 * @author Josel
 */


public class MedicamentoDAO implements IMedicamentoDAO {

    @Override
    public void agregar(Medicamento m) {
        String sql = "INSERT INTO Medicamento(tratamiento_id, descripcion, duracion, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, m.getTratamiento().getId_tratamiento());
            ps.setString(2, m.getDescripcion());
            ps.setString(3, m.getDuracion());
            ps.setString(4, m.getCantidad());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                m.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar medicamento: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Medicamento m) {
        String sql = "UPDATE Medicamento SET tratamiento_id=?, descripcion=?, duracion=?, cantidad=? WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getTratamiento().getId_tratamiento());
            ps.setString(2, m.getDescripcion());
            ps.setString(3, m.getDuracion());
            ps.setString(4, m.getCantidad());
            ps.setInt(5, m.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar medicamento: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Medicamento WHERE id=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar medicamento: " + e.getMessage());
        }
    }

    @Override
    public List<Medicamento> listar() {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medicamento";

        try (Connection conn = ConexionDB.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Medicamento m = new Medicamento();
                m.setId(rs.getInt("id"));

                Tratamiento t = new Tratamiento();
                t.setId_tratamiento(rs.getInt("id_tratamiento"));
                m.setTratamiento(t);

                m.setDescripcion(rs.getString("descripcion"));
                m.setDuracion(rs.getString("duracion"));
                m.setCantidad(rs.getString("cantidad"));

                lista.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar medicamentos: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<Medicamento> listarPorTratamiento(int tratamientoId) {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medicamento WHERE tratamiento_id=?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, tratamientoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Medicamento m = new Medicamento();
                m.setId(rs.getInt("id"));

                Tratamiento t = new Tratamiento();
                t.setId_tratamiento(tratamientoId);
                m.setTratamiento(t);

                m.setDescripcion(rs.getString("descripcion"));
                m.setDuracion(rs.getString("duracion"));
                m.setCantidad(rs.getString("cantidad"));

                lista.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar medicamentos por tratamiento: " + e.getMessage());
        }

        return lista;
    }
}
>>>>>>> 9df1de2cd2ca02ad56345db6ae4a26cb7fb75833
