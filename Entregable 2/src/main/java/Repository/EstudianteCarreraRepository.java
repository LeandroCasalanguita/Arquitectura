package Repository;

import DTO.ReporteDTO;
import Entidad.Carrera;
import Entidad.Estudiante;
import Entidad.EstudianteCarrera;
import Factory.JpaUtil;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class EstudianteCarreraRepository {

    public void insertarDesdeCSV(String rutaArchivo) {

        EntityManager em = JpaUtil.getEntityManager();

        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {

            String[] linea;
            reader.readNext();

            em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                EstudianteCarrera eC = new EstudianteCarrera();

                Estudiante estudiante = em.find(Estudiante.class, Integer.parseInt(linea[1]));
                eC.setEstudiante(estudiante);

                Carrera carrera = em.find(Carrera.class, Integer.parseInt(linea[2]));
                eC.setCarrera(carrera);

                eC.setAnio_inscripcion(Integer.parseInt((linea[3])));
                eC.setAnio_graduacion(Integer.parseInt((linea[4])));
                eC.setAntiguedad(Integer.parseInt((linea[5])));

                em.persist(eC);

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

    public void insertar(int estudiante,int carrera,int fecha){

        EntityManager em = JpaUtil.getEntityManager();
        EstudianteCarrera eC = new EstudianteCarrera();

        try{
            em.getTransaction().begin();

            eC.setAnio_graduacion(0);
            eC.setAnio_inscripcion(fecha);
            eC.setAntiguedad(0);

            Estudiante estudiante1 = em.find(Estudiante.class,estudiante);
            Carrera carrera1 = em.find(Carrera.class , carrera);

            if(carrera1!= null && estudiante1 != null){

                eC.setCarrera(carrera1);
                eC.setEstudiante(estudiante1);
                em.persist(eC);
                em.getTransaction().commit();

            }
            else {
                System.out.println("Se produjo un error");
            }
        }
        catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
        }
        finally {
            em.close();
        }




    }

    public List<ReporteDTO> ordenarPorInscriptos(){

        EntityManager em = JpaUtil.getEntityManager();
        List<ReporteDTO> resultados = new ArrayList<>();

        try{
            resultados = em.createQuery(
                    "SELECT new DTO.ReporteDTO(ce.carrera.nombre, COUNT(ce.estudiante)) " +
                            "FROM EstudianteCarrera ce " +
                            "GROUP BY ce.carrera.nombre " +
                            "ORDER BY COUNT(ce.estudiante) DESC",
                    ReporteDTO.class
            ).getResultList();
        }
        catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
        }
        finally {
            em.close();
        }

        return resultados;
    }


}
