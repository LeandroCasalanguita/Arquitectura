package entregable3.Service.DTO.EstudianteCarrera;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstudianteCarreraResponseDTO {

    private int inscripcion;
    private int graduacion;
    private int antiguedad;
    private int dni_estudiante;
    private int id_carrera;

}
