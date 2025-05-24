package domain;

import java.util.List;

/**
 * Interfaz que define el comportamiento básico de un Pokémon en el sistema.
 * Permite obtener atributos esenciales, manipular puntos de vida, clonar el objeto
 * y gestionar sus movimientos.
 */
public interface Pokemon {

    /**
     * Obtiene el nombre del Pokémon.
     * @return Nombre del Pokémon.
     */
    String getName();

    /**
     * Obtiene el tipo primario del Pokémon.
     * @return Tipo primario.
     */
    Type getPrimaryType();

    /**
     * Obtiene el tipo secundario del Pokémon.
     * Puede devolver null si no tiene tipo secundario.
     * @return Tipo secundario o null.
     */
    Type getSecondaryType();

    /**
     * Obtiene la cantidad máxima de puntos de salud (HP) del Pokémon.
     * @return HP máximo.
     */
    int getMaxHP();

    /**
     * Obtiene los puntos de salud actuales del Pokémon.
     * @return HP actual.
     */
    int getCurrentHP();

    /**
     * Obtiene el valor del atributo de ataque físico.
     * @return Ataque.
     */
    int getAttack();

    /**
     * Obtiene el valor de defensa física.
     * @return Defensa.
     */
    int getDefense();

    /**
     * Obtiene el valor del ataque especial.
     * @return Ataque especial.
     */
    int getSpecialAttack();

    /**
     * Obtiene el valor de la defensa especial.
     * @return Defensa especial.
     */
    int getSpecialDefense();

    /**
     * Obtiene el valor de la velocidad del Pokémon.
     * @return Velocidad.
     */
    int getSpeed();

    /**
     * Obtiene la lista de movimientos que conoce el Pokémon.
     * @return Lista de movimientos.
     */
    List<Move> getMoves();

    /**
     * Aplica daño al Pokémon, reduciendo sus puntos de salud actuales.
     * @param amount Cantidad de daño recibido.
     */
    void takeDamage(int amount);

    /**
     * Restaura puntos de salud al Pokémon.
     * @param amount Cantidad de salud restaurada.
     */
    void heal(int amount);

    /**
     * Verifica si el Pokémon se ha debilitado (HP <= 0).
     * @return true si está debilitado, false en caso contrario.
     */
    boolean isFainted();

    /**
     * Crea una copia exacta del Pokémon actual.
     * @return Una nueva instancia del Pokémon.
     */
    Pokemon clone();

    /**
     * Inicializa la lista de movimientos del Pokémon de acuerdo a sus tipos.
     * Usualmente se llama al crear o revivir un Pokémon.
     */
    void initializeMoves();
}
