package entregable3.Repository;

import entregable3.Entidad.Carrera;
import entregable3.Service.DTO.Carrera.Helper.ReporteCarreraResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    @Query("SELECT new entregable3.Service.DTO.Carrera.Helper.ReporteCarreraResponseDTO(" +
            "    ce.carrera.nombre, " +
            "    ce.anio_inscripcion, " +
            "    COUNT(ce), " +
            "    CAST(0 AS long)) " +
            "FROM EstudianteCarrera ce " +
            "WHERE ce.anio_inscripcion IS NOT NULL " +
            "GROUP BY ce.carrera.nombre, ce.anio_inscripcion")
    List<ReporteCarreraResponseDTO> buscarInscriptosPorAnio();

    @Query("SELECT new entregable3.Service.DTO.Carrera.Helper.ReporteCarreraResponseDTO(" +
            "    ce.carrera.nombre, " +
            "    ce.anio_graduacion, " +
            "    CAST(0 AS long) , " +
            "    COUNT(ce)) " +
            "FROM EstudianteCarrera ce " +
            "WHERE ce.anio_graduacion IS NOT NULL AND ce.anio_graduacion > 0 " +
            "GROUP BY ce.carrera.nombre, ce.anio_graduacion")
    List<ReporteCarreraResponseDTO> buscarGraduadosPorAnio();

    @Query ("SELECT c FROM Carrera c WHERE c.nombre=:carrera")
    Carrera buscarPorNombre(String carrera);

}
