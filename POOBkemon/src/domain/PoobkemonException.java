package domain;

/**
 * Excepción personalizada para el dominio de Poobkemon.
 * Se lanza cuando ocurre cualquier error específico relacionado con la lógica
 * de Poobkemon, como movimientos inválidos, clonaciones fallidas, o al
 * interactuar con archivos de partida.
 */
public class PoobkemonException extends Exception {

    /**
     * Crea una nueva instancia de PoobkemonException con un mensaje descriptivo.
     * @param message Mensaje que describe la causa de la excepción.
     */
    public PoobkemonException(String message) {
        super(message);
    }

    /**
     * Crea una nueva instancia de PoobkemonException con un mensaje descriptivo
     * y una causa subyacente.
     * @param message Mensaje que describe la causa de la excepción.
     * @param cause   Excepción original que provocó esta excepción.
     */
    public PoobkemonException(String message, Throwable cause) {
        super(message, cause);
    }
}
