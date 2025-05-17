package domain;

import java.util.ArrayList;

public class Donphan extends AbstractPokemon {

    private boolean sturdyActivated;

    public Donphan() {
        this.name = "Donphan";
        this.primaryType = Type.GROUND;
        this.secondaryType = null;
        this.maxHP = 384;
        this.currentHP = maxHP;
        this.attack = 372;
        this.defense = 372;
        this.specialAttack = 240;
        this.specialDefense = 240;
        this.speed = 218;

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
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        // Si Donphan recibe daño que podría haberlo dejado con menos del 1% de HP, activa su habilidad Sturdy.
        if (currentHP <= 1 && !sturdyActivated) {
            sturdyActivated = true;
            System.out.println("¡Donphan resiste el ataque con su habilidad Sturdy!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            sturdyActivated = false; // Desactiva Sturdy cuando Donphan está bien de salud.
        }
    }

    public boolean hasSturdyActivated() {
        return sturdyActivated;
    }

    // Método especial para hacer un ataque de roca con Rollout
    public void rollout() {
        System.out.println("¡Donphan lanza Rollout con gran poder!");
        // Lógica de Rollout en combate
    }

    // Método adicional para utilizar Stealth Rock, que daña a los Pokémon enemigos que entran al campo
    public void stealthRock() {
        System.out.println("¡Donphan coloca Stealth Rock en el campo!");
    }
}