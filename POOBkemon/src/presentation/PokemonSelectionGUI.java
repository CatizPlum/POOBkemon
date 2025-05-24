package presentation;

import domain.*;
import javax.swing.*;
import java.awt.*;
import domain.Type;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.List;

import static domain.Type.*;
/**
 * Interfaz gráfica para la selección del equipo de Pokémon por parte del jugador.
 *
 * Esta clase permite al jugador elegir un equipo de 6 Pokémon, seleccionar los movimientos
 * para cada uno, y maneja tanto los modos jugador contra jugador (PvP) como jugador contra máquina (PvM).
 *
 * Atributos principales:
 * - mode: modo de juego ("PvsP" o "PvsM").
 * - playerName, playerColor: datos del jugador actual.
 * - opponentName, opponentColor: datos del oponente.
 * - isSecondPlayer: indica si esta instancia es para el segundo jugador en PvP.
 * - firstPlayerTeam, firstPlayerMoves: datos del equipo y movimientos del primer jugador en PvP.
 * - selectedPokemons, selectedMoves: lista y movimientos del equipo seleccionado actualmente.
 *
 * Constructor:
 *
 * @param //mode          modo de juego (ej. "PvsP" o "PvsM")
 * @param //playerName    nombre del jugador actual
 * @param //playerColor   color asociado al jugador actual
 * @param //opponentName  nombre del oponente
 * @param //opponentColor color asociado al oponente
 * @param //isSecondPlayer indica si esta instancia es para el segundo jugador (PvP)
 */
public class PokemonSelectionGUI extends JFrame {
    private static final int POKEMON_PER_TRAINER = 6;
    private static final int MOVES_PER_POKEMON = 4;

    private final String mode;
    private final String playerName;
    private final Color playerColor;
    private final String opponentName;
    private final Color opponentColor;
    private final boolean isSecondPlayer;
    private boolean isFirstPlayerInPvP;
    private List<Pokemon> firstPlayerTeam;
    private Map<Pokemon, List<Move>> firstPlayerMoves;

    private List<Pokemon> selectedPokemons = new ArrayList<>();
    private Map<Pokemon, List<Move>> selectedMoves = new HashMap<>();


    public PokemonSelectionGUI(String mode, String playerName, Color playerColor,
                               String opponentName, Color opponentColor, boolean isSecondPlayer) {
        this.mode = mode;
        this.playerName = playerName;
        this.playerColor = playerColor;
        this.opponentName = opponentName;
        this.opponentColor = opponentColor;
        this.isSecondPlayer = isSecondPlayer;

        configureWindow();
        initComponents();
    }

    /**
     * Configura las propiedades básicas de la ventana principal.
     *
     * - Establece el título de la ventana incluyendo el nombre del jugador.
     * - Define el tamaño inicial de la ventana (1000x700 píxeles).
     * - Configura la operación por defecto al cerrar la ventana (DISPOSE_ON_CLOSE).
     * - Centra la ventana en la pantalla.
     * - Permite que la ventana sea redimensionable por el usuario.
     */
    private void configureWindow() {
        setTitle("POOBkemon - Selección para " + playerName);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    /**
     * Inicializa y configura los componentes gráficos principales de la ventana.
     *
     * Crea un panel principal con un layout BorderLayout que contiene:
     * - Un título centrado en la parte superior con el nombre del jugador y la instrucción para seleccionar 6 Pokémon.
     * - Un panel central con la selección de Pokémon dentro de un JScrollPane para facilitar la navegación.
     * - Un panel inferior con controles (como el botón para confirmar el equipo).
     *
     * Finalmente, añade el panel principal al contenedor de la ventana.
     */
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de título
        JLabel titleLabel = new JLabel(playerName + " - Selecciona tu equipo (6 Pokémon)");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de selección de Pokémon
        JPanel pokemonPanel = createPokemonSelectionPanel();
        mainPanel.add(new JScrollPane(pokemonPanel), BorderLayout.CENTER);

        // Panel de control
        JPanel controlPanel = createControlPanel();
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Crea un panel que muestra una cuadrícula de botones para seleccionar Pokémon.
     *
     * Este panel carga todos los Pokémon disponibles, los ordena alfabéticamente
     * y genera un botón personalizado para cada uno utilizando el método `createPokemonButton`.
     * Si ocurre algún error al cargar o crear los botones, se muestra un mensaje de error.
     *
     * @return Un JPanel que contiene una cuadrícula de botones para la selección de Pokémon.
     */
    private JPanel createPokemonSelectionPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 4, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        List<Pokemon> allPokemons;
        try {
            allPokemons = createAllPokemons();
            if (allPokemons == null || allPokemons.isEmpty()) {
                throw new RuntimeException("No se pudieron cargar los Pokémon");
            }

            // Ordenar Pokémon alfabéticamente con manejo de nombres nulos
            allPokemons.sort((p1, p2) -> {
                String name1 = p1.getName() != null ? p1.getName() : "";
                String name2 = p2.getName() != null ? p2.getName() : "";
                return name1.compareToIgnoreCase(name2);
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar la lista de Pokémon: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return panel;
        }

        for (Pokemon pokemon : allPokemons) {
            try {
                if (pokemon.getName() != null) { // Solo agregar Pokémon con nombre válido
                    JButton btn = createPokemonButton(pokemon);
                    if (btn != null) {
                        panel.add(btn);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error creando botón para Pokémon: " + e.getMessage());
                JLabel errorLabel = new JLabel("Error", SwingConstants.CENTER);
                errorLabel.setForeground(Color.RED);
                panel.add(errorLabel);
            }
        }

        return panel;
    }

    /**
     * Crea un botón personalizado para un Pokémon dado.
     *
     * El botón incluye la imagen del Pokémon, su nombre y un color de fondo
     * basado en su tipo principal. Al hacer clic, selecciona ese Pokémon para el equipo.
     *
     * @param pokemon El objeto Pokémon para el cual se crea el botón.
     * @return Un JButton configurado con la imagen, nombre y color representativo del Pokémon.
     */
    private JButton createPokemonButton(Pokemon pokemon) {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        // 1. Cargar imagen con manejo de errores
        ImageIcon pokemonIcon = loadPokemonIcon(pokemon.getName());
        JLabel imageLabel = new JLabel(pokemonIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(imageLabel, BorderLayout.CENTER);

        // 2. Etiqueta del nombre
        JLabel nameLabel = new JLabel(pokemon.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        contentPanel.add(nameLabel, BorderLayout.SOUTH);

        // 3. Configurar botón
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.add(contentPanel);
        button.setBackground(getTypeColor(pokemon.getPrimaryType()));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setPreferredSize(new Dimension(120, 120));

        button.addActionListener(e -> selectPokemon(pokemon));

        return button;
    }

    /**
     * Devuelve un color representativo para un tipo de Pokémon dado.
     *
     * Cada tipo tiene un color asociado que puede ser usado en la interfaz gráfica
     * para mejorar la identificación visual del tipo.
     *
     * Si el tipo es `null` o no está definido, retorna un color gris claro por defecto.
     *
     * @param type El tipo de Pokémon (domain.Type) del que se quiere obtener el color.
     * @return Un objeto Color que representa visualmente el tipo.
     */
    private Color getTypeColor(domain.Type type) {
        if (type == null) return Color.LIGHT_GRAY;

        switch(type) {
            case FIRE: return new Color(255, 150, 150);
            case WATER: return new Color(150, 200, 255);
            case PLANT: return new Color(150, 255, 150);
            case ELECTRIC: return new Color(255, 255, 150);
            case PSYCHIC: return new Color(255, 150, 255);
            case ICE: return new Color(200, 240, 255);
            case DRAGON: return new Color(150, 150, 255);
            case DARK: return new Color(150, 100, 100);
            case FAIRY: return new Color(255, 200, 255);
            case FIGHTING: return new Color(200, 100, 100);
            case FLYING: return new Color(200, 200, 255);
            case POISON: return new Color(200, 100, 200);
            case GROUND: return new Color(200, 150, 100);
            case ROCK: return new Color(180, 150, 100);
            case BUG: return new Color(200, 200, 100);
            case GHOST: return new Color(150, 100, 150);
            case STEEL: return new Color(200, 200, 200);
            case NORMAL: return new Color(230, 230, 230);
            default: return Color.LIGHT_GRAY;
        }
    }

    /**
     * Crea y devuelve un panel de control que contiene un contador
     * de Pokémon seleccionados y un botón para confirmar la selección del equipo.
     *
     * El contador muestra cuántos Pokémon han sido seleccionados hasta el momento
     * y el límite máximo permitido.
     *
     * El botón "Confirmar Equipo" llama al método `confirmSelection` para
     * proceder con la validación y el siguiente paso en la selección del equipo.
     *
     * @return Un JPanel con el contador y el botón de confirmación.
     */
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JLabel counterLabel = new JLabel("Seleccionados: " + selectedPokemons.size() + "/" + POKEMON_PER_TRAINER);
        counterLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton confirmButton = new JButton("Confirmar Equipo");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 18));
        confirmButton.setBackground(new Color(100, 200, 100));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.addActionListener(e -> confirmSelection());

        panel.add(counterLabel);
        panel.add(confirmButton);

        return panel;
    }

    /**
     * Permite seleccionar un Pokémon para añadirlo al equipo del entrenador.
     *
     * Este método verifica que no se haya alcanzado el límite máximo de Pokémon seleccionados (6).
     * Luego clona el Pokémon original para evitar modificarlo directamente, carga sus movimientos,
     * y abre un diálogo para que el usuario seleccione los movimientos específicos para ese Pokémon.
     *
     * Si la selección de movimientos es válida, asigna los movimientos seleccionados al clon,
     * agrega el clon a la lista de Pokémon seleccionados y guarda los movimientos en el mapa correspondiente.
     *
     * @param pokemon El Pokémon que se desea seleccionar para el equipo.
     */
    private void selectPokemon(Pokemon pokemon) {
        if (selectedPokemons.size() >= POKEMON_PER_TRAINER) {
            JOptionPane.showMessageDialog(this,
                    "Ya has seleccionado 6 Pokémon",
                    "Límite alcanzado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Clonar el Pokémon para no modificar el original
        Pokemon clonedPokemon = pokemon.clone();

        // Asegurarse de que tenga sus movimientos
        clonedPokemon.getMoves().clear();
        clonedPokemon.initializeMoves(); // Esto carga los movimientos específicos de este Pokémon

        List<Move> moves = selectMovesForPokemon(clonedPokemon);

        if (moves != null && !moves.isEmpty()) {
            // Asignar solo los movimientos seleccionados
            clonedPokemon.getMoves().clear();
            clonedPokemon.getMoves().addAll(moves);

            selectedPokemons.add(clonedPokemon);
            selectedMoves.put(clonedPokemon, moves);

            JOptionPane.showMessageDialog(this,
                    "¡" + clonedPokemon.getName() + " añadido a tu equipo!",
                    "Pokémon seleccionado",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Muestra un diálogo para que el usuario seleccione exactamente 4 movimientos
     * de un Pokémon dado. El método crea un Pokémon temporal para cargar todos los movimientos
     * posibles del Pokémon original y presenta una lista con checkboxes coloreados según
     * la categoría de cada movimiento (Físico, Especial, Estado).
     *
     * El usuario debe seleccionar exactamente 4 movimientos, incluyendo al menos uno de cada categoría:
     * - Al menos 1 movimiento Físico
     * - Al menos 1 movimiento Especial
     * - Al menos 1 movimiento de Estado
     *
     * Si la selección no cumple con estas reglas, se mostrará un mensaje de error y se devolverá null.
     *
     * @param pokemon El Pokémon para el cual se seleccionarán movimientos.
     * @return Una lista con los 4 movimientos seleccionados si la selección es válida, o null si
     *         hubo error, cancelación o selección inválida.
     */
    private List<Move> selectMovesForPokemon(Pokemon pokemon) {
        // Crear Pokémon temporal con todos sus movimientos posibles
        Pokemon tempPokemon;
        try {
            tempPokemon = pokemon.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar movimientos del Pokémon",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Cargar todos los movimientos posibles
        tempPokemon.getMoves().clear();
        tempPokemon.initializeMoves();

        // Verificar que tenga suficientes movimientos
        if (tempPokemon.getMoves().size() < MOVES_PER_POKEMON) {
            JOptionPane.showMessageDialog(this,
                    "Este Pokémon no tiene suficientes movimientos disponibles",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Crear panel principal con scroll
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Lista para almacenar los checkboxes
        List<JCheckBox> checkBoxes = new ArrayList<>();

        // Crear checkbox para cada movimiento con su categoría
        for (Move move : tempPokemon.getMoves()) {
            // Crear texto descriptivo con categoría
            String moveText = String.format("%-20s (%s)",
                    move.getName(),
                    getCategoryText(move.getCategory()));

            JCheckBox checkBox = new JCheckBox(moveText);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Color diferente según categoría
            switch(move.getCategory()) {
                case PHYSICAL:
                    checkBox.setForeground(new Color(0, 100, 0)); // Verde para físico
                    break;
                case SPECIAL:
                    checkBox.setForeground(new Color(0, 0, 150)); // Azul para especial
                    break;
                case STATUS:
                    checkBox.setForeground(new Color(150, 0, 0)); // Rojo para estado
                    break;
            }

            checkBoxes.add(checkBox);
            checkBoxPanel.add(checkBox);
            checkBoxPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre checkboxes
        }

        // Panel de control con contador
        JPanel controlPanel = new JPanel();
        JLabel counterLabel = new JLabel("Seleccionados: 0/"+MOVES_PER_POKEMON);
        counterLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Configurar listener para los checkboxes
        ItemListener itemListener = e -> {
            long selectedCount = checkBoxes.stream().filter(JCheckBox::isSelected).count();
            counterLabel.setText("Seleccionados: "+selectedCount+"/"+MOVES_PER_POKEMON);

            // Habilitar/deshabilitar checkboxes según el límite
            if (selectedCount >= MOVES_PER_POKEMON) {
                checkBoxes.forEach(cb -> {
                    if (!cb.isSelected()) cb.setEnabled(false);
                });
            } else {
                checkBoxes.forEach(cb -> cb.setEnabled(true));
            }
        };

        checkBoxes.forEach(cb -> cb.addItemListener(itemListener));

        // Panel de leyenda
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        legendPanel.add(new JLabel("Leyenda: "));

        JLabel physicalLabel = new JLabel("Físico");
        physicalLabel.setForeground(new Color(0, 100, 0));
        legendPanel.add(physicalLabel);

        JLabel specialLabel = new JLabel("Especial");
        specialLabel.setForeground(new Color(0, 0, 150));
        legendPanel.add(specialLabel);

        JLabel statusLabel = new JLabel("Estado");
        statusLabel.setForeground(new Color(150, 0, 0));
        legendPanel.add(statusLabel);

        controlPanel.add(counterLabel);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(legendPanel, BorderLayout.NORTH);
        southPanel.add(controlPanel, BorderLayout.SOUTH);

        mainPanel.add(new JScrollPane(checkBoxPanel), BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Mostrar diálogo
        int result = JOptionPane.showConfirmDialog(
                this,
                mainPanel,
                "Selecciona 4 movimientos para " + pokemon.getName(),
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Obtener movimientos seleccionados
            List<Move> selected = new ArrayList<>();
            for (int i = 0; i < checkBoxes.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    selected.add(tempPokemon.getMoves().get(i));
                }
            }

            // Validar selección
            if (selected.size() != MOVES_PER_POKEMON) {
                JOptionPane.showMessageDialog(this,
                        "Debes seleccionar exactamente " + MOVES_PER_POKEMON + " movimientos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }

            if (!hasAllMoveCategories(selected)) {
                JOptionPane.showMessageDialog(this,
                        "Selección inválida. Requerido:\n" +
                                "- Al menos 1 movimiento Físico\n" +
                                "- Al menos 1 movimiento Especial\n" +
                                "- Al menos 1 movimiento de Estado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return null;
            }

            return selected;
        }

        return null;
    }

    /**
     * Convierte una categoría de movimiento (MoveCategory) a su representación en texto en español.
     *
     * @param category La categoría del movimiento.
     * @return Una cadena con la representación textual de la categoría:
     *         "Físico" para PHYSICAL,
     *         "Especial" para SPECIAL,
     *         "Estado" para STATUS,
     *         o "Desconocido" si la categoría no coincide con ninguna conocida.
     */
    private String getCategoryText(MoveCategory category) {
        switch(category) {
            case PHYSICAL: return "Físico";
            case SPECIAL: return "Especial";
            case STATUS: return "Estado";
            default: return "Desconocido";
        }
    }

    /**
     * Verifica si una lista de movimientos contiene al menos un movimiento de cada categoría:
     * física, especial y estado.
     *
     * @param moves Lista de movimientos a evaluar.
     * @return true si la lista contiene movimientos de las tres categorías; false en caso contrario.
     */
    private boolean hasAllMoveCategories(List<Move> moves) {
        boolean hasPhysical = false, hasSpecial = false, hasStatus = false;

        for (Move move : moves) {
            switch (move.getCategory()) {
                case PHYSICAL: hasPhysical = true; break;
                case SPECIAL: hasSpecial = true; break;
                case STATUS: hasStatus = true; break;
            }
            if (hasPhysical && hasSpecial && hasStatus) return true;
        }

        return hasPhysical && hasSpecial && hasStatus;
    }

    /**
     * Confirma la selección de Pokémon por parte del jugador.
     *
     * Valida que se hayan seleccionado exactamente 6 Pokémon.
     *
     * Según el modo de juego, maneja la transición adecuada:
     * - En modo jugador vs jugador (PvsP), guarda el equipo del primer jugador,
     *   abre la ventana de selección para el segundo jugador y transfiere los datos necesarios.
     * - En modo jugador vs máquina (PvsM), genera un equipo aleatorio para la máquina,
     *   inicializa sus movimientos y lanza la ventana del juego.
     * - En otros casos, asume que es la selección del segundo jugador en PvsP,
     *   clona y usa los equipos y movimientos para iniciar la partida.
     *
     * Muestra mensajes de error si el equipo seleccionado no es válido o si ocurre un error durante la configuración.
     */
    private void confirmSelection() {
        if (selectedPokemons.size() != 6) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar exactamente 6 Pokémon",
                    "Equipo incompleto",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (!isSecondPlayer && "PvsP".equals(mode)) {
                // Guardar el equipo del primer jugador (con clonación)
                this.firstPlayerTeam = new ArrayList<>();
                for (Pokemon p : selectedPokemons) {
                    this.firstPlayerTeam.add(p.clone());
                }
                this.firstPlayerMoves = new HashMap<>(selectedMoves);

                // Abrir selección para segundo jugador
                PokemonSelectionGUI secondGUI = new PokemonSelectionGUI(
                        mode,
                        opponentName,
                        opponentColor,
                        playerName,
                        playerColor,
                        true
                );

                // Pasar los datos del primer jugador al segundo
                secondGUI.firstPlayerTeam = this.firstPlayerTeam;
                secondGUI.firstPlayerMoves = this.firstPlayerMoves;
                secondGUI.setVisible(true);
                dispose();

            } else if ("PvsM".equals(mode)) {
                // El jugador seleccionó su equipo. Generar equipo para la máquina
                List<Pokemon> playerTeam = new ArrayList<>();
                for (Pokemon p : selectedPokemons) {
                    playerTeam.add(p.clone());
                }
                Map<Pokemon, List<Move>> playerMoves = new HashMap<>(selectedMoves);

                List<Pokemon> machineTeam = new ArrayList<>();
                Map<Pokemon, List<Move>> machineMoves = new HashMap<>();

                List<Pokemon> all = createAllPokemons();
                Collections.shuffle(all);
                for (int i = 0; i < 6 && i < all.size(); i++) {
                    Pokemon p = all.get(i).clone();
                    p.getMoves().clear();
                    p.initializeMoves();
                    List<Move> moves = selectRandomMoves(p, 4);
                    p.getMoves().clear();
                    p.getMoves().addAll(moves);
                    machineTeam.add(p);
                    machineMoves.put(p, moves);
                }

                Game game = new Game(playerName, opponentName, playerColor, opponentColor,
                        playerTeam, playerMoves, machineTeam, machineMoves);
                new GameGUI(game, mode).setVisible(true);
                dispose();

            } else {
                // Es el segundo jugador → iniciar GameGUI
                List<Pokemon> team1 = new ArrayList<>();
                for (Pokemon p : this.firstPlayerTeam) {
                    team1.add(p.clone());
                }
                Map<Pokemon, List<Move>> moves1 = new HashMap<>(this.firstPlayerMoves);

                List<Pokemon> team2 = new ArrayList<>();
                for (Pokemon p : selectedPokemons) {
                    team2.add(p.clone());
                }
                Map<Pokemon, List<Move>> moves2 = new HashMap<>(selectedMoves);

                Game game = new Game(playerName, opponentName, playerColor, opponentColor,
                        team1, moves1, team2, moves2);
                new GameGUI(game, mode).setVisible(true);
                dispose();
            }
        } catch (PoobkemonException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al configurar el juego: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Selecciona una cantidad específica de movimientos al azar de la lista de movimientos disponibles de un Pokémon.
     *
     * @param p     El Pokémon del cual se seleccionarán los movimientos.
     * @param count La cantidad máxima de movimientos a seleccionar.
     * @return      Una lista con movimientos seleccionados aleatoriamente, hasta un máximo de {@code count}.
     */
    private List<Move> selectRandomMoves(Pokemon p, int count) {
        List<Move> available = new ArrayList<>(p.getMoves());
        Collections.shuffle(available);
        return available.subList(0, Math.min(count, available.size()));
    }

    /**
     * Crea y devuelve una lista con instancias de todos los Pokémon disponibles en el juego.
     *
     * Este método utiliza reflexión para instanciar cada clase de Pokémon definida en
     * el arreglo interno {@code pokemonClasses}. Si ocurre algún error durante la creación
     * de alguna instancia, se captura la excepción, se imprime la traza y se muestra un mensaje
     * de error al usuario.
     *
     * @return una lista de objetos {@link Pokemon} con todos los Pokémon creados
     */
    private List<Pokemon> createAllPokemons() {
        List<Pokemon> allPokemons = new ArrayList<>();

        try {
            // Lista de todas las clases de Pokémon
            Class<?>[] pokemonClasses = {
                    Absol.class, Altaria.class, Banette.class, Blastoise.class, Blaziken.class,
                    Charizard.class, Crobat.class, Delibird.class, Donphan.class, Dragonite.class,
                    Flygon.class, Gardevoir.class, Gengar.class, Glalie.class, Granbull.class,
                    Grumpig.class, Machamp.class, Manectric.class, Masquerain.class, Mawile.class,
                    Medicham.class, Metagross.class, Moltres.class, Ninjask.class, Pidgeot.class,
                    Raichu.class, Sceptile.class, Seviper.class, Snorlax.class, Solrock.class,
                    Swampert.class, Togetic.class, Tyranitar.class, Umbreon.class, Venusaur.class,
                    Zangoose.class
            };

            // Crear instancia de cada Pokémon
            for (Class<?> pokemonClass : pokemonClasses) {
                Pokemon pokemon = (Pokemon) pokemonClass.getDeclaredConstructor().newInstance();
                allPokemons.add(pokemon);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar la lista de Pokémon",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return allPokemons;
    }


    /**
     * Carga y escala la imagen de un Pokémon, con manejo robusto de errores
     * @param pokemonName Nombre del Pokémon (ej. "charizard")
     * @return ImageIcon escalada a 80x80px, o imagen de error si falla
     */
    private ImageIcon loadPokemonIcon(String pokemonName) {
        // 1. Configuración de tamaño (ajusta estos valores según necesites)
        final int TARGET_SIZE = 64; // 64x64 píxeles

        // Ruta base absoluta
        String basePath = "/Users/dianasmacbook/POOBkemon/src/main/resources/";
        Map<String, String> pokemonGifs = Map.ofEntries(
                Map.entry("charizard", "6p.gif"),
                Map.entry("blastoise", "9p.gif"),
                Map.entry("venusaur", "3p.gif"),
                Map.entry("gengar", "94p.gif"),
                Map.entry("machamp", "68p.gif"),
                Map.entry("raichu", "26p.gif"),
                Map.entry("snorlax", "143p.gif"),
                Map.entry("tyranitar", "248p.gif"),
                Map.entry("donphan", "232p.gif"),
                Map.entry("delibird", "225p.gif"),
                Map.entry("togetic", "176p.gif"),
                Map.entry("dragonite", "149p.gif"),
                Map.entry("metagross", "376p.gif"),
                Map.entry("gardevoir", "282p.gif"),
                Map.entry("mawile", "303p.gif"),
                Map.entry("swampert", "260p.gif"),
                Map.entry("ninjask", "291p.gif"),
                Map.entry("altaria", "334p.gif"),
                Map.entry("manectric", "310p.gif"),
                Map.entry("banette", "354p.gif"),
                Map.entry("blaziken", "257p.gif"),
                Map.entry("granbull", "210p.gif"),
                Map.entry("glalie", "362p.gif"),
                Map.entry("medicham", "308p.gif"),
                Map.entry("zangoose", "335p.gif"),
                Map.entry("sceptile", "254p.gif"),
                Map.entry("grumpig", "326p.gif"),
                Map.entry("solrock", "338p.gif"),
                Map.entry("umbreon", "197p.gif"),
                Map.entry("flygon", "330p.gif"),
                Map.entry("crobat", "169p.gif"),
                Map.entry("moltres", "146p.gif"),
                Map.entry("absol", "359p.gif"),
                Map.entry("seviper", "336p.gif"),
                Map.entry("pidgeot", "18p.gif"),
                Map.entry("masquerain", "284p.gif"),
                Map.entry("unknown", "unknown.png") // Imagen por defecto
        );

        String fileName = pokemonGifs.getOrDefault(pokemonName.toLowerCase(), "unknown.png");
        String fullPath = basePath + "pokemon_sprites/" + fileName;

        System.out.println("Buscando imagen en: " + fullPath);

        // 1. Intento con ruta física absoluta
        File file = new File(fullPath);
        if (file.exists()) {
            System.out.println("✅ Encontrado físicamente");
            return new ImageIcon(fullPath);
        } else {
            System.out.println("❌ No existe físicamente");
        }

        // 2. Intento como recurso (para cuando se ejecute desde JAR)
        URL resourceUrl = getClass().getResource("/pokemon_sprites/" + fileName);
        if (resourceUrl != null) {
            System.out.println("✅ Encontrado como recurso");
            return new ImageIcon(resourceUrl);
        }

        // 3. Crear imagen de error
        return createErrorIcon();
    }

    /**
     * Crea un ícono de imagen personalizado que indica un error o la ausencia de imagen.
     * @return un {@link ImageIcon} que representa un ícono de error o marcador de posición
     */
    private ImageIcon createErrorIcon() {
        BufferedImage errorImg = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = errorImg.createGraphics();

        // Fondo rojo
        g.setColor(new Color(255, 100, 100));
        g.fillRect(0, 0, 80, 80);

        // Borde negro
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawRect(1, 1, 77, 77);

        // Texto de error
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        String errorText = "NO IMAGE";
        int textWidth = g.getFontMetrics().stringWidth(errorText);
        g.drawString(errorText, (80 - textWidth)/2, 40);

        g.dispose();
        return new ImageIcon(errorImg);
    }
}
