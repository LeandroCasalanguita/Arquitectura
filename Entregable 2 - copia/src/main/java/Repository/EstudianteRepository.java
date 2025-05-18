package Repository;

import Entidad.Estudiante;
import Factory.JpaUtil;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class EstudianteRepository {

    public void insertarDesdeCSV(String rutaArchivo) {

        EntityManager em = JpaUtil.getEntityManager();

        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {

            String[] linea;
            reader.readNext();

            em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                Estudiante estudiante = new Estudiante();
                estudiante.setDni(Integer.parseInt(linea[0]));
                estudiante.setLu(Integer.parseInt(linea[6]));
                estudiante.setNombre(linea[1]);
                estudiante.setApellido(linea[2]);
                estudiante.setEdad(Integer.parseInt(linea[3]));
                estudiante.setGenero(linea[4]);
                estudiante.setCiudad(linea[5]);


                em.persist(estudiante);

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

    public void insertar(Estudiante estudiante){

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(estudiante);
            em.getTransaction().commit();


        }
        catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
        }
        finally {
            em.close();
        }

    }

    public List<EstudianteDTO> estudiantesPorLibretaUni(){

        EntityManager em = JpaUtil.getEntityManager();
        List<EstudianteDTO> estudianteDTOS = new ArrayList<>();
        try{
            estudianteDTOS= em.createQuery(
                            "SELECT new DTO.EstudianteDTO(e.dni,e.lu, e.nombre, e.apellido, e.edad, e.genero, e.ciudad) " +
                                    "FROM Estudiante e " +
                                    "ORDER BY e.lu",
                            EstudianteDTO.class)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
        }
        finally {
            em.close();
        }

        return estudianteDTOS;

    }

    public EstudianteDTO buscarPorLu(int lu){

        EntityManager em = JpaUtil.getEntityManager();
        EstudianteDTO estDTO  =new EstudianteDTO();

        try{
            TypedQuery<Estudiante> query = em.createQuery(
                    "SELECT e FROM Estudiante e WHERE e.lu = :nroLibreta", Estudiante.class);
            query.setParameter("nroLibreta", lu);
            Estudiante estudiante = query.getSingleResult();
            estDTO = new EstudianteDTO(estudiante.getDni(), estudiante.getLu(), estudiante.getNombre(),estudiante.getApellido(),estudiante.getEdad(),estudiante.getGenero(),estudiante.getCiudad());
        }
        catch (Exception e) {
            System.out.println("Se produjo un error: no se encontro el estudiante con LU: " + lu);
            return null;
        }
        finally {
            em.close();
        }

        return estDTO;
    }

    public List<EstudianteDTO> buscarPorGenero(String genero){
        EntityManager em = JpaUtil.getEntityManager();
        List<EstudianteDTO> estudiantes = new ArrayList<>();
        try{
            estudiantes = em.createQuery(
                            "SELECT new DTO.EstudianteDTO(e.dni, e.lu, e.nombre, e.apellido, e.edad, e.genero, e.ciudad) " +
                                    "FROM Estudiante e WHERE e.genero = :genero",
                            EstudianteDTO.class
                    ).setParameter("genero", genero)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
        }
        finally {
            em.close();
        }

        return estudiantes;
    }

    public List<EstudianteDTO> obtenerPorCiudad(String carrera, String ciudad){

        EntityManager em = JpaUtil.getEntityManager();
        List<EstudianteDTO> estudiantes = new ArrayList<>();

        try{
            estudiantes = em.createQuery(
                            "SELECT new DTO.EstudianteDTO(ce.estudiante.dni, ce.estudiante.lu, ce.estudiante.nombre, ce.estudiante.apellido, ce.estudiante.edad, ce.estudiante.genero, ce.estudiante.ciudad) " +
                                    "FROM EstudianteCarrera ce " +
                                    "WHERE ce.carrera.nombre = :nombreCarrera " +
                                    "AND ce.estudiante.ciudad = :ciudad",
                            EstudianteDTO.class
                    ).setParameter("nombreCarrera", carrera)
                    .setParameter("ciudad", ciudad)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
        }
        finally {
            em.close();
        }

        return estudiantes;

    }
}
