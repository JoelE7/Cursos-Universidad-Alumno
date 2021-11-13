package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioCursoImpl implements ServicioCurso{

    private RepositorioCurso repositorioCurso;

    @Autowired
    public ServicioCursoImpl(RepositorioCurso repositorioCurso) {
        this.repositorioCurso = repositorioCurso;
    }
}
