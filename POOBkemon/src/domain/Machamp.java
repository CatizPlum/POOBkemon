package domain;

import java.util.ArrayList;

public class Machamp extends AbstractPokemon {
    public Machamp() {
        this.name = "Machamp";
        this.primaryType = Type.FIGHTING;
        this.secondaryType = null;
        this.maxHP = 384;
        this.currentHP = maxHP;
        this.attack = 394; // Muy alto
        this.defense = 284;
        this.specialAttack = 218;
        this.specialDefense = 273;
        this.speed = 207;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Low Kick", Type.FIGHTING, 50, 100, MoveCategory.PHYSICAL)); // Simulamos daño medio
        moves.add(new Move("Leer", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Focus Energy", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Seismic Toss", Type.FIGHTING, 70, 100, MoveCategory.PHYSICAL)); // Aproximado
        moves.add(new Move("Revenge", Type.FIGHTING, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Vital Throw", Type.FIGHTING, 70, 0, MoveCategory.PHYSICAL)); // Nunca falla
        moves.add(new Move("Cross Chop", Type.FIGHTING, 100, 80, MoveCategory.PHYSICAL));
        moves.add(new Move("Scary Face", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Submission", Type.FIGHTING, 80, 80, MoveCategory.PHYSICAL)); // Recoil
        moves.add(new Move("Dynamic Punch", Type.FIGHTING, 100, 50, MoveCategory.PHYSICAL)); // Confunde
        moves.add(new Move("Bulk Up", Type.FIGHTING, 0, 0, MoveCategory.STATUS));

        // Movimientos por huevo
        moves.add(new Move("Light Screen", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Meditate", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Encore", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Rolling Kick", Type.FIGHTING, 60, 85, MoveCategory.PHYSICAL));
        moves.add(new Move("SmellingSalt", Type.NORMAL, 60, 100, MoveCategory.PHYSICAL)); // doble daño si paralizado

        // Movimientos por MT
        moves.add(new Move("Earthquake", Type.GROUND, 100, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Rock Slide", Type.ROCK, 75, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Brick Break", Type.FIGHTING, 75, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Strength", Type.NORMAL, 80, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Fire Blast", Type.FIRE, 110, 85, MoveCategory.SPECIAL)); // Cobertura
        moves.add(new Move("ThunderPunch", Type.ELECTRIC, 75, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Ice Punch", Type.ICE, 75, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Facade", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
    }

    // Habilidad pasiva: aumenta daño físico
    public void applyPassiveAbility(Move move) {
        if (move.getCategory() == MoveCategory.PHYSICAL) {
            double boostedPower = move.getPower() * 1.25;
            move.setPower((int) boostedPower);
            System.out.println(this.name + "'s BruteForceBoost increases " + move.getName() + "'s power to " + move.getPower());
        }
    }



}
