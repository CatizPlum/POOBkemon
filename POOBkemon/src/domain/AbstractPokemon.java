package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que implementa la interfaz Pokemon
 * Esta clase puede servir como base para implementaciones concretas
 * y proporciona implementaciones predeterminadas de algunos métodos
 */
public abstract class AbstractPokemon implements Pokemon, Serializable, Cloneable {
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
     * Implementación del método clone para permitir la clonación de Pokémon
     */
    @Override
    public Pokemon clone() {
        try {
            AbstractPokemon cloned = (AbstractPokemon) super.clone();
            // Clonar la lista de movimientos
            cloned.moves = new ArrayList<>();
            for (Move move : this.moves) {
                cloned.moves.add(move.clone());
            }
            // Restaurar HP al máximo
            cloned.currentHP = cloned.maxHP;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar Pokémon", e);
        }
    }

    /**
     * Reduce el PP de todos los movimientos especiales en 1
     */
    public void reduceSpecialMovesPp() {
        for (Move move : moves) {
            if (move.getCategory() == MoveCategory.SPECIAL && move.getPp() > 0) {
                move.setPp(move.getPp() - 1);
            }
        }
    }

    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Type getPrimaryType() {
        return primaryType;
    }
    
    @Override
    public Type getSecondaryType() {
        return secondaryType;
    }
    
    @Override
    public int getMaxHP() {
        return maxHP;
    }
    
    @Override
    public int getCurrentHP() {
        return currentHP;
    }
    
    @Override
    public int getAttack() {
        return attack;
    }
    
    @Override
    public int getDefense() {
        return defense;
    }
    
    @Override
    public int getSpecialAttack() {
        return specialAttack;
    }
    
    @Override
    public int getSpecialDefense() {
        return specialDefense;
    }
    
    @Override
    public int getSpeed() {
        return speed;
    }
    
    @Override
    public List<Move> getMoves() {
        return moves;
    }
    
    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
    }
    
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
    }
    
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    // Método protegido para aprender movimientos desde el repositorio
    public void learnMove(String moveName) {
        Move move = MoveRepository.getMove(moveName);
        if (move != null) {
            // Elimina la verificación del límite de 4 movimientos
            this.moves.add(move);
        }
    }

    // Método para inicializar movimientos (debe ser implementado por las clases hijas)
    @Override
    public abstract void initializeMoves();

    protected void incrementAttack(int i) {

    }
}
