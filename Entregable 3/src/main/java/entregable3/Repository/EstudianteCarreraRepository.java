package entregable3.Repository;

import entregable3.Entidad.EstudianteCarrera;
import entregable3.Service.DTO.Carrera.CarreraResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Integer> {

    @Query("SELECT new entregable3.Service.DTO.Carrera.CarreraResponseDTO(ce.carrera.nombre, COUNT(ce.estudiante)) " +
            "FROM EstudianteCarrera ce " +
            "GROUP BY ce.carrera.nombre " +
            "ORDER BY COUNT(ce.estudiante) DESC")
    List<CarreraResponseDTO> buscarCarrerasConEstudiantes();


    @Query("SELECT ce " +
            "FROM EstudianteCarrera ce " +
            "WHERE ce.carrera.id_carrera = :idCarrera " +
            "AND ce.estudiante.dni = :dniEstudiante")
    Optional<EstudianteCarrera> buscarInscripto(int idCarrera, int dniEstudiante);


}
