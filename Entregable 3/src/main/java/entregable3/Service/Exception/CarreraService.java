package entregable3.Service.Exception;

import entregable3.Entidad.Carrera;
import entregable3.Repository.CarreraRepository;
import entregable3.Service.DTO.Carrera.CarreraRequestDTO;
import entregable3.Service.DTO.Carrera.CarreraResponseDTO;
import entregable3.Service.DTO.Carrera.Helper.ReporteCarreraResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    //Persiste una carrera en la base de datos si no existe el id sino arroja un error.
    @Transactional
    public CarreraResponseDTO cargarCarrera(CarreraRequestDTO request){
        Carrera carrera = new Carrera(request);
        String nombre = carrera.getNombre();

        if(carreraRepository.buscarPorNombre(nombre) == null){
            final var result = this.carreraRepository.save(carrera);

            return new CarreraResponseDTO(result.getNombre(), result.getDuracion());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La carrera con el nombre " + nombre + " ya existe.");
        }
    }

    //Genera un reporte de todas las carreras ordenadas alfabéticamente por nombre y cronológicamente por años
    @Transactional(readOnly = true)
    public List<ReporteCarreraResponseDTO> generarReporteCarreras(){
        List<ReporteCarreraResponseDTO> graduados = this.carreraRepository.buscarGraduadosPorAnio();
        List<ReporteCarreraResponseDTO> reportes = new ArrayList<>(this.carreraRepository.buscarInscriptosPorAnio());

        for(ReporteCarreraResponseDTO grad:graduados){
            boolean encontro = false;

            for(ReporteCarreraResponseDTO insc:reportes){

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

        reportes.sort(Comparator.comparing(ReporteCarreraResponseDTO::getNombreCarrera)
                .thenComparing(ReporteCarreraResponseDTO::getAnio));

        return reportes;

    }

}
