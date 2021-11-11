package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioAlumnoImpl implements RepositorioAlumno{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAlumnoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
