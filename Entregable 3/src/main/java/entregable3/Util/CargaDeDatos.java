package entregable3.Util;

import entregable3.Entidad.Carrera;
import entregable3.Entidad.Estudiante;
import entregable3.Entidad.EstudianteCarrera;
import entregable3.Repository.CarreraRepository;
import entregable3.Repository.EstudianteCarreraRepository;
import entregable3.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import java.io.File;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CargaDeDatos {

    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;
    private final EstudianteCarreraRepository ecRepository;

    Map<Integer, Estudiante> estudiantesMap = new HashMap<>();
    Map<Integer, Carrera> carrerasMap = new HashMap<>();

    @Autowired
    public CargaDeDatos(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository, EstudianteCarreraRepository ecRepository) {
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
        this.ecRepository = ecRepository;
    }

    //Carga los datos desde los csv de estudiantes, carreras y estudianteCarrera a la base de datos
    public void cargarDatosCSV() throws IOException {
        File estudianteCSV = ResourceUtils.getFile("src/main/resources/csv/estudiantes.csv");
        File carreraCSV = ResourceUtils.getFile("src/main/resources/csv/carreras.csv");
        File estudianteCarreraCSV = ResourceUtils.getFile("src/main/resources/csv/estudianteCarrera.csv");

        cargarEstudianteCSV(estudianteCSV);
        cargarCarreraCSV(carreraCSV);
        cargarEstudianteCarreraCSV(estudianteCarreraCSV);

    }

    //Carga los datos desde el csv de estudiantes
    public void cargarEstudianteCSV(File estudianteCSV) throws IOException {
        try (FileReader reader = new FileReader(estudianteCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                persistEstudiante(csvRecord);
            }
        }
    }

    //Carga los datos desde el csv de carreras
    public void cargarCarreraCSV(File carreraCSV) throws IOException {
        try (FileReader reader = new FileReader(carreraCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                persistCarrera(csvRecord);
            }
        }
    }

    //Carga los datos desde el csv de estudianteCarrera
    public void cargarEstudianteCarreraCSV(File estudianteCarreraCSV) throws IOException {
        try (FileReader reader = new FileReader(estudianteCarreraCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                persistEstudianteCarrera(csvRecord);
            }
        }
    }

    //Persiste a un estudiante en la base de datos
    public void persistEstudiante(CSVRecord csvRecord){
        Estudiante estudiante = new Estudiante();
        int id_estudiante = Integer.parseInt(csvRecord.get("DNI"));
        estudiante.setDni(id_estudiante);
        estudiante.setNombre(csvRecord.get("nombre"));
        estudiante.setApellido(csvRecord.get("apellido"));
        estudiante.setEdad(Integer.parseInt(csvRecord.get("edad")));
        estudiante.setGenero(csvRecord.get("genero"));
        estudiante.setCiudad(csvRecord.get("ciudad"));
        estudiante.setLu(Integer.parseInt(csvRecord.get("LU")));
        estudiantesMap.put(id_estudiante,estudiante);
        estudianteRepository.save(estudiante);
    }

    //Persiste a una carrera en la base de datos
    public void persistCarrera(CSVRecord csvRecord){
        Carrera carrera = new Carrera();
        int id_carrera =Integer.parseInt(csvRecord.get("id_carrera"));
        carrera.setId_carrera(id_carrera);
        carrera.setNombre(csvRecord.get("carrera"));
        carrera.setDuracion(Integer.parseInt(csvRecord.get("duracion")));
        carrerasMap.put(id_carrera, carrera);
        carreraRepository.save(carrera);
    }

    //Persiste a un estudiante en una carrera determinada en la base de datos
    public void persistEstudianteCarrera(CSVRecord csvRecord){
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
        int id_estudiante =Integer.parseInt(csvRecord.get("id_estudiante"));
        int id_carrera =Integer.parseInt(csvRecord.get("id_carrera"));
        estudianteCarrera.setEstudiante(estudiantesMap.get(id_estudiante));
        estudianteCarrera.setCarrera(carrerasMap.get(id_carrera));
        estudianteCarrera.setId_estudiante_carrera(Integer.parseInt(csvRecord.get("id")));
        estudianteCarrera.setAnio_inscripcion(Integer.parseInt(csvRecord.get("inscripcion")));
        estudianteCarrera.setAnio_graduacion(Integer.parseInt(csvRecord.get("graduacion")));
        estudianteCarrera.setAntiguedad(Integer.parseInt(csvRecord.get("antiguedad")));

        ecRepository.save(estudianteCarrera);
    }

}
