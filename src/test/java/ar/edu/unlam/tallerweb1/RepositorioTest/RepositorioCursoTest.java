package ar.edu.unlam.tallerweb1.RepositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCurso;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RepositorioCursoTest extends SpringTest {

    @Autowired
    private RepositorioCurso repositorioCurso;
    private List<Curso> listaCursos;
    private List<Alumno> listaAlumnos;
    private Curso curso;
    private final String CODIGO_CURSO = "777";
    private  Long ID_ALUMNO;
    private  Long ID_CURSO;

    @Test
    @Rollback
    @Transactional
    public void queSePuedanListarLosCursos(){

        List<Curso> listaAlumnoEsperada = givenQueExisteUnaListaDeCursos();

        List<Curso> listaAlumnoObtenido = whenListoLosCursos();

        thenMeDevuelveLaListaDeCursos(listaAlumnoEsperada,listaAlumnoObtenido);
        
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnCursoPorId(){

        Long idCurso = givenQueExisteUnCurso();

        whenBuscoElCurso(idCurso);

        thenMeDevuelveElCursoBuscado();

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaModificarUnCurso(){

        Long idCurso = givenQueExisteUnCurso();

        whenModificoElCurso(idCurso);

        thenSeModificanLosaDatosDelCurso();

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnCursoPorCodigo(){

        givenQueExisteUnCurso();

        whenBuscoElCursoPorCodigo();

        thenMeDevuelveElCursoBuscado();
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaEliminarUnCurso(){

        Long idCurso = givenQueExisteUnCurso();

        whenEliminoElCurso(idCurso);

        thenElCursoNoExiste();

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnAlumnoEnUnCurso(){

        givenQueExisteUnAlumnoYUnCurso();

        whenBuscoElAlumno();

        thenMeDevuelveELAlumnoBuscado();


    }

    @Test
    @Rollback
    @Transactional
    public void queUnAlumnoPuedaAgregarseAUnCurso(){

        Long idCurso = givenQueExisteUnCursoYUnAlumno();

        whenElAlumnoQuiereAgregarseAlCurso(idCurso);

        thenElAlumnoSeAgregaExitosamente();

    }

    private Long givenQueExisteUnCursoYUnAlumno() {
        Alumno a1 = new Alumno();
        Alumno a2 = new Alumno();
        //setteando los atributos a los alumnos
        a1.setEmail("joel@gmail.com");
        a1.setNombre("Joel");
        a1.setTelefono("11332");
        a2.setEmail("flor@gmail.com");
        a2.setNombre("flor");
        a2.setTelefono("113323123");
        //Creando curso
        Curso c1 = new Curso();
        c1.setNombre("Java");
        c1.setCodigo("333");
        //guardando datos
        c1.añadirAlumnoAlCurso(a1);
        c1.añadirAlumnoAlCurso(a2);
        session().save(a1);
        session().save(a2);
        session().save(c1);
        return c1.getId();
    }

    private void whenElAlumnoQuiereAgregarseAlCurso(Long idAlumno) {
        listaCursos =   repositorioCurso.listarCursos();
        listaAlumnos = listaCursos.get(0).getListaAlumnos();

    }

    private void thenElAlumnoSeAgregaExitosamente() {
        assertThat(listaCursos).isNotNull();
        assertThat(listaCursos).hasSize(1);
        assertThat(listaAlumnos).isNotNull();
        assertThat(listaAlumnos).hasSize(2);
    }


    private void givenQueExisteUnAlumnoYUnCurso() {
        Alumno a1 = new Alumno();
        a1.setNombre("Joel");
        Curso c1 = new Curso();
        c1.añadirAlumnoAlCurso(a1);
        ID_ALUMNO = (Long) session().save(a1);
        ID_CURSO = (Long) session().save(c1);

    }

    private void whenBuscoElAlumno() {
       curso = repositorioCurso.buscarAlumnoEnUnCurso(ID_ALUMNO,ID_CURSO);
    }

    private void thenMeDevuelveELAlumnoBuscado() {
        assertThat(curso).isNotNull();
        assertThat(curso.getListaAlumnos().get(0).getNombre()).isEqualTo("Joel");
    }

    private void whenEliminoElCurso(Long idCurso) {
        curso = repositorioCurso.buscarCursoPorId(idCurso);
        repositorioCurso.eliminarCurso(curso);
        curso = repositorioCurso.buscarCursoPorId(idCurso);
    }

    private void thenElCursoNoExiste() {
        assertThat(curso).isNull();
    }

    private void whenBuscoElCursoPorCodigo() {
        curso = repositorioCurso.buscarCursoPorCodigo(CODIGO_CURSO);
    }

    private void whenModificoElCurso(Long idCurso) {
        curso = repositorioCurso.buscarCursoPorId(idCurso);
        curso.setNombre("PHP");
        curso.setCodigo("888");
        repositorioCurso.guardarCurso(curso);
        curso = repositorioCurso.buscarCursoPorId(idCurso);
    }

    private void thenSeModificanLosaDatosDelCurso() {
        assertThat(curso.getNombre()).isEqualTo("PHP");
        assertThat(curso.getCodigo()).isEqualTo("888");
    }

    private Long givenQueExisteUnCurso() {
        Curso curso = new Curso();
        curso.setNombre("Java");
        curso.setCodigo("777");
        return (Long) session().save(curso);
    }

    private void whenBuscoElCurso(Long idCurso) {
        curso = repositorioCurso.buscarCursoPorId(idCurso);
    }

    private void thenMeDevuelveElCursoBuscado() {
    }

    private List<Curso> givenQueExisteUnaListaDeCursos() {
        List<Curso>cursosLista = new ArrayList<>();
        Curso c1 = new Curso();
        Curso c2 = new Curso();
        Curso c3 = new Curso();
        session().save(c1);
        session().save(c2);
        session().save(c3);
        cursosLista.add(c1);
        cursosLista.add(c2);
        cursosLista.add(c3);
        return cursosLista;
    }

    private List<Curso> whenListoLosCursos() {
        return listaCursos = repositorioCurso.listarCursos();
    }

    private void thenMeDevuelveLaListaDeCursos(List<Curso> listaAlumnoEsperada, List<Curso> listaAlumnoObtenido) {
        assertThat(listaAlumnoObtenido).isEqualTo(listaAlumnoEsperada);
        assertThat(listaAlumnoObtenido).hasSize(3);
    }

}
