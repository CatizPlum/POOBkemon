package domain;

import java.util.ArrayList;

public class Flygon extends AbstractPokemon {

    private boolean levitateActivated;

    public Flygon() {
        this.name = "Flygon";
        this.primaryType = Type.GROUND;
        this.secondaryType = Type.DRAGON;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 328;
        this.defense = 284;
        this.specialAttack = 284;
        this.specialDefense = 284;
        this.speed = 328;

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
        // Si Flygon recibe daño de un movimiento de tipo tierra, revisamos si tiene Levitate.
        if (this.primaryType == Type.GROUND || (this.secondaryType != null && this.secondaryType == Type.GROUND)) {
            if (levitateActivated) {
                System.out.println("¡Flygon es inmune a los movimientos de tipo Tierra gracias a su habilidad Levitate!");
                // Si es inmune a movimientos de tipo Tierra debido a Levitate, no recibe el daño.
                return;
            }
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        // Si la salud de Flygon se encuentra por encima del 70%, desactiva su habilidad Levitate.
        if (currentHP > maxHP * 0.7) {
            levitateActivated = true;
        }
    }

    public boolean hasLevitateActivated() {
        return levitateActivated;
    }

    // Método especial para utilizar Dragon Claw, un fuerte ataque de tipo Dragón
    public void dragonClaw() {
        System.out.println("¡Flygon utiliza Dragon Claw con gran poder!");
    }

    // Método adicional para usar Fly y hacer daño mientras vuela
    public void fly() {
        System.out.println("¡Flygon se eleva en el aire para realizar un potente ataque Fly!");
    }

    // Método adicional para usar U-turn y cambiar de lugar
    public void uTurn() {
        System.out.println("¡Flygon usa U-turn y cambia de Pokémon rápidamente!");
    }

    // Método para activar la habilidad Levitate cuando Flygon está en el aire
    public void activateLevitate() {
        levitateActivated = true;
        System.out.println("¡Flygon activa su habilidad Levitate!");
    }

    // Método para desactivar la habilidad Levitate cuando Flygon está en el suelo
    public void deactivateLevitate() {
        levitateActivated = false;
        System.out.println("¡Flygon desactiva su habilidad Levitate!");
    }
}