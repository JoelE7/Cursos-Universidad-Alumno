package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosCurso;
import ar.edu.unlam.tallerweb1.Excepciones.CodigoCursoExistente;
import ar.edu.unlam.tallerweb1.Excepciones.CursoNoEncontrado;
import ar.edu.unlam.tallerweb1.Excepciones.listaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioCursoImpl implements ServicioCurso{

    private RepositorioCurso repositorioCurso;

    @Autowired
    public ServicioCursoImpl(RepositorioCurso repositorioCurso) {
        this.repositorioCurso = repositorioCurso;
    }

    @Override
    public void registrarCurso(DatosCurso datosCurso) {
        Curso cursoNuevo = new Curso();

        if(buscarCursoPorCodigo(datosCurso.getCodigoCurso())){
            throw new CodigoCursoExistente();
        }

        cursoNuevo.setNombre(datosCurso.getNombreCurso());
        cursoNuevo.setCodigo(datosCurso.getCodigoCurso());

        this.repositorioCurso.guardarCurso(cursoNuevo);

    }

    @Override
    public List<Curso> listarCursos() {
        List<Curso> listaCursos = repositorioCurso.listarCursos();

        if(listaCursos.size()<1){
            throw new listaNoEncontrada();
        }

        return listaCursos;
    }

    @Override
    public Curso buscarCursoPorId(Long idCurso) {
        Curso curso = repositorioCurso.buscarCursoPorId(idCurso);

        if(curso==null){
            throw new CursoNoEncontrado();
        }

        return curso;
    }

    @Override
    public void modificarCurso(DatosCurso datosCurso) {

        Curso curso = repositorioCurso.buscarCursoPorId(datosCurso.getIdCurso());
        if(curso==null){
            throw new CursoNoEncontrado();
        }
        curso.setCodigo(datosCurso.getCodigoCurso());
        curso.setNombre(datosCurso.getNombreCurso());

        repositorioCurso.guardarCurso(curso);

    }

    @Override
    public void eliminarCurso(Long idCurso) {
        Curso cursoAEliminar = repositorioCurso.buscarCursoPorId(idCurso);
        if(cursoAEliminar==null){
            throw new CursoNoEncontrado();
        }
        repositorioCurso.eliminarCurso(cursoAEliminar);
    }

    @Override
    public Boolean buscarCursoPorCodigo(String codigo) {


        if(repositorioCurso.buscarCursoPorCodigo(codigo)!=null){
            return true;
        }


        return false;
    }


}
