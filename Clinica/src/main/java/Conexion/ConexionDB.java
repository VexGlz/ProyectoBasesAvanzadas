/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Josel
 */
public class ConexionDB {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try (InputStream input = ConexionDB.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            
            Properties props = new Properties();
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo db.properties");
            }
            props.load(input);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            driver = props.getProperty("db.driver");

            Class.forName(driver);
            
            String sqlPacientes = "CREATE TABLE IF NOT EXISTS Pacientes ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nombre VARCHAR(100), "
                    + "apellido VARCHAR(100), "
                    + "sexo VARCHAR(100), "
                    + "edad int, "
                    + "direccion VARCHAR(100), "
                    + "telefono int, "
                    + "correo VARCHAR(100)"
                    + ");";
            try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sqlPacientes)) {
                ps.execute();
                System.out.println("Tabla Pacientes creada");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

            String sqlCitas = "CREATE TABLE IF NOT EXISTS Citas ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "id_paciente INT NOT NULL, "
                    + "id_doctor INT NOT NULL,"
                    + "fecha DATE, "
                    + "motivo VARCHAR(100), "
                    + "estado VARCHAR(100), "
                    + "FOREIGN KEY (id_paciente) REFERENCES Pacientes(id),"
                    + "FOREIGN KEY (id_doctor) REFERENCES Doctor(id)"
                    + ");";
            try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sqlCitas)) {
                ps.execute();
                System.out.println("Tabla Citas creada");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

            String sqlTratamientos = "CREATE TABLE IF NOT EXISTS Tratamientos ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "id_citas INT NOT NULL, "
                    + "id_doctor INT NOT NULL,"
                    + "descripcion VARCHAR(100), "
                    + "FOREIGN KEY (id_citas) REFERENCES Citas(id)"
                    + ");";
            try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sqlTratamientos)) {
                ps.execute();
                System.out.println("Tabla Tratamientos creada");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            String sqlDoctor= "CREATE TABLE IF NOT EXISTS Doctor ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL, "
                    + "apellido VARCHAR(100), "
                    + "especialidad VARCHAR(100), "
                    + "telefono INT, "
                    + "correo VARCHAR(100)"
                    + ");";
            try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sqlDoctor)) {
                ps.execute();
                System.out.println("Tabla Doctor creada");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

            String sqlMedicamentos = "CREATE TABLE IF NOT EXISTS Medicamentos ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "id_tratamiento INT NOT NULL, "
                    + "descripcion VARCHAR(100), "
                    + "duracion VARCHAR(100), "
                    + "cantidad VARCHAR(100), "
                    + "FOREIGN KEY (id_tratamiento) REFERENCES Tratamientos(id)"
                    + ");";
            try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sqlMedicamentos)) {
                ps.execute();
                System.out.println("Tabla Medicamentos creada");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println("Conexion exitosa");

        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException("Error cargando configuración de BD: " + ex.getMessage(), ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
  }        
    

