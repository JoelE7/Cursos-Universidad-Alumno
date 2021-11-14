package ar.edu.unlam.tallerweb1.ServiciosTest;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosAlumno;
import ar.edu.unlam.tallerweb1.Excepciones.AlumnoNoEncontrado;
import ar.edu.unlam.tallerweb1.Excepciones.MailExistente;
import ar.edu.unlam.tallerweb1.Excepciones.listaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioAlumnoTest {

    private RepositorioAlumno repositorioAlumno = mock(RepositorioAlumno.class);
    private ServicioAlumno servicioAlumno = new ServicioAlumnoImpl(repositorioAlumno);
    private DatosAlumno datosAlumno = new DatosAlumno("Joel", "joel@gmail.com", "112233445566",1L);
    private List<Alumno> listaAlumnos;
    private Alumno alumno;
    private Long idAlumno = 1L;

    @Test
    public void queSePuedanListarLosAlumnos(){


        givenQueExisteUnaListaDeAlumnos();

        whenListoLosAlumnos();

        thenMeDevuelveLaListaDeAlumnos();

    }

    @Test(expected = listaNoEncontrada.class)
    public void queSiNoExisteUnaListaDeAlumnosLanzeUnListaNoEncontradaException(){

        givenUnaListaDeAlumnosInexistente();

        whenListoLosAlumnos();

    }

    @Test(expected = MailExistente.class)
    public void queSiUnAlumnoSeRegistraConUnMailExistenteEntoncesLanzeUnMailExistenteException(){

        givenUnAlumnoSeRegistraConUnMailExistente();

        whenRegistroElAlumno();

    }

    @Test
    public void queSePuedaBuscarUnAlumno(){

        givenQueExisteUnAlumno();

        whenBuscoElAlumno();

        thenMeDevuelveElAlumnoBuscado();

    }

    @Test(expected = AlumnoNoEncontrado.class)
    public void queSiBuscoUnAlumnoInexistenteLanzeUnAlumnoNoEncontradoException(){

        givenUnAlumnoInexistente();

        whenBuscoElAlumno();

    }

    @Test
    public void queSePuedaModificarUnAlumno(){

        givenQueExisteUnAlumnoAModificar();

        whenModificoElAlumno();

        thenElAlumnoSeModificoCorrectamente();

    }
    @Test(expected = AlumnoNoEncontrado.class)
    public void queSiModificoUnAlumnoQueNoExisteLanzeUnAlumnoNoEncontradoException(){

        givenUnAlumnoInexistente();

        whenModificoElAlumno();

    }

    @Test(expected = AlumnoNoEncontrado.class)
    public void queSiEliminoUnAlumnoQueNoExisteLanzeUnAlumnoNoEncontradoException(){

        givenUnAlumnoInexistente();

        whenEliminoELAlumno();

    }

    private void whenEliminoELAlumno() {
        servicioAlumno.eliminarAlumno(idAlumno);
    }

    private void givenQueExisteUnAlumnoAModificar() {
        Alumno alumno = new Alumno();
        alumno.setEmail("flor@gmail.com");
        alumno.setTelefono("21231312312");
        alumno.setNombre("Florencia");
        when(repositorioAlumno.buscarAlumnoPorId(idAlumno)).thenReturn(alumno);

    }

    private void whenModificoElAlumno() {
        servicioAlumno.modificarAlumno(datosAlumno);
        alumno = servicioAlumno.buscarAlumnoPorId(idAlumno);
    }

    private void thenElAlumnoSeModificoCorrectamente() {
        assertThat(alumno.getNombre()).isEqualTo("Joel");
    }



    private void givenUnAlumnoInexistente() {
        when(repositorioAlumno.buscarAlumnoPorId(idAlumno)).thenReturn(null);
    }

    private void givenQueExisteUnAlumno() {
        Alumno alumno = new Alumno();
        alumno.setNombre("Joel");
        when(repositorioAlumno.buscarAlumnoPorId(1L)).thenReturn(alumno);
    }

    private void whenBuscoElAlumno() {
        alumno = servicioAlumno.buscarAlumnoPorId(1L);
    }

    private void thenMeDevuelveElAlumnoBuscado() {
        assertThat(alumno).isNotNull();
        assertThat(alumno.getNombre()).isEqualTo("Joel");
    }

    private void givenUnAlumnoSeRegistraConUnMailExistente() {
        when(repositorioAlumno.buscarAlumnoPorEmail(datosAlumno.getEmail())).thenReturn(new Alumno());
    }

    private void whenRegistroElAlumno() {
        servicioAlumno.registrarAlumno(datosAlumno);
    }

    private void givenUnaListaDeAlumnosInexistente() {
        when(servicioAlumno.listarAlumnos()).thenReturn(new ArrayList<>());
    }

    private void givenQueExisteUnaListaDeAlumnos() {
        List<Alumno> alumnoLista = new ArrayList<>();
        Alumno a1 = new Alumno();
        Alumno a2 = new Alumno();
        Alumno a3 = new Alumno();
        alumnoLista.add(a1);
        alumnoLista.add(a2);
        alumnoLista.add(a3);
        when(repositorioAlumno.listarAlumnos()).thenReturn(alumnoLista);
    }

    private void whenListoLosAlumnos() {
        listaAlumnos = servicioAlumno.listarAlumnos();
    }

    private void thenMeDevuelveLaListaDeAlumnos() {
        assertThat(listaAlumnos).isNotNull();
        assertThat(listaAlumnos).hasSize(3);
    }

}
