package domain;

import java.util.ArrayList;

public class Crobat extends AbstractPokemon {

    public Crobat() {
        this.name = "Crobat";
        this.primaryType = Type.POISON;
        this.secondaryType = Type.FLYING;
        this.maxHP = 374;  // HP ligeramente menor que Seviper
        this.currentHP = maxHP;
        this.attack = 306;  // Ataque físico más bajo
        this.defense = 284;  // Defensa más baja
        this.specialAttack = 262;  // Ataque especial más bajo
        this.specialDefense = 284;  // Defensa especial más baja
        this.speed = 394;  // Velocidad mucho mayor (Crobat es uno de los Pokémon más rápidos)

        this.moves = new ArrayList<>();

        // Movimientos por nivel
        moves.add(new Move("Cross Poison", Type.POISON, 70, 100, MoveCategory.PHYSICAL)); // Movimiento característico
        moves.add(new Move("Air Slash", Type.FLYING, 75, 95, MoveCategory.SPECIAL)); // Movimiento de tipo volador
        moves.add(new Move("Bite", Type.DARK, 60, 100, MoveCategory.PHYSICAL)); // Movimiento de mordisco
        moves.add(new Move("Poison Fang", Type.POISON, 50, 100, MoveCategory.PHYSICAL)); // Movimiento venenoso
        moves.add(new Move("Supersonic", Type.NORMAL, 0, 55, MoveCategory.STATUS)); // Confunde al oponente
        moves.add(new Move("Toxic", Type.POISON, 0, 90, MoveCategory.STATUS)); // Envenena al oponente
        moves.add(new Move("Quick Attack", Type.NORMAL, 40, 100, MoveCategory.PHYSICAL)); // Ataque prioritario
        moves.add(new Move("Wing Attack", Type.FLYING, 60, 100, MoveCategory.PHYSICAL)); // Ataque de alas

        // Movimientos MT/HM
        moves.add(new Move("Shadow Ball", Type.GHOST, 80, 100, MoveCategory.SPECIAL)); // Buena cobertura
        moves.add(new Move("U-turn", Type.BUG, 70, 100, MoveCategory.PHYSICAL)); // Movimiento táctico
        moves.add(new Move("Steel Wing", Type.STEEL, 70, 90, MoveCategory.PHYSICAL)); // Movimiento de acero
        moves.add(new Move("Fly", Type.FLYING, 90, 95, MoveCategory.PHYSICAL)); // Movimiento volador fuerte

        // Movimientos de Tutor
        moves.add(new Move("Giga Drain", Type.PLANT, 75, 100, MoveCategory.SPECIAL)); // Para recuperar HP
        moves.add(new Move("Defog", Type.FLYING, 0, 0, MoveCategory.STATUS)); // Elimina trampas y reduce evasión
        moves.add(new Move("Brave Bird", Type.FLYING, 120, 100, MoveCategory.PHYSICAL)); // Ataque poderoso con retroceso
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        // Crobat no tiene habilidades especiales que afecten el daño recibido
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        // Sin habilidades especiales de curación
    }

    // Método especial para Cross Poison, su ataque característico
    public void crossPoison() {
        System.out.println("¡Crobat usa Cross Poison con alta probabilidad de golpe crítico!");
    }

    // Método para Air Slash, con posibilidad de hacer retroceder al oponente
    public void airSlash() {
        System.out.println("¡Crobat corta el aire con sus alas! Puede hacer retroceder al oponente.");
    }

    // Método para U-turn, ataque táctico
    public void uTurn() {
        System.out.println("¡Crobat usa U-turn, atacando y luego cambiando de Pokémon!");
    }

    // Método para Brave Bird, ataque poderoso con retroceso
    public void braveBird() {
        System.out.println("¡Crobat se lanza en picado con Brave Bird! Causa daño pero también recibe retroceso.");
    }
}