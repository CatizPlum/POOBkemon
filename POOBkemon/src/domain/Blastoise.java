package domain;

import java.util.ArrayList;

public class Blastoise extends AbstractPokemon  {

    private boolean cannonsPowered;  // Estado de los cañones
    
    public Blastoise() {
        this.name = "Blastoise";
        this.primaryType = Type.WATER;
        this.secondaryType = null;
        this.maxHP = 362;
        this.currentHP = maxHP;
        this.attack = 291;
        this.defense = 328;
        this.specialAttack = 295;
        this.specialDefense = 339;
        this.speed = 280;
        this.cannonsPowered = true;

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Tackle", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Tail Whip", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Bubble", Type.WATER, 20, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Withdraw", Type.WATER, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Water Gun", Type.WATER, 40, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Bite", Type.DARK, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Rapid Spin", Type.NORMAL, 20, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Protect", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Rain Dance", Type.WATER, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Skull Bash", Type.NORMAL, 130, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Hydro Pump", Type.WATER, 110, 80, MoveCategory.SPECIAL));

        // Movimientos por Huevo
        moves.add(new Move("Mirror Coat", Type.PSYCHIC, 0, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Haze", Type.ICE, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Foresight", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Yawn", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Fake Out", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL));

        // Movimientos por MT/MO
        moves.add(new Move("Ice Beam", Type.ICE, 95, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Blizzard", Type.ICE, 110, 70, MoveCategory.SPECIAL));
        moves.add(new Move("Earthquake", Type.GROUND, 100, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Brick Break", Type.FIGHTING, 75, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Surf", Type.WATER, 90, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Strength", Type.NORMAL, 80, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Rock Tomb", Type.ROCK, 60, 95, MoveCategory.PHYSICAL));
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL));
    }

    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
        // Si los PS bajan demasiado, la potencia de los cañones disminuye
        if (currentHP < maxHP * 0.2) {
            cannonsPowered = false;
            System.out.println("¡Los cañones de Blastoise pierden presión!");
        }
    }
    
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
        
        // Si se recupera lo suficiente, los cañones recuperan potencia
        if (currentHP > maxHP * 0.5) {
            cannonsPowered = true;
            System.out.println("¡Los cañones de Blastoise recuperan su presión normal!");
        }
    }

    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }
    // Métodos específicos de Blastoise
    public boolean areCannonsPowered() {
        return cannonsPowered;
    }
    
    public void shellDefense() {
        // Incrementa temporalmente la defensa
        this.defense = (int)(this.defense * 1.5);
        System.out.println("¡Blastoise se refugia en su caparazón! Su defensa aumenta considerablemente.");
    }
    
    public void hydroCannon() {
        if (cannonsPowered) {
            // Disparo concentrado que aumenta el poder de ataque de agua
            for (Move move : moves) {
                if (move.getType() == Type.WATER) {
                    move.setPower((int)(move.getPower() * 1.3));
                }
            }
            System.out.println("¡Blastoise prepara sus cañones para un disparo concentrado!");
        } else {
            System.out.println("Los cañones de Blastoise no tienen suficiente presión para un disparo concentrado.");
        }
    }
}