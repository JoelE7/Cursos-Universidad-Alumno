package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Curso;

import java.util.List;

public interface RepositorioCurso {
    List<Curso> listarCursos();

    Curso buscarCursoPorCodigo(String codigo_curso);

    void guardarCurso(Curso cursoNuevo);

    Curso buscarCursoPorId(Long id_curso);

    void eliminarCurso(Curso cursoAEliminar);

    Curso buscarAlumnoEnUnCurso(Long alumno, Long idCurso);

}
