package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorAlumno {

    private ServicioAlumno servicioAlumno;

    @Autowired
    public ControladorAlumno(ServicioAlumno servicioAlumno) {
        this.servicioAlumno = servicioAlumno;
    }

    @RequestMapping("formulario-alumno")
    public ModelAndView formularioAlumno(){



        return new ModelAndView("formulario-alumno");
    }


    @RequestMapping("registrar-alumno")
    public ModelAndView registrarAlumno(DatosAlumno datosAlumno) {

        ModelMap




    }
}
