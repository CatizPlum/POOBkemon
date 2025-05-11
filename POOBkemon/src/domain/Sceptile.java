package domain;

import java.util.ArrayList;

public class Sceptile extends AbstractPokemon {

    private boolean bladesSharpened;

    public Sceptile() {
        this.name = "Sceptile";
        this.primaryType = Type.PLANT;
        this.secondaryType = null;
        this.maxHP = 310;
        this.currentHP = maxHP;
        this.attack = 265;
        this.defense = 215;
        this.specialAttack = 300;
        this.specialDefense = 210;
        this.speed = 350;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Tackle", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Leer", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Absorb", Type.PLANT, 20, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Quick Attack", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Growth", Type.PLANT, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Synthesis", Type.PLANT, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Razor Leaf", Type.PLANT, 55, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Fury Cutter", Type.BUG, 10, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Mega Drain", Type.PLANT, 40, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Leaf Blade", Type.PLANT, 70, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Energy Ball", Type.PLANT, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Quick Attack", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));

        // Movimientos por MT/HM y tutor (puedes mantener igual que antes)
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            bladesSharpened = false;
            System.out.println("¡Sceptile pierde su filo tras recibir daño!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            bladesSharpened = true;
            System.out.println("¡Sceptile afila sus hojas mientras se recupera!");
        }
    }

    public boolean areBladesSharpened() {
        return bladesSharpened;
    }

    public void leafStorm() {
        if (bladesSharpened) {
            System.out.println("¡Sceptile lanza una tormenta de hojas afiladas devastadora!");
        } else {
            System.out.println("Sceptile no ha afilado sus hojas. ¡El ataque pierde potencia!");
        }
    }
}
