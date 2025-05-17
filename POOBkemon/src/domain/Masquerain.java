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
    @Override
    public boolean isFainted() {
        return currentHP <= 0;
    }

    public boolean isEyeThreatenedMode() {
        return eyeThreatenedMode;
    }

}
