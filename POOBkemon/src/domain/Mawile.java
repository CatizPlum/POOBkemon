package domain;

import java.util.ArrayList;


public class Mawile extends AbstractPokemon {

    private boolean deceivingStance; // representa su habilidad de intimidar con apariencia inofensiva

    public Mawile (){
        this.name = "Mawile";
        this.primaryType = Type.STEEL;
        this.secondaryType = Type.FAIRY;
        this.maxHP = 304;
        this.currentHP = maxHP;
        this.attack = 295;
        this.defense = 295;
        this.specialAttack = 229;
        this.specialDefense = 229;
        this.speed = 218;
        this.deceivingStance = true;

        this.moves = new ArrayList<>();

        moves.add(new Move("Vice Grip", Type.NORMAL, 55, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Fake Tears", Type.DARK, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Bite", Type.DARK, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Sweet Scent", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Iron Defense", Type.STEEL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Crunch", Type.DARK, 80, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Iron Tail", Type.STEEL, 100, 75, MoveCategory.PHYSICAL));
        moves.add(new Move("Double-Edge", Type.NORMAL, 120, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Taunt", Type.DARK, 0, 100, MoveCategory.STATUS));

    }

    public boolean isDeceivingStance(){
        return deceivingStance;
    }
}
