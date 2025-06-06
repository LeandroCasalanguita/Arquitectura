package entregable3.Service.DTO.Carrera;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties( ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor

public class CarreraRequestDTO {

    @NotBlank( message = "El nombre de la carrera es un campo obligatorio.")
    private String nombre;

    @NotNull( message = "La duración de la carrera es un campo obligatorio.")
    private int duracion;

    @NotNull( message = "La cantidad de inscriptos de la carrera es un campo obligatorio.")
    private long inscriptos;

    public CarreraRequestDTO(String nombreCarrera, Long cantidadEstudiantes) {
        this.nombre = nombreCarrera;
        this.inscriptos = cantidadEstudiantes;
    }

}

