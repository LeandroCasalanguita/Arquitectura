
import Entidad.Estudiante;
import Factory.JpaUtil;
import Repository.CarreraRepository;
import Repository.EstudianteCarreraRepository;
import Repository.EstudianteRepository;
import jakarta.persistence.EntityManager;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManager eM = JpaUtil.getEntityManager();
        EstudianteRepository estudianteR = new EstudianteRepository();
        CarreraRepository carreraR = new CarreraRepository();
        EstudianteCarreraRepository estudianteCarreraR = new EstudianteCarreraRepository();

        estudianteR.insertarDesdeCSV("src/main/resources/estudiantes.csv");
        carreraR.insertarDesdeCSV("src/main/resources/carreras.csv");
        estudianteCarreraR.insertarDesdeCSV("src/main/resources/estudianteCarrera.csv");

        System.out.println("dar de alta un estudiante");

        Estudiante e1 = new Estudiante(1,"a","a",2,"a","a",3);
        estudianteR.insertar(e1);



        System.out.println("matricular un estudiante en una carrera");
        estudianteCarreraR.insertar(1,1,1);



        System.out.println("estudiantes por ordenados por libreta");
        for(EstudianteDTO est: estudianteR.estudiantesPorLibretaUni()) System.out.println(est);


        System.out.println("buscar por lu");
        EstudianteDTO estu = estudianteR.buscarPorLu(96779);
        if(estu!=null) System.out.println(estu);

        EstudianteDTO estu1 = estudianteR.buscarPorLu(4);
        if(estu1!=null) System.out.println(estu1);


        System.out.println("filtro por genero");
        for(EstudianteDTO cPI: estudianteR.buscarPorGenero("male")) System.out.println(cPI);


        System.out.println("carrera por inscriptos");
        for(ReporteDTO cPI: estudianteCarreraR.ordenarPorInscriptos()) System.out.println(cPI);


        System.out.println("buscar carrera por ciudad");
        List<EstudianteDTO> res = estudianteR.obtenerPorCiudad("TUDAI","Necochea");

        if(res.isEmpty()){
            System.out.println("no hay estudiantes en esa ciudad de esa carrera");
        }
        else{
            for(EstudianteDTO ca: res) System.out.println(ca);
        }

        System.out.println("obtener informe de carreras");

        for(ReporteDTO rep : carreraR.obtenerInforme()) {
            System.out.println(rep);
        }


    }
}
