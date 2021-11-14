package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class ItemCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Alumno alumno;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Curso curso;

    private Integer cantidadAlumnos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(Integer cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }
}
