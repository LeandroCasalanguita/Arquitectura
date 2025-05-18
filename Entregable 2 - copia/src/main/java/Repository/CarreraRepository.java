package Repository;

import Entidad.Carrera;
import Factory.JpaUtil;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
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
            List<ReporteDTO> inscripciones = em.createQuery(
                    "SELECT new DTO.ReporteDTO(" +
                            "    ce.carrera.nombre, " +
                            "    ce.anio_inscripcion, " +
                            "    COUNT(ce), " +
                            "    CAST(0 AS long)) " +
                            "FROM EstudianteCarrera ce " +
                            "WHERE ce.anio_inscripcion IS NOT NULL " +
                            "GROUP BY ce.carrera.nombre, ce.anio_inscripcion",
                    ReporteDTO.class
            ).getResultList();


            List<ReporteDTO> graduaciones = em.createQuery(
                    "SELECT new DTO.ReporteDTO(" +
                            "    ce.carrera.nombre, " +
                            "    ce.anio_graduacion, " +
                            "    CAST(0 AS long) , " +
                            "    COUNT(ce)) " +
                            "FROM EstudianteCarrera ce " +
                            "WHERE ce.anio_graduacion IS NOT NULL AND ce.anio_graduacion > 0 " +
                            "GROUP BY ce.carrera.nombre, ce.anio_graduacion",
                    ReporteDTO.class
            ).getResultList();

            reportes.addAll(inscripciones);

            for(ReporteDTO grad:graduaciones){
                boolean encontro = false;

                for(ReporteDTO insc:reportes){

                    if(grad.getAnio().equals(insc.getAnio()) && grad.getNombreCarrera().equals(insc.getNombreCarrera())){
                        insc.setGraduados(grad.getGraduados());
                        encontro = true;
                        break;

                    }
                }
                if(!encontro){

                    reportes.add(grad);
                }
            }

            reportes.sort(Comparator.comparing(ReporteDTO::getNombreCarrera)
                    .thenComparing(ReporteDTO::getAnio));



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
