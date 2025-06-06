package entregable3;

import entregable3.Util.CargaDeDatos;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class SpringbootApplication {

    @Autowired
    CargaDeDatos cargaDeDatos;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        cargaDeDatos.cargarDatosCSV();
    }
}
