package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosAlumno;
import ar.edu.unlam.tallerweb1.modelo.Alumno;

import java.util.List;

public interface ServicioAlumno {
    void registrarAlumno(DatosAlumno datosAlumno);

    List<Alumno> listarAlumnos();

    Alumno buscarAlumnoPorId(Long idAlumno);

    void modificarAlumno(DatosAlumno datosAlumno);

    void eliminarAlumno(Long idAlumno);

    Boolean buscarMailExistente(String email);
}
