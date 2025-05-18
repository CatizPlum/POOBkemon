package domain;

import java.util.ArrayList;

public class Swampert extends AbstractPokemon {
    private boolean muddyArmor;

    public Swampert() {
        this.name = "Swampert";
        this.primaryType = Type.WATER;
        this.secondaryType = Type.GROUND;
        this.maxHP = 404;
        this.currentHP = maxHP;
        this.attack = 350;
        this.defense = 306;
        this.specialAttack = 295;
        this.specialDefense = 306;
        this.speed = 240;

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

    public void receiveElectricAttack(int amount) {
        System.out.println("El ataque eléctrico no afecta a Swampert, ¡pero activa su armadura fangosa!");
        this.defense = (int)(this.defense * 1.2);
        this.muddyArmor = true;
    }

    public boolean isMuddyArmorActive() {
        return muddyArmor;
    }

}
