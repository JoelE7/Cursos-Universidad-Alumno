package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCurso {

    private ServicioCurso servicioCurso;
    private ModelAndView mav;

    @Autowired
    public ControladorCurso(ServicioCurso servicioCurso) {
        this.servicioCurso = servicioCurso;
    }


}
