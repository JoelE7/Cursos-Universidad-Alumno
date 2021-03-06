package ar.edu.unlam.tallerweb1.AttributeModel;

public class DatosAlumno {

    private Long idAlumno;

    private String nombre;

    private String email;

    private String telefono;

    public DatosAlumno(){

    }

    public DatosAlumno(String nombre, String email, String telefono,Long id) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.idAlumno = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }
}
