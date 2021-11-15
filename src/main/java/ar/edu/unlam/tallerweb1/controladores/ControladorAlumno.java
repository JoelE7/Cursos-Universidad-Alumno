package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosAlumno;
import ar.edu.unlam.tallerweb1.Excepciones.AlumnoNoEncontrado;
import ar.edu.unlam.tallerweb1.Excepciones.MailExistente;
import ar.edu.unlam.tallerweb1.Excepciones.listaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorAlumno {

    private ServicioAlumno servicioAlumno;
    private ModelAndView mav;

    @Autowired
    public ControladorAlumno(ServicioAlumno servicioAlumno) {
        this.servicioAlumno = servicioAlumno;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:formulario-alumno");
    }

    @RequestMapping("formulario-alumno")
    public ModelAndView formularioAlumno() {
        mav = procesarRegistroFormulario(null);
        return mav;
    }

    @RequestMapping("registro-exitoso")
    public ModelAndView registroAlumnoExitoso() {
        mav = procesarRegistroFormulario("El alumno ha sido registrado con exito");
        return mav;

    }

    @RequestMapping("registro-validacion")
    public ModelAndView procesarRegistroAlumno(@ModelAttribute("alumnoRegistro") DatosAlumno alumnoRegistro) {

        ModelMap model = new ModelMap();

        Map<String, String> errores = validarRegistroAlumnos(alumnoRegistro);

        model.put("emailDefault", alumnoRegistro.getEmail());
        model.put("nombreDefault", alumnoRegistro.getNombre());
        model.put("telefonoDefault", alumnoRegistro.getTelefono());

        if (errores.size() > 0) {
            model.put("erroresValidacion", errores);
            return new ModelAndView("formulario-alumno", model);
        }

        try {
            servicioAlumno.registrarAlumno(alumnoRegistro);
            return new ModelAndView("redirect:registro-exitoso");
        } catch (MailExistente e) {
            model.put("mailExistente", "Este email, ya se encuentra registrado en el sistema");
        }
        return new ModelAndView("formulario-alumno", model);
    }

    @RequestMapping("alumno-detalle")
    public ModelAndView buscarAlumnoPorId(@RequestParam Long idAlumno) {
        ModelMap model = new ModelMap();

        try {
            Alumno alumnoBuscado = servicioAlumno.buscarAlumnoPorId(idAlumno);
            model.put("alumnoBuscado", alumnoBuscado);
        } catch (AlumnoNoEncontrado e) {
            return procesarErrores("Alumno inexistente, no se pudo encontrar el alumno");
        }
        return new ModelAndView("alumno-detalle", model);
    }

    @RequestMapping("formulario-modificar-alumno-lista")
    public ModelAndView formularioModificarAlumnoLista(@RequestParam Long idAlumno) {
        return prepararFormularioModificarAlumno(null,idAlumno);
    }

    @RequestMapping("formulario-modificar-alumno-detalle")
    public ModelAndView formularioModificarAlumnoDetalle(@RequestParam Long idAlumno,Model model) {
        return prepararFormularioModificarAlumno("detalle",idAlumno);
    }

    @RequestMapping("modificar-alumno-lista")
    public ModelAndView modificarAlumnoLista(@ModelAttribute DatosAlumno datosAlumno) {

            return modificarAlumnoRedirect("redirect:listar-alumnos",datosAlumno);
    }
    @RequestMapping("modificar-alumno-detalle")
    public ModelAndView modificarAlumnoDetalle(@ModelAttribute DatosAlumno datosAlumno) {

        return modificarAlumnoRedirect("redirect:alumno-detalle?idAlumno=" + datosAlumno.getIdAlumno(),datosAlumno);
    }

    @RequestMapping("eliminar-alumno")
    public ModelAndView eliminarAlumno(@RequestParam Long idAlumno) {

        try {
            servicioAlumno.eliminarAlumno(idAlumno);
            mav = new ModelAndView("redirect:listar-alumnos");
        } catch (AlumnoNoEncontrado e) {
            mav = procesarErrores("Alumno inexistente, no se puede eliminar");
        }
        return mav;
    }

    @RequestMapping("listar-alumnos")
    public ModelAndView listarAlumnos() {

        ModelMap model = new ModelMap();

        try {
            List<Alumno> listaAlumnos = servicioAlumno.listarAlumnos();
            model.put("listaAlumnos", listaAlumnos);
        } catch (listaNoEncontrada e) {
            model.put("listaAlumnosVacia", "No hay alumnos que mostrar");
        }
        return new ModelAndView("lista-alumnos", model);
    }

    private Map<String, String> validarRegistroAlumnos(DatosAlumno alumno) {
        Map<String, String> errores = new HashMap<>();

        if (alumno.getEmail() == "" || alumno.getEmail() == null) {
            errores.put("emailError", "El email es incorrecto");
        } else if (alumno.getNombre() == "" || alumno.getEmail() == null) {
            errores.put("nombreError", "Tu nombre no puede quedar vacio");
        } else if (alumno.getTelefono() == "" || alumno.getTelefono() == null) {
            errores.put("telefonoError", "Por favor ingresa un numero de telefono");
        }

        return errores;
    }

    private ModelAndView procesarRegistroFormulario(String mensaje) {
        ModelMap model = new ModelMap();

        if (mensaje != null) {
            model.put("mensajeRegistro", mensaje);
        }
        DatosAlumno alumno = new DatosAlumno();
        model.put("alumnoRegistro", alumno);
        return new ModelAndView("formulario-alumno", model);

    }

    private ModelAndView procesarErrores(String mensaje) {

        ModelMap model = new ModelMap();
        model.put("mensaje", mensaje);

        return new ModelAndView("error", model);
    }

    private ModelAndView modificarAlumnoRedirect(String vista, DatosAlumno datosAlumno) {

        try {
            servicioAlumno.modificarAlumno(datosAlumno);
        } catch (AlumnoNoEncontrado e) {
            mav = procesarErrores("Alumno inexistente, no se puede modificar");
            return mav;
        }

        return new ModelAndView(vista);
    }

    private ModelAndView prepararFormularioModificarAlumno (String redirigir,Long idAlumno) {
        ModelMap model = new ModelMap();

        try {
            Alumno alumnoBuscado = servicioAlumno.buscarAlumnoPorId(idAlumno);
            DatosAlumno datosAlumno = new DatosAlumno();
            model.put("datosAModificar", datosAlumno);
            model.put("alumnoModificar", alumnoBuscado);
            if(redirigir!=null){
                model.put("redirect",redirigir);
            }
        } catch (AlumnoNoEncontrado e) {
            mav = procesarErrores("Alumno inexistente, no se puede modificar");
            return mav;
        }
        return new ModelAndView("formulario-modificar-alumno", model);
    }
}


