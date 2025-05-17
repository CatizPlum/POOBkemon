package presentation;

import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

        String imageName = "3.0.png";
        String[] possiblePaths = {
                "front/" + imageName,
                "resources/front/" + imageName,
                "src/main/resources/front/" + imageName,
                "POOBkemon/resources/front/" + imageName,
                System.getProperty("user.dir") + "/front/" + imageName,
                System.getProperty("user.dir") + "/resources/front/" + imageName,
                System.getProperty("user.dir") + "/src/main/resources/front/" + imageName,
                System.getProperty("user.dir") + "/POOBkemon/resources/front/" + imageName
        };

        try {
            BufferedImage image = loadImageFromMultipleLocations(imageName, possiblePaths);

            if (image != null) {
                Image scaledImage = image.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
                panel.add(new JLabel(new ImageIcon(scaledImage)), BorderLayout.CENTER);
            } else {
                image = createFallbackImage();
                panel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);

                JOptionPane.showMessageDialog(this,
                        "No se encontró la imagen de portada.\n" +
                                "Por favor, coloca el archivo '" + imageName + "'\n" +
                                "en una de estas ubicaciones:\n" +
                                String.join("\n", possiblePaths),
                        "Imagen no encontrada",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            panel.add(new JLabel("Error técnico al cargar la portada", SwingConstants.CENTER), BorderLayout.CENTER);
        }

        return panel;
    }

    private BufferedImage loadImageFromMultipleLocations(String imageName, String[] paths) {
        for (String path : paths) {
            try {
                System.out.println("Buscando imagen en: " + path);

                InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
                if (stream != null) {
                    BufferedImage image = ImageIO.read(stream);
                    if (image != null) {
                        System.out.println("¡Imagen encontrada como recurso!");
                        return image;
                    }
                }

                File file = new File(path);
                if (file.exists()) {
                    BufferedImage image = ImageIO.read(file);
                    if (image != null) {
                        System.out.println("¡Imagen encontrada en sistema de archivos!");
                        return image;
                    }
                }

            } catch (IOException e) {
                System.err.println("Error al cargar desde " + path + ": " + e.getMessage());
            }
        }
        return null;
    }

    private BufferedImage createFallbackImage() {
        BufferedImage image = new BufferedImage(800, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        GradientPaint gradient = new GradientPaint(0, 0, new Color(50, 100, 200), 800, 500, new Color(200, 50, 100));
        g.setPaint(gradient);
        g.fillRect(0, 0, 800, 500);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String title = "POOBkémon";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (800 - titleWidth)/2, 150);

        g.setFont(new Font("Arial", Font.PLAIN, 16));
        String message = "Imagen de portada no encontrada";
        int msgWidth = g.getFontMetrics().stringWidth(message);
        g.drawString(message, (800 - msgWidth)/2, 200);

        g.dispose();
        return image;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 40, 40));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        // Panel de selección de modo
        JPanel modePanel = new JPanel();
        modePanel.setBackground(new Color(40, 40, 40));
        modePanel.add(new JLabel("Modo de juego: "));

        modeComboBox = new JComboBox<>(new String[]{
                "Jugador vs Jugador",
                "Jugador vs Máquina",
                "Máquina vs Máquina"
        });
        modeComboBox.setMaximumSize(new Dimension(200, 30));
        modePanel.add(modeComboBox);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(40, 40, 40));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton startButton = createStyledButton("Iniciar Juego");
        startButton.addActionListener(e -> startGame());

        JButton exitButton = createStyledButton("Salir");
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createHorizontalGlue());

        panel.add(modePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(buttonPanel);

        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(80, 140, 220));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(5, 25, 5, 25)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

    private void startGame() {
        String selectedMode = (String) modeComboBox.getSelectedItem();
        String mode = convertModeToInternal(selectedMode);

        dispose();

        // Configuración básica del juego
        Pokemon charizard = new Charizard();
        Pokemon blastoise = new Blastoise();
        Pokemon dragonite = new Dragonite();
        Pokemon gengar = new Gengar();
        Pokemon venusaur = new Venusaur();
        Pokemon tyranitar = new Tyranitar();
        Pokemon togetic = new Togetic();

        List<Item> items = List.of(
                new Item("Poción", "heal20"),
                new Item("Superpoción", "heal50"),
                new Item("Revivir", "revive")
        );

        // Crear equipos más variados
        Trainer trainer1 = new Trainer(
                mode.equals("PvsP") ? "Jugador 1" : "Entrenador",
                new Color(50, 100, 200),
                List.of(charizard, dragonite, venusaur),
                new ArrayList<>(items)
        );

        Trainer trainer2 = new Trainer(
                mode.equals("PvsP") ? "Jugador 2" :
                        mode.equals("PvsM") ? "Máquina" : "Máquina",
                new Color(200, 50, 100),
                List.of(blastoise, gengar, tyranitar, togetic),
                new ArrayList<>(items)
        );

        new GameGUI(new Game(trainer1, trainer2), mode).setVisible(true);
    }

    private String convertModeToInternal(String selectedMode) {
        return switch (selectedMode) {
            case "Jugador vs Jugador" -> "PvsP";
            case "Jugador vs Máquina" -> "PvsM";
            case "Máquina vs Máquina" -> "MvsM";
            default -> "PvsP";
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            MainMenuGUI menu = new MainMenuGUI();
            menu.setVisible(true);
        });
    }
}