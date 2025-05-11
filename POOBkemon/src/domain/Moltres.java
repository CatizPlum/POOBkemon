package domain;

import java.util.ArrayList;

public class Moltres extends AbstractPokemon {

    public Moltres() {
        this.name = "Moltres";
        this.primaryType = Type.FIRE;
        this.secondaryType = Type.FLYING;
        this.maxHP = 380;  // HP alto como Pokémon legendario
        this.currentHP = maxHP;
        this.attack = 290;  // Ataque físico decente
        this.defense = 280;  // Defensa sólida
        this.specialAttack = 350;  // Ataque especial muy alto (especialidad de Moltres)
        this.specialDefense = 280;  // Defensa especial sólida
        this.speed = 290;  // Velocidad decente para legendario

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Wing Attack", Type.FLYING, 60, 100, MoveCategory.PHYSICAL));
        moves.add(new Move("Ember", Type.FIRE, 40, 100, MoveCategory.SPECIAL)); // Movimiento inicial de fuego
        moves.add(new Move("Fire Spin", Type.FIRE, 35, 85, MoveCategory.SPECIAL)); // Atrapa al oponente
        moves.add(new Move("Agility", Type.PSYCHIC, 0, 0, MoveCategory.STATUS)); // Aumenta velocidad
        moves.add(new Move("Ancient Power", Type.ROCK, 60, 100, MoveCategory.SPECIAL)); // Movimiento ancestral
        moves.add(new Move("Flamethrower", Type.FIRE, 90, 100, MoveCategory.SPECIAL)); // Ataque de fuego potente
        moves.add(new Move("Safeguard", Type.NORMAL, 0, 0, MoveCategory.STATUS)); // Protección de estados
        moves.add(new Move("Air Slash", Type.FLYING, 75, 95, MoveCategory.SPECIAL)); // Con posibilidad de retroceso
        moves.add(new Move("Heat Wave", Type.FIRE, 95, 90, MoveCategory.SPECIAL)); // Ataque de fuego de área
        moves.add(new Move("Solar Beam", Type.PLANT, 120, 100, MoveCategory.SPECIAL)); // Para cobertura contra agua/tierra

        // Movimientos MT/HM
        moves.add(new Move("Fire Blast", Type.FIRE, 110, 85, MoveCategory.SPECIAL)); // Ataque de fuego devastador
        moves.add(new Move("Hyper Beam", Type.NORMAL, 150, 90, MoveCategory.SPECIAL)); // Movimiento final
        moves.add(new Move("Will-O-Wisp", Type.FIRE, 0, 85, MoveCategory.STATUS)); // Quema al oponente
        moves.add(new Move("Roost", Type.FLYING, 0, 0, MoveCategory.STATUS)); // Recupera HP
        moves.add(new Move("Hurricane", Type.FLYING, 110, 70, MoveCategory.SPECIAL)); // Movimiento volador poderoso

        // Movimientos de Tutor
        moves.add(new Move("Sky Attack", Type.FLYING, 140, 90, MoveCategory.PHYSICAL)); // Movimiento de dos turnos
        moves.add(new Move("Burn Up", Type.FIRE, 130, 100, MoveCategory.SPECIAL)); // Ataque de fuego único
        moves.add(new Move("Defog", Type.FLYING, 0, 0, MoveCategory.STATUS)); // Elimina trampas
    }

    @Override
    public void takeDamage(int amount) {
        // Resistencia al fuego (daño reducido por movimientos de fuego)
        super.takeDamage(amount);
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
    }

    // Método especial para Fire Blast
    public void fireBlast() {
        System.out.println("¡Moltres lanza un devastador Fire Blast! ¡Puede causar quemaduras!");
    }

    // Método para Burn Up - ataque poderoso pero pierde tipo fuego
    public void burnUp() {
        System.out.println("¡Moltres usa Burn Up, liberando todo su poder ígneo! (Pierde tipo Fuego temporalmente)");
    }

    // Método para Solar Beam - carga energía solar
    public void solarBeam() {
        System.out.println("¡Moltres absorbe energía solar para lanzar un poderoso Solar Beam!");
    }

    // Método para Ancient Power - movimiento ancestral
    public void ancientPower() {
        System.out.println("¡Moltres invoca poder ancestral! Puede aumentar todas sus estadísticas.");
    }
}