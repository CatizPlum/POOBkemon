package domain;

import java.util.ArrayList;


public class Masquerain extends AbstractPokemon{
    private boolean eyeThreatenedMode; // si entra en combate y logra intimidar al oponente

    public Masquerain(){
        this.name = "Masquerain";
        this.primaryType = Type.BUG;
        this.secondaryType = Type.FLYING;
        this.maxHP = 344;
        this.currentHP = maxHP;
        this.attack = 240;
        this.defense = 245;
        this.specialAttack = 284;
        this.specialDefense = 289;
        this.speed = 240;
        this.eyeThreatenedMode = true;
        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Bubble", Type.WATER, 40, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Quick Attack", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Sweet Scent", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Water Sport", Type.WATER, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Gust", Type.FLYING, 40, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Scary Face", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Stun Spore", Type.PLANT, 0, 75, MoveCategory.STATUS));
        moves.add(new Move("Silver Wind", Type.BUG, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Whirlwind", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Air Cutter", Type.FLYING, 55, 95, MoveCategory.SPECIAL));
        moves.add(new Move("Ominous Wind", Type.GHOST, 60, 100, MoveCategory.SPECIAL));

        // Movimientos por huevo
        moves.add(new Move("Hydro Pump", Type.WATER, 110, 80, MoveCategory.SPECIAL));
        moves.add(new Move("Psybeam", Type.PSYCHIC, 65, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Mud Shot", Type.GROUND, 55, 95, MoveCategory.SPECIAL));
        moves.add(new Move("Signal Beam", Type.BUG, 75, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Bug Bite", Type.BUG, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Agility", Type.PSYCHIC, 0, 100, MoveCategory.STATUS));

        // Movimientos por MT/MO (TM/HM)
        moves.add(new Move("Toxic", Type.POISON, 0, 90, MoveCategory.STATUS));
        moves.add(new Move("Hidden Power", Type.NORMAL, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Ice Beam", Type.ICE, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Rain Dance", Type.WATER, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("SolarBeam", Type.PLANT, 120, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Return", Type.NORMAL, 0, 100, MoveCategory.PHYSICAL)); // daño depende de amistad
        moves.add(new Move("Psychic", Type.PSYCHIC, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Double Team", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Aerial Ace", Type.FLYING, 60, -1, MoveCategory.PHYSICAL)); // nunca falla
        moves.add(new Move("Facade", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Rest", Type.PSYCHIC, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Attract", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Thief", Type.DARK, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Steel Wing", Type.STEEL, 70, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Substitute", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Flash", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Snore", Type.NORMAL, 50, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Sleep Talk", Type.NORMAL, 0, 100, MoveCategory.STATUS));
    }
    @Override
    public boolean isFainted() {
        return currentHP <= 0;
    }

    public boolean isEyeThreatenedMode() {
        return eyeThreatenedMode;
    }

}
