package domain;

import java.util.ArrayList;

public class Pidgeot extends AbstractPokemon {

    public Pidgeot() {
        this.name = "Pidgeot";
        this.primaryType = Type.NORMAL;
        this.secondaryType = Type.FLYING;
        this.maxHP = 370;  // HP más alto que Crobat
        this.currentHP = maxHP;
        this.attack = 284;  // Ataque físico decente
        this.defense = 273;  // Defensa decente
        this.specialAttack = 262;  // Ataque especial más bajo
        this.specialDefense = 262;  // Defensa especial decente
        this.speed = 331;  // Velocidad alta pero no tanto como Crobat

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
        // Pidgeot no tiene habilidades especiales que afecten el daño recibido
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        // Sin habilidades especiales de curación
    }

    // Método especial para Hurricane, su ataque más poderoso
    public void hurricane() {
        System.out.println("¡Pidgeot crea un poderoso Hurricane! Puede confundir al oponente.");
    }

    // Método para Roost, que recupera HP
    public void roost() {
        System.out.println("¡Pidgeot se posa para descansar y recuperar HP!");
    }

    // Método para Feather Dance, que reduce el ataque del oponente
    public void featherDance() {
        System.out.println("¡Pidgeot realiza un Feather Dance, reduciendo el ataque del oponente!");
    }

    // Método para Sky Attack, movimiento poderoso de dos turnos
    public void skyAttack() {
        System.out.println("¡Pidgeot se prepara para un devastador Sky Attack en el próximo turno!");
    }
}