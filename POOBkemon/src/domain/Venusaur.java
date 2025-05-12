package domain;

import java.util.ArrayList;
import java.util.List;

public class Venusaur extends AbstractPokemon {

    private boolean flowerBlossomed;  // Estado de la flor
    
    public Venusaur() {
        this.name = "Venusaur";
        this.primaryType = Type.PLANT;
        this.secondaryType = Type.POISON;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 289;
        this.defense = 291;
        this.specialAttack = 328;
        this.specialDefense = 328;
        this.speed = 284;
        this.flowerBlossomed = true;
        
        // Inicializar movimientos de ejemplo
        this.moves = new ArrayList<>();
        moves.add(new Move("Rayo Solar", Type.PLANT, 120, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Bomba Lodo", Type.POISON, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Terremoto", Type.GROUND, 100, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Síntesis", Type.PLANT, 0, 100, MoveCategory.STATUS));
    }

    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
        
        // Si los PS bajan demasiado, la flor pierde su esplendor
        if (currentHP < maxHP * 0.3) {
            flowerBlossomed = false;
            System.out.println("¡La flor de Venusaur pierde su esplendor!");
        }
    }
    
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
        
        // Si se recupera lo suficiente, la flor florece de nuevo
        if (currentHP > maxHP * 0.6) {
            flowerBlossomed = true;
            System.out.println("¡La flor de Venusaur recupera su esplendor!");
        }
    }
    
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }
    
    // Métodos específicos de Venusaur
    public boolean isFlowerBlossomed() {
        return flowerBlossomed;
    }
    
    public void photosynthesis() {
        if (flowerBlossomed) {
            // Recuperación de PS mediante fotosíntesis
            int healAmount = maxHP / 4;
            heal(healAmount);
            System.out.println("¡Venusaur realiza fotosíntesis y recupera " + healAmount + " PS!");
        } else {
            System.out.println("La flor de Venusaur no está en condiciones para realizar fotosíntesis efectiva.");
        }
    }
    
    public void powderRelease() {
        // Libera esporas que pueden afectar al oponente
        System.out.println("¡Venusaur libera una nube de esporas desde su flor!");
        
        // Aumenta temporalmente la defensa y defensa especial
        this.defense = (int)(this.defense * 1.2);
        this.specialDefense = (int)(this.specialDefense * 1.2);
    }
} 