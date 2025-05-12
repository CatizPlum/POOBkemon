package test;

import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Trainer trainer1;
    private Trainer trainer2;
    private Game game;

    @BeforeEach
    void setUp() {
        List<Pokemon> team1 = new ArrayList<>();
        team1.add(new Absol());

        List<Pokemon> team2 = new ArrayList<>();
        team2.add(new Blastoise());

        trainer1 = new Trainer("Ash", Color.RED, team1, new ArrayList<>());
        trainer2 = new Trainer("Gary", Color.BLUE, team2, new ArrayList<>());

        game = new Game(trainer1, trainer2);
    }

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
        // Turno inicial
        assertEquals(trainer1, game.getCurrentTrainer());
        assertEquals(trainer2, game.getWaitingTrainer());

        // Cambio de turno
        game.nextTurn();
        assertEquals(trainer2, game.getCurrentTrainer());
        assertEquals(trainer1, game.getWaitingTrainer());

        // Segundo cambio
        game.nextTurn();
        assertEquals(trainer1, game.getCurrentTrainer());
        assertEquals(trainer2, game.getWaitingTrainer());
    }

    @Test
    void testIsOverWithSinglePokemon() {
        // Juego no termina con Pokémon activos
        assertFalse(game.isOver());

        // Debilitar el Pokémon de Ash
        trainer1.getCurrentPokemon().takeDamage(1000);
        assertTrue(trainer1.getCurrentPokemon().isFainted());
        assertFalse(game.isOver()); // Gary todavía tiene su Pokémon

        // Debilitar el Pokémon de Gary
        trainer2.getCurrentPokemon().takeDamage(1000);
        assertTrue(trainer2.getCurrentPokemon().isFainted());
        assertTrue(game.isOver());
    }

    @Test
    void testGameWithMultiplePokemon() throws PoobkemonException {
        // Configurar equipos con múltiples Pokémon
        List<Pokemon> team1 = new ArrayList<>();
        team1.add(new Absol());
        team1.add(new Blastoise());

        List<Pokemon> team2 = new ArrayList<>();
        team2.add(new Blastoise());
        team2.add(new Absol());

        Trainer multiTrainer1 = new Trainer("Ash", Color.RED, team1, new ArrayList<>());
        Trainer multiTrainer2 = new Trainer("Gary", Color.BLUE, team2, new ArrayList<>());
        Game multiGame = new Game(multiTrainer1, multiTrainer2);

        // Verificar Pokémon inicial
        assertEquals("Absol", multiGame.getCurrentTrainer().getCurrentPokemon().getName());

        // Cambiar Pokémon
        multiTrainer1.switchPokemon(1);
        assertEquals("Blastoise", multiGame.getCurrentTrainer().getCurrentPokemon().getName());

        // Verificar que el cambio no afecta el turno
        assertEquals(multiTrainer1, multiGame.getCurrentTrainer());
    }
}