package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosCurso;
import ar.edu.unlam.tallerweb1.Excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorCurso {

    private ServicioCurso servicioCurso;
    private ServicioAlumno servicioAlumno;
    private ModelAndView mav;

    @Autowired
    public ControladorCurso(ServicioCurso servicioCurso, ServicioAlumno servicioAlumno) {
        this.servicioCurso = servicioCurso;
        this.servicioAlumno = servicioAlumno;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //metodos del registro de cursos
    @RequestMapping("formulario-curso")
    public ModelAndView formularioCurso() {
        mav = procesarRegistroCurso(null);
        return mav;
    }

    @RequestMapping("registro-curso-exitoso")
    public ModelAndView registroCursoExitoso() {
        mav = procesarRegistroCurso("El curso ha sido registrado con exito");
        return mav;
    }

    @RequestMapping("curso-validacion")
    public ModelAndView procesarCurso(DatosCurso datosCurso) {

        ModelMap model = new ModelMap();

        Map<String, String> errores = validarRegistroCurso(datosCurso);

        if (redirectValidaciones(datosCurso, "formulario-curso") != null) {
            return redirectValidaciones(datosCurso, "formulario-curso");
        }
        try {
            servicioCurso.registrarCurso(datosCurso);
            return new ModelAndView("redirect:registro-curso-exitoso");
        } catch (CodigoCursoExistente e) {
            model.put("codigoExistente", "Este código de curso, ya se encuentra registrado en el sistema");
        }
        return new ModelAndView("formulario-curso", model);

    }

    private ModelAndView procesarRegistroCurso(String mensaje) {
        ModelMap model = new ModelMap();
        if (mensaje != null) {
            model.put("mensajeRegistro", mensaje);
        }
        DatosCurso curso = new DatosCurso();
        model.put("cursoRegistro", curso);
        return new ModelAndView("formulario-curso", model);
    }

    private Map<String, String> validarRegistroCurso(DatosCurso curso) {
        Map<String, String> errores = new HashMap<>();

        if (curso.getNombreCurso() == "" || curso.getNombreCurso() == null) {
            errores.put("nombreError", "El nombre del curso no puede quedar vacio");
        } else if (curso.getCodigoCurso() == "" || curso.getCodigoCurso() == null) {
            errores.put("codigoError", "Debe ingresar un identificador del curso");
        }
        return errores;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //metodo listar los cursos
    @RequestMapping("listar-cursos")
    public ModelAndView listarCursos() {
        ModelMap model = new ModelMap();
        try {
            List<Curso> listaCursos = servicioCurso.listarCursos();
            model.put("listaCursos", listaCursos);
        } catch (listaNoEncontrada e) {
            model.put("listaCursosVacia", "No hay cursos que mostrar");
        }
        return new ModelAndView("lista-cursos", model);
    }

    //metodo que busca un curso por id
    @RequestMapping("curso-detalle")
    public ModelAndView buscarCursoPorId(@RequestParam Long idCurso) {
        ModelMap model = new ModelMap();

        try {
            Curso cursoBuscado = servicioCurso.buscarCursoPorId(idCurso);
            model.put("cursoBuscado", cursoBuscado);
        } catch (CursoNoEncontrado e) {
            return procesarErrores("Curso inexistente, no se pudo encontrar el curso");
        }
        return new ModelAndView("curso-detalle", model);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //metodos que modifican los datos de los cursos
    @RequestMapping("formulario-modificar-curso-lista")
    public ModelAndView formularioModificarCursoLista(@RequestParam Long idCurso) {
        return prepararFormularioModificarCurso(null, idCurso);
    }

    @RequestMapping("formulario-modificar-curso-detalle")
    public ModelAndView formularioModificarCursoDetalle(@RequestParam Long idCurso) {
        return prepararFormularioModificarCurso("detalle", idCurso);
    }

    //validara al usuario para enviarlo a una vista dependiendo desde donde haya entrado al formulario
    // para modificar el curso
    private ModelAndView prepararFormularioModificarCurso(String redirigir, Long idCurso) {
        ModelMap model = new ModelMap();
        try {
            Curso cursoBuscado = servicioCurso.buscarCursoPorId(idCurso);
            DatosCurso datosCurso = new DatosCurso();
            model.put("datosAModificar", datosCurso);
            model.put("cursoModificar", cursoBuscado);
            if (redirigir != null) {
                model.put("redirect", redirigir);
            }
        } catch (CursoNoEncontrado e) {
            mav = procesarErrores("Curso inexistente, no se puede modificar");
            return mav;
        }
        return new ModelAndView("formulario-modificar-curso", model);
    }

    @RequestMapping("modificar-curso-lista")
    public ModelAndView modificarCursoLista(@ModelAttribute DatosCurso datosCurso) {
        if (redirectValidaciones(datosCurso, "formulario-modificar-curso") != null) {
            return redirectValidaciones(datosCurso, "formulario-modificar-curso");
        }
        return modificarCursoRedirect("redirect:listar-cursos", datosCurso);
    }

    @RequestMapping("modificar-curso-detalle")
    public ModelAndView modificarCursoDetalle(@ModelAttribute DatosCurso datosCurso) {
        if (redirectValidaciones(datosCurso, "formulario-modificar-curso") != null) {
            return redirectValidaciones(datosCurso, "formulario-modificar-curso");
        }
        return modificarCursoRedirect("redirect:curso-detalle?idCurso=" + datosCurso.getIdCurso(), datosCurso);
    }

    private ModelAndView modificarCursoRedirect(String vista, DatosCurso datosCurso) {

        try {
            servicioCurso.modificarCurso(datosCurso);
        } catch (CursoNoEncontrado e) {
            mav = procesarErrores("Curso inexistente, no se puede modificar");
            return mav;
        }

        return new ModelAndView(vista);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //metodo para eliminar un curso
    @RequestMapping("eliminar-curso")
    public ModelAndView eliminarCurso(@RequestParam Long idCurso) {

        try {
            servicioCurso.eliminarCurso(idCurso);
            mav = new ModelAndView("redirect:listar-cursos");
        } catch (CursoNoEncontrado e) {
            mav = procesarErrores("Curso inexistente, no se puede eliminar");
        }
        return mav;
    }

    private ModelAndView redirectValidaciones(DatosCurso datosCurso, String vista) {

        Map<String, String> errores = validarRegistroCurso(datosCurso);
        ModelMap model = new ModelMap();
        model.put("nombreDefault", datosCurso.getNombreCurso());
        model.put("codigoDefault", datosCurso.getCodigoCurso());
        model.put("idDefault", datosCurso.getIdCurso());
        if (errores.size() > 0) {
            model.put("erroresValidacion", errores);
            return new ModelAndView(vista, model);
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //agregado y eliminado de alumnos en cursos
    @RequestMapping("agregar-alumno")
    public ModelAndView listaParaAgregarAlumnos(@RequestParam Long idCurso) {
        return redirectAgregarEliminarAlumnosCurso(idCurso);

    }

    @RequestMapping("quitar-alumno")
    public ModelAndView listaParaEliminarAlumnos(@RequestParam Long idCurso) {
        return redirectAgregarEliminarAlumnosCurso(idCurso);
    }

    private ModelAndView redirectAgregarEliminarAlumnosCurso(Long idCurso) {

        ModelMap model = new ModelMap();

        try {
            List<Alumno> listaAlumnos = servicioAlumno.listarAlumnos();
            Curso curso = servicioCurso.buscarCursoPorId(idCurso);
            model.put("listaAlumnos", listaAlumnos);
            model.put("curso", curso);
        } catch (listaNoEncontrada e) {
            model.put("listaAlumnosVacia", "No hay alumnos que mostrar");
        } catch (CursoNoEncontrado e) {
            return procesarErrores("Curso inexistente, no se puede agregar ningún alumno");
        }
        return new ModelAndView("lista-alumnos", model);
    }

    @RequestMapping("agregar-alumno-curso")
    public ModelAndView agregarAlumnoAUnCurso(@RequestParam Long idCurso, @RequestParam Long idAlumno) {
        return redirectAgregarAlumno(idCurso, idAlumno, "redirect:agregar-alumno?idCurso=" + idAlumno);
    }

    private ModelAndView redirectAgregarAlumno(@RequestParam Long idCurso, @RequestParam Long idAlumno, String vista) {
        ModelMap model = new ModelMap();

        try {
            servicioCurso.agregarAlumnoAUnCurso(idAlumno, idCurso);
            Curso curso = servicioCurso.buscarCursoPorId(idCurso);
            model.put("curso",curso);
        } catch (CursoNoEncontrado e) {
            return procesarErrores("Curso inexistente, no se puede agregar ningún alumno");
        } catch (AlumnoNoEncontrado e) {
            return procesarErrores("Alumno inexistente, no se puede agregar al curso");
        } catch (AlumnoExistente e) {
            return procesarErrores("Este alumno ya se encuentra inscripto en este curso");
        }
        return new ModelAndView(vista,model);
    }

    @RequestMapping("eliminar-alumno-curso")
    public ModelAndView eliminarAlumnoDeUnCurso(Long idCurso, Long idAlumno) {

        try {
            servicioCurso.eliminarAlumnoDeUnCurso(idCurso, idAlumno);
        } catch (CursoNoEncontrado e) {
            return procesarErrores("Curso inexistente, no se puede eliminar ningún alumno");
        } catch (AlumnoNoEncontrado e) {
            return procesarErrores("Alumno inexistente, no se puede eliminar al curso");
        }
        return new ModelAndView("redirect:agregar-alumno?idCurso=" + idCurso);

    }

    //este metodo se encarga de mostrar los errores que puede hacer el usuario
    private ModelAndView procesarErrores(String mensaje) {
        ModelMap model = new ModelMap();
        model.put("mensaje", mensaje);
        return new ModelAndView("error", model);
    }


}
