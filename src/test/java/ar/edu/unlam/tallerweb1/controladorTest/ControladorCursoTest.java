package ar.edu.unlam.tallerweb1.controladorTest;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosCurso;
import ar.edu.unlam.tallerweb1.Excepciones.AlumnoExistente;
import ar.edu.unlam.tallerweb1.Excepciones.AlumnoNoEncontrado;
import ar.edu.unlam.tallerweb1.Excepciones.CodigoCursoExistente;
import ar.edu.unlam.tallerweb1.Excepciones.CursoNoEncontrado;
import ar.edu.unlam.tallerweb1.controladores.ControladorCurso;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import com.sun.xml.bind.v2.model.core.ID;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorCursoTest {

    private ModelAndView mav;
    private ServicioCurso servicioCurso;
    private ServicioAlumno servicioAlumno;
    private ControladorCurso controladorCurso;
    DatosCurso datosCurso = new DatosCurso(1L,"333","Java");
    DatosCurso datosCursoIncompleto = new DatosCurso(1L,"333","");
    private final Long ID_CURSO = 1L;
    private final Long ID_ALUMNO = 2L;

    @Before
    public void init() {
        servicioCurso = mock(ServicioCurso.class);
        servicioAlumno = mock(ServicioAlumno.class);
        controladorCurso = new ControladorCurso(servicioCurso,servicioAlumno);
    }

    @Test
    public void queSePuedaRegistrarUnCurso(){
        
        givenUnCursoSinRegistrar();
        
        whenRegistroElCurso();
        
        thenElCursoSeRegistraConExito();

    }

    @Test
    public void queUnCursoNoSePuedaRegistrarSiIngresaMalUnCampo() {

        givenUnCursoSinRegistrarIncompleto();

        whenRegistroElCursoIncompleto();

        thenFallaElRegistroPorFaltaDeDatos();

    }

    @Test
    public void queSePuedanListarLosCursos(){

        givenQueExisteUnaListaDeCurso();

        whenListoLosCursos();

        thenMeDevuelveLaListaDeCursos();

    }

    @Test
    public void queUnCursoNoSePuedaRegistrarConElMismoCodigoDeCurso2Veces(){

        givenUnCursoRegistradoConCodigoExistente();

        whenRegistroElCurso();

        thenElRegistroFallaPorCodigoDeCursoExistente();
    }

    @Test
    public void queSePuedaBuscarUnCurso(){

        givenUnCursoExistente();

        whenBuscoElCurso();

        thenMeDevuelveElCurso();

    }

    @Test
    public void queSiBuscoUnCursoInexistenteMeMandeElMsj(){

        givenUnCursoInexistente();

        whenBuscoElCurso();

        thenMeDiceQueElCursoBuscadoNoExiste();

    }

    @Test
    public void queSePuedaModificarUnCurso(){

        givenUnCursoExistente();

        whenQuieroModificarlo();

        thenElCursoEsModificado();

    }

    @Test
    public void queSePuedaEliminarUnCurso(){

        givenUnCursoExistente();

        whenQuieroEliminarlo();

        thenElCursoEsEliminado();


    }

    @Test
    public void queSiQuiereEliminarUnCursoQueNoExisteMeMandeElMsj(){

        givenUnCursoInexistente();

        whenQuieroEliminarlo();

        thenMeMandaElMsjDeQueNoExiste("Curso inexistente, no se puede eliminar");

    }

    @Test
    public void queSiQuieroModificarUnAlumnoQueNoExisteMeMandeElMsj(){

        givenUnCursoInexistente();

        whenQuieroModificarlo();

        thenMeMandaElMsjDeQueNoExiste("Curso inexistente, no se puede modificar");

    }

    @Test
    public void queSePuedaAgregarUnAlumnoAUnCurso(){

        givenQUeExisteUnAlumnoYUnCurso();

        whenAgregoElAlumnoAlCurso();

        thenElAlumnoSeAgrega();

    }

    @Test
    public void queAlAgregarUnAlumnoAUnCursoYNoExisteMandeUnMsj(){

        givenQueUnAlumnoNoExiste();

        whenAgregoElAlumnoAlCurso();

        thenMeMandaElMsjDeQueNoExiste("Alumno inexistente, no se puede agregar al curso");

    }

    @Test
    public void queAlQuererAgregarUnAlumnoAUnCursoInexistenteMandeUnMsj(){

        givenQueUnCursoNoExiste();

        whenAgregoElAlumnoAlCurso();

        thenMeMandaElMsjDeQueNoExiste("Curso inexistente, no se puede agregar ningún alumno");

    }

    @Test
    public void queAlAgregarUnAlumnoAUnCursoQueYaExisteMandeUnMsj(){

        givenQueUnAlumnoYaExisteDentroDeUnCurso();

        whenAgregoElAlumnoAlCurso();

        thenMeMandaElMsjDeQueNoExiste("Este alumno ya se encuentra inscripto en este curso");
    }

    @Test
    public void queSePuedaEliminarUnAlumnoDeUnCurso(){

        givenQUeExisteUnAlumnoYUnCurso();

        whenQuieroEliminarElAlumnoDelCurso();

        thenElAlumnoSeEliminaDelCurso();



    }

    private void whenQuieroEliminarElAlumnoDelCurso() {
        mav = controladorCurso.eliminarAlumnoDeUnCurso(ID_CURSO,ID_ALUMNO);
    }

    private void thenElAlumnoSeEliminaDelCurso() {
        assertThat(mav.getViewName()).isEqualTo("redirect:agregar-alumno?="+ID_CURSO);
    }

    private void givenQueUnAlumnoYaExisteDentroDeUnCurso() {
        doThrow(AlumnoExistente.class).when(servicioCurso).agregarAlumnoAUnCurso(anyLong(),anyLong());
    }

    private void givenQueUnCursoNoExiste() {
        doThrow(CursoNoEncontrado.class).when(servicioCurso).agregarAlumnoAUnCurso(anyLong(),anyLong());
    }

    private void givenQueUnAlumnoNoExiste() {
        doThrow(AlumnoNoEncontrado.class).when(servicioCurso).agregarAlumnoAUnCurso(anyLong(),anyLong());
    }

    private void givenQUeExisteUnAlumnoYUnCurso() {
        when(servicioCurso.buscarCursoPorId(anyLong())).thenReturn(new Curso());
        when(servicioAlumno.buscarAlumnoPorId(anyLong())).thenReturn(new Alumno());

    }

    private void whenAgregoElAlumnoAlCurso() {
        mav = controladorCurso.agregarAlumnoAUnCursoLista(ID_CURSO,ID_ALUMNO);
    }

    private void thenElAlumnoSeAgrega() {
        assertThat(mav.getViewName()).isEqualTo("redirect:lista-cursos");
    }

    private void thenMeMandaElMsjDeQueNoExiste(String mensaje) {
        assertThat(mav.getViewName()).isEqualTo("error");
        assertThat(mav.getModel().get("mensaje")).isEqualTo(mensaje);
    }

    private void whenQuieroEliminarlo() {
        mav = controladorCurso.eliminarCurso(ID_CURSO);
    }

    private void thenElCursoEsEliminado() {
        assertThat(mav.getViewName()).isEqualTo("redirect:listar-cursos");
    }

    private void whenQuieroModificarlo() {
        mav = controladorCurso.modificarCursoLista(datosCurso);
    }

    private void thenElCursoEsModificado() {
        assertThat(mav.getViewName()).isEqualTo("redirect:listar-cursos");
    }

    private void givenUnCursoInexistente() {
        when(servicioCurso.buscarCursoPorId(ID_CURSO)).thenThrow(CursoNoEncontrado.class);
        doThrow(CursoNoEncontrado.class).when(servicioCurso).eliminarCurso(ID_CURSO);
        doThrow(CursoNoEncontrado.class).when(servicioCurso).modificarCurso(datosCurso);
    }

    private void thenMeDiceQueElCursoBuscadoNoExiste() {
        assertThat(mav.getViewName()).isEqualTo("error");
        assertThat(mav.getModel().get("mensaje")).isEqualTo("Curso inexistente, no se pudo encontrar el curso");
    }

    private void givenUnCursoExistente() {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNombre("Joel");
        curso.setNombre("JavaScript");
        when(servicioCurso.buscarCursoPorId(ID_CURSO)).thenReturn(curso);
    }

    private void whenBuscoElCurso() {
        mav = controladorCurso.buscarCursoPorId(ID_CURSO);
    }

    private void thenMeDevuelveElCurso() {
        assertThat(mav.getViewName()).isEqualTo("curso-detalle");
        assertThat(mav.getModel().get("cursoBuscado")).isEqualTo(servicioCurso.buscarCursoPorId(ID_CURSO));
    }

    private void givenUnCursoRegistradoConCodigoExistente() {
        doThrow(CodigoCursoExistente.class).when(servicioCurso).registrarCurso(datosCurso);
    }

    private void thenElRegistroFallaPorCodigoDeCursoExistente() {
        assertThat(mav.getViewName()).isEqualTo("formulario-curso");
        assertThat(mav.getModel().get("codigoExistente")).isEqualTo("Este código de curso, ya se encuentra registrado en el sistema");
    }

    private void givenQueExisteUnaListaDeCurso() {
        List<Curso> listaCursos = new ArrayList<>();
        Curso c1 = new Curso();
        Curso c2 = new Curso();
        Curso c3 = new Curso();
        listaCursos.add(c1);
        listaCursos.add(c2);
        listaCursos.add(c3);
        when(servicioCurso.listarCursos()).thenReturn(listaCursos);
    }

    private void whenListoLosCursos() {
        mav = controladorCurso.listarCursos();
    }

    private void thenMeDevuelveLaListaDeCursos() {
        assertThat(mav.getViewName()).isEqualTo("lista-cursos");
        assertThat(mav.getModel().get("listaCursos")).isEqualTo(servicioCurso.listarCursos());
    }

    private void givenUnCursoSinRegistrarIncompleto() {
    }

    private void whenRegistroElCursoIncompleto() {
        mav = controladorCurso.procesarCurso(datosCursoIncompleto);
    }

    private void thenFallaElRegistroPorFaltaDeDatos() {
        Map<String, String> errores = (Map<String, String>) mav.getModel().get("erroresValidacion");
        assertThat(mav.getViewName()).isEqualTo("formulario-curso");
        assertThat(errores.get("nombreError")).isEqualTo("El nombre del curso no puede quedar vacio");
    }

    private void givenUnCursoSinRegistrar() {
    }

    private void whenRegistroElCurso() {
        mav = controladorCurso.procesarCurso(datosCurso);
    }

    private void thenElCursoSeRegistraConExito() {
        assertThat(mav.getViewName()).isEqualTo("redirect:registro-curso-exitoso");
    }

}
