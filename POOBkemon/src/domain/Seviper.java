package domain;

import java.util.ArrayList;

public class Seviper extends AbstractPokemon {

    public Seviper() {
        this.name = "Seviper";
        this.primaryType = Type.POISON;
        this.secondaryType = Type.BUG;
        this.maxHP = 350;
        this.currentHP = maxHP;
        this.attack = 328;
        this.defense = 240;
        this.specialAttack = 328;
        this.specialDefense = 240;
        this.speed = 251;

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
        // No es necesario manejar habilidades como Levitate, ya que Seviper no la tiene.
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        // Seviper no tiene una habilidad activa como Levitate, por lo que no hay que cambiar la lógica aquí.
    }

    // Método especial para utilizar Sludge Bomb, un potente ataque de veneno
    public void sludgeBomb() {
        System.out.println("¡Seviper lanza Sludge Bomb, cubriendo al oponente en veneno!");
    }

    // Método adicional para utilizar Bug Bite, un ataque que consume energía del oponente
    public void bugBite() {
        System.out.println("¡Seviper usa Bug Bite, mordiendo con sus poderosas mandíbulas!");
    }

    // Método para usar Toxic y envenenar al oponente
    public void toxic() {
        System.out.println("¡Seviper usa Toxic y envenena al oponente!");
    }

    // Método adicional para usar Double Team y aumentar su evasión
    public void doubleTeam() {
        System.out.println("¡Seviper usa Double Team, aumentando su evasión!");
    }
}