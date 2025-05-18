package DTO.Carrera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CarreraResponseDTO {

    private String carrera;
    private int duracion;
    //private long inscriptos;

}
