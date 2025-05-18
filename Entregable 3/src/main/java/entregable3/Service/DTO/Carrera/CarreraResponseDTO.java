package entregable3.Service.DTO.Carrera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CarreraResponseDTO {

    private String carrera;
    private int duracion;
    private long inscriptos;

    public CarreraResponseDTO(String carrera, int duracion){
        this.carrera = carrera;
        this.duracion = duracion;
        this.inscriptos = 0;
    }

}
