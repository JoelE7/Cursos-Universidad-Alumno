package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosCurso;
import ar.edu.unlam.tallerweb1.Excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlumno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioCursoImpl implements ServicioCurso {

    private RepositorioCurso repositorioCurso;
    private RepositorioAlumno repositorioAlumno;

    @Autowired
    public ServicioCursoImpl(RepositorioCurso repositorioCurso, RepositorioAlumno repositorioAlumno) {
        this.repositorioCurso = repositorioCurso;
        this.repositorioAlumno = repositorioAlumno;
    }

    @Override
    public void registrarCurso(DatosCurso datosCurso) {
        Curso cursoNuevo = new Curso();

        if (buscarCursoPorCodigo(datosCurso.getCodigoCurso())) {
            throw new CodigoCursoExistente();
        }

        cursoNuevo.setNombre(datosCurso.getNombreCurso());
        cursoNuevo.setCodigo(datosCurso.getCodigoCurso());

        this.repositorioCurso.guardarCurso(cursoNuevo);

    }

    @Override
    public List<Curso> listarCursos() {
        List<Curso> cursos = repositorioCurso.listarCursos();
        List<Curso> listaCursos = new ArrayList<>();

        for(int i = 0; i < cursos.size();i++){
            if(listaCursos.contains(cursos.get(i))==false){
                listaCursos.add(cursos.get(i));
            }
        }

        if (listaCursos.size() < 1) {
            throw new listaNoEncontrada();
        }

        return listaCursos;
    }

    @Override
    public Curso buscarCursoPorId(Long idCurso) {
        Curso curso = repositorioCurso.buscarCursoPorId(idCurso);

        if (curso == null) {
            throw new CursoNoEncontrado();
        }

        return curso;
    }

    @Override
    public void modificarCurso(DatosCurso datosCurso) {

        Curso curso = repositorioCurso.buscarCursoPorId(datosCurso.getIdCurso());
        if (curso == null) {
            throw new CursoNoEncontrado();
        }
        curso.setCodigo(datosCurso.getCodigoCurso());
        curso.setNombre(datosCurso.getNombreCurso());

        repositorioCurso.guardarCurso(curso);

    }

    @Override
    public void eliminarCurso(Long idCurso) {
        Curso cursoAEliminar = repositorioCurso.buscarCursoPorId(idCurso);
        if (cursoAEliminar == null) {
            throw new CursoNoEncontrado();
        }
        repositorioCurso.eliminarCurso(cursoAEliminar);
    }

    @Override
    public Boolean buscarCursoPorCodigo(String codigo) {


        if (repositorioCurso.buscarCursoPorCodigo(codigo) != null) {
            return true;
        }


        return false;
    }

    @Override
    public void agregarAlumnoAUnCurso(Long idAlumno, Long idCurso) {

        Curso curso = repositorioCurso.buscarCursoPorId(idCurso);

        Alumno alumno = repositorioAlumno.buscarAlumnoPorId(idAlumno);

        lanzarExcepcionesCurso(curso,alumno,1);

        curso.añadirAlumnoAlCurso(alumno);
        Integer estudiantes = curso.getCantidadDeAlumnos();
        curso.setCantidadDeAlumnos(++estudiantes);

        repositorioCurso.guardarCurso(curso);

    }

    @Override
    public void eliminarAlumnoDeUnCurso(Long idCurso, Long idAlumno) {

        Curso curso = repositorioCurso.buscarCursoPorId(idCurso);

        Alumno alumno = repositorioAlumno.buscarAlumnoPorId(idAlumno);

        lanzarExcepcionesCurso(curso,alumno,0);

        Integer estudiantes = curso.getCantidadDeAlumnos();
        curso.setCantidadDeAlumnos(--estudiantes);

        curso.getListaAlumnos().remove(alumno);

        repositorioCurso.guardarCurso(curso);

    }

    private void lanzarExcepcionesCurso(Curso curso, Alumno alumno,Integer redirect) {

        if (curso == null) {
            throw new CursoNoEncontrado();
        }

        if (alumno == null) {
            throw new AlumnoNoEncontrado();
        }

        if(redirect==1) {
            if (validarAlumnoEnUnCurso(curso.getId(), alumno.getId())) {
                throw new AlumnoExistente();
            }
        }

    }


    private Boolean validarAlumnoEnUnCurso(Long idAlumno, Long idCurso) {
        if (repositorioCurso.buscarAlumnoEnUnCurso(idAlumno, idCurso) != null) {
            return true;
        }
        return false;
    }


}
