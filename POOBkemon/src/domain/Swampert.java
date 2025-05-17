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

        // Movimientos por nivel (Mudkip, Marshtomp y Swampert)
        moves.add(new Move("Tackle", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Growl", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Mud-Slap", Type.GROUND, 20, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Water Gun", Type.WATER, 40, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Mud Sport", Type.GROUND, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Foresight", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Bide", Type.NORMAL, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Mud Shot", Type.GROUND, 55, 95, MoveCategory.SPECIAL));
        moves.add(new Move("Rock Throw", Type.ROCK, 50, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 100, MoveCategory.STATUS)); // ya estaba
        moves.add(new Move("Take Down", Type.NORMAL, 90, 85, MoveCategory.PHYSICAL));
        moves.add(new Move("Earthquake", Type.GROUND, 100, 100, MoveCategory.PHYSICAL)); // ya estaba
        moves.add(new Move("Endeavor", Type.NORMAL, 0, 100, MoveCategory.PHYSICAL));

        // Movimientos por huevo (Mudkip)
        moves.add(new Move("Stomp", Type.NORMAL, 65, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Counter", Type.FIGHTING, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Mirror Coat", Type.PSYCHIC, 0, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Curse", Type.GHOST, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Refresh", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Uproar", Type.NORMAL, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Yawn", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Ancient Power", Type.ROCK, 60, 100, MoveCategory.SPECIAL));

        // Movimientos por MT/MO
        moves.add(new Move("Surf", Type.WATER, 90, 100, MoveCategory.SPECIAL)); // ya estaba
        moves.add(new Move("Ice Beam", Type.ICE, 95, 100, MoveCategory.SPECIAL)); // ya estaba
        moves.add(new Move("Blizzard", Type.ICE, 120, 70, MoveCategory.SPECIAL));
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
        moves.add(new Move("Strength", Type.NORMAL, 80, 100, MoveCategory.PHYSICAL)); // MO04
        moves.add(new Move("Rock Tomb", Type.ROCK, 50, 80, MoveCategory.PHYSICAL));
        moves.add(new Move("Facade", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Focus Punch", Type.FIGHTING, 150, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Brick Break", Type.FIGHTING, 75, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Double Team", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Rest", Type.PSYCHIC, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Attract", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Earthquake", Type.GROUND, 100, 100, MoveCategory.PHYSICAL)); // repetido
        moves.add(new Move("Rock Slide", Type.ROCK, 75, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Waterfall", Type.WATER, 80, 100, MoveCategory.PHYSICAL)); // MO07
        moves.add(new Move("Dive", Type.WATER, 80, 100, MoveCategory.PHYSICAL));      // MO08

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
