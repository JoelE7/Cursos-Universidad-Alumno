package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.AttributeModel.DatosAlumno;
import ar.edu.unlam.tallerweb1.Excepciones.AlumnoNoEncontrado;
import ar.edu.unlam.tallerweb1.Excepciones.MailExistente;
import ar.edu.unlam.tallerweb1.Excepciones.listaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioAlumnoImpl implements ServicioAlumno{

    private RepositorioAlumno repositorioAlumno;

    @Autowired
    public ServicioAlumnoImpl(RepositorioAlumno repositorioAlumno) {
        this.repositorioAlumno = repositorioAlumno;
    }

    @Override
    public void registrarAlumno(DatosAlumno datosAlumno) {
        Alumno alumnoARegistrar = new Alumno();

        if(buscarMailExistente(datosAlumno.getEmail())){
            throw new MailExistente();
        }

        alumnoARegistrar.setTelefono(datosAlumno.getTelefono());
        alumnoARegistrar.setNombre(datosAlumno.getNombre());
        alumnoARegistrar.setEmail(datosAlumno.getEmail());

        repositorioAlumno.guardarAlumno(alumnoARegistrar);

    }

    @Override
    public List<Alumno> listarAlumnos() {
        List<Alumno> listaAlumnos = repositorioAlumno.listarAlumnos();

        if(listaAlumnos.size()<1){
            throw new listaNoEncontrada();
        }


        return listaAlumnos;
    }

    @Override
    public Alumno buscarAlumnoPorId(Long idAlumno) {

        Alumno alumnoBuscado = repositorioAlumno.buscarAlumnoPorId(idAlumno);

        if(alumnoBuscado==null){
            throw new AlumnoNoEncontrado();
        }

        return alumnoBuscado;
    }

    @Override
    public void modificarAlumno(DatosAlumno datosAlumno) {

        Alumno alumnoAModificar = buscarAlumnoPorId(datosAlumno.getIdAlumno());
        if(alumnoAModificar==null){
            throw new AlumnoNoEncontrado();
        }
        alumnoAModificar.setTelefono(datosAlumno.getTelefono());
        alumnoAModificar.setNombre(datosAlumno.getNombre());
        alumnoAModificar.setEmail(datosAlumno.getEmail());

        repositorioAlumno.guardarAlumno(alumnoAModificar);
    }

    @Override
    public void eliminarAlumno(Long idAlumno) {
        Alumno alumnoAEliminar = buscarAlumnoPorId(idAlumno);
        if(alumnoAEliminar==null){
            throw new AlumnoNoEncontrado();
        }
        repositorioAlumno.eliminarAlumno(alumnoAEliminar);
    }

    @Override
    public Boolean buscarMailExistente(String email) {

        Alumno alumnoExistente = repositorioAlumno.buscarAlumnoPorEmail(email);

        if(alumnoExistente!=null){
            return true;
        }
        return false;
    }
}
