package entregable3.Service.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {

    private final String message;

    public NotFoundException(String entity, String atributo, int valor ){
        this.message = String.format( "La entidad %s con %s : %s no existe.", entity, atributo, valor );
    }
}