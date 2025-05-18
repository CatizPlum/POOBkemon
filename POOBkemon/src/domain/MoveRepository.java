package domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MoveRepository implements Serializable {
    private static final Map<String, Move> moves = new HashMap<>();

    static {
        //==ABSOL==//
        registerMove(new Move("Leer", Type.NORMAL, 0, 100, 30, MoveEffect.DEFENSE_DOWN, 1, MoveCategory.STATUS,100)); //Leer reduce la defensa del objetivo en una etapa.
        registerMove(new Move("Quick Attack", Type.NORMAL, 40, 100, 30, MoveEffect.NONE, 1, MoveCategory.PHYSICAL,100));//Ataque rápido inflige daño y tiene una prioridad de +1.
        registerMove(new Move("Double Team", Type.NORMAL, 0, 0, 15, MoveEffect.EVASION_UP, 0, MoveCategory.STATUS,100));//Double Team aumenta la evasión del usuario en una etapa, haciendo así que el usuario sea más difícil de golpear.
        registerMove(new Move("Knock Off", Type.DARK, 65, 100, 20, MoveEffect.NONE, 0, MoveCategory.PHYSICAL,100));//Inflinge daño
        registerMove(new Move("Future Sight", Type.PSYCHIC, 120, 100, 10, MoveEffect.NONE, 0, MoveCategory.SPECIAL,100)); // inflinge daño, pero no golpea hasta dos turnos después de que se use el movimiento.
        registerMove(new Move("Water Pulse", Type.WATER, 60, 100, 20, MoveEffect.CONFUSION, 0, MoveCategory.SPECIAL,20));//El pulso de agua inflige daño y tiene un 20% de posibilidades de confundir al objetivo.

        //==BLASTOISE==//
        registerMove(new Move("Tackle", Type.NORMAL, 40, 100,35, MoveEffect.NONE ,0, MoveCategory.PHYSICAL,100));//Causa daño sin efectos adicionales.
        registerMove(new Move("Tail Whip", Type.NORMAL, 0, 100,30,MoveEffect.NONE,0, MoveCategory.STATUS,100));//Tail Whip reduce la defensa del objetivo en un nivel.
        registerMove(new Move("Water Gun", Type.WATER, 40, 100,25,MoveEffect.NONE,0, MoveCategory.SPECIAL,100));//La pistola de agua inflige daño sin efecto adicional.
        registerMove(new Move("Withdraw", Type.WATER, 0, 0,40,MoveEffect.NONE,0, MoveCategory.STATUS,100));//Aumenta la defensa del usuario en una etapa.
        registerMove(new Move("Water Pulse", Type.WATER, 60, 100,20,MoveEffect.CONFUSION,0, MoveCategory.SPECIAL,20));//El pulso de agua inflige daño y tiene un 20% de posibilidades de confundir al objetivo.
        registerMove(new Move("Bite", Type.DARK, 60, 100,25,MoveEffect.FLINCH,0, MoveCategory.PHYSICAL,30));//La mordedura inflige daño y tiene un 30% de posibilidades de hacer que el objetivo retroceda (si el objetivo aún no ha retrocedido).

        //==CHARIZARD==//
        registerMove(new Move("Scratch", Type.NORMAL, 40, 100,35,MoveEffect.NONE,0, MoveCategory.PHYSICAL,100));//Es uno de los movimientos más comunes y básicos que aprende un Pokémon. Causa daño sin efectos adicionales.
        registerMove(new Move("Growl", Type.NORMAL, 0, 100, 40,MoveEffect.NONE,0,MoveCategory.STATUS,100));//Reduce el ataque del objetivo en una etapa.
        registerMove(new Move("Ember", Type.FIRE, 40, 100,25,MoveEffect.BURN,0, MoveCategory.SPECIAL,10));//inflige daño y tiene un 10% de probabilidad de quemar al objetivo.
        registerMove(new Move("Smokescreen", Type.NORMAL, 0, 100,20,MoveEffect.NONE,0, MoveCategory.STATUS,100));//Reduce la precisión del objetivo en una etapa.
        registerMove(new Move("Flamethrower", Type.FIRE, 90, 100,15,MoveEffect.BURN,0, MoveCategory.SPECIAL,10));//Inflige daño y tiene un 10% de probabilidad de quemar el objetivo.
        registerMove(new Move("Slash", Type.NORMAL, 70, 100,20,MoveEffect.NONE,0, MoveCategory.PHYSICAL,100));//Inflige daño.

        //==CROBAT==//
        // Movimientos por nivel
        registerMove(new Move("Cross Poison", Type.POISON, 70, 100,20,MoveEffect.POISON,0, MoveCategory.PHYSICAL,10)); // Inflige daño y tiene un 10% de probabilidad de envenenar al objetivo.
        registerMove(new Move("Air Slash", Type.FLYING, 75, 95,15,MoveEffect.FLINCH,0, MoveCategory.SPECIAL,30)); // Inflige daño y tiene un 30% de probabilidad de hacer que el objetivo retroceda (si el objetivo aún no ha retrocedido).
        registerMove(new Move("Bite", Type.DARK, 60, 100,25,MoveEffect.FLINCH,0, MoveCategory.PHYSICAL,30)); // Inflige daño y tiene un 30% de probabilidad de hacer que el objetivo retroceda (si el objetivo aún no ha retrocedido).
        registerMove(new Move("Mean Look", Type.NORMAL, 0, 0,5,MoveEffect.NONE,0, MoveCategory.STATUS,100)); //Opositor no puede huir ni cambiar.
        registerMove(new Move("Screech", Type.NORMAL, 0, 85,40,MoveEffect.NONE,0, MoveCategory.STATUS,100)); // Reduce la defensa del objetivo en dos etapas.
        registerMove(new Move("Absorb", Type.PLANT, 20, 100,25,MoveEffect.HP,0, MoveCategory.SPECIAL,100)); // Causa daños y el usuario recuperará el 50 % del HP drenado.
    }

    public static void registerMove(Move move) {
        moves.put(move.getName(), move);
    }

    public static Move getMove(String name) {
        return moves.get(name);
    }
}