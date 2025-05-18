package entregable3.Controller;


import entregable3.Service.DTO.Carrera.CarreraRequestDTO;
import entregable3.Service.DTO.Carrera.CarreraResponseDTO;
import entregable3.Service.DTO.Carrera.Helper.ReporteCarreraResponseDTO;
import entregable3.Service.Exception.CarreraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    //Genera reporte de carreras ordenado por año y nombre de carrera
    @GetMapping("")
    public List<ReporteCarreraResponseDTO> generarReporteCarreras(){
        return this.carreraService.generarReporteCarreras();
    }

    //Carga una nueva carrera
    @PostMapping("")
    public ResponseEntity<CarreraResponseDTO> cargarCarrera(@RequestBody @Valid CarreraRequestDTO request){
        final var result = this.carreraService.cargarCarrera(request);
        return ResponseEntity.accepted().body(result);
    }
}
