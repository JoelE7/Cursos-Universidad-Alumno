package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosCurso;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.ItemCurso;

import java.util.List;

public interface ServicioCurso {
    void registrarCurso(DatosCurso datosCurso);

    List<Curso> listarCursos();

    Curso buscarCursoPorId(Long idCurso);

    void modificarCurso(DatosCurso datosCurso);

    void eliminarCurso(Long idAlumno);

    Boolean buscarCursoPorCodigo(String codigo);
}
