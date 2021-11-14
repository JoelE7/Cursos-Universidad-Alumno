package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.ItemCurso;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioCursoImpl implements RepositorioCurso{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCursoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Curso> listarCursos() {
        final Session session = this.sessionFactory.getCurrentSession();
        List<Curso> cursos = session.createCriteria(Curso.class).list();
        return cursos;
    }

    @Override
    public Curso buscarCursoPorCodigo(String codigo_curso) {
        final Session session = this.sessionFactory.getCurrentSession();
        Curso cursoBuscado = (Curso) session.createCriteria(Curso.class)
                .add(Restrictions.eq("codigo",codigo_curso)).uniqueResult();
        return cursoBuscado;
    }

    @Override
    public void guardarCurso(Curso cursoNuevo) {
        sessionFactory.getCurrentSession().save(cursoNuevo);
    }

    @Override
    public Curso buscarCursoPorId(Long id_curso) {
        final Session session = this.sessionFactory.getCurrentSession();
        Curso cursoBuscado = (Curso) session.createCriteria(Curso.class)
                            .add(Restrictions.eq("id",id_curso)).uniqueResult();
        return cursoBuscado;
    }

    @Override
    public void eliminarCurso(Curso cursoAEliminar) {
        sessionFactory.getCurrentSession().delete("Curso",cursoAEliminar);
    }
}
