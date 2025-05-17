package domain;

import java.util.List;

/**
 * Clase abstracta que implementa la interfaz Pokemon
 * Esta clase puede servir como base para implementaciones concretas
 * y proporciona implementaciones predeterminadas de algunos métodos
 */
public abstract class AbstractPokemon implements Pokemon {
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
    protected void learnMove(String moveName) {
        Move move = MoveRepository.getMove(moveName);
        if (move != null && moves.size() < 4) { // Límite de 4 movimientos
            moves.add(move);
        }
    }

    // Método para inicializar movimientos (debe ser implementado por las clases hijas)
    protected abstract void initializeMoves();

    protected void incrementAttack(int i) {

    }
}