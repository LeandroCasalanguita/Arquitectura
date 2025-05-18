package Entidad;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carrera;

    @Column (nullable = false)
    private String nombre;

    @Column
    private int duracion;

    @OneToMany (mappedBy = "carrera")

    private List<EstudianteCarrera> carreras;


}

