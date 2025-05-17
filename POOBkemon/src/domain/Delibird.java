package domain;

import java.util.ArrayList;
import java.util.Random;

public class Delibird extends AbstractPokemon {

    public Delibird() {
        this.name = "Delibird";
        this.primaryType = Type.ICE;
        this.secondaryType = Type.FLYING;
        this.maxHP = 294;
        this.currentHP = maxHP;
        this.attack = 229;
        this.defense = 207;
        this.specialAttack = 251;
        this.specialDefense = 207;
        this.speed = 273;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    @Override
    protected void initializeMoves() {
        learnMove("Leer");
        learnMove("Quick Attack");
        learnMove("Double Team");
        learnMove("Knock Off");
        learnMove("Future Sight");
        learnMove("Water Pulse");

    }

    // Habilidad pasiva: Regalo sorpresa
    public void surpriseGift(Pokemon opponent) {
        Random rand = new Random();
        int effect = rand.nextInt(3);  // Genera un valor aleatorio entre 0 y 2

        switch (effect) {
            case 0:
                System.out.println("Delibird entrega un regalo explosivo al oponente.");
                opponent.takeDamage(50);  // Inflige daño
                break;

            case 1:
                System.out.println("Delibird entrega un regalo curativo al oponente.");
                opponent.heal(30);  // Cura
                break;

            case 2:
                System.out.println("Delibird entrega un regalo que aumenta las estadísticas del oponente.");
                if (opponent instanceof AbstractPokemon) {
                    ((AbstractPokemon) opponent).incrementAttack(10);  // Aumenta ataque
                }
                break;
        }
    }

}
