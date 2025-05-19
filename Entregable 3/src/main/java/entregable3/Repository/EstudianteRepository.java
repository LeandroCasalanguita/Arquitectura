package entregable3.Repository;

import entregable3.Entidad.Estudiante;
import entregable3.Service.DTO.Estudiante.EstudianteResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    //Obtiene un estudianteDTO por libreta universitaria
    @Query("SELECT new entregable3.Service.DTO.Estudiante.EstudianteResponseDTO(e) FROM Estudiante e WHERE e.lu = :LU")
    Optional<EstudianteResponseDTO> buscarEstudianteLU(@Param("LU") int lu);

    //Obtiene todos los estudiantes filtrados por género(recibido por parámetro)
    @Query("SELECT new entregable3.Service.DTO.Estudiante.EstudianteResponseDTO(e) FROM Estudiante e WHERE e.genero = :genero")
    List<EstudianteResponseDTO> buscarEstudiantesGenero(String genero);

    //Obtiene todos los estudiantes ordenados por libreta
    @Query("SELECT new entregable3.Service.DTO.Estudiante.EstudianteResponseDTO(e) FROM Estudiante e ORDER BY e.lu ASC")
    List<EstudianteResponseDTO> buscarEstudiantesLibreta();

    @Query("SELECT new entregable3.Service.DTO.Estudiante.EstudianteResponseDTO(e.estudiante) " +
            "FROM EstudianteCarrera e WHERE e.carrera.nombre = :nombreCarrera AND e.estudiante.ciudad = :ciudad")
    List<EstudianteResponseDTO> buscarEstudiantesCarreraCiudad(
            @Param("nombreCarrera") String carrera,
            @Param("ciudad") String ciudad);
}
