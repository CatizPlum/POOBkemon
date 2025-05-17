package domain;

import java.util.ArrayList;

public class Glalie extends AbstractPokemon {

    public Glalie() {
        this.name = "Glalie";
        this.primaryType = Type.ICE;
        this.secondaryType = null;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 284;
        this.defense = 284;
        this.specialAttack = 284;
        this.specialDefense = 284;
        this.speed = 284;
        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Powder Snow", Type.ICE, 40, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Leer", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Double Team", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Bite", Type.DARK, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Icy Wind", Type.ICE, 55, 95, MoveCategory.SPECIAL));
        moves.add(new Move("Headbutt", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Crunch", Type.DARK, 80, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Ice Beam", Type.ICE, 95, 100, MoveCategory.SPECIAL));

        // Movimientos por huevo
        moves.add(new Move("Block", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Disable", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Rollout", Type.ROCK, 30, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Spikes", Type.GROUND, 0, 0, MoveCategory.STATUS));

        // Movimientos por TM
        moves.add(new Move("Hail", Type.ICE, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Hidden Power", Type.NORMAL, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Ice Beam", Type.ICE, 95, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Blizzard", Type.ICE, 120, 70, MoveCategory.SPECIAL));
        moves.add(new Move("Rest", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Attract", Type.NORMAL, 0, 100, MoveCategory.STATUS));
    }
    // Habilidad pasiva: Congelación instantánea
    public void instantFreeze() {
        System.out.println("Glalie congela instantáneamente la humedad del aire.");
        // Aquí podrías implementar un efecto de congelación simple si el juego lo permite.
    }


}
