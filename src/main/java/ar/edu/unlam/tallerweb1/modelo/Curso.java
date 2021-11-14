package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long id;

    private String codigo;

    private String nombre;

    private Integer cantidadDeAlumnos;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
        name = "curso_alumno",
            joinColumns = @JoinColumn(name = "id_alumno"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")

    )
    private List<Alumno> alumnos;

    public List<Alumno> getListaAlumnos() {
        if (this.alumnos == null) {
            this.alumnos = new ArrayList<>();
        }
        return alumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos){
        this.alumnos = listaAlumnos;
    }

    public void añadirAlumnoAlCurso(Alumno alumno) {
        getListaAlumnos().add(alumno);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadDeAlumnos() {
        if(this.cantidadDeAlumnos==null){
            this.cantidadDeAlumnos = 0;
        }
        return cantidadDeAlumnos;
    }

    public void setCantidadDeAlumnos(Integer cantidadDeAlumnos) {
        this.cantidadDeAlumnos = cantidadDeAlumnos;
    }
}
