package presentation;

import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameGUI extends JFrame {
    private Game game;
    private JPanel battlePanel;
    private JPanel infoPanel;
    private JPanel movesPanel;
    private JLabel trainer1Label;
    private JLabel trainer2Label;
    private JLabel pokemon1Label;
    private JLabel pokemon2Label;
    private JLabel pokemon1Sprite;
    private JLabel pokemon2Sprite;
    private JComboBox<String> movesCombo;
    private JButton attackButton;
    private JButton changeButton;
    private JButton itemButton;
    private JButton nextTurnButton;
    private JProgressBar hpBar1;
    private JProgressBar hpBar2;
    private BufferedImage backgroundImage;
    private BufferedImage battleBoxImage;
    private String mode;
    private JLabel timerLabel;
    private javax.swing.Timer guiTimer;

    public GameGUI(Game game, String mode) {
        this.game = game;
        this.mode = mode;
        setTitle("POOBkemon Battle");
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loadBackgroundImages();
        initComponents();
        updateScreen();
        setJMenuBar(createMenuBar());
        setVisible(true);
    }
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menú Archivo
        JMenu fileMenu = new JMenu("Archivo");
        menuBar.add(fileMenu);

        // Opción Nueva Partida
        JMenuItem newGameItem = new JMenuItem("Nueva Partida");
        newGameItem.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    GameGUI.this,
                    "¿Estás seguro de que quieres comenzar una nueva partida?",
                    "Confirmar nueva partida",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Cierra la ventana actual
                new MainMenuGUI().setVisible(true); // Regresa al menú principal
            }
        });
        fileMenu.add(newGameItem);

        // Separador
        fileMenu.addSeparator();

        // Opción Abrir Partida
        JMenuItem openItem = new JMenuItem("Abrir");
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Abrir Partida Guardada");

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Archivos de POOBkemon (*.pokemon)", "pokemon");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(GameGUI.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    // Cambio aquí: Usar Game.SaveData en lugar de GameSaveManager.GameSaveData
                    Game.SaveData saveData = Game.loadFromFile(fileChooser.getSelectedFile().getAbsolutePath());

                    dispose(); // Cierra la ventana actual
                    new GameGUI(saveData.getGame(), saveData.getMode()).setVisible(true);
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(
                            GameGUI.this,
                            "Error al cargar la partida: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fileMenu.add(openItem);

        // Opción Guardar Como
        JMenuItem saveAsItem = new JMenuItem("Guardar Como");
        saveAsItem.addActionListener(e -> saveGameAs());
        fileMenu.add(saveAsItem);

        // Separador
        fileMenu.addSeparator();

        // Opción Salir
        JMenuItem exitItem = new JMenuItem("Salir");
        exitItem.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    GameGUI.this,
                    "¿Estás seguro de que quieres salir del juego?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        return menuBar;
    }

    // Método para guardar la partida
    private void saveGameAs() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Partida Como");

        // Configurar filtro de archivos
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Archivos de POOBkemon (*.pokemon)", "pokemon");
        fileChooser.setFileFilter(filter);

        // Mostrar diálogo de guardado
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Asegurar que el archivo tenga la extensión correcta
            if (!fileToSave.getName().toLowerCase().endsWith(".pokemon")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pokemon");
            }

            try {
                // Cambio aquí: Usar el método saveToFile de Game directamente
                game.saveToFile(fileToSave.getAbsolutePath(), mode);

                JOptionPane.showMessageDialog(this,
                        "Partida guardada exitosamente en:\n" + fileToSave.getAbsolutePath(),
                        "Guardado Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al guardar la partida:\n" + ex.getMessage(),
                        "Error al Guardar",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadBackgroundImages() {
        try {
            // Cargar imagen de fondo principal (battle.png)
            InputStream bgStream = getClass().getResourceAsStream("/front/battle.png");
            if (bgStream != null) {
                backgroundImage = ImageIO.read(bgStream);
            } else {
                backgroundImage = ImageIO.read(new File("POOBkemon/resources/front/battle.png"));
            }

            // Cargar y escalar la battle_box.png
            InputStream boxStream = getClass().getResourceAsStream("/front/battle_box.png");
            if (boxStream != null) {
                BufferedImage originalBoxImage = ImageIO.read(boxStream);

                // Definir la nueva altura deseada (ajusta este valor)
                int newHeight = 180;  // Puedes cambiarlo a 200, 150, etc.
                int originalWidth = originalBoxImage.getWidth();
                int originalHeight = originalBoxImage.getHeight();

                // Calcular el nuevo ancho proporcionalmente
                int newWidth = (int) ((double) originalWidth / originalHeight * newHeight);

                // Escalar la imagen con mejor calidad
                battleBoxImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = battleBoxImage.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawImage(originalBoxImage, 0, 0, newWidth, newHeight, null);
                g.dispose();
            } else {
                // Si no se carga desde recursos, intentar cargar desde archivo y escalar
                BufferedImage originalBoxImage = ImageIO.read(new File("POOBkemon/resources/front/battle_box.png"));
                int newHeight = 180;
                int originalWidth = originalBoxImage.getWidth();
                int originalHeight = originalBoxImage.getHeight();
                int newWidth = (int) ((double) originalWidth / originalHeight * newHeight);

                battleBoxImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = battleBoxImage.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawImage(originalBoxImage, 0, 0, newWidth, newHeight, null);
                g.dispose();
            }
        } catch (IOException e) {
            System.err.println("Error loading background images: " + e.getMessage());

            // Imagen de respaldo para battle.png
            backgroundImage = new BufferedImage(900, 550, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = backgroundImage.createGraphics();
            g.setColor(new Color(70, 130, 180));
            g.fillRect(0, 0, 900, 550);
            g.dispose();

            // Imagen de respaldo para battle_box.png (más alta)
            battleBoxImage = new BufferedImage(900, 180, BufferedImage.TYPE_INT_RGB); // Altura aumentada
            g = battleBoxImage.createGraphics();
            g.setColor(new Color(50, 50, 80));
            g.fillRect(0, 0, 900, 180);  // Misma altura que arriba
            g.dispose();
        }
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(900, 650));

        JPanel battleBackgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        battleBackgroundPanel.setPreferredSize(new Dimension(900, 550));

        JPanel boxPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(battleBoxImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        boxPanel.setPreferredSize(new Dimension(900, 100));

        battlePanel = new JPanel(new GridLayout(1, 2));
        battlePanel.setOpaque(false);

        trainer1Label = new JLabel("", SwingConstants.CENTER);
        trainer2Label = new JLabel("", SwingConstants.CENTER);
        trainer1Label.setOpaque(true);
        trainer2Label.setOpaque(true);

        pokemon1Sprite = new JLabel("", SwingConstants.CENTER);
        pokemon2Sprite = new JLabel("", SwingConstants.CENTER);
        pokemon1Label = new JLabel("", SwingConstants.CENTER);
        pokemon2Label = new JLabel("", SwingConstants.CENTER);
        pokemon1Label.setForeground(Color.WHITE);
        pokemon2Label.setForeground(Color.WHITE);

        hpBar1 = new JProgressBar();
        hpBar2 = new JProgressBar();
        hpBar1.setStringPainted(true);
        hpBar2.setStringPainted(true);
        hpBar1.setBackground(new Color(0, 0, 0, 150));
        hpBar2.setBackground(new Color(0, 0, 0, 150));

        // Panel Pokémon 1 con posicionamiento absoluto
        JPanel pokemon1Panel = new JPanel(null);
        pokemon1Panel.setOpaque(false);

        // Posicionamiento de componentes del jugador 1
        pokemon1Label.setBounds(130, 285, 200, 20);
        pokemon1Sprite.setBounds(135, 300, 200, 200);
        hpBar1.setBounds(50, 230, 200, 20);

        pokemon1Panel.add(pokemon1Label);
        pokemon1Panel.add(pokemon1Sprite);
        pokemon1Panel.add(hpBar1);

        // Panel Pokémon 2 con posicionamiento absoluto
        JPanel pokemon2Panel = new JPanel(null);
        pokemon2Panel.setOpaque(false);

        // Posicionamiento de componentes del jugador 2
        pokemon2Label.setBounds(100, 65, 200, 20);
        pokemon2Sprite.setBounds(105, 65, 200, 200);
        hpBar2.setBounds(10, 230, 200, 20);

        pokemon2Panel.add(pokemon2Label);
        pokemon2Panel.add(pokemon2Sprite);
        pokemon2Panel.add(hpBar2);

        // Paneles laterales
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.add(trainer1Label, BorderLayout.NORTH);
        leftPanel.add(pokemon1Panel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(trainer2Label, BorderLayout.NORTH);
        rightPanel.add(pokemon2Panel, BorderLayout.CENTER);

        battlePanel.add(leftPanel);
        battlePanel.add(rightPanel);
        battleBackgroundPanel.add(battlePanel, BorderLayout.CENTER);

        infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Battle Info"));
        battleBackgroundPanel.add(infoPanel, BorderLayout.NORTH);

        movesPanel = new JPanel(new FlowLayout());
        movesPanel.setOpaque(false);
        movesCombo = new JComboBox<>();
        attackButton = createStyledButton("Attack");
        changeButton = createStyledButton("Change Pokémon");
        itemButton = createStyledButton("Use Item");
        nextTurnButton = createStyledButton("Next Turn");

        movesCombo.setPreferredSize(new Dimension(200, 25));
        movesCombo.setFont(new Font("Arial", Font.PLAIN, 14));

        movesPanel.add(new JLabel("Select Move: "));
        movesPanel.add(movesCombo);
        movesPanel.add(Box.createHorizontalStrut(10));
        movesPanel.add(attackButton);
        movesPanel.add(changeButton);
        movesPanel.add(itemButton);
        movesPanel.add(nextTurnButton);

        boxPanel.add(movesPanel, BorderLayout.CENTER);

        mainPanel.add(battleBackgroundPanel, BorderLayout.CENTER);
        mainPanel.add(boxPanel, BorderLayout.SOUTH);
        add(mainPanel);

        attackButton.addActionListener(e -> attack());
        changeButton.addActionListener(e -> changePokemon());
        itemButton.addActionListener(e -> useItem());
        nextTurnButton.addActionListener(e -> nextTurn());

        // Configurar el label del temporizador
        timerLabel = new JLabel("20", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setBackground(new Color(0, 0, 0, 150));
        timerLabel.setOpaque(true);
        timerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Panel para el temporizador (esquina superior derecha)
        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setOpaque(false);
        timerPanel.add(timerLabel, BorderLayout.EAST);
        battleBackgroundPanel.add(timerPanel, BorderLayout.NORTH);

        // Configurar el callback para cuando se agote el tiempo
        game.setOnTimeOutCallback(this::handleTimeOut);
    }

    private void handleTimeOut() {
        // Detener el temporizador visual
        if (guiTimer != null && guiTimer.isRunning()) {
            guiTimer.stop();
        }

        Pokemon currentPokemon = game.getCurrentTrainer().getCurrentPokemon();
        StringBuilder message = new StringBuilder("¡Tiempo agotado!\n");

        // Mostrar cambios en los PP
        message.append("Se redujo el PP de los movimientos especiales de ")
                .append(currentPokemon.getName()).append(":\n");

        for (Move move : currentPokemon.getMoves()) {
            if (move.getCategory() == MoveCategory.SPECIAL) {
                message.append("- ").append(move.getName())
                        .append(": PP ").append(move.getPp()).append("\n");
            }
        }

        // Mostrar diálogo modal que pausará la ejecución hasta que se presione OK
        JOptionPane.showMessageDialog(this, message.toString(), "Tiempo agotado", JOptionPane.WARNING_MESSAGE);

        // Solo después de presionar OK, pasar al siguiente turno
        game.nextTurn();
        updateScreen();
    }


    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(70, 120, 200));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return button;
    }

    private void updateScreen() {
        // Detener el temporizador anterior si existe
        if (guiTimer != null && guiTimer.isRunning()) {
            guiTimer.stop();
        }
        Trainer current = game.getCurrentTrainer();
        Trainer waiting = game.getWaitingTrainer();

        // Actualizar etiquetas de entrenadores con indicador de turno
        trainer1Label.setText(game.getCurrentTrainer() == game.getTrainer1() ? "▶ " + game.getTrainer1().getName() : game.getTrainer1().getName());
        trainer2Label.setText(game.getCurrentTrainer() == game.getTrainer2() ? "▶ " + game.getTrainer2().getName() : game.getTrainer2().getName());

        // Configurar colores de fondo para los entrenadores
        trainer1Label.setBackground(new Color(
                game.getTrainer1().getColor().getRed(),
                game.getTrainer1().getColor().getGreen(),
                game.getTrainer1().getColor().getBlue(), 150));
        trainer2Label.setBackground(new Color(
                game.getTrainer2().getColor().getRed(),
                game.getTrainer2().getColor().getGreen(),
                game.getTrainer2().getColor().getBlue(), 150));

        trainer1Label.setForeground(Color.WHITE);
        trainer2Label.setForeground(Color.WHITE);

        Pokemon p1 = game.getTrainer1().getCurrentPokemon();
        Pokemon p2 = game.getTrainer2().getCurrentPokemon();

        // Mostrar nivel según el modo de juego
        if ("SURVIVAL".equals(mode)) {
            pokemon1Label.setText(p1.getName() + " Lv.100");
            pokemon2Label.setText(p2.getName() + " Lv.100");
        } else {
            pokemon1Label.setText(p1.getName() + " Lv.50");
            pokemon2Label.setText(p2.getName() + " Lv.50");
        }

        // Configurar barras de HP
        hpBar1.setMaximum(p1.getMaxHP());
        hpBar1.setValue(p1.getCurrentHP());
        hpBar1.setString(p1.getCurrentHP() + "/" + p1.getMaxHP());
        hpBar1.setForeground(getHpColor(p1.getCurrentHP(), p1.getMaxHP()));

        hpBar2.setMaximum(p2.getMaxHP());
        hpBar2.setValue(p2.getCurrentHP());
        hpBar2.setString(p2.getCurrentHP() + "/" + p2.getMaxHP());
        hpBar2.setForeground(getHpColor(p2.getCurrentHP(), p2.getMaxHP()));

        // Cargar sprites de los Pokémon
        loadPokemonSprite(pokemon1Sprite, p1, false); // Vista trasera para jugador 1
        loadPokemonSprite(pokemon2Sprite, p2, true);  // Vista frontal para jugador 2

        // Actualizar lista de movimientos
        movesCombo.removeAllItems();
        List<Move> moves = current.getCurrentPokemon().getMoves();
        for (Move move : moves) {
            movesCombo.addItem(move.getName() + " (" + move.getType() + ", " + move.getPower() + " power)");
        }

        // Manejar turno automático para modos con IA
        if (mode.equals("MvsM") || (mode.equals("PvsM") && current == game.getTrainer2())) {
            Timer timer = new Timer(1000, e -> {
                randomAction();
                ((Timer) e.getSource()).stop();
            });
            timer.setRepeats(false);
            timer.start();
        }

        // Configurar el temporizador visual
        timerLabel.setText(String.valueOf(game.getTimeRemaining()));
        updateTimerColor();

        // Iniciar el temporizador de la interfaz
        guiTimer = new javax.swing.Timer(1000, e -> {
            int remaining = game.getTimeRemaining();
            timerLabel.setText(String.valueOf(remaining));
            updateTimerColor();
        });
        guiTimer.start();

        // Iniciar el temporizador del juego
        game.startTurnTimer();
    }

    private void updateTimerColor() {
        int remaining = game.getTimeRemaining();
        if (remaining <= 5) {
            timerLabel.setForeground(Color.RED);
            timerLabel.setFont(timerLabel.getFont().deriveFont(Font.BOLD, 28));
        } else {
            timerLabel.setForeground(Color.WHITE);
            timerLabel.setFont(timerLabel.getFont().deriveFont(Font.BOLD, 24));
        }

        // Verificar si el juego ha terminado
        checkEnd();
    }

    private Color getHpColor(int current, int max) {
        double ratio = (double)current / max;
        if (ratio > 0.5) return Color.GREEN;
        if (ratio > 0.2) return Color.YELLOW;
        return Color.RED;
    }

    private void loadPokemonSprite(JLabel label, Pokemon pokemon, boolean back) {
        try {
            String spritePath = getSpritePath(pokemon.getName(), back);
            InputStream inputStream = getClass().getResourceAsStream(spritePath);

            if (inputStream != null) {
                label.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(inputStream))
                        .getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            } else {
                String fullPath = System.getProperty("user.dir") + "/POOBkemon" + spritePath;
                if (new File(fullPath).exists()) {
                    label.setIcon(new ImageIcon(new ImageIcon(fullPath)
                            .getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
                } else {
                    label.setIcon(null);
                    label.setText("Sprite no encontrado");
                }
            }
        } catch (IOException e) {
            label.setIcon(null);
            label.setText("Error en sprite");
        }
    }

    private String getSpritePath(String pokemonName, boolean back) {
        String normalizedName = pokemonName.toLowerCase().trim();

        // Sprites para el jugador 1 (vista tracera)
        if (!back) {
            switch (normalizedName) {
                case "charizard": return "/resources/pokemon_back_sprites/6.png";
                case "blastoise": return "/resources/pokemon_back_sprites/9.png";
                case "venusaur": return "/resources/pokemon_back_sprites/3.png";
                case "gengar": return "/resources/pokemon_back_sprites/94.png";
                case "machamp": return "/resources/pokemon_back_sprites/68.png";
                case "raichu": return "/resources/pokemon_back_sprites/26.png";
                case "snorlax": return "/resources/pokemon_back_sprites/143.png";
                case "tyranitar": return "/resources/pokemon_back_sprites/248.png";
                case "donphan": return "/resources/pokemon_back_sprites/232.png";
                case "delibird": return "/resources/pokemon_back_sprites/225.png";
                case "togetic": return "/resources/pokemon_back_sprites/176.png";
                case "dragonite": return "/resources/pokemon_back_sprites/149.png";
                case "metagross": return "/resources/pokemon_back_sprites/376.png";
                case "gardevoir": return "/resources/pokemon_back_sprites/282.png";
                case "mawile": return "/resources/pokemon_back_sprites/303.png";
                case "swampert": return "/resources/pokemon_back_sprites/260.png";
                case "ninjask": return "/resources/pokemon_back_sprites/291.png";
                case "altaria": return "/resources/pokemon_back_sprites/334.png";
                case "manectric": return "/resources/pokemon_back_sprites/310.png";
                case "banette": return "/resources/pokemon_back_sprites/354.png";
                case "blaziken": return "/resources/pokemon_back_sprites/257.png";
                case "granbull": return "/resources/pokemon_back_sprites/210.png";
                case "glalie": return "/resources/pokemon_back_sprites/362.png";
                case "medicham": return "/resources/pokemon_back_sprites/308.png";
                case "zangoose": return "/resources/pokemon_back_sprites/335.png";
                case "sceptile": return "/resources/pokemon_back_sprites/254.png";
                case "grumpig": return "/resources/pokemon_back_sprites/326.png";
                case "solrock": return "/resources/pokemon_back_sprites/338.png";
                case "umbreon": return "/resources/pokemon_back_sprites/197.png";
                case "flygon": return "/resources/pokemon_back_sprites/330.png";
                case "crobat": return "/resources/pokemon_back_sprites/169.png";
                case "moltres": return "/resources/pokemon_back_sprites/146.png";
                case "absol": return "/resources/pokemon_back_sprites/359.png";
                case "seviper": return "/resources/pokemon_back_sprites/336.png";
                case "pidgeot": return "/resources/pokemon_back_sprites/18.png";
                case "masquerain": return "/resources/pokemon_back_sprites/284.png";
                default: return "/resources/pokemon_back_sprites/unknown.png";

            }
        }
        // Sprites para el jugador 2 (vista frontal - sin cambios)
        else {
            switch (normalizedName) {
                case "charizard": return "/resources/pokemon_sprites/6.gif";
                case "blastoise": return "/resources/pokemon_sprites/9.gif";
                case "venusaur": return "/resources/pokemon_sprites/3.gif";
                case "gengar": return "/resources/pokemon_sprites/94.gif";
                case "machamp": return "/resources/pokemon_sprites/68.gif";
                case "raichu": return "/resources/pokemon_sprites/26.gif";
                case "snorlax": return "/resources/pokemon_sprites/143.gif";
                case "tyranitar": return "/resources/pokemon_sprites/248.gif";
                case "donphan": return "/resources/pokemon_sprites/232.gif";
                case "delibird": return "/resources/pokemon_sprites/225.gif";
                case "togetic": return "/resources/pokemon_sprites/176.gif";
                case "dragonite": return "/resources/pokemon_sprites/149.gif";
                case "metagross": return "/resources/pokemon_sprites/376.gif";
                case "gardevoir": return "/resources/pokemon_sprites/282.gif";
                case "mawile": return "/resources/pokemon_sprites/303.gif";
                case "swampert": return "/resources/pokemon_sprites/260.gif";
                case "ninjask": return "/resources/pokemon_sprites/291.gif";
                case "altaria": return "/resources/pokemon_sprites/334.gif";
                case "manectric": return "/resources/pokemon_sprites/310.gif";
                case "banette": return "/resources/pokemon_sprites/354.gif";
                case "blaziken": return "/resources/pokemon_sprites/257.gif";
                case "granbull": return "/resources/pokemon_sprites/210.gif";
                case "glalie": return "/resources/pokemon_sprites/362.gif";
                case "medicham": return "/resources/pokemon_sprites/308.gif";
                case "zangoose": return "/resources/pokemon_sprites/335.gif";
                case "sceptile": return "/resources/pokemon_sprites/254.gif";
                case "grumpig": return "/resources/pokemon_sprites/326.gif";
                case "solrock": return "/resources/pokemon_sprites/338.gif";
                case "umbreon": return "/resources/pokemon_sprites/197.gif";
                case "flygon": return "/resources/pokemon_sprites/330.gif";
                case "crobat": return "/resources/pokemon_sprites/169.gif";
                case "moltres": return "/resources/pokemon_sprites/146.gif";
                case "absol": return "/resources/pokemon_sprites/359.gif";
                case "seviper": return "/resources/pokemon_sprites/336.gif";
                case "pidgeot": return "/resources/pokemon_sprites/18g.gif";
                case "masquerain": return "/resources/pokemon_sprites/284.gif";
                default: return "/resources/pokemon_sprites/unknown.png";
            }
        }
    }

    private void attack() {
        try {
            game.stopTurnTimer();
            Trainer current = game.getCurrentTrainer();
            Trainer opponent = game.getWaitingTrainer();
            int index = movesCombo.getSelectedIndex();
            Move move = current.getCurrentPokemon().getMoves().get(index);

            int damage = move.calculateDamage(current.getCurrentPokemon(), opponent.getCurrentPokemon());
            opponent.getCurrentPokemon().takeDamage(damage);

            String attackInfo = String.format("%s used %s! It dealt %d damage!",
                    current.getCurrentPokemon().getName(),
                    move.getName(),
                    damage);

            JOptionPane.showMessageDialog(this, attackInfo, "Attack", JOptionPane.INFORMATION_MESSAGE);

            animateAttack();
            checkEnd();
            game.nextTurn();
            updateScreen();
        }catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    private void animateAttack() {
        JLabel targetLabel = game.getCurrentTrainer() == game.getTrainer1() ? pokemon2Sprite : pokemon1Sprite;
        Point originalLocation = targetLabel.getLocation();

        Timer timer = new Timer(50, null);
        timer.addActionListener(e -> {
            int x = originalLocation.x + (int)(Math.random() * 10 - 5);
            int y = originalLocation.y + (int)(Math.random() * 10 - 5);
            targetLabel.setLocation(x, y);
        });

        Timer stopper = new Timer(300, ev -> {
            timer.stop();
            targetLabel.setLocation(originalLocation);
        });
        stopper.setRepeats(false);

        timer.start();
        stopper.start();
    }

    private void changePokemon() {
        try {
            game.stopTurnTimer();
            Trainer current = game.getCurrentTrainer();
            List<Pokemon> team = current.getTeam();

            List<Pokemon> available = new ArrayList<>();
            for (Pokemon p : team) {
                if (!p.isFainted() && p != current.getCurrentPokemon()) {
                    available.add(p);
                }
            }

            if (available.isEmpty()) {
                throw new PoobkemonException("No other Pokémon available!");
            }

            Pokemon[] options = available.toArray(new Pokemon[0]);
            Pokemon selected = (Pokemon) JOptionPane.showInputDialog(
                    this,
                    "Select a Pokémon to switch to:",
                    "Change Pokémon",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (selected != null) {
                current.switchPokemon(team.indexOf(selected));
                JOptionPane.showMessageDialog(this,
                        "Go! " + selected.getName() + "!",
                        "Pokémon Change",
                        JOptionPane.INFORMATION_MESSAGE);
                game.nextTurn();
                updateScreen();
            }
        } catch (PoobkemonException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


private void useItem() {
    try {
        game.stopTurnTimer();
        Trainer current = game.getCurrentTrainer();
        List<Item> items = current.getItems();

        if (items.isEmpty()) {
            throw new PoobkemonException("No items available!");
        }

        Item[] options = items.toArray(new Item[0]);
        Item selected = (Item) JOptionPane.showInputDialog(
                this,
                "Select an item to use:",
                "Use Item",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (selected != null) {
            selected.apply(current.getCurrentPokemon());
            items.remove(selected);
            JOptionPane.showMessageDialog(this,
                    "Used " + selected.getName() + "!",
                    "Item Used",
                    JOptionPane.INFORMATION_MESSAGE);
            game.nextTurn();
            updateScreen();
        }
    } catch (PoobkemonException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void nextTurn() {
        game.stopTurnTimer();
        game.nextTurn();
        updateScreen();
    }


    private void randomAction() {
        Random random = new Random();
        int action = random.nextInt(3);
        switch (action) {
            case 0 -> attack();
            case 1 -> changePokemon();
            case 2 -> useItem();
        }
    }

    private void checkEnd() {
        if (game.isOver()) {
            String winner = game.getTrainer1().getCurrentPokemon().isFainted() ?
                    game.getTrainer2().getName() : game.getTrainer1().getName();

            JOptionPane.showMessageDialog(this,
                    winner + " wins the battle!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Pokemon charizard = new Charizard();
            Pokemon blastoise = new Blastoise();
            List<Item> items = List.of(
                    new Item("Potion", "heal20"),
                    new Item("Super Potion", "heal50")
            );

            Trainer trainer1 = new Trainer("Player 1", Color.BLUE, List.of(charizard), items);
            Trainer trainer2 = new Trainer("Player 2", Color.RED, List.of(blastoise), items);

            new GameGUI(new Game(trainer1, trainer2), "PvsP");
        });
    }
}
