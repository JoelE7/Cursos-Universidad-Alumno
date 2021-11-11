package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioAlumnoImpl implements ServicioAlumno{

    private RepositorioAlumno repositorioAlumno;

    @Autowired
    public ServicioAlumnoImpl(RepositorioAlumno repositorioAlumno) {
        this.repositorioAlumno = repositorioAlumno;
    }
}
