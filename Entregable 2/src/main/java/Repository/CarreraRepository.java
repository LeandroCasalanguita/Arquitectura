package Repository;

import DTO.ReporteDTO;
import Entidad.Carrera;
import Factory.JpaUtil;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CarreraRepository {

    public void insertarDesdeCSV(String rutaArchivo) {

        EntityManager em = JpaUtil.getEntityManager();

        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            reader.readNext();
            em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                Carrera carrera = new Carrera();
                carrera.setNombre(linea[1]);
                carrera.setDuracion(Integer.parseInt(linea[2]));

                em.persist(carrera);



            }
            em.getTransaction().commit();

        }
        catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
        }
        finally {
            em.close();
        }
    }

    public List<ReporteDTO> obtenerInforme(){

        EntityManager em = JpaUtil.getEntityManager();
        List<ReporteDTO> reportes = new ArrayList<>();

        try {
            reportes= em.createQuery("SELECT new DTO.ReporteDTO(" +
                    "ce.carrera.nombre, " +
                    "COALESCE(ce.anio_inscripcion, ce.anio_graduacion), " +
                    "SUM(CASE WHEN ce.anio_inscripcion IS NOT NULL THEN 1 ELSE 0 END), " +
                    "SUM(CASE WHEN ce.anio_graduacion IS NOT NULL AND ce.anio_graduacion > 0 THEN 1 ELSE 0 END)) " +
                    "FROM EstudianteCarrera ce " +
                    "GROUP BY ce.carrera.nombre, COALESCE(ce.anio_inscripcion, ce.anio_graduacion) " +
                    "ORDER BY ce.carrera.nombre, COALESCE(ce.anio_inscripcion, ce.anio_graduacion)",ReporteDTO.class).getResultList();

        }
        catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
        }
        finally {
            em.close();
        }

        return reportes;
    }
}
