package entregable3.Service.Exception;

import entregable3.Entidad.Estudiante;
import entregable3.Repository.EstudianteRepository;
import entregable3.Service.DTO.Estudiante.EstudianteRequestDTO;
import entregable3.Service.DTO.Estudiante.EstudianteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    //Da de alta un estudiante nuevo
    @Transactional
    public EstudianteResponseDTO altaEstudiante(EstudianteRequestDTO request){
        Estudiante estudiante = new Estudiante(request);

        if(estudianteRepository.findById(estudiante.getDni()).isEmpty()){
            Estudiante result = this.estudianteRepository.save(estudiante);
            return new EstudianteResponseDTO(estudiante.getDni(), result.getNombre(), result.getApellido(), result.getEdad(), result.getGenero(), result.getCiudad(), result.getLu());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estudiante con DNI " + estudiante.getDni() + " ya existe.");
        }
    }

    //Obtiene un estudiante por LU si existe y sino arroja un error
    @Transactional(readOnly = true)
    public EstudianteResponseDTO buscarEstudianteLU(int LU){
        return this.estudianteRepository.buscarEstudianteLU(LU)
                .orElseThrow(() -> new NotFoundException("Estudiante", "LU", LU ));
    }

    //Obtiene una lista de todos los estudiantes filtrados por género
    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> buscarEstudiantesGenero(String genero){
        return estudianteRepository.buscarEstudiantesGenero(genero);
    }

    //Obtiene la lista de todos los estudiantes ordenados por LU ascendentemente
    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> buscarEstudiantesLibreta(){
        return estudianteRepository.buscarEstudiantesLibreta();
    }

    //Obtiene una lista de todos los estudiantes de una ciudad y una carrera (recibidas por parámetro)
    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> buscarEstudiantesCarreraCiudad(String carrera, String ciudad){
        return estudianteRepository.buscarEstudiantesCarreraCiudad(carrera, ciudad);
    }

}
