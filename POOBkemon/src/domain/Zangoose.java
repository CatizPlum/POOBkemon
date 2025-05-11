package domain;

import java.util.ArrayList;

public class Zangoose extends AbstractPokemon {

    private boolean clawTemper; // Reemplaza a happyMood

    public Zangoose() {
        this.name = "Zangoose";
        this.primaryType = Type.NORMAL;
        this.secondaryType = null;
        this.maxHP = 350;
        this.currentHP = maxHP;
        this.attack = 361;
        this.defense = 240;
        this.specialAttack = 240;
        this.specialDefense = 240;
        this.speed = 306;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Leer", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Quick Attack", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Swords Dance", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Slash", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Taunt", Type.DARK, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Crush Claw", Type.NORMAL, 75, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Facade", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));

        // MT/HM
        moves.add(new Move("Focus Punch", Type.FIGHTING, 150, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Hidden Power", Type.NORMAL, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Taunt", Type.DARK, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Frustration", Type.NORMAL, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Return", Type.NORMAL, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Double Team", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Aerial Ace", Type.FLYING, 60, 0, MoveCategory.PHYSICAL));
        moves.add(new Move("Facade", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Secret Power", Type.NORMAL, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Attract", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Cut", Type.NORMAL, 50, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Strength", Type.NORMAL, 80, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Rock Smash", Type.FIGHTING, 20, 100, MoveCategory.PHYSICAL));

        // Tutor
        moves.add(new Move("Body Slam", Type.NORMAL, 85, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Counter", Type.FIGHTING, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Double-Edge", Type.NORMAL, 120, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Seismic Toss", Type.FIGHTING, 0, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Swagger", Type.NORMAL, 0, 90, MoveCategory.STATUS));

        // Crianza
        moves.add(new Move("Fury Cutter", Type.BUG, 10, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Metal Claw", Type.STEEL, 50, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Razor Wind", Type.NORMAL, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Roar", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("False Swipe", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Detect", Type.FIGHTING, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Flail", Type.NORMAL, 0, 100, MoveCategory.PHYSICAL));
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            clawTemper = true;
            System.out.println("¡Zangoose está enfurecido por el daño recibido!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            clawTemper = false;
            System.out.println("Zangoose se calma un poco al sentirse mejor.");
        }
    }

    public boolean isClawTemperActive() {
        return clawTemper;
    }

    public void brandishClaws() {
        if (clawTemper) {
            System.out.println("¡Zangoose muestra sus garras con furia, listo para la batalla!");
        } else {
            System.out.println("Zangoose mantiene la calma, observando a su oponente.");
        }
    }
}
