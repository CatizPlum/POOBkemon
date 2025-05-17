package domain;

import java.util.ArrayList;

public class Raichu extends AbstractPokemon {

    public Raichu() {
        this.name = "RAICHU";
        this.primaryType = Type.ELECTRIC;
        this.secondaryType = null;
        this.maxHP = 324;
        this.currentHP = maxHP;
        this.attack = 306;
        this.defense = 229;
        this.specialAttack = 306;
        this.specialDefense = 284;
        this.speed = 350;

        this.moves = new ArrayList<>();

        // Movimientos por nivel (cuando era Pikachu)
        moves.add(new Move("Thunderbolt", Type.ELECTRIC, 95, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Quick Attack", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Thunder Wave", Type.ELECTRIC, 0, 90, MoveCategory.STATUS));
        moves.add(new Move("Slam", Type.NORMAL, 80, 75, MoveCategory.PHYSICAL));
        moves.add(new Move("Agility", Type.PSYCHIC, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Thunder", Type.ELECTRIC, 120, 70, MoveCategory.SPECIAL));
        moves.add(new Move("Growl", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Tail Whip", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Spark", Type.ELECTRIC, 65, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Feint", Type.NORMAL, 30, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Electro Ball", Type.ELECTRIC, 0, 100, MoveCategory.SPECIAL)); // efecto variable

        // Movimientos por huevo (de Pichu)
        moves.add(new Move("Reversal", Type.FIGHTING, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Wish", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Charge", Type.ELECTRIC, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Fake Out", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Present", Type.NORMAL, 0, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Volt Tackle", Type.ELECTRIC, 120, 100, MoveCategory.PHYSICAL));

        // Movimientos por MT/MO
        moves.add(new Move("Iron Tail", Type.STEEL, 100, 75, MoveCategory.PHYSICAL)); // ya estaba
        moves.add(new Move("Thunder", Type.ELECTRIC, 120, 70, MoveCategory.SPECIAL));
        moves.add(new Move("Thunderbolt", Type.ELECTRIC, 95, 100, MoveCategory.SPECIAL)); // ya estaba
        moves.add(new Move("Shock Wave", Type.ELECTRIC, 60, -1, MoveCategory.SPECIAL));
        moves.add(new Move("Hidden Power", Type.NORMAL, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Rain Dance", Type.WATER, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Return", Type.NORMAL, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Facade", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Brick Break", Type.FIGHTING, 75, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Focus Punch", Type.FIGHTING, 150, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Double Team", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Rest", Type.PSYCHIC, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Attract", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Strength", Type.NORMAL, 80, 100, MoveCategory.PHYSICAL)); // MO04
        moves.add(new Move("Flash", Type.NORMAL, 0, 100, MoveCategory.STATUS));       // MO05

    }

    public boolean tryStaticEffect() {
        double chance = Math.random();
        if (chance < 0.3) {
            System.out.println("¡El atacante fue paralizado por electricidad estática!");
            return true;
        }
        return false;
    }
}
