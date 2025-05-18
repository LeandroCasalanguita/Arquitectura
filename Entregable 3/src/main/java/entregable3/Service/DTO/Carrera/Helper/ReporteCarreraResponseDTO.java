package entregable3.Service.DTO.Carrera.Helper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteCarreraResponseDTO {

    private String nombreCarrera;
    private Integer anio;
    private long inscriptos;
    private long graduados;
}
