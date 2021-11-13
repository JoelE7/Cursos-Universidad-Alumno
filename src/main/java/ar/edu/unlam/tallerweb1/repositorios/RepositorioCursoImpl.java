package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioCursoImpl implements RepositorioCurso{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCursoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
