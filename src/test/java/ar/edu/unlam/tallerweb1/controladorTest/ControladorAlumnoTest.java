package ar.edu.unlam.tallerweb1.controladorTest;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosAlumno;
import ar.edu.unlam.tallerweb1.controladores.ControladorAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorAlumnoTest {

    private ModelAndView mav;
    private ServicioAlumno servicioAlumno;
    private ControladorAlumno controladorAlumno;
    private DatosAlumno datosAlumno;

    @Before
    public void init() {
        servicioAlumno = mock(ServicioAlumno.class);
        controladorAlumno = new ControladorAlumno(servicioAlumno);
    }

    @Test
    public void queSePuedaRegistrarUnAlumno(){

        givenUnAlumnoSinRegistrar();

        whenRegistroAlAlumno();

        thenElAlumnoSeRegistraConExito();

    }

    private void givenUnAlumnoSinRegistrar() {
    }

    private void whenRegistroAlAlumno() {
        mav = controladorAlumno.registrarAlumno(datosAlumno);
    }

    private void thenElAlumnoSeRegistraConExito() {
    }


}
