package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioAlumnoImpl implements RepositorioAlumno{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAlumnoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Alumno buscarAlumnoPorEmail(String email) {
        final Session session = this.sessionFactory.getCurrentSession();
        Alumno alumno = (Alumno) session.createCriteria(Alumno.class)
                .add(Restrictions.eq("email",email)).uniqueResult();
        return alumno;
    }

    @Override
    public void guardarAlumno(Alumno alumnoARegistrar) {
        sessionFactory.getCurrentSession().save(alumnoARegistrar);
    }

    @Override
    public List<Alumno> listarAlumnos() {
        final Session session = this.sessionFactory.getCurrentSession();
        List<Alumno> listaAlumnos = session.createCriteria(Alumno.class).list();
        return listaAlumnos;
    }

    @Override
    public Alumno buscarAlumnoPorId(Long idAlumno) {
        final Session session = this.sessionFactory.getCurrentSession();
        Alumno alumno = (Alumno) session.createCriteria(Alumno.class)
                        .add(Restrictions.eq("id",idAlumno)).uniqueResult();
        return alumno;
    }

    @Override
    public void eliminarAlumno(Alumno alumnoAEliminar) {
        sessionFactory.getCurrentSession().delete("Alumno",alumnoAEliminar);
    }
}
