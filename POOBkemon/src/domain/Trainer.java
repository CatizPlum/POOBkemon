package domain;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * La clase Trainer representa a un entrenador en un juego de Pokémon.
 * Cada entrenador tiene un nombre, color distintivo, equipo de Pokémon,
 * colección de ítems y un Pokémon activo en batalla.
 */
public class Trainer implements Serializable {
    private String name;               // Nombre del entrenador
    private Color color;              // Color asociado al entrenador
    private List<Pokemon> team;       // Lista de Pokémon en el equipo (máx 6)
    private Map<String, List<Item>> itemMap = new java.util.HashMap<>();
    private int activePokemonIndex;   // Índice del Pokémon activo en batalla
    private transient AbstractMachine machine; // Máquina asociada (solo para IA)

    /**
     * Constructor para crear un nuevo entrenador con ítems controlados.
     *
     * @param name  Nombre del entrenador
     * @param color Color asociado al entrenador
     * @param team  Lista de Pokémon que forman el equipo
     * @param items Lista de ítems que posee el entrenador
     */
    public Trainer(String name, Color color, List<Pokemon> team, List<Item> items) throws PoobkemonException {
        this.name = name;
        this.color = color;
        this.team = team;
        this.itemMap = new java.util.HashMap<>();
        this.activePokemonIndex = 0;

        for (Item item : items) {
            addItem(item);
        }
    }

    /**
     * Constructor para crear entrenador con movimientos personalizados (usado por IA).
     */
    public Trainer(String name, Color color,
                   List<Pokemon> team,
                   Map<Pokemon, List<Move>> moveMap) {
        this.name = name;
        this.color = color;
        this.team = team;
        this.itemMap = new java.util.HashMap<>();
        this.activePokemonIndex = 0;

        // Asignar los movimientos personalizados a cada Pokémon
        for (Pokemon p : this.team) {
            List<Move> customMoves = moveMap.get(p);
            if (customMoves != null && !customMoves.isEmpty()) {
                p.getMoves().clear();
                p.getMoves().addAll(customMoves);
            }
        }
    }

    /**
     * Constructor especial para registrar un AbstractMachine como entrenador.
     */
    public Trainer(AbstractMachine machine) {
        this.name = machine.getName();
        this.color = machine.getColor();
        this.team = machine.getTeam();
        this.itemMap = new java.util.HashMap<>();
        this.activePokemonIndex = 0;
        this.machine = machine;
    }

    /**
     * Devuelve la máquina IA asociada a este entrenador (si aplica).
     */
    public AbstractMachine getMachine() {
        return machine;
    }

    public void addItem(Item item) throws PoobkemonException {
        String key = item.getClass().getSimpleName();
        List<Item> list = itemMap.getOrDefault(key, new java.util.ArrayList<>());

        int maxAllowed = (item instanceof Revive) ? 1 : 2;
        if (list.size() >= maxAllowed) {
            throw new PoobkemonException("No puedes tener más de " + maxAllowed + " " + key + "(s).");
        }

        list.add(item);
        itemMap.put(key, list);
    }

    public Pokemon getCurrentPokemon() {
        return team.get(activePokemonIndex);
    }

    public void switchPokemon(int index) throws PoobkemonException {
        if (index < 0 || index >= team.size()) {
            throw new PoobkemonException("Índice inválido para cambio de Pokémon.");
        }
        if (team.get(index).isFainted()) {
            throw new PoobkemonException("¡Ese Pokémon está debilitado!");
        }
        activePokemonIndex = index;
    }

    public void useItem(Item item) throws PoobkemonException {
        item.apply(getCurrentPokemon());

        String key = item.getClass().getSimpleName();
        List<Item> list = itemMap.get(key);
        if (list != null) {
            list.remove(item);
            if (list.isEmpty()) {
                itemMap.remove(key);
            }
        }
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public List<Item> getItems() {
        List<Item> all = new java.util.ArrayList<>();
        for (List<Item> group : itemMap.values()) {
            all.addAll(group);
        }
        return all;
    }
}
