package entregable3.Service.Exception;

import entregable3.Entidad.Carrera;
import entregable3.Entidad.Estudiante;
import entregable3.Entidad.EstudianteCarrera;
import entregable3.Repository.CarreraRepository;
import entregable3.Repository.EstudianteCarreraRepository;
import entregable3.Repository.EstudianteRepository;
import entregable3.Service.DTO.Carrera.CarreraResponseDTO;
import entregable3.Service.DTO.EstudianteCarrera.EstudianteCarreraRequestDTO;
import entregable3.Service.DTO.EstudianteCarrera.EstudianteCarreraResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteCarreraService {

    @Autowired
    private EstudianteCarreraRepository ecRepository;
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;

    //Matricula a un estudiante en una carrera determinada
    @Transactional
    public EstudianteCarreraResponseDTO matricularEstudiante(EstudianteCarreraRequestDTO request){

        int dniEstudiante = request.getDni_estudiante();
        int idCarrera = request.getId_carrera();
        Carrera carrera =  carreraRepository.findById(idCarrera).orElseThrow(() ->new NotFoundException("Carrera", "id", idCarrera));
        Estudiante estudiante = estudianteRepository.findById(dniEstudiante).orElseThrow(() ->new NotFoundException("Estudiante", "dni", dniEstudiante));
        Optional<EstudianteCarrera> inscripto = ecRepository.buscarInscripto(idCarrera,dniEstudiante);
        if(inscripto.isEmpty()){
            int anio_inscripcion = Calendar.getInstance().get(Calendar.YEAR);
            EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
            estudianteCarrera.setAnio_inscripcion(Calendar.getInstance().get(Calendar.YEAR));
            estudianteCarrera.setAnio_graduacion(0);
            estudianteCarrera.setAntiguedad(0);
            estudianteCarrera.setEstudiante(estudiante);
            estudianteCarrera.setCarrera(carrera);
            this.ecRepository.save(estudianteCarrera);

            return new EstudianteCarreraResponseDTO(anio_inscripcion, 0 , 0, dniEstudiante, idCarrera);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estudiante con DNI " + dniEstudiante + " no pudo matricularse.");
        }
    }

    //Obtiene una lista de CarreraResponseDTO con la cantidad de inscriptos correspondientes
    @Transactional(readOnly = true)
    public List<CarreraResponseDTO> buscarCarrerasConEstudiantes(){
        return this.ecRepository.buscarCarrerasConEstudiantes();
    }


}
