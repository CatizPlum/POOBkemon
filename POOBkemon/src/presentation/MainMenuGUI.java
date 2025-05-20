package presentation;

import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;

public class MainMenuGUI extends JFrame {
    private Font pokemonEmeraldFont;
    private Font pokemonFontBold;
    private int selectedButtonIndex = 0; // 0 para START, 1 para SALIR
    private JButton startButton;
    private JButton exitButton;
    private JPanel buttonPanel;
    private JTextField nameField;
    private List<JButton> currentMenuButtons = new ArrayList<>();
    private JPanel currentButtonPanel;


    public MainMenuGUI() {
        loadPokemonFonts();
        configureWindow();
        initStartScreen();
    }

    private void loadPokemonFonts() {
        try {
            // Usar la fuente instalada en el sistema
            pokemonEmeraldFont = new Font("Pokemon Emerald Latin FC", Font.PLAIN, 24);

            // Verificar si la fuente se cargó correctamente
            if (!pokemonEmeraldFont.getFamily().equals("Pokemon Emerald Latin FC")) {
                throw new FontFormatException("Font not available");
            }

            pokemonFontBold = new Font("Pokemon Emerald Latin FC", Font.BOLD, 16);
        } catch (Exception e) {
            System.err.println("No se pudo cargar la fuente Pokémon Emerald: " + e.getMessage());
            // Fuentes de respaldo
            pokemonEmeraldFont = new Font("Arial Rounded MT Bold", Font.BOLD, 24);
            pokemonFontBold = new Font("Arial Rounded MT Bold", Font.BOLD, 16);
        }
    }

    private void configureWindow() {
        setTitle("POOBkemon - Menú Principal");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            // Intenta cargar el ícono desde recursos
            InputStream iconStream = getClass().getResourceAsStream("/front/pokeball.png");
            if (iconStream != null) {
                setIconImage(ImageIO.read(iconStream));
            }
        } catch (Exception e) {
            System.err.println("No se pudo cargar el ícono de la ventana: " + e.getMessage());
        }
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
            }
        });

        initStartScreen();
    }

    private void initStartScreen() {
        getContentPane().removeAll();
        currentMenuButtons.clear();
        selectedButtonIndex = 0;

        JPanel startPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = loadCoverImage();
                    if (image != null) {
                        Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                        g.drawImage(scaledImage, 0, 0, this);
                    } else {
                        g.setColor(new Color(48, 96, 160));
                        g.fillRect(0, 0, getWidth(), getHeight());

                        g.setFont(pokemonEmeraldFont.deriveFont(36f));
                        g.setColor(Color.RED);
                        String title = "POOBkemon";
                        int titleWidth = g.getFontMetrics().stringWidth(title);
                        g.drawString(title, (getWidth() - titleWidth)/2, getHeight()/2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        buttonPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!currentMenuButtons.isEmpty() && selectedButtonIndex < currentMenuButtons.size()) {
                    Graphics2D g2 = (Graphics2D)g.create();
                    try {
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Color.BLACK);
                        g2.setFont(new Font("Arial", Font.BOLD, 24));

                        JButton selectedButton = currentMenuButtons.get(selectedButtonIndex);
                        FontMetrics fm = g2.getFontMetrics(selectedButton.getFont());
                        String text = selectedButton.getText();
                        int textWidth = fm.stringWidth(text);

                        Point buttonPos = selectedButton.getLocation();
                        int buttonCenterX = buttonPos.x + (selectedButton.getWidth() / 2);
                        int textStartX = buttonCenterX - (textWidth / 2);

                        int arrowX = textStartX - 50; // Ajuste clave aquí
                        int arrowY = buttonPos.y + (selectedButton.getHeight() / 2) + 8;

                        g2.drawString("▶", arrowX, arrowY);
                    } finally {
                        g2.dispose();
                    }
                }
            }
        };

        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, -250, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 50, 2, 0);

        // Botón START
        startButton = new JButton("START") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setFont(pokemonEmeraldFont);
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());

                // Sombra sutil
                g2.setColor(new Color(200, 200, 200, 100));
                g2.drawString(getText(),
                        (getWidth() - textWidth)/2 + 2,
                        (getHeight() + fm.getAscent() - fm.getDescent())/2 + 2);

                // Texto principal
                g2.setColor(Color.BLACK);
                g2.drawString(getText(),
                        (getWidth() - textWidth)/2,
                        (getHeight() + fm.getAscent() - fm.getDescent())/2);
            }

            @Override
            public Dimension getPreferredSize() {
                FontMetrics fm = getFontMetrics(pokemonEmeraldFont);
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getHeight();

                return new Dimension(
                        Math.max(300, textWidth + 100), // Mínimo 300px de ancho
                        Math.max(60, textHeight + 30)   // Mínimo 60px de alto
                );
            }

            @Override
            protected void paintBorder(Graphics g) {
                // Sin borde
            }
        };
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40)); // Padding simétrico
        startButton.setOpaque(false);
        startButton.addActionListener(e -> showMainMenu());

        // Botón SALIR
        exitButton = new JButton("SALIR") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setFont(pokemonEmeraldFont);
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());

                // Sombra sutil
                g2.setColor(new Color(200, 200, 200, 100));
                g2.drawString(getText(),
                        (getWidth() - textWidth)/2 + 2,
                        (getHeight() + fm.getAscent() - fm.getDescent())/2 + 2);

                // Texto principal
                g2.setColor(Color.BLACK);
                g2.drawString(getText(),
                        (getWidth() - textWidth)/2,
                        (getHeight() + fm.getAscent() - fm.getDescent())/2);
            }

            @Override
            public Dimension getPreferredSize() {
                FontMetrics fm = getFontMetrics(pokemonEmeraldFont);
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getHeight();

                return new Dimension(
                        Math.max(300, textWidth + 100), // Mínimo 300px de ancho
                        Math.max(60, textHeight + 30)   // Mínimo 60px de alto
                );
            }

            @Override
            protected void paintBorder(Graphics g) {
                // Sin borde
            }
        };

        // Añadir botones a la lista de navegación
        currentMenuButtons.add(startButton);
        currentMenuButtons.add(exitButton);

        buttonPanel.add(startButton, gbc);
        buttonPanel.add(exitButton, gbc);

        startPanel.add(buttonPanel, BorderLayout.CENTER);
        add(startPanel);

        currentButtonPanel = buttonPanel;
        setupKeyBindings(startPanel);
    }
    // Variable de clase para almacenar el tamaño de fuente calculado
    private float calculatedFontSize = 22f; // Tamaño base inicial

    private JButton createEmeraldStyleButton(String text) {
        // Primero calculamos el tamaño de fuente óptimo para el texto más largo
        calculateOptimalFontSize(text);

        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Font buttonFont = pokemonEmeraldFont.deriveFont(22f);
                g2.setFont(buttonFont);
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(text);

                // Sombra del texto
                g2.setColor(new Color(200, 200, 200, 100));
                g2.drawString(text,
                        (getWidth() - textWidth)/2 + 2,
                        (getHeight() + fm.getAscent() - fm.getDescent())/2 + 2);

                // Texto principal
                g2.setColor(Color.BLACK);
                g2.drawString(text,
                        (getWidth() - textWidth)/2,
                        (getHeight() + fm.getAscent() - fm.getDescent())/2);
            }

            @Override
            public Dimension getPreferredSize() {
                FontMetrics fm = getFontMetrics(pokemonEmeraldFont.deriveFont(22f));
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getHeight();

                // Aumentamos significativamente el padding para textos largos
                int paddingHorizontal = Math.max(80, (int)(textWidth * 0.3)); // Mínimo 80px o 30% del ancho del texto
                int paddingVertical = 30; // Más espacio vertical

                return new Dimension(
                        textWidth + paddingHorizontal,
                        Math.max(60, textHeight + paddingVertical)
                );
            }

            @Override
            public Dimension getMinimumSize() {
                return getPreferredSize();
            }

            @Override
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
        return button;
    }

    private void calculateOptimalFontSize(String text) {
        // Solo calcular una vez (la primera vez que se llama)
        if (calculatedFontSize == 22f) {
            Font testFont = pokemonEmeraldFont.deriveFont(22f);
            FontMetrics fm = getFontMetrics(testFont);

            // Textos conocidos que podrían ser largos
            String[] testTexts = {
                    "START", "SALIR", "NUEVO JUEGO", "CÓMO JUGAR", "VOLVER",
                    "JUGADOR vs JUGADOR", "JUGADOR vs MÁQUINA", "MÁQUINA vs MÁQUINA",
                    "MODO SUPERVIVENCIA"
            };

            // Encontrar el texto más ancho
            int maxWidth = 0;
            for (String testText : testTexts) {
                maxWidth = Math.max(maxWidth, fm.stringWidth(testText));
            }

            // Calcular el ancho disponible (usando el 80% del ancho del panel)
            int availableWidth = (int)(getWidth() * 0.8) - 60; // Restar padding

            // Ajustar tamaño de fuente si es necesario
            if (maxWidth > availableWidth) {
                calculatedFontSize = 22f * ((float)availableWidth / (float)maxWidth);
                calculatedFontSize = Math.max(16f, calculatedFontSize); // No menor a 16px
            }
        }
    }

    private void setupKeyBindings(JPanel panel) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        actionMap.put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentMenuButtons.isEmpty() && selectedButtonIndex < currentMenuButtons.size()) {
                    currentMenuButtons.get(selectedButtonIndex).doClick();
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        actionMap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentMenuButtons.isEmpty()) {
                    selectedButtonIndex = (selectedButtonIndex == 0) ?
                            currentMenuButtons.size() - 1 : selectedButtonIndex - 1;
                    currentButtonPanel.repaint();
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        actionMap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentMenuButtons.isEmpty()) {
                    selectedButtonIndex = (selectedButtonIndex == currentMenuButtons.size() - 1) ?
                            0 : selectedButtonIndex + 1;
                    currentButtonPanel.repaint();
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
        actionMap.put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private BufferedImage loadCoverImage() throws IOException {
        String imageName = "Portada.png";
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

    private void showMainMenu() {
        getContentPane().removeAll();
        currentMenuButtons.clear();
        selectedButtonIndex = 0;

        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = loadCoverImage();
                    if (image != null) {
                        Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                        g.drawImage(scaledImage, 0, 0, this);
                    } else {
                        g.setColor(new Color(48, 96, 160));
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Panel de opciones principales con capacidad para dibujar la flecha de selección
        JPanel optionsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!currentMenuButtons.isEmpty() && selectedButtonIndex < currentMenuButtons.size()) {
                    Graphics2D g2 = (Graphics2D)g.create();
                    try {
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Color.BLACK);
                        g2.setFont(new Font("Arial", Font.BOLD, 24));

                        JButton selectedButton = currentMenuButtons.get(selectedButtonIndex);
                        FontMetrics fm = g2.getFontMetrics(selectedButton.getFont());
                        String text = selectedButton.getText();
                        int textWidth = fm.stringWidth(text);

                        Point buttonPos = selectedButton.getLocation();
                        int buttonCenterX = buttonPos.x + (selectedButton.getWidth() / 2);
                        int textStartX = buttonCenterX - (textWidth / 2);

                        int arrowX = textStartX - 65; // Ajuste clave aquí
                        int arrowY = buttonPos.y + (selectedButton.getHeight() / 2) + 8;

                        g2.drawString("▶", arrowX, arrowY);
                    } finally {
                        g2.dispose();
                    }
                }
            }
        };
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 150, 200));

        // Crear botones con estilo Pokémon
        JButton newGameButton = createEmeraldStyleButton("NUEVO JUEGO");
        JButton howToPlayButton = createEmeraldStyleButton("COMO JUGAR");
        JButton backButton = createEmeraldStyleButton("VOLVER");

        // Configurar acciones de los botones
        newGameButton.addActionListener(e -> showGameModeSelection());
        howToPlayButton.addActionListener(e -> showPokemonInstructions());
        backButton.addActionListener(e -> initStartScreen());

        // Añadir botones a la lista de navegación
        currentMenuButtons.add(newGameButton);
        currentMenuButtons.add(howToPlayButton);
        currentMenuButtons.add(backButton);

        // Configurar alineación centrada
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        howToPlayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Organizar componentes en el panel con espacios
        optionsPanel.add(Box.createVerticalGlue());
        optionsPanel.add(newGameButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        optionsPanel.add(howToPlayButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        optionsPanel.add(backButton);
        optionsPanel.add(Box.createVerticalGlue());

        mainPanel.add(optionsPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Configurar el panel actual para repintado de la flecha
        currentButtonPanel = optionsPanel;

        // Configurar atajos de teclado para navegación
        setupKeyBindings(mainPanel);

        // Actualizar la interfaz
        revalidate();
        repaint();
    }
    private void showPlayerSelection(boolean forTwoPlayers, boolean isSurvivalMode, String mode) {
        getContentPane().removeAll();
        currentMenuButtons.clear();
        selectedButtonIndex = 0;

        boolean isMvsMMode = "MvsM".equals(mode);
        boolean isPvsMMode = "PvsM".equals(mode);

        JPanel playerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = loadCoverImage();
                    if (image != null) {
                        Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                        g.drawImage(scaledImage, 0, 0, this);
                    } else {
                        g.setColor(new Color(48, 96, 160));
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Panel de contenido central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        // Título dinámico
        String titleText;
        if (isMvsMMode) {
            titleText = "MODO MAQUINA vs MAQUINA";
        } else if (isPvsMMode) {
            titleText = "MODO JUGADOR vs MAQUINA";
        } else if (forTwoPlayers) {
            titleText = "SELECCION DEL JUGADOR 1";
        } else {
            titleText = "SELECCION DEL JUGADOR";
        }

        JLabel titleLabel = new JLabel(titleText);
        stylePokemonLabel(titleLabel, Color.RED);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Panel para nombre (solo si no es MvsM)
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setVisible(!isMvsMMode);

        if (!isMvsMMode) {
            JLabel nameLabel = new JLabel("Nombre:");
            stylePokemonLabel(nameLabel, Color.WHITE);
            nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

            nameField = new JTextField(15); // Usamos la variable de clase
            nameField.setFont(pokemonEmeraldFont.deriveFont(18f));
            nameField.setMaximumSize(new Dimension(300, 30));

            namePanel.add(nameLabel);
            namePanel.add(Box.createRigidArea(new Dimension(10, 0)));
            namePanel.add(nameField);
            namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        }


        // Panel para color
        JPanel colorPanel = new JPanel();
        colorPanel.setOpaque(false);
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));

        JLabel colorLabel = new JLabel("Color:");
        stylePokemonLabel(colorLabel, Color.WHITE);
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JColorChooser colorChooser = new JColorChooser(isMvsMMode ? Color.BLUE : Color.BLUE);
        colorChooser.setPreviewPanel(new JPanel()); // Eliminar panel de previsualización
        colorChooser.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Reducir el tamaño del color chooser
        JPanel chooserPanel = new JPanel(new BorderLayout());
        chooserPanel.setOpaque(false);
        chooserPanel.add(colorChooser, BorderLayout.CENTER);
        chooserPanel.setMaximumSize(new Dimension(400, 200));

        colorPanel.add(colorLabel);
        colorPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        colorPanel.add(chooserPanel);
        colorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = createEmeraldStyleButton("VOLVER");
        JButton confirmButton = createEmeraldStyleButton("CONFIRMAR");

        buttonPanel.add(backButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(confirmButton);

        // Configurar acciones de los botones
        backButton.addActionListener(e -> {
            if (isMvsMMode) {
                showNormalModeOptions(); // Volver al menú de modos normales
            } else {
                showMainMenu();
            }
        });

        confirmButton.addActionListener(e -> {
            try {
                if ("MvsM".equals(mode)) {
                    // Modo Máquina vs Máquina
                    startStandardMode("MvsM", "Máquina 1", colorChooser.getColor(),
                            "Máquina 2", new Color(200, 50, 100));

                } else if ("PvsM".equals(mode)) {
                    // Modo Jugador vs Máquina
                    String playerName = nameField.getText().trim();
                    if (playerName.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "¡Ingresa un nombre válido!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    startStandardMode("PvsM", playerName, colorChooser.getColor());

                } else if (forTwoPlayers) {
                    // Modo Jugador vs Jugador
                    String playerName = nameField.getText().trim();
                    if (playerName.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "¡Ingresa un nombre válido!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    showSecondPlayerSelection(playerName, colorChooser.getColor(), isSurvivalMode);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al confirmar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Añadir botones a la lista de navegación
        currentMenuButtons.add(backButton);
        currentMenuButtons.add(confirmButton);

        // Agregar componentes al panel central
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(buttonPanel);  // Botones primero
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(namePanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(colorPanel);

        playerPanel.add(centerPanel, BorderLayout.CENTER);
        add(playerPanel);

        // Configurar el panel actual para repintado de la flecha
        currentButtonPanel = centerPanel;

        // Configurar atajos de teclado
        setupKeyBindings(playerPanel);

        // Actualizar la interfaz
        revalidate();
        repaint();
    }

    private void showSecondPlayerSelection(String player1Name, Color player1Color, boolean isSurvivalMode) {
        getContentPane().removeAll();
        currentMenuButtons.clear();
        selectedButtonIndex = 0;

        JPanel playerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = loadCoverImage();
                    if (image != null) {
                        Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                        g.drawImage(scaledImage, 0, 0, this);
                    } else {
                        g.setColor(new Color(48, 96, 160));
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Panel de contenido central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        // Título
        JLabel titleLabel = new JLabel("SELECCION DEL JUGADOR 2");
        stylePokemonLabel(titleLabel, Color.RED);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Panel para nombre
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));

        JLabel nameLabel = new JLabel("Nombre:");
        stylePokemonLabel(nameLabel, Color.WHITE);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JTextField nameField = new JTextField(15);
        nameField.setFont(pokemonEmeraldFont.deriveFont(18f));
        nameField.setMaximumSize(new Dimension(300, 30));

        namePanel.add(nameLabel);
        namePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        namePanel.add(nameField);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para color
        JPanel colorPanel = new JPanel();
        colorPanel.setOpaque(false);
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));

        JLabel colorLabel = new JLabel("Color:");
        stylePokemonLabel(colorLabel, Color.WHITE);
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JColorChooser colorChooser = new JColorChooser(Color.RED); // Rojo como color por defecto para jugador 2
        colorChooser.setPreviewPanel(new JPanel());
        colorChooser.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel chooserPanel = new JPanel(new BorderLayout());
        chooserPanel.setOpaque(false);
        chooserPanel.add(colorChooser, BorderLayout.CENTER);
        chooserPanel.setMaximumSize(new Dimension(400, 200));

        colorPanel.add(colorLabel);
        colorPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        colorPanel.add(chooserPanel);
        colorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = createEmeraldStyleButton("VOLVER");
        JButton confirmButton = createEmeraldStyleButton("CONFIRMAR");

        buttonPanel.add(backButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(confirmButton);

        // Configurar acciones
        backButton.addActionListener(e -> showPlayerSelection(true,false,"PvsP"));
        confirmButton.addActionListener(e -> {
            String player2Name = nameField.getText().trim();
            if (player2Name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa un nombre", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isSurvivalMode) {
                startGame("SURVIVAL", player1Name, player1Color, player2Name, colorChooser.getColor());
            } else {
                startGame("PvsP", player1Name, player1Color, player2Name, colorChooser.getColor());
            }
        });

        // Agregar componentes
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(buttonPanel);  // Botones primero
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(namePanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(colorPanel);

        playerPanel.add(centerPanel, BorderLayout.CENTER);
        add(playerPanel);

        // Configurar navegación
        currentMenuButtons.add(backButton);
        currentMenuButtons.add(confirmButton);
        currentButtonPanel = centerPanel;
        setupKeyBindings(playerPanel);

        revalidate();
        repaint();
    }

    private void startPvPMode(String player1Name, Color player1Color,
                              String player2Name, Color player2Color) {
        List<Item> items = List.of(
                new Item("Poción", "heal20"),
                new Item("Superpoción", "heal50"),
                new Item("HyperPotion", "heal200"),
                new Item("Revivir", "revive")
        );

        List<Pokemon> allPokemons = createAllPokemons();

        Trainer trainer1 = new Trainer(
                player1Name,
                player1Color,
                new ArrayList<>(allPokemons),
                new ArrayList<>(items)
        );

        Trainer trainer2 = new Trainer(
                player2Name,
                player2Color,
                new ArrayList<>(allPokemons),
                new ArrayList<>(items)
        );

        new GameGUI(new Game(trainer1, trainer2), "PvsP").setVisible(true);
    }
    private JButton createPokemonButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(new Color(160, 0, 0));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(200, 0, 0));
                } else {
                    g2.setColor(new Color(160, 0, 0));
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 10, 10);

                g2.setFont(pokemonFontBold);
                g2.setColor(Color.WHITE);

                // Sombra del texto
                g2.setColor(new Color(0, 0, 0, 100));
                g2.drawString(text,
                        (getWidth() - g2.getFontMetrics().stringWidth(text))/2 + 2,
                        (getHeight() + g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent())/2 + 2);

                // Texto principal
                g2.setColor(Color.WHITE);
                g2.drawString(text,
                        (getWidth() - g2.getFontMetrics().stringWidth(text))/2,
                        (getHeight() + g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent())/2);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No pintar borde por defecto
            }
        };

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setPreferredSize(new Dimension(250, 50));

        return button;
    }

    private void showNormalModeOptions() {
        getContentPane().removeAll();
        currentMenuButtons.clear();
        selectedButtonIndex = 0;

        JPanel modePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = loadCoverImage();
                    if (image != null) {
                        Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                        g.drawImage(scaledImage, 0, 0, this);
                    } else {
                        g.setColor(new Color(48, 96, 160));
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Panel central para las opciones
        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!currentMenuButtons.isEmpty() && selectedButtonIndex < currentMenuButtons.size()) {
                    Graphics2D g2 = (Graphics2D)g.create();
                    try {
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Color.BLACK);
                        g2.setFont(new Font("Arial", Font.BOLD, 24));

                        JButton selectedButton = currentMenuButtons.get(selectedButtonIndex);
                        FontMetrics fm = g2.getFontMetrics(selectedButton.getFont());
                        String text = selectedButton.getText();
                        int textWidth = fm.stringWidth(text);

                        Point buttonPos = selectedButton.getLocation();
                        int buttonCenterX = buttonPos.x + (selectedButton.getWidth() / 2);
                        int textStartX = buttonCenterX - (textWidth / 2);

                        int arrowX = textStartX - 100;
                        int arrowY = buttonPos.y + (selectedButton.getHeight() / 2) + 8;

                        g2.drawString("▶", arrowX, arrowY);
                    } finally {
                        g2.dispose();
                    }
                }
            }
        };
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));

        // Título
        JLabel titleLabel = new JLabel("MODO NORMAL - SELECCIONA OPCION");
        stylePokemonLabel(titleLabel, Color.RED);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));

        // Crear botones para los modos normales
        JButton pvpButton = createEmeraldStyleButton("JUGADOR vs JUGADOR");
        JButton pvmButton = createEmeraldStyleButton("JUGADOR vs MAQUINA");
        JButton mvmButton = createEmeraldStyleButton("MAQUINA vs MAQUINA");
        JButton backButton = createEmeraldStyleButton("VOLVER");

        // Configurar acciones
        pvpButton.addActionListener(e -> showPlayerSelection(true, false,"PvsP")); // PvsP
        pvmButton.addActionListener(e -> showPlayerSelection(false, false, "PvsM")); // PvsM
        mvmButton.addActionListener(e -> showPlayerSelection(false, false, "MvsM")); // MvsM
        backButton.addActionListener(e -> showGameModeSelection());

        // Añadir botones a la lista de navegación
        currentMenuButtons.add(pvpButton);
        currentMenuButtons.add(pvmButton);
        currentMenuButtons.add(mvmButton);
        currentMenuButtons.add(backButton);

        // Configurar alineación
        pvpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pvmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mvmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Organizar componentes en el panel
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(pvpButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(pvmButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(mvmButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(backButton);

        modePanel.add(centerPanel, BorderLayout.CENTER);
        add(modePanel);

        // Configurar el panel actual para repintado de la flecha
        currentButtonPanel = centerPanel;

        // Configurar atajos de teclado
        setupKeyBindings(modePanel);

        // Actualizar la interfaz
        revalidate();
        repaint();
    }

    private void showGameModeSelection() {
        getContentPane().removeAll();
        currentMenuButtons.clear();
        selectedButtonIndex = 0;

        JPanel modePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = loadCoverImage();
                    if (image != null) {
                        Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                        g.drawImage(scaledImage, 0, 0, this);
                    } else {
                        g.setColor(new Color(48, 96, 160));
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Panel de selección de modo con capacidad para dibujar la flecha de selección
        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!currentMenuButtons.isEmpty() && selectedButtonIndex < currentMenuButtons.size()) {
                    Graphics2D g2 = (Graphics2D)g.create();
                    try {
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Color.BLACK);
                        g2.setFont(new Font("Arial", Font.BOLD, 24));

                        JButton selectedButton = currentMenuButtons.get(selectedButtonIndex);
                        FontMetrics fm = g2.getFontMetrics(selectedButton.getFont());
                        String text = selectedButton.getText();
                        int textWidth = fm.stringWidth(text);

                        Point buttonPos = selectedButton.getLocation();
                        int buttonCenterX = buttonPos.x + (selectedButton.getWidth() / 2);
                        int textStartX = buttonCenterX - (textWidth / 2);

                        int arrowX = textStartX - 100; // Ajuste clave aquí
                        int arrowY = buttonPos.y + (selectedButton.getHeight() / 2) + 8;

                        g2.drawString("▶", arrowX, arrowY);
                    } finally {
                        g2.dispose();
                    }
                }
            }
        };
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));

        // Título
        JLabel titleLabel = new JLabel("SELECCIONA UN MODO DE JUEGO");
        stylePokemonLabel(titleLabel, Color.RED);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Crear botones para los modos de juego
        JButton normalModeButton = createEmeraldStyleButton("MODO NORMAL");
        JButton survivalModeButton = createEmeraldStyleButton("MODO SUPERVIVENCIA");
        JButton backButton = createEmeraldStyleButton("VOLVER");

// Configurar acciones de los botones
        normalModeButton.addActionListener(e -> showNormalModeOptions());
        survivalModeButton.addActionListener(e -> showPlayerSelection(true, true,"PvsP"));
        backButton.addActionListener(e -> showMainMenu());;

// Añadir botones a la lista de navegación
        currentMenuButtons.add(normalModeButton);
        currentMenuButtons.add(survivalModeButton);
        currentMenuButtons.add(backButton);

// Configurar alineación
        normalModeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        survivalModeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

// Organizar componentes en el panel
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(normalModeButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(survivalModeButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(backButton);

        modePanel.add(centerPanel, BorderLayout.CENTER);
        add(modePanel);

        // Configurar el panel actual para repintado de la flecha
        currentButtonPanel = centerPanel;

        // Configurar atajos de teclado
        setupKeyBindings(modePanel);

        // Actualizar la interfaz
        revalidate();
        repaint();
    }

    private void stylePokemonLabel(JLabel label, Color color) {
        label.setFont(pokemonFontBold);
        label.setForeground(color);
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
    }

    private void showPokemonInstructions() {
        String message = "<html><div style='width:400px;color:#000;font-family:Arial Rounded MT Bold;'>"
                + "<div style='background-color:#e8e8e8;border:4px solid #404040;border-radius:10px;padding:15px;'>"
                + "<h1 style='color:#d00000;text-align:center;font-size:20px;'>INSTRUCCIONES</h1>"
                + "<div style='background-color:#f8f8f8;border:2px solid #a0a0a0;border-radius:5px;padding:10px;margin-bottom:10px;'>"
                + "<p style='font-size:14px;'><b style='color:#d00000;'>MODO SUPERVIVENCIA:</b> Equipos aleatorios sin ítems</p>"
                + "<p style='font-size:14px;'><b style='color:#d00000;'>JUGADOR vs JUGADOR:</b> Selecciona tus Pokémon y usa ítems</p>"
                + "<p style='font-size:14px;'><b style='color:#d00000;'>JUGADOR vs MAQUINA:</b> Enfrenta tu equipo contra la IA</p>"
                + "<p style='font-size:14px;'><b style='color:#d00000;'>CONTROLES:</b> Usa el mouse para seleccionar movimientos</p>"
                + "</div></div></div></html>";

        JLabel label = new JLabel(message);

        JOptionPane.showMessageDialog(this, label, "INSTRUCCIONES",
                JOptionPane.INFORMATION_MESSAGE,
                createScaledIcon("POOBkemon/resources/front/battle.png", 48, 48));
    }

    private ImageIcon createScaledIcon(String path, int width, int height) {
        try {
            InputStream stream = getClass().getResourceAsStream(path);
            if (stream != null) {
                BufferedImage image = ImageIO.read(stream);
                return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
            }
        } catch (Exception e) {
            System.err.println("No se pudo cargar el ícono: " + e.getMessage());
        }
        return null;
    }

    // Método base con todos los parámetros
    private void startGame(String mode, String player1Name, Color player1Color,
                           String player2Name, Color player2Color) {
        Item.setSurvivalMode("SURVIVAL".equals(mode));
        dispose();

        if ("SURVIVAL".equals(mode)) {
            startSurvivalMode(player1Name, player1Color, player2Name, player2Color);
        } else if ("PvsP".equals(mode)) {
            startPvPMode(player1Name, player1Color, player2Name, player2Color);
        } else {
            startStandardMode(mode, player1Name, player1Color);
        }
    }

    // Sobrecarga para modos single-player
    private void startGame(String mode, String playerName, Color playerColor) {
        startGame(mode, playerName, playerColor, "Maquina", new Color(200, 50, 100));
    }

    // Sobrecarga para compatibilidad (valores por defecto)
    private void startGame(String mode) {
        startGame(mode, "Jugador", Color.BLUE, "Maquina", new Color(200, 50, 100));
    }

    private void startSurvivalMode(String player1Name, Color player1Color,
                                   String player2Name, Color player2Color) {
        List<Pokemon> team1 = SurvivalMode.generateRandomTeam();
        List<Pokemon> team2 = SurvivalMode.generateRandomTeam();

        Trainer trainer1 = new Trainer(
                player1Name,
                player1Color,
                team1,
                new ArrayList<>() // No items en supervivencia
        );

        Trainer trainer2 = new Trainer(
                player2Name,
                player2Color,
                team2,
                new ArrayList<>() // No items en supervivencia
        );

        new GameGUI(new Game(trainer1, trainer2), "SURVIVAL").setVisible(true);
    }
    private void startStandardMode(String mode, String player1Name, Color player1Color, String player2Name, Color player2Color) {
        List<Item> items = List.of(
                new Item("Poción", "heal20"),
                new Item("Superpoción", "heal50"),
                new Item("HyperPotion", "heal200"),
                new Item("Revivir", "revive")
        );

        List<Pokemon> allPokemons = createAllPokemons();

        Trainer trainer1 = new Trainer(
                player1Name,
                player1Color,
                "MvsM".equals(mode) ? List.of(
                        allPokemons.get(0),  // Absol
                        allPokemons.get(5),  // Charizard
                        allPokemons.get(9),  // Dragonite
                        allPokemons.get(15), // Grumpig
                        allPokemons.get(22), // Moltres
                        allPokemons.get(28)  // Snorlax
                ) : new ArrayList<>(allPokemons),
                new ArrayList<>(items)
        );

        Trainer trainer2 = new Trainer(
                player2Name,
                player2Color,
                List.of(
                        allPokemons.get(3),  // Blastoise
                        allPokemons.get(12), // Gengar
                        allPokemons.get(31), // Tyranitar
                        allPokemons.get(30), // Togetic
                        allPokemons.get(21), // Metagross
                        allPokemons.get(4)   // Blaziken
                ),
                new ArrayList<>(items)
        );

        new GameGUI(new Game(trainer1, trainer2), mode).setVisible(true);
    }

    // Sobrecarga para compatibilidad con llamadas existentes
    private void startStandardMode(String mode, String playerName, Color playerColor) {
        startStandardMode(mode, playerName, playerColor, "Máquina", new Color(200, 50, 100));
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                new MainMenuGUI().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error al iniciar la aplicación: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}