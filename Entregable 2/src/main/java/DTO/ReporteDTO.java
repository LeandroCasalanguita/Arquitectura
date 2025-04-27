package DTO;
import lombok.*;

@Setter
@Getter
public class ReporteDTO {

    private String nombreCarrera;
    private Integer anio;
    private Long inscriptos;
    private Long graduados;

    public ReporteDTO(String nombreCarrera, Integer anio, Long cantidadInscriptos, Long cantidadEgresados) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        this.inscriptos = cantidadInscriptos;
        this.graduados = cantidadEgresados;
    }

    public ReporteDTO(String nombreCarrera, Long inscriptos) {
        this.nombreCarrera = nombreCarrera;
        this.inscriptos = inscriptos;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", anio=" + anio +
                ", inscriptos=" + inscriptos +
                ", graduados=" + graduados +
                '}';
    }

}
