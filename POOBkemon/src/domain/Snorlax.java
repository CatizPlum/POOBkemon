package domain;

import java.util.ArrayList;

public class Snorlax extends AbstractPokemon {

    private boolean deeplyAsleep;

    public Snorlax() {
        this.name = "Snorlax";
        this.primaryType = Type.NORMAL;
        this.secondaryType = null;
        this.maxHP = 524;
        this.currentHP = maxHP;
        this.attack = 350;
        this.defense = 251;
        this.specialAttack = 251;
        this.specialDefense = 350;
        this.speed = 174;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Tackle", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Amnesia", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Defense Curl", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Belly Drum", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Headbutt", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Yawn", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Rest", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Snore", Type.NORMAL, 40, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Body Slam", Type.NORMAL, 85, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Rollout", Type.ROCK, 30, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Block", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));

        // Movimientos por MT/HM y tutor (igual que antes)...
        // [Omitidos aquí por brevedad, pero puedes mantenerlos igual]
    }

    public void restDeeply() {
        this.deeplyAsleep = true;
        this.currentHP = this.maxHP;
        System.out.println("Snorlax entra en un sueño profundo y se cura completamente.");
    }

    public void wakeUp() {
        this.deeplyAsleep = false;
        System.out.println("Snorlax se ha despertado.");
    }

    public boolean isDeeplyAsleep() {
        return deeplyAsleep;
    }

    public void snoreLoudly() {
        if (deeplyAsleep) {
            System.out.println("¡Snorlax ronca tan fuerte que asusta a sus enemigos!");
        } else {
            System.out.println("Snorlax no está dormido profundamente y no puede usar Ronquido.");
        }
    }
}
