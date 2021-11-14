package ar.edu.unlam.tallerweb1.AttributeModel;

public class DatosCurso {

    private Long idCurso;
    private String nombreCurso;
    private String codigoCurso;

    public DatosCurso(Long idCurso, String codigoCurso, String nombreCurso) {
        this.idCurso=idCurso;
        this.codigoCurso=codigoCurso;
        this.nombreCurso=nombreCurso;
    }

    public DatosCurso() {

    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
}
