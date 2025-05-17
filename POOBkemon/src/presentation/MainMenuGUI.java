package presentation;

import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class MainMenuGUI extends JFrame {
    private JComboBox<String> modeComboBox;

    public MainMenuGUI() {
        configureWindow();
        initUI();
    }

    private void configureWindow() {
        setTitle("POOBkemon - Menú Principal");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initUI() {
        setLayout(new BorderLayout());
        add(createCoverPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createCoverPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        try {
            BufferedImage image = loadCoverImage();
            if (image != null) {
                Image scaledImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
                panel.add(new JLabel(new ImageIcon(scaledImage)), BorderLayout.CENTER);
            } else {
                panel.add(createFallbackCover(), BorderLayout.CENTER);
            }
        } catch (Exception e) {
            panel.add(new JLabel("Error técnico al cargar la portada", SwingConstants.CENTER), BorderLayout.CENTER);
        }

        return panel;
    }

    private BufferedImage loadCoverImage() throws IOException {
        String imageName = "3.0.png";
        String[] possiblePaths = {
                "/front/" + imageName,
                "/resources/front/" + imageName,
                System.getProperty("user.dir") + "/POOBkemon/resources/front/" + imageName
        };

        for (String path : possiblePaths) {
            InputStream stream = getClass().getResourceAsStream(path);
            if (stream != null) {
                return ImageIO.read(stream);
            }
            File file = new File(path);
            if (file.exists()) {
                return ImageIO.read(file);
            }
        }
        return null;
    }

    private JLabel createFallbackCover() {
        BufferedImage image = new BufferedImage(800, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        GradientPaint gradient = new GradientPaint(0, 0, new Color(50, 100, 200), 800, 500, new Color(200, 50, 100));
        g.setPaint(gradient);
        g.fillRect(0, 0, 800, 500);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String title = "POOBkémon";
        g.drawString(title, (800 - g.getFontMetrics().stringWidth(title))/2, 150);

        g.dispose();
        return new JLabel(new ImageIcon(image));
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 40, 40));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        // Panel de selección de modo
        JPanel modePanel = new JPanel();
        modePanel.setBackground(new Color(40, 40, 40));
        modePanel.add(new JLabel("Modo de juego:"));

        modeComboBox = new JComboBox<>(new String[]{
                "Jugador vs Jugador",
                "Jugador vs Máquina",
                "Máquina vs Máquina",
                "Supervivencia (PvP)"
        });
        modeComboBox.setPreferredSize(new Dimension(200, 30));
        modePanel.add(modeComboBox);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(40, 40, 40));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton startButton = createStyledButton("Iniciar Juego");
        startButton.addActionListener(e -> startGame());

        JButton howToPlayButton = createStyledButton("Cómo Jugar");
        howToPlayButton.addActionListener(e -> showInstructions());

        JButton exitButton = createStyledButton("Salir");
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(howToPlayButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createHorizontalGlue());

        panel.add(modePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(buttonPanel);

        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 45));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(80, 140, 220));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 160, 240));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(80, 140, 220));
            }
        });

        return button;
    }

    private void showInstructions() {
        String message = "<html><div style='text-align:center;width:300px;'>"
                + "<h2>Instrucciones</h2>"
                + "<p><b>Modo Supervivencia:</b> Equipos aleatorios sin ítems</p>"
                + "<p><b>Otros modos:</b> Selecciona tus Pokémon y usa ítems</p>"
                + "<p><b>Controles:</b> Elige movimientos y gestiona tu equipo</p></div></html>";

        JOptionPane.showMessageDialog(this, message, "Cómo Jugar", JOptionPane.INFORMATION_MESSAGE);
    }

    private void startGame() {
        String selectedMode = (String) modeComboBox.getSelectedItem();
        String mode = convertModeToInternal(selectedMode);

        // Activar/desactivar modo Supervivencia
        Item.setSurvivalMode("SURVIVAL".equals(mode));
        dispose();

        if ("SURVIVAL".equals(mode)) {
            startSurvivalMode();
        } else {
            startStandardMode(mode);
        }
    }

    private void startSurvivalMode() {
        List<Pokemon> team1 = SurvivalMode.generateRandomTeam();
        List<Pokemon> team2 = SurvivalMode.generateRandomTeam();

        Trainer trainer1 = new Trainer(
                "Jugador 1",
                new Color(50, 100, 200),
                team1,
                new ArrayList<>()
        );

        Trainer trainer2 = new Trainer(
                "Jugador 2",
                new Color(200, 50, 100),
                team2,
                new ArrayList<>()
        );

        new GameGUI(new Game(trainer1, trainer2), "SURVIVAL").setVisible(true);
    }

    private void startStandardMode(String mode) {
        List<Item> items = List.of(
                new Item("Poción", "heal20"),
                new Item("Superpoción", "heal50"),
                new Item("HyperPotion", "heal200"),
                new Item("Revivir", "revive")
        );

        List<Pokemon> allPokemons = createAllPokemons();

        Trainer trainer1 = createTrainer(mode, "Jugador 1", "Entrenador",
                new Color(50, 100, 200), allPokemons, items);

        Trainer trainer2 = createTrainer(mode, "Jugador 2", "Máquina",
                new Color(200, 50, 100),
                List.of(
                        allPokemons.get(3),  // Blastoise
                        allPokemons.get(12), // Gengar
                        allPokemons.get(31), // Tyranitar
                        allPokemons.get(30), // Togetic
                        allPokemons.get(21), // Metagross
                        allPokemons.get(4)   // Blaziken
                ),
                items);

        new GameGUI(new Game(trainer1, trainer2), mode).setVisible(true);
    }

    private List<Pokemon> createAllPokemons() {
        return List.of(
                new Absol(), new Altaria(), new Banette(), new Blastoise(),
                new Blaziken(), new Charizard(), new Crobat(), new Delibird(),
                new Donphan(), new Dragonite(), new Flygon(), new Gardevoir(),
                new Gengar(), new Glalie(), new Granbull(), new Grumpig(),
                new Machamp(), new Manectric(), new Masquerain(), new Mawile(),
                new Medicham(), new Metagross(), new Moltres(), new Ninjask(),
                new Pidgeot(), new Raichu(), new Sceptile(), new Seviper(),
                new Snorlax(), new Solrock(), new Swampert(), new Togetic(),
                new Tyranitar(), new Umbreon(), new Venusaur(), new Zangoose()
        );
    }

    private Trainer createTrainer(String mode, String playerName, String aiName,
                                  Color color, List<Pokemon> pokemons, List<Item> items) {
        return new Trainer(
                mode.equals("PvsP") ? playerName : aiName,
                color,
                pokemons,
                new ArrayList<>(items)
        );
    }

    private String convertModeToInternal(String selectedMode) {
        return switch (selectedMode) {
            case "Jugador vs Jugador" -> "PvsP";
            case "Jugador vs Máquina" -> "PvsM";
            case "Máquina vs Máquina" -> "MvsM";
            case "Supervivencia (PvP)" -> "SURVIVAL";
            default -> "PvsP";
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new MainMenuGUI().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}