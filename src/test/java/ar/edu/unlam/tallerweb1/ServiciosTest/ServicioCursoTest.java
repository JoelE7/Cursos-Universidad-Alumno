package ar.edu.unlam.tallerweb1.ServiciosTest;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosCurso;
import ar.edu.unlam.tallerweb1.Excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioCursoImpl;
import com.sun.xml.bind.v2.model.core.ID;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioCursoTest {


    private RepositorioCurso repositorioCurso = mock(RepositorioCurso.class);
    private ServicioCurso servicioCurso = new ServicioCursoImpl(repositorioCurso);
    private List<Curso> listaCursos;
    private Curso curso;
    private final Long ID_CURSO = 1L;
    private final String CODIGO_CURSO = "777";
    private DatosCurso datosCurso = new DatosCurso(1L,"333","Java");

    @Test
    public void queSePuedanListarLosCursos(){


        givenQueExisteUnaListaDeCursos();

        whenListoLosCursos();

        thenMeDevuelveLaListaDeCursos();


    }

    @Test(expected = listaNoEncontrada.class)
    public void queSiNoExisteUnaListaDeAlumnosLanzeUnListaNoEncontradaException(){

        givenUnaListaDeCursosInexistente();

        whenListoLosCursos();

    }

    @Test(expected = CodigoCursoExistente.class)
    public void queSiUnCursoSeRegistraConUnCodigoExistenteEntoncesLanzeCodigoCursoExistenteException(){

        givenUnCursoSeRegistraConUnCodigoExistente();

        whenRegistroElCurso();

    }

    @Test
    public void queSePuedaBuscarUnCurso(){

        givenQueExisteCurso();

        whenBuscoElCurso();

        thenMeDevuelveElCursoBuscado();

    }

    @Test(expected = CursoNoEncontrado.class)
    public void queSiBuscoUnAlumnoInexistenteLanzeUnAlumnoNoEncontradoException(){

        givenUnCursoInexistente();

        whenBuscoElCurso();

    }

    @Test
    public void queSePuedaModificarUnCurso(){

        givenQueExisteUnCursoAModificar();

        whenModificoElCurso();

        thenElCursoSeModificoCorrectamente();

    }

    @Test(expected = CursoNoEncontrado.class)
    public void queSiModificoUnCursoQueNoExisteLanzeUnCursoNoEncontradoException(){

        givenUnCursoInexistente();

        whenModificoElCurso();

    }

    @Test(expected = CursoNoEncontrado.class)
    public void queSiEliminoUnCursoQueNoExisteLanzeUnCursoNoEncontradoException(){

        givenUnCursoInexistente();

        whenEliminoELCurso();

    }

    private void whenEliminoELCurso() {
        servicioCurso.eliminarCurso(ID_CURSO);
    }

    private void givenQueExisteUnCursoAModificar() {
        Curso curso = new Curso();
        curso.setCodigo("777");
        curso.setNombre("C#");
        when(repositorioCurso.buscarCursoPorId(ID_CURSO)).thenReturn(curso);
    }

    private void whenModificoElCurso() {
         servicioCurso.modificarCurso(datosCurso);
         curso = servicioCurso.buscarCursoPorId(ID_CURSO);
    }

    private void thenElCursoSeModificoCorrectamente() {
        assertThat(curso.getNombre()).isEqualTo("Java");
    }

    private void givenUnCursoInexistente() {
        when(servicioCurso.buscarCursoPorId(ID_CURSO)).thenReturn(null);
    }

    private void givenQueExisteCurso() {
        Curso curso = new Curso();
        curso.setNombre("Java");
        when(repositorioCurso.buscarCursoPorId(ID_CURSO)).thenReturn(curso);
    }

    private void whenBuscoElCurso() {
        curso = servicioCurso.buscarCursoPorId(ID_CURSO);
    }

    private void thenMeDevuelveElCursoBuscado() {
        assertThat(curso).isNotNull();
        assertThat(curso.getNombre()).isEqualTo("Java");
    }

    private void givenUnCursoSeRegistraConUnCodigoExistente() {
        when(repositorioCurso.buscarCursoPorCodigo(anyString())).thenReturn(new Curso());
    }

    private void whenRegistroElCurso() {
        servicioCurso.registrarCurso(datosCurso);
    }

    private void givenUnaListaDeCursosInexistente() {
        when(servicioCurso.listarCursos()).thenReturn(new ArrayList<>());
    }

    private void givenQueExisteUnaListaDeCursos() {
        List<Curso> cursoLista = new ArrayList<>();
        Curso c1 = new Curso();
        Curso c2 = new Curso();
        Curso c3 = new Curso();
        Alumno a2 = new Alumno();
        Alumno a3 = new Alumno();
        cursoLista.add(c1);
        cursoLista.add(c2);
        cursoLista.add(c3);
        when(repositorioCurso.listarCursos()).thenReturn(cursoLista);
    }

    private void whenListoLosCursos() {
        listaCursos = servicioCurso.listarCursos();
    }

    private void thenMeDevuelveLaListaDeCursos() {
        assertThat(listaCursos).isNotNull();
        assertThat(listaCursos).hasSize(3);
    }


}
