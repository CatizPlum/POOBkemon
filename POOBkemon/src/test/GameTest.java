package test;

import domain.*;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Trainer trainer1;
    private Trainer trainer2;
    private Game game;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Move move1;
    private Move move2;
    private Item potion;
    private Item revive;


    // Pruebas para la clase Game
    @Test
    void testGameInitialization() {
        assertNotNull(game.getTrainer1());
        assertNotNull(game.getTrainer2());
        assertEquals("Ash", game.getTrainer1().getName());
        assertEquals("Gary", game.getTrainer2().getName());
        assertEquals(trainer1, game.getCurrentTrainer());
        assertEquals(trainer2, game.getWaitingTrainer());
    }

    @Test
    void testCurrentPokemon() {
        assertEquals("Absol", game.getCurrentTrainer().getCurrentPokemon().getName());
        assertEquals("Blastoise", game.getWaitingTrainer().getCurrentPokemon().getName());
    }

    @Test
    void testNextTurn() {
        assertEquals(trainer1, game.getCurrentTrainer());
        assertEquals(trainer2, game.getWaitingTrainer());

        game.nextTurn();
        assertEquals(trainer2, game.getCurrentTrainer());
        assertEquals(trainer1, game.getWaitingTrainer());

        game.nextTurn();
        assertEquals(trainer1, game.getCurrentTrainer());
        assertEquals(trainer2, game.getWaitingTrainer());
    }

    @Test
    void testIsOverWithSinglePokemon() {
        assertFalse(game.isOver());

        trainer1.getCurrentPokemon().takeDamage(1000);
        assertTrue(trainer1.getCurrentPokemon().isFainted());
        assertFalse(game.isOver());

        trainer2.getCurrentPokemon().takeDamage(1000);
        assertTrue(trainer2.getCurrentPokemon().isFainted());
        assertTrue(game.isOver());
    }

    @Test
    void testGameWithMultiplePokemon() throws PoobkemonException {
        List<Pokemon> team1 = new ArrayList<>();
        team1.add(new Absol());
        team1.add(new Blastoise());

        List<Pokemon> team2 = new ArrayList<>();
        team2.add(new Blastoise());
        team2.add(new Absol());

        Trainer multiTrainer1 = new Trainer("Ash", Color.RED, team1, new HashMap<>());
        Trainer multiTrainer2 = new Trainer("Gary", Color.BLUE, team2, new HashMap<>());
        Game multiGame = new Game(multiTrainer1, multiTrainer2);

        assertEquals("Absol", multiGame.getCurrentTrainer().getCurrentPokemon().getName());

        multiTrainer1.switchPokemon(1);
        assertEquals("Blastoise", multiGame.getCurrentTrainer().getCurrentPokemon().getName());
        assertEquals(multiTrainer1, multiGame.getCurrentTrainer());
    }

    @Test
    void testMachineAttack() {
        AbstractMachine machine = new AttackingMachine("AI", Color.GREEN,
                List.of(pokemon1), new HashMap<>());
        Game machineGame = new Game(trainer1, machine);

        int initialHP = trainer1.getCurrentPokemon().getCurrentHP();
        machineGame.machineAttack(machine, move1);

        assertTrue(trainer1.getCurrentPokemon().getCurrentHP() < initialHP);
    }

    @Test
    void testSetStartingTrainer() {
        game.setStartingTrainer(trainer2);
        assertEquals(trainer2, game.getCurrentTrainer());
        assertEquals(trainer1, game.getWaitingTrainer());
    }

    // Pruebas para la clase Trainer
    @Test
    void testTrainerInitialization() {
        assertEquals("Ash", trainer1.getName());
        assertEquals(Color.RED, trainer1.getColor());
        assertEquals(1, trainer1.getTeam().size());
        assertEquals("Absol", trainer1.getCurrentPokemon().getName());
    }

    @Test
    void testSwitchPokemon() throws PoobkemonException {
        trainer1.getTeam().add(new Blastoise());
        trainer1.switchPokemon(1);
        assertEquals("Blastoise", trainer1.getCurrentPokemon().getName());
    }

    @Test
    void testSwitchPokemonWithFaintedPokemon() {
        trainer1.getTeam().add(new Blastoise());
        trainer1.getTeam().get(1).takeDamage(1000);

        assertThrows(PoobkemonException.class, () -> trainer1.switchPokemon(1));
    }

    @Test
    void testAddItem() throws PoobkemonException {
        trainer1.addItem(potion);
        assertEquals(1, trainer1.getItems().size());
        assertEquals("Potion", trainer1.getItems().get(0).getName());
    }

    @Test
    void testUseItem() throws PoobkemonException {
        trainer1.addItem(potion);
        int initialHP = trainer1.getCurrentPokemon().getCurrentHP();
        trainer1.getCurrentPokemon().takeDamage(50);

        trainer1.useItem(potion);
        assertTrue(trainer1.getCurrentPokemon().getCurrentHP() > initialHP - 50);
        assertEquals(0, trainer1.getItems().size());
    }

    // Pruebas para la clase Pokemon
    @Test
    void testPokemonTakeDamage() {
        int initialHP = pokemon1.getCurrentHP();
        pokemon1.takeDamage(50);
        assertEquals(initialHP - 50, pokemon1.getCurrentHP());
    }

    @Test
    void testPokemonHeal() {
        pokemon1.takeDamage(50);
        int damagedHP = pokemon1.getCurrentHP();
        pokemon1.heal(30);
        assertEquals(damagedHP + 30, pokemon1.getCurrentHP());
    }

    @Test
    void testPokemonHealCannotExceedMaxHP() {
        pokemon1.heal(10);
        assertEquals(pokemon1.getMaxHP(), pokemon1.getCurrentHP());
    }

    @Test
    void testPokemonIsFainted() {
        assertFalse(pokemon1.isFainted());
        pokemon1.takeDamage(1000);
        assertTrue(pokemon1.isFainted());
    }


    // Pruebas para la clase Move
    @Test
    void testMoveInitialization() {
        assertEquals("Tackle", move1.getName());
        assertEquals(Type.NORMAL, move1.getType());
        assertEquals(40, move1.getPower());
        assertEquals(100, move1.getAccuracy());
        assertEquals(MoveCategory.PHYSICAL, move1.getCategory());
    }

    @Test
    void testMoveCalculateDamage() {
        int damage = move1.calculateDamage(pokemon1, pokemon2);
        assertTrue(damage > 0);
    }

    // Pruebas para la clase Item
    @Test
    void testPotionUse() throws PoobkemonException {
        pokemon1.takeDamage(50);
        int damagedHP = pokemon1.getCurrentHP();
        potion.apply(pokemon1);
        assertTrue(pokemon1.getCurrentHP() > damagedHP);
    }

    @Test
    void testPotionCannotUseOnFainted() {
        pokemon1.takeDamage(1000);
        assertThrows(PoobkemonException.class, () -> potion.apply(pokemon1));
    }

    @Test
    void testReviveUse() throws PoobkemonException {
        pokemon1.takeDamage(1000);
        revive.apply(pokemon1);
        assertTrue(pokemon1.getCurrentHP() > 0);
    }

    @Test
    void testReviveCannotUseOnHealthy() {
        assertThrows(PoobkemonException.class, () -> revive.apply(pokemon1));
    }

    // Pruebas para máquinas (AI)
    @Test
    void testAttackingMachine() {
        AttackingMachine machine = new AttackingMachine("AI", Color.GREEN,
                List.of(pokemon1), new HashMap<>());
        machine.getItems().add(potion);

        // Forzar condición de uso de ítem
        pokemon1.takeDamage(pokemon1.getMaxHP() / 2 + 10);
        machine.makeMove(game);

        // Verificar que usó el ítem
        assertTrue(pokemon1.getCurrentHP() > pokemon1.getMaxHP() / 2);
    }

    @Test
    void testDefensiveMachine() {
        DefensiveMachine machine = new DefensiveMachine("AI", Color.GREEN,
                List.of(pokemon1), new HashMap<>());
        machine.makeMove(game);
        // Verificar que se ejecutó sin errores
        assertTrue(true);
    }

    @Test
    void testExpertMachine() {
        ExpertMachine machine = new ExpertMachine("AI", Color.GREEN,
                List.of(pokemon1), new HashMap<>());
        machine.makeMove(game);
        // Verificar que se ejecutó sin errores
        assertTrue(true);
    }

    @Test
    void testChangingMachine() {
        ChangingMachine machine = new ChangingMachine("AI", Color.GREEN,
                List.of(pokemon1, pokemon2), new HashMap<>());
        machine.makeMove(game);
        // Verificar que se ejecutó sin errores
        assertTrue(true);
    }

    // Pruebas para tipos específicos de Pokémon
    @Test
    void testBlastoiseSpecialAbility() {
        Blastoise blastoise = (Blastoise) pokemon2;
        assertFalse(blastoise.areCannonsPowered());

        blastoise.takeDamage(blastoise.getMaxHP() * 4 / 5);
        assertFalse(blastoise.areCannonsPowered());

        blastoise.heal(blastoise.getMaxHP() / 2);
        assertTrue(blastoise.areCannonsPowered());
    }

    @Test
    void testCharizardSpecialAbility() {
        Charizard charizard = new Charizard();
        assertTrue(charizard.isFlameActive());

        charizard.takeDamage(charizard.getMaxHP() * 9 / 10);
        assertFalse(charizard.isFlameActive());
    }
}
