package ar.edu.unlam.tallerweb1.RepositorioTest;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosAlumno;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlumno;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RepositorioAlumnoTest extends SpringTest {

    @Autowired
    private RepositorioAlumno repositorioAlumno;
    private Alumno alumno;
    private DatosAlumno datosAlumno = new DatosAlumno("Joel", "joel@gmail.com", "112233445566",1L);
    private final String EMAIL = "joel@gmail.com";

    @Test
    @Rollback
    @Transactional
    public void queSePuedaListarLosAlumnos(){

        List<Alumno> listaAlumnoEsperada = givenQueExisteUnaListaDeAlumnos();

        List<Alumno> listaAlumnoObtenido = whenListoLosAlumnos();

        thenMeDevuelveLaListaDeAlumnos(listaAlumnoEsperada,listaAlumnoObtenido);

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnAlumnoPorId(){

        Long idAlumno = givenQueExisteUnAlumno();

        whenBuscoElAlumno(idAlumno);

        thenMeDevuelveElAlumnoBuscado();

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaModificarUnAlumno(){

        Long idAlumno = givenQueExisteUnAlumno();

        whenModificoElAlumno(idAlumno);

        thenSeModificanLosaDatosDelAlumno();

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaBuscarUnAlumnoPorMail(){

        givenQueExisteUnAlumno();

        whenBuscoElAlumnoPorEmail();

        thenMeDevuelveElAlumnoBuscado();
    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedaEliminarUnAlumno(){

        Long idAlumno = givenQueExisteUnAlumno();

        whenEliminoElAlumno(idAlumno);

        thenElAlumnoNoExiste();

    }

    private void whenBuscoElAlumnoPorEmail() {
        alumno = repositorioAlumno.buscarAlumnoPorEmail(EMAIL);
    }

    private void whenEliminoElAlumno(Long idAlumno) {
        alumno = repositorioAlumno.buscarAlumnoPorId(idAlumno);
        repositorioAlumno.eliminarAlumno(alumno);
        alumno = repositorioAlumno.buscarAlumnoPorId(idAlumno);
    }

    private void thenElAlumnoNoExiste() {
        assertThat(alumno).isNull();
    }

    private void whenModificoElAlumno(Long idAlumno) {
        alumno = repositorioAlumno.buscarAlumnoPorId(idAlumno);
        alumno.setNombre("flor");
        alumno.setTelefono("119988776655");
        alumno.setEmail("flor@gmail.com");
        repositorioAlumno.guardarAlumno(alumno);
        alumno = repositorioAlumno.buscarAlumnoPorId(idAlumno);
    }

    private void thenSeModificanLosaDatosDelAlumno() {
        assertThat(alumno.getNombre()).isEqualTo("flor");
        assertThat(alumno.getTelefono()).isEqualTo("119988776655");
        assertThat(alumno.getEmail()).isEqualTo("flor@gmail.com");
    }

    private Long givenQueExisteUnAlumno() {
        Alumno alumno = new Alumno();
        alumno.setNombre("Joel");
        alumno.setTelefono("113322332");
        alumno.setEmail("joel@gmail.com");
        return (Long) session().save(alumno);
    }

    private void whenBuscoElAlumno(Long idAlumno) {
        alumno = repositorioAlumno.buscarAlumnoPorId(idAlumno);
    }

    private void thenMeDevuelveElAlumnoBuscado() {
        assertThat(alumno).isNotNull();
    }

    private List<Alumno> givenQueExisteUnaListaDeAlumnos() {
        List<Alumno>alumnosLista = new ArrayList<>();
        Alumno a1 = new Alumno();
        Alumno a2 = new Alumno();
        Alumno a3 = new Alumno();
        session().save(a1);
        session().save(a2);
        session().save(a3);
        alumnosLista.add(a1);
        alumnosLista.add(a2);
        alumnosLista.add(a3);
        return alumnosLista;
    }

    private List<Alumno> whenListoLosAlumnos() {
        return repositorioAlumno.listarAlumnos();
    }

    private void thenMeDevuelveLaListaDeAlumnos(List<Alumno> listaAlumnoEsperada, List<Alumno> listaAlumnoObtenido) {
        assertThat(listaAlumnoObtenido).isEqualTo(listaAlumnoEsperada);
        assertThat(listaAlumnoObtenido).hasSize(3);
    }


}
