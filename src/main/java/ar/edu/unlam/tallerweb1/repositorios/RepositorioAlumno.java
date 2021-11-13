package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alumno;

import java.util.List;

public interface RepositorioAlumno {
    Alumno buscarAlumnoPorEmail(String email);

    void guardarAlumno(Alumno alumnoARegistrar);

    List<Alumno> listarAlumnos();

    Alumno buscarAlumnoPorId(Long idAlumno);

    void eliminarAlumno(Alumno alumnoAEliminar);
}
