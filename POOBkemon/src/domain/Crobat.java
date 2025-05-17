package domain;

import java.util.ArrayList;

public class Crobat extends AbstractPokemon {

    public Crobat() {
        this.name = "Crobat";
        this.primaryType = Type.POISON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 374;  // HP ligeramente menor que Seviper
        this.currentHP = maxHP;
        this.attack = 306;  // Ataque físico más bajo
        this.defense = 284;  // Defensa más baja
        this.specialAttack = 262;  // Ataque especial más bajo
        this.specialDefense = 284;  // Defensa especial más baja
        this.speed = 394;  // Velocidad mucho mayor (Crobat es uno de los Pokémon más rápidos)

        this.moves = new ArrayList<>();

        initializeMoves();
    }

    @Override
    protected void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }


    @Override
    public void heal(int amount) {
        super.heal(amount);
    }

    // Método especial para Cross Poison, su ataque característico
    public void crossPoison() {
        System.out.println("¡Crobat usa Cross Poison con alta probabilidad de golpe crítico!");
    }

    // Método para Air Slash, con posibilidad de hacer retroceder al oponente
    public void airSlash() {
        System.out.println("¡Crobat corta el aire con sus alas! Puede hacer retroceder al oponente.");
    }

    // Método para U-turn, ataque táctico
    public void uTurn() {
        System.out.println("¡Crobat usa U-turn, atacando y luego cambiando de Pokémon!");
    }

    // Método para Brave Bird, ataque poderoso con retroceso
    public void braveBird() {
        System.out.println("¡Crobat se lanza en picado con Brave Bird! Causa daño pero también recibe retroceso.");
    }
}