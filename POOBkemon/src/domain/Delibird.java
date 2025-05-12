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

        // Movimientos por nivel (nivel de aprendizaje según Pokémon Esmeralda)
        moves.add(new Move("Present", Type.NORMAL, 0, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Drill Peck", Type.FLYING, 80, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Icy Wind", Type.ICE, 55, 95, MoveCategory.SPECIAL));
        moves.add(new Move("Quick Attack", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Blizzard", Type.ICE, 110, 70, MoveCategory.SPECIAL));
        moves.add(new Move("Fly", Type.FLYING, 90, 95, MoveCategory.PHYSICAL));

        // Movimientos por huevo
        moves.add(new Move("Pursuit", Type.DARK, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Astonish", Type.GHOST, 30, 100, MoveCategory.PHYSICAL));

        // Movimientos por TM (movimientos que Delibird puede aprender por TM)
        moves.add(new Move("TM01", Type.NORMAL, 50, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("TM14", Type.ICE, 60, 100, MoveCategory.SPECIAL));
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
