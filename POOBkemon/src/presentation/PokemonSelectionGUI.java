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

    public static void main(String[] args) {
        // 1. PRIMERO verifica recursos
        testResourceLoading();

        // 2. Solo después inicia la GUI
        SwingUtilities.invokeLater(() -> {
            new PokemonSelectionGUI("PvP", "Ash", Color.RED, "Misty", Color.BLUE, false)
                    .setVisible(true);
        });
    }

    private static void testResourceLoading() {
        System.out.println("=== DIAGNÓSTICO DE RECURSOS ===");

        // Verifica imágenes clave
        testResource("/pokemon_sprites/6.gif");  // Charizard
        testResource("/pokemon_sprites/unknown.png");

        // Verifica estructura de directorios
        URL dirUrl = PokemonSelectionGUI.class.getResource("/pokemon_sprites");
        System.out.println("Directorio '/pokemon_sprites': " +
                (dirUrl != null ? "✅ ENCONTRADO" : "❌ NO ENCONTRADO"));
    }



    private static void testResource(String path) {
        // Método 1: Usando getResource (para JARs)
        URL url = PokemonSelectionGUI.class.getResource(path);

        // Método 2: Ruta física (para desarrollo)
        String physicalPath = "src/main/resources" + path;
        File file = new File(physicalPath);

        System.out.println(path + ":");
        System.out.println("  Como recurso: " + (url != null ? "✅" : "❌"));
        System.out.println("  Ruta física: " + (file.exists() ? "✅ " + file.getAbsolutePath() : "❌"));
    }

    private static void checkResource(String path) {
        URL url = PokemonSelectionGUI.class.getResource(path);
        System.out.println(path + " → " + (url != null ? "ENCONTRADO" : "NO ENCONTRADO"));
        if (url != null) {
            System.out.println("   Ruta física: " + url.getPath());
        }
    }

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

    private void configureWindow() {
        setTitle("POOBkemon - Selección para " + playerName);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
    }

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

    private ImageIcon loadPokemonImage(String pokemonName) {
        // 1. Normalizar nombre
        String normalizedName = pokemonName.toLowerCase().trim();

        // 2. Mapeo de nombres a archivos (completo)
        Map<String, String> pokemonGifs = Map.ofEntries(
                Map.entry("charizard", "6.gif"),
                Map.entry("blastoise", "9.gif"),
                Map.entry("venusaur", "3.gif"),
                // ... (todos los demás Pokémon)
                Map.entry("unknown", "unknown.png")
        );

        // 3. Obtener nombre de archivo
        String fileName = pokemonGifs.getOrDefault(normalizedName, "unknown.png");
        String imagePath = "/pokemon_sprites/" + fileName;

        // 4. Debugging: Verificar ruta
        System.out.println("Buscando imagen en: " + imagePath);

        // 5. Cargar imagen con ClassLoader
        InputStream imgStream = getClass().getResourceAsStream(imagePath);

        if (imgStream != null) {
            try {
                byte[] bytes = imgStream.readAllBytes();
                ImageIcon originalIcon = new ImageIcon(bytes);
                Image scaledImage = originalIcon.getImage()
                        .getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } catch (IOException e) {
                System.err.println("Error leyendo imagen: " + e.getMessage());
            }
        }

        // 6. Si falla, crear imagen de error
        System.err.println("No se pudo cargar: " + imagePath);
        return createErrorImage();
    }

    private ImageIcon createErrorImage() {
        BufferedImage img = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();

        // Fondo rojo
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, 80, 80);

        // Texto de error
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        g2d.drawString("ERROR", 30, 40);

        g2d.dispose();
        return new ImageIcon(img);
    }

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

    private String getPokemonGifPath(String pokemonName) {
        // Mapa completo de todos los Pokémon con sus archivos correspondientes
        Map<String, String> pokemonGifs = new HashMap<String, String>() {{
            put("charizard", "6.gif");
            put("blastoise", "9.gif");
            put("venusaur", "3.gif");
            put("gengar", "94.gif");
            put("machamp", "68.gif");
            put("raichu", "26.gif");
            put("snorlax", "143.gif");
            put("tyranitar", "248.gif");
            put("donphan", "232.gif");
            put("delibird", "225.gif");
            put("togetic", "176.gif");
            put("dragonite", "149.gif");
            put("metagross", "376.gif");
            put("gardevoir", "282.gif");
            put("mawile", "303.gif");
            put("swampert", "260.gif");
            put("ninjask", "291.gif");
            put("altaria", "334.gif");
            put("manectric", "310.gif");
            put("banette", "354.gif");
            put("blaziken", "257.gif");
            put("granbull", "210.gif");
            put("glalie", "362.gif");
            put("medicham", "308.gif");
            put("zangoose", "335.gif");
            put("sceptile", "254.gif");
            put("grumpig", "326.gif");
            put("solrock", "338.gif");
            put("umbreon", "197.gif");
            put("flygon", "330.gif");
            put("crobat", "169.gif");
            put("moltres", "146.gif");
            put("absol", "359.gif");
            put("seviper", "336.gif");
            put("pidgeot", "18g.gif");
            put("masquerain", "284.gif");
            // Imagen por defecto
            put("unknown", "unknown.png");
        }};

        // Normalizar el nombre (minúsculas, sin espacios)
        String normalizedName = pokemonName.toLowerCase().trim();

        // Verificar existencia en el mapa
        if (!pokemonGifs.containsKey(normalizedName)) {
            System.err.println("Pokémon no encontrado en el mapa: " + pokemonName);
        }

        return "/pokemon_sprites/" + pokemonGifs.getOrDefault(normalizedName, "unknown.png");
    }



    private ImageIcon createPlaceholderImage() {
        // Crear una imagen de placeholder (cuadro rojo con 'X' blanca)
        BufferedImage img = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();

        // Fondo rojo
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, 80, 80);

        // 'X' blanca
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(10, 10, 70, 70);
        g2d.drawLine(10, 70, 70, 10);

        g2d.dispose();
        return new ImageIcon(img);
    }

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

    // Método auxiliar para obtener texto de categoría
    private String getCategoryText(MoveCategory category) {
        switch(category) {
            case PHYSICAL: return "Físico";
            case SPECIAL: return "Especial";
            case STATUS: return "Estado";
            default: return "Desconocido";
        }
    }

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

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Error en selección",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // Método auxiliar para actualizar el contador
    private void updateCounterLabel(JLabel label, int selectedCount) {
        label.setText("<html><b>Seleccionados: " + selectedCount + "/" + MOVES_PER_POKEMON + "</b></html>");
        if (selectedCount == MOVES_PER_POKEMON) {
            label.setForeground(new Color(0, 100, 0)); // Verde cuando está completo
        } else {
            label.setForeground(Color.BLACK);
        }
    }

    private void confirmSelection() {
        if (selectedPokemons.size() != 6) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar exactamente 6 Pokémon",
                    "Equipo incompleto",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!isSecondPlayer && "PvsP".equals(mode)) {
            // Guardar el equipo del primer jugador
            this.firstPlayerTeam = selectedPokemons;
            this.firstPlayerMoves = selectedMoves;

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
            List<Pokemon> playerTeam = this.selectedPokemons;
            Map<Pokemon, List<Move>> playerMoves = this.selectedMoves;

            List<Pokemon> machineTeam = new ArrayList<>();
            Map<Pokemon, List<Move>> machineMoves = new HashMap<>();

            try {
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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error generando equipo de la máquina: " + ex.getMessage());
                return;
            }

            Game game = new Game(playerName, opponentName, playerColor, opponentColor,
                    playerTeam, playerMoves, machineTeam, machineMoves);
            new GameGUI(game, mode).setVisible(true);
            dispose();

        } else {
            // Es el segundo jugador → iniciar GameGUI
            List<Pokemon> team1 = this.firstPlayerTeam;
            Map<Pokemon, List<Move>> moves1 = this.firstPlayerMoves;
            List<Pokemon> team2 = this.selectedPokemons;
            Map<Pokemon, List<Move>> moves2 = this.selectedMoves;

            Game game = new Game(playerName, opponentName, playerColor, opponentColor,
                    team1, moves1, team2, moves2);

            new GameGUI(game, mode).setVisible(true);
            dispose();
        }
    }

    private List<Move> selectRandomMoves(Pokemon p, int count) {
        List<Move> available = new ArrayList<>(p.getMoves());
        Collections.shuffle(available);
        return available.subList(0, Math.min(count, available.size()));
    }




    // Añadir estos campos y métodos a la clase PokemonSelectionGUI


    public void setFirstPlayerData(List<Pokemon> team, Map<Pokemon, List<Move>> moves) {
        this.firstPlayerTeam = team;
        this.firstPlayerMoves = moves;
    }

    private void startGame() {
        List<Item> items = List.of(
                new Item("Poción", "heal20"),
                new Item("Superpoción", "heal50"),
                new Item("HyperPotion", "heal200"),
                new Item("Revivir", "revive")
        );

        Trainer trainer1 = new Trainer(
                playerName,
                playerColor,
                new ArrayList<>(selectedPokemons),
                new ArrayList<>(items)
        );

        Trainer trainer2 = new Trainer(
                opponentName,
                opponentColor,
                new ArrayList<>(selectedPokemons),
                new ArrayList<>(items)
        );

        dispose();
        new GameGUI(new Game(trainer1, trainer2), mode).setVisible(true);
    }

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

    private static class MoveListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Move) {
                Move move = (Move) value;
                setText(String.format("%-20s %-10s Pwr:%-3d PP:%-2d %s",
                        move.getName(),
                        move.getType(),
                        move.getPower(),
                        move.getPp(),
                        move.getCategory()));
            }
            return this;
        }
    }
    /**
     * Carga y escala la imagen de un Pokémon, con manejo robusto de errores
     * @param pokemonName Nombre del Pokémon (ej. "charizard")
     * @return ImageIcon escalada a 80x80px, o imagen de error si falla
     */
    private ImageIcon loadPokemonIcon(String pokemonName) {
        // 1. Configuración de tamaño (ajusta estos valores según necesites)
        final int TARGET_SIZE = 64; // 64x64 píxeles

        // Ruta base absoluta - AJUSTA ESTA RUTA A TU PROYECTO
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

    /** Crea una imagen de error visible */

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
