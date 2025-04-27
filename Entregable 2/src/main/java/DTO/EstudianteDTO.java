package DTO;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor

public class EstudianteDTO {

    private int DNI;
    private int LU;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;


    @Override
    public String toString(){
        return "\nDNI: " + DNI
                + " | Nombre: " + nombre
                + " | apellido: " + apellido
                + " | edad: " + edad
                + " | genero: " + genero
                + " | ciudad: " + ciudad
                + " | LU: " + LU;
    }
}
