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
        initializeMoves();
    }

    @Override
    protected void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
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