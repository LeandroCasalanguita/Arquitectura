package entregable3.Controller;


import entregable3.Service.DTO.Carrera.CarreraResponseDTO;
import entregable3.Service.DTO.EstudianteCarrera.EstudianteCarreraRequestDTO;
import entregable3.Service.DTO.EstudianteCarrera.EstudianteCarreraResponseDTO;
import entregable3.Service.Exception.EstudianteCarreraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/carreras/estudiantes")

public class EstudianteCarreraController {

    @Autowired
    private EstudianteCarreraService ecService;


    //Devuelve una lista de carreras con la cantidad de inscriptos
    @GetMapping("")
    public List<CarreraResponseDTO> buscarCarrerasConEstudiantes(){
        return this.ecService.buscarCarrerasConEstudiantes();
    }

    //Matricula un estudiante en una carrera
    @PostMapping("")
    public ResponseEntity<EstudianteCarreraResponseDTO> matricularEstudiante(@RequestBody @Valid EstudianteCarreraRequestDTO request){
        final var result = this.ecService.matricularEstudiante(request);
        return ResponseEntity.accepted().body(result);
    }
}