package ar.edu.unlam.tallerweb1.controladorTest;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosAlumno;
import ar.edu.unlam.tallerweb1.Excepciones.AlumnoNoEncontrado;
import ar.edu.unlam.tallerweb1.Excepciones.MailExistente;
import ar.edu.unlam.tallerweb1.controladores.ControladorAlumno;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorAlumnoTest {

    private ModelAndView mav;
    private ServicioAlumno servicioAlumno;
    private ControladorAlumno controladorAlumno;
    private DatosAlumno datosAlumno = new DatosAlumno("Joel", "joel@gmail.com", "112233445566",1L);
    private DatosAlumno datosAlumnoIncompleto = new DatosAlumno("Joel2", "joel2@gmail.com", "",null);

    @Before
    public void init() {
        servicioAlumno = mock(ServicioAlumno.class);
        controladorAlumno = new ControladorAlumno(servicioAlumno);
    }

    @Test
    public void queSePuedaRegistrarUnAlumno() {

        givenUnAlumnoSinRegistrar();

        whenRegistroAlAlumno();

        thenElAlumnoSeRegistraConExito();

    }

    @Test
    public void queSePuedaListarLosAlumnos() {

        givenQueExisteUnaListaDeAlumnos();

        whenListoLosAlumnos();

        thenMeDevuelveLaListaDeAlumnos();
    }

    @Test
    public void queUnAlumnoNoSePuedaRegistrarSiIngresaMalUnCampo() {

        givenUnAlumnoSinRegistrarIncompleto();

        whenRegistroAlAlumnoIncompleto();

        thenFallaElRegistroPorFaltaDeDatos();

    }

    @Test
    public void queUnAlumnoNoSePuedaRegistrarConElMismoMail2Veces(){

        givenUnAlumnoRegistradoConMailExistente();

        whenRegistroAlAlumno();

        thenElRegistroFallaPorMailExistente();
    }

    @Test
    public void queSePuedaBuscarUnAlumno(){

        givenUnAlumnoExistente();

        whenBuscoElAlumno();

        thenMeDevuelveElAlumno();

    }

    @Test
    public void queSiBuscoUnAlumnoInexistenteMeMandeElMsj(){

        givenUnAlumnoInexistente();

        whenBuscoElAlumno();

        thenMeDiceQueElAlumnoBuscadoNoExiste();

    }

    @Test
    public void queSePuedaModificarUnAlumno(){

        givenUnAlumnoExistente();

        whenQuieroModificarlo();

        thenElAlumnoEsModificado();

    }
    
    @Test
    public void queSePuedaEliminarUnAlumno(){
        
        givenUnAlumnoExistente();
        
        whenQuieroEliminarlo();
        
        thenElAlumnoEsEliminado();
    }

    @Test
    public void queSiQuiereEliminarUnAlumnoQueNoExisteMeMandeElMsj(){

        givenUnAlumnoInexistente();

        whenQuieroEliminarlo();

        thenMeMandaElMsjDeQueNoExiste("Alumno inexistente, no se puede eliminar");

    }

    @Test
    public void queSiQuieroModificarUnAlumnoQueNoExisteMeMandeElMsj(){

        givenUnAlumnoInexistente();

        whenQuieroModificarlo();

        thenMeMandaElMsjDeQueNoExiste("Alumno inexistente, no se puede modificar");

    }

    private void givenUnAlumnoInexistente() {
        when(servicioAlumno.buscarAlumnoPorId(1L)).thenThrow(AlumnoNoEncontrado.class);
        doThrow(AlumnoNoEncontrado.class).when(servicioAlumno).eliminarAlumno(1L);
        doThrow(AlumnoNoEncontrado.class).when(servicioAlumno).modificarAlumno(datosAlumno);
    }

    private void thenMeMandaElMsjDeQueNoExiste(String mensaje) {
        assertThat(mav.getViewName()).isEqualTo("error");
        assertThat(mav.getModel().get("alumnoNoEncontrado")).isEqualTo(mensaje);
    }


    private void whenQuieroEliminarlo() {
        mav = controladorAlumno.eliminarAlumno(1L);
    }

    private void thenElAlumnoEsEliminado() {
        assertThat(mav.getViewName()).isEqualTo("redirect:listar-alumnos");
    }


    private void whenBuscoElAlumno() {
        mav = controladorAlumno.buscarAlumnoPorId(1L);
    }

    private void thenMeDevuelveElAlumno() {
        assertThat(mav.getViewName()).isEqualTo("alumno-detalle");
        assertThat(mav.getModel().get("alumnoBuscado")).isEqualTo(servicioAlumno.buscarAlumnoPorId(1L));
    }

    private void givenUnAlumnoExistente() {
        Alumno alumno = new Alumno();
        alumno.setId(1L);
        alumno.setEmail("Joel@gmail.com");
        alumno.setNombre("Joel");
        alumno.setTelefono("112233445577");
        when(servicioAlumno.buscarAlumnoPorId(1L)).thenReturn(alumno);
    }

    private void whenQuieroModificarlo() {
       mav = controladorAlumno.modificarAlumnoLista(datosAlumno);
    }

    private void thenElAlumnoEsModificado() {
        assertThat(mav.getViewName()).isEqualTo("redirect:listar-alumnos");
    }

    private void givenUnAlumnoRegistradoConMailExistente() {
        doThrow(MailExistente.class).when(servicioAlumno).registrarAlumno(datosAlumno);
    }

    private void thenElRegistroFallaPorMailExistente() {
        assertThat(mav.getViewName()).isEqualTo("formulario-alumno");
        assertThat(mav.getModel().get("mailExistente")).isEqualTo("Este email, ya se encuentra registrado en el sistema");
    }

    private void givenUnAlumnoSinRegistrarIncompleto() {
    }

    private void whenRegistroAlAlumnoIncompleto() {
        mav = controladorAlumno.procesarRegistroAlumno(datosAlumnoIncompleto);
    }

    private void thenFallaElRegistroPorFaltaDeDatos() {
        Map<String, String> errores = (Map<String, String>) mav.getModel().get("erroresValidacion");
        assertThat(mav.getViewName()).isEqualTo("formulario-alumno");
        assertThat(errores.get("telefonoError")).isEqualTo("Por favor ingresa un numero de telefono");
    }


    private void givenQueExisteUnaListaDeAlumnos() {
        List<Alumno> listaAlumnos = new ArrayList<>();
        Alumno a1 = new Alumno();
        Alumno a2 = new Alumno();
        Alumno a3 = new Alumno();
        listaAlumnos.add(a1);
        listaAlumnos.add(a2);
        listaAlumnos.add(a3);
        when(servicioAlumno.listarAlumnos()).thenReturn(listaAlumnos);
    }

    private void whenListoLosAlumnos() {
        mav = controladorAlumno.listarAlumnos();
    }

    private void thenMeDevuelveLaListaDeAlumnos() {
        assertThat(mav.getViewName()).isEqualTo("lista-alumnos");
        assertThat(mav.getModel().get("listaAlumnos")).isEqualTo(servicioAlumno.listarAlumnos());
    }

    private void givenUnAlumnoSinRegistrar() {
    }

    private void whenRegistroAlAlumno() {
        mav = controladorAlumno.procesarRegistroAlumno(datosAlumno);
    }

    private void thenElAlumnoSeRegistraConExito() {
        assertThat(mav.getViewName()).isEqualTo("redirect:registro-exitoso");
    }

    private void thenMeDiceQueElAlumnoBuscadoNoExiste() {
        assertThat(mav.getViewName()).isEqualTo("error");
        assertThat(mav.getModel().get("alumnoNoEncontrado")).isEqualTo("Alumno inexistente, no se pudo encontrar el alumno");

    }
}
