package domain;

import java.util.ArrayList;

/**
 * Clase que representa al Pokémon Solrock, un Pokémon de tipo Roca/Psíquico.
 * Conocido como el Pokémon Meteorito, absorbe y almacena energía solar para potenciar sus ataques.
 * Su efectividad en combate depende de su nivel de energía solar acumulada.
 */
public class Solrock extends AbstractPokemon {

    /**
     * Indica si Solrock tiene energía solar acumulada.
     * Cuando es true:
     * - Potencia sus movimientos de tipo Fuego y Psíquico
     * - Permite usar Solar Beam sin carga
     * - Aumenta su capacidad defensiva
     */
    private boolean solarEnergy;

    /**
     * Constructor de Solrock. Inicializa sus estadísticas base, tipos y movimientos.
     * Características principales:
     * - Defensa física sólida (295)
     * - Ataque físico considerable (317)
     * - Tipo dual Roca/Psíquico
     * - HP moderado (344)
     * - Velocidad decente (262)
     */
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
        this.solarEnergy = true; // Comienza con energía solar

        this.moves = new ArrayList<>();
        initializeMoves();
    }

    /**
     * Inicializa los movimientos que Solrock puede aprender por defecto.
     * Incluye movimientos de varios tipos para versatilidad en combate.
     */
    @Override
    protected void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }

    /**
     * Método para recibir daño. Afecta su reserva de energía solar.
     * @param amount Cantidad de daño recibido
     *
     * Efecto secundario:
     * - Pierde energía solar si recibe daño significativo (>25% HP máximo)
     */
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            solarEnergy = false;
            System.out.println("¡Solrock pierde su energía solar por el daño recibido!");
        }
    }

    /**
     * Método para curar HP. Restaura energía solar si HP >70%.
     * @param amount Cantidad de HP a recuperar
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            solarEnergy = true;
            System.out.println("¡Solrock recarga su energía solar y se siente revitalizado!");
        }
    }

    /**
     * Verifica el estado de energía solar de Solrock.
     * @return true si tiene energía solar acumulada, false en caso contrario
     */
    public boolean hasSolarEnergy() {
        return solarEnergy;
    }

    /**
     * Ataque especial: Solar Beam.
     * Solo funciona cuando Solrock tiene energía solar acumulada.
     * Potencia: 120 | Tipo: Planta | Sin necesidad de carga con energía solar
     */
    public void solarBeam() {
        if (solarEnergy) {
            System.out.println("¡Solrock desata un rayo de energía solar!");
        } else {
            System.out.println("Solrock necesita recargar energía solar.");
        }
    }

    /**
     * Cambio climático: Sunny Day.
     * Activa luz solar intensa y recarga energía solar.
     * Efectos:
     * - Recarga energía solar automáticamente
     * - Potencia movimientos de tipo Fuego en 50%
     * - Reduce potencia de movimientos Agua en 50%
     * - Dura 5 turnos
     */
    public void sunnyDay() {
        System.out.println("¡Solrock invoca el sol con Sunny Day!");
        solarEnergy = true;
    }
}
