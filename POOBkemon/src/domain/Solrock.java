package domain;

import java.util.ArrayList;

public class Solrock extends AbstractPokemon {

    private boolean solarEnergy;

    public Solrock() {
        this.name = "Solrock";
        this.primaryType = Type.ROCK;
        this.secondaryType = Type.PSYCHIC;
        this.maxHP = 344;
        this.currentHP = maxHP;
        this.attack = 317;
        this.defense = 295;
        this.specialAttack = 229;
        this.specialDefense = 251;
        this.speed = 262;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Confusion", Type.PSYCHIC, 50, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Rock Throw", Type.ROCK, 50, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Solar Beam", Type.PLANT, 120, 100, MoveCategory.SPECIAL)); // Movimiento solar
        moves.add(new Move("Sunny Day", Type.FIRE, 0, 0, MoveCategory.STATUS)); // Aumenta el sol
        moves.add(new Move("Zen Headbutt", Type.PSYCHIC, 80, 90, MoveCategory.PHYSICAL)); // Movimiento Psíquico físico
        moves.add(new Move("Rock Slide", Type.ROCK, 75, 90, MoveCategory.PHYSICAL)); // Movimiento roca
        moves.add(new Move("Calm Mind", Type.PSYCHIC, 0, 0, MoveCategory.STATUS)); // Aumenta las estadísticas psíquicas
        moves.add(new Move("Explosion", Type.NORMAL, 250, 100, MoveCategory.PHYSICAL)); // Movimiento explosivo
        moves.add(new Move("Earthquake", Type.GROUND, 100, 100, MoveCategory.PHYSICAL)); // Movimiento tierra

        // Movimientos MT/HM
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Energy Ball", Type.PLANT, 80, 100, MoveCategory.SPECIAL)); // Otro movimiento de energía solar
        moves.add(new Move("Dazzling Gleam", Type.FAIRY, 80, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));

        // Movimientos de Tutor
        moves.add(new Move("Heal Bell", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Helping Hand", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Signal Beam", Type.BUG, 75, 100, MoveCategory.SPECIAL));
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            solarEnergy = false;
            System.out.println("¡Solrock pierde su energía solar por el daño recibido!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            solarEnergy = true;
            System.out.println("¡Solrock recarga su energía solar y se siente revitalizado!");
        }
    }

    public boolean hasSolarEnergy() {
        return solarEnergy;
    }

    public void solarBeam() {
        if (solarEnergy) {
            System.out.println("¡Solrock desata un rayo de energía solar!");
        } else {
            System.out.println("Solrock necesita recargar energía solar.");
        }
    }

    // Método adicional para utilizar Sunny Day y potenciar Solar Beam
    public void sunnyDay() {
        System.out.println("¡Solrock invoca el sol con Sunny Day!");
        solarEnergy = true; // Asegura que Solrock siempre tiene energía solar mientras el sol está activo.
    }
}
