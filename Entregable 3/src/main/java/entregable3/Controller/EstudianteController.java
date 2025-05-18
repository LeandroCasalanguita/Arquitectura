package entregable3.Controller;

import entregable3.Service.DTO.Estudiante.EstudianteRequestDTO;
import entregable3.Service.DTO.Estudiante.EstudianteResponseDTO;
import entregable3.Service.Exception.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    //Obtiene estudiante por LU
    @GetMapping("/lu/{LU}")
    public EstudianteResponseDTO buscarEstudianteLU(@PathVariable int LU){
        return this.estudianteService.buscarEstudianteLU(LU);
    }

    //Obtiene estudiantes por género
    @GetMapping("/genero/{genero}")
    public List<EstudianteResponseDTO> buscarEstudiantesGenero(@PathVariable String genero){
        return this.estudianteService.buscarEstudiantesGenero(genero);
    }

    //Obtiene estudiantes ordenados por libreta
    @GetMapping("/orderBy/libreta")
    public List<EstudianteResponseDTO> buscarEstudiantesLibreta(){
        return this.estudianteService.buscarEstudiantesLibreta();
    }

    //Obtiene estudiantes filtrados por carrera y ciudad
    @GetMapping("/carreraPorCiudad/{carrera}/{ciudad}")
    public List<EstudianteResponseDTO> buscarEstudiantesCarreraCiudad( @PathVariable String carrera, @PathVariable String ciudad){
        return this.estudianteService.buscarEstudiantesCarreraCiudad(carrera, ciudad);
    }

    //Da de alta un estudiante
    @PostMapping("")
    public ResponseEntity<EstudianteResponseDTO> altaEstudiante (@RequestBody @Valid EstudianteRequestDTO request){
        final var result = this.estudianteService.altaEstudiante(request);
        return ResponseEntity.accepted().body(result);
    }

}
