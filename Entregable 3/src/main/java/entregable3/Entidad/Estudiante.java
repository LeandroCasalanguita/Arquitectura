package entregable3.Entidad;

import entregable3.Service.DTO.Estudiante.EstudianteRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Setter
@Getter
public class Estudiante {
    @Id
    private int dni;

    @Column (nullable = false)
    private String nombre;

    @Column (nullable = false)
    private String apellido;

    @Column (nullable = false)
    private int edad;

    @Column
    private String genero;

    @Column
    private String ciudad;

    @Column (nullable = false)
    private int lu;

    public Estudiante(int dni, String nombre, String apellido, int edad, String genero,String ciudad, int lu) {
        this.ciudad = ciudad;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.lu = lu;
    }

    @OneToMany (mappedBy = "estudiante")
    private List<EstudianteCarrera> carreras;

    public Estudiante (EstudianteRequestDTO request){
        this.ciudad = request.getCiudad();
        this.dni = request.getDNI();
        this.nombre = request.getNombre();
        this.apellido = request.getApellido();
        this.edad = request.getEdad();
        this.genero = request.getGenero();
        this.lu = request.getLU();
    }

}
