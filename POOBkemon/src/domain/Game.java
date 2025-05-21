package domain;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Clase que representa una partida de combate Pokémon entre dos entrenadores.
 * Controla el flujo del juego, turnos y estados de los entrenadores.
 * Implementa Serializable para permitir guardar y cargar partidas.
 */
public class Game implements Serializable {
    private static final int TURN_TIME_LIMIT = 20; // 20 segundos por turno
    private transient Timer turnTimer; // Timer para controlar el tiempo
    private int timeRemaining = TURN_TIME_LIMIT;
    private transient TimerTask countdownTask;
    private transient Runnable onTimeOutCallback;


    /**
     * Primer entrenador participante en la batalla
     */
    private Trainer trainer1;

    /**
     * Segundo entrenador participante en la batalla
     */
    private Trainer trainer2;

    /**
     * Entrenador cuyo turno es actual
     */
    private Trainer currentTrainer;

    /**
     * Entrenador que espera su turno
     */
    private Trainer waitingTrainer;

    /**
     * Constructor que inicializa una nueva partida con dos entrenadores
     * @param trainer1 Primer entrenador
     * @param trainer2 Segundo entrenador
     */
    public Game(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.currentTrainer = trainer1;
        this.waitingTrainer = trainer2;
    }

    public Game(String player1Name, String player2Name,
                Color color1, Color color2,
                List<Pokemon> team1, Map<Pokemon, List<Move>> moves1,
                List<Pokemon> team2, Map<Pokemon, List<Move>> moves2) {

        this.trainer1 = new Trainer(player1Name, color1, team1, moves1);
        this.trainer2 = new Trainer(player2Name, color2, team2, moves2);
        this.currentTrainer = trainer1;
        this.waitingTrainer = trainer2;
    }


    /**
     * Inicia el temporizador del turno.
     */
    public void startTurnTimer() {
        stopTurnTimer(); // Detener cualquier temporizador existente

        timeRemaining = TURN_TIME_LIMIT;
        turnTimer = new Timer();

        // Tarea para actualizar el contador cada segundo
        countdownTask = new TimerTask() {
            @Override
            public void run() {
                timeRemaining--;
                if (timeRemaining <= 0) {
                    timeOut();
                    this.cancel();
                }
            }
        };

        turnTimer.scheduleAtFixedRate(countdownTask, 1000, 1000);
    }

    /**
     * Detiene el temporizador del turno.
     */
    public void stopTurnTimer() {
        if (turnTimer != null) {
            turnTimer.cancel();
            turnTimer.purge();
            turnTimer = null;
        }
        if (countdownTask != null) {
            countdownTask.cancel();
            countdownTask = null;
        }
    }

    /**
     * Método que se ejecuta cuando se agota el tiempo del turno.
     * Reduce el PP de los movimientos especiales del Pokémon actual.
     */
    private void timeOut() {
        stopTurnTimer();

        // Reducir PP de movimientos especiales
        Pokemon currentPokemon = currentTrainer.getCurrentPokemon();
        if (currentPokemon instanceof AbstractPokemon) {
            ((AbstractPokemon) currentPokemon).reduceSpecialMovesPp();
        }

        // Notificar a la GUI (pero NO pasar al siguiente turno todavía)
        if (onTimeOutCallback != null) {
            SwingUtilities.invokeLater(onTimeOutCallback);
        }

        // ELIMINADO: nextTurn(); // Ahora esto lo manejará la GUI después de que el usuario presione OK
    }





    /**
     * Obtiene el primer entrenador
     * @return Objeto Trainer del primer jugador
     */
    public Trainer getTrainer1() {
        return trainer1;
    }

    /**
     * Obtiene el segundo entrenador
     * @return Objeto Trainer del segundo jugador
     */
    public Trainer getTrainer2() {
        return trainer2;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * Avanza al siguiente turno, intercambiando los entrenadores actual y en espera
     */
    // En el método nextTurn(), asegurarse de detener y reiniciar el temporizador
    public void nextTurn() {
        stopTurnTimer();
        Trainer temp = currentTrainer;
        currentTrainer = waitingTrainer;
        waitingTrainer = temp;
        startTurnTimer();
    }

    /**
     * Obtiene el entrenador cuyo turno es actual
     * @return Entrenador actual
     */
    public Trainer getCurrentTrainer() {
        return currentTrainer;
    }

    /**
     * Obtiene el entrenador que espera su turno
     * @return Entrenador en espera
     */
    public Trainer getWaitingTrainer() {
        return waitingTrainer;
    }

    /**
     * Verifica si la partida ha terminado
     * @return true si alguno de los Pokémon actuales está debilitado, false en caso contrario
     */
    public boolean isOver() {
        return trainer1.getCurrentPokemon().isFainted() || trainer2.getCurrentPokemon().isFainted();
    }

    /**
     * Activa la habilidad especial de Delibird (Regalo Sorpresa)
     * @throws PoobkemonException si el Pokémon activo no es un Delibird
     */
    public void triggerDelibirdGift() throws PoobkemonException {
        Pokemon current = currentTrainer.getCurrentPokemon();
        Pokemon opponent = waitingTrainer.getCurrentPokemon();

        if (current instanceof Delibird) {
            ((Delibird) current).surpriseGift(opponent);
        } else {
            throw new PoobkemonException("El Pokémon activo no es Delibird.");
        }
    }

    /**
     * Establece el callback para cuando se agote el tiempo
     */
    public void setOnTimeOutCallback(Runnable callback) {
        this.onTimeOutCallback = callback;
    }
    /**
     * Clase interna para manejar los datos de guardado
     */
    public static class SaveData implements Serializable {
        private final Game game;
        private final String mode;

        public SaveData(Game game, String mode) {
            this.game = game;
            this.mode = mode;
        }

        public Game getGame() {
            return game;
        }

        public String getMode() {
            return mode;
        }
    }

    /**
     * Guarda el estado actual del juego en un archivo
     * @param filePath Ruta del archivo donde guardar
     * @param mode Modo de juego actual
     * @throws IOException Si ocurre un error de E/S
     */
    public void saveToFile(String filePath, String mode) throws IOException {
        SaveData saveData = new SaveData(this, mode);
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File(filePath).toPath()))) {
            oos.writeObject(saveData);
        }
    }

    /**
     * Carga un juego desde un archivo
     * @param filePath Ruta del archivo a cargar
     * @return Objeto SaveData con el juego y modo cargados
     * @throws IOException Si ocurre un error de E/S
     * @throws ClassNotFoundException Si la clase del objeto serializado no se encuentra
     */
    public static SaveData loadFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(new File(filePath).toPath()))) {
            return (SaveData) ois.readObject();
        }
    }


}