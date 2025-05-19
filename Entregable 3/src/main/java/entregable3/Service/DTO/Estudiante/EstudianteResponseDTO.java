package entregable3.Service.DTO.Estudiante;

import entregable3.Entidad.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EstudianteResponseDTO {

    private int DNI;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;
    private int LU;

    public EstudianteResponseDTO(Estudiante estudiante) {
        this.DNI = estudiante.getDni();
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.edad = estudiante.getEdad();
        this.genero = estudiante.getGenero();
        this.ciudad = estudiante.getCiudad();
        this.LU = estudiante.getLu();
    }
}