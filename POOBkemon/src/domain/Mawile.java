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

    public boolean isDeceivingStance(){
        return deceivingStance;
    }
}
