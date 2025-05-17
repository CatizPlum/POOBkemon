package domain;

import java.util.ArrayList;

public class Blaziken extends AbstractPokemon {

    private boolean intenseFlamesActive = false; // Para comprobar si la habilidad está activa
    private int flamesTurns = 0; // Contador para la duración de las llamas intensas (si fuera temporal)

    public Blaziken() {
        this.name = "Blaziken";
        this.primaryType = Type.FIRE;
        this.secondaryType = Type.FIGHTING;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 372;
        this.defense = 262;
        this.specialAttack = 350;
        this.specialDefense = 262;
        this.speed = 284;
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


    // Habilidad pasiva: Llamas intensas
    public void intenseFlames() {
        System.out.println("Blaziken expulsa llamas intensas desde sus muñecas.");

        // Activamos la habilidad de Llamas Intensadas.
        if (!intenseFlamesActive) {
            intenseFlamesActive = true;
            this.attack *= 1.3; // Aumenta el ataque en un 30%
            System.out.println("¡El poder de Blaziken aumenta por las llamas intensas!");
        }
        //Duración de la habilidad es de tres turnos
        flamesTurns = 3;
    }

    // Método que puede ser llamado al final de cada turno para decrementar la duración de la habilidad
    public void endTurn() {
        if (intenseFlamesActive) {
            flamesTurns--;
            if (flamesTurns <= 0) {
                intenseFlamesActive = false;
                this.attack /= 1.3; // El ataque vuelve a su valor normal
                System.out.println("El poder de Blaziken disminuye al cesar las llamas intensas.");
            }
        }
    }
    public void igniteSpirit() {
        this.attack *= 1.05;
        this.speed *= 1.05;
        System.out.println("Blaziken's fighting spirit flares up!");
    }

}
