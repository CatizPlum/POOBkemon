package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta base para todos los Pokémon que implementa la interfaz Pokemon.
 * Proporciona implementaciones predeterminadas de los métodos comunes
 * y define la estructura básica de un Pokémon.
 */
public abstract class AbstractPokemon implements Pokemon, Serializable, Cloneable {

    // Atributos protegidos accesibles por las clases hijas
    protected String name;
    protected Type primaryType;
    protected Type secondaryType;
    protected int maxHP;
    protected int currentHP;
    protected int attack;
    protected int defense;
    protected int specialAttack;
    protected int specialDefense;
    protected int speed;
    protected List<Move> moves;

    /**
     * Implementación del método clone para permitir la clonación profunda de Pokémon.
     * @return Una copia exacta del Pokémon actual
     * @throws RuntimeException Si ocurre un error durante la clonación
     */
    @Override
    public Pokemon clone() {
        try {
            AbstractPokemon cloned = (AbstractPokemon) super.clone();
            // Clonar la lista de movimientos para evitar compartir referencias
            cloned.moves = new ArrayList<>();
            for (Move move : this.moves) {
                cloned.moves.add(move.clone());
            }
            // Restaurar HP al máximo en el clon
            cloned.currentHP = cloned.maxHP;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar Pokémon", e);
        }
    }

    /**
     * Reduce el PP de todos los movimientos especiales en 1.
     * Solo afecta a movimientos con PP mayor a 0.
     */
    public void reduceSpecialMovesPp() {
        for (Move move : moves) {
            if (move.getCategory() == MoveCategory.SPECIAL && move.getPp() > 0) {
                move.setPp(move.getPp() - 1);
            }
        }
    }

    // Métodos de acceso básicos

    /**
     * Obtiene el nombre del Pokémon.
     * @return El nombre del Pokémon
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Obtiene el tipo primario del Pokémon.
     * @return El tipo primario
     */
    @Override
    public Type getPrimaryType() {
        return primaryType;
    }

    /**
     * Obtiene el tipo secundario del Pokémon.
     * @return El tipo secundario, o null si no tiene
     */
    @Override
    public Type getSecondaryType() {
        return secondaryType;
    }

    /**
     * Obtiene los puntos de salud máximos del Pokémon.
     * @return El HP máximo
     */
    @Override
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Obtiene los puntos de salud actuales del Pokémon.
     * @return El HP actual
     */
    @Override
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * Obtiene el valor de ataque físico del Pokémon.
     * @return El valor de ataque
     */
    @Override
    public int getAttack() {
        return attack;
    }

    /**
     * Obtiene el valor de defensa física del Pokémon.
     * @return El valor de defensa
     */
    @Override
    public int getDefense() {
        return defense;
    }

    /**
     * Obtiene el valor de ataque especial del Pokémon.
     * @return El valor de ataque especial
     */
    @Override
    public int getSpecialAttack() {
        return specialAttack;
    }

    /**
     * Obtiene el valor de defensa especial del Pokémon.
     * @return El valor de defensa especial
     */
    @Override
    public int getSpecialDefense() {
        return specialDefense;
    }

    /**
     * Obtiene el valor de velocidad del Pokémon.
     * @return El valor de velocidad
     */
    @Override
    public int getSpeed() {
        return speed;
    }

    /**
     * Obtiene la lista de movimientos que conoce el Pokémon.
     * @return Lista de movimientos
     */
    @Override
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * Reduce los puntos de salud del Pokémon según el daño recibido.
     * @param amount Cantidad de daño a recibir
     */
    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
    }

    /**
     * Restaura puntos de salud al Pokémon.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
    }

    /**
     * Verifica si el Pokémon está debilitado.
     * @return true si el HP actual es 0, false en caso contrario
     */
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    /**
     * Enseña un nuevo movimiento al Pokémon obteniéndolo del repositorio.
     * @param moveName Nombre del movimiento a aprender
     */
    public void learnMove(String moveName) {
        Move move = MoveRepository.getMove(moveName);
        if (move != null) {
            this.moves.add(move);
        }
    }

    /**
     * Método abstracto para inicializar los movimientos del Pokémon.
     * Debe ser implementado por cada clase concreta de Pokémon.
     */
    @Override
    public abstract void initializeMoves();

    /**
     * Incrementa el ataque del Pokémon en la cantidad especificada.
     * @param amount Cantidad a incrementar
     */
    protected void incrementAttack(int amount) {
        this.attack += amount;
    }
}