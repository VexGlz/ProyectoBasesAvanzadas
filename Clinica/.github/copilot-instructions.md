# Copilot Instructions for Clinica Java Project

## Project Overview
- This is a Java Swing desktop application for managing a medical clinic, using a modular structure.
- Main components:
  - `entidades/`: JavaBeans for domain entities (Paciente, Doctor, Cita, Medicamento, Tratamiento, HistorialMedico).
  - `Conexion/ConexionDB.java`: Handles database connection logic.
  - `Interfaces/`: Contains all Swing GUI forms and their logic for CRUD operations (e.g., DaoCitas, DaoDoctores, DaoPacientes, DaoRecetas, DaoMenuPrincipal).
  - `resources/db.propierties.properties`: Database configuration.

## Key Patterns & Conventions
- **Entity classes** in `entidades/` are POJOs with fields, getters/setters, and minimal logic.
- **DAO classes** in `Interfaces/` handle all database operations for their respective entities. Each DAO is paired with a `.form` file for the GUI.
- **Database access** is always via `ConexionDB.getConnection()`.
- **Forms** are built with NetBeans GUI Builder; avoid manual edits to `.form` files.
- **Testing**: `Pruebas/pruebas.java` is used for manual or ad-hoc testing, not automated tests.

## Developer Workflows
- **Build**: Use Maven (`mvn clean package`) to build the project. Output JAR is in `target/`.
- **Run**: Launch the main GUI via the class in `Interfaces/Clinica.java`.
- **Database**: Ensure `db.propierties.properties` is configured and the database is running before starting the app.
- **Add new entity**: Create a POJO in `entidades/`, a DAO+form in `Interfaces/`, and update the menu in `DaoMenuPrincipal`.

## Integration & Dependencies
- Relies on a relational database (see `db.propierties.properties`).
- No external REST APIs or microservices; all logic is local.
- No automated test suite; manual testing only.

## Examples
- To add a new patient: use `DaoPacientes` form, which interacts with `Paciente` entity and database via `DaoPacientes.java`.
- To connect to the database: see `Conexion/ConexionDB.java` and `resources/db.propierties.properties`.

## Special Notes
- Do not edit `.form` files by hand—use NetBeans GUI Builder.
- Keep entity/DAO/form naming consistent for discoverability.
- All business logic should reside in DAOs, not in GUI classes.

---
For questions or unclear patterns, review the structure in `src/main/` and reference existing DAO/entity pairs for examples.
