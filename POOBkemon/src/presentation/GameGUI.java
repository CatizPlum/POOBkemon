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
import java.util.Map;
import java.util.Random;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;

/**
 * Clase GameGUI que extiende JFrame y representa la interfaz gráfica
 * principal del juego POOBkemon durante una batalla.
 *
 * Esta clase maneja la visualización del estado del juego, incluyendo
 * los paneles para los entrenadores, Pokémon, barras de HP, movimientos,
 * temporizador y controles de batalla.
 *
 * También integra la lógica para interactuar con el objeto Game que
 * controla la lógica de la batalla, y gestiona eventos de interfaz,
 * como ataques, cambios de Pokémon, uso de objetos, y pausas.
 */
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
    private boolean isPaused = false;


    public GameGUI(Game game, String mode) {
        this.game = game;
        this.mode = mode;
        setTitle("POOBkemon Battle");
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loadBackgroundImages();
        initComponents();
        setJMenuBar(createMenuBar());
        setVisible(true);
        SwingUtilities.invokeLater(this::showCoinFlipAnimation);

    }

    /**
     * Crea y configura la barra de menú principal para la ventana del juego.
     *
     * La barra contiene un menú "Archivo" con las siguientes opciones:
     * - Pausar Juego: alterna entre pausar y reanudar el juego mediante togglePauseGame().
     * - Nueva Partida: confirma con el usuario si desea empezar una nueva partida y vuelve al menú principal.
     * - Abrir: permite al usuario seleccionar un archivo de partida guardada (*.pokemon) para cargarlo y abrirlo.
     * - Guardar Como: abre un diálogo para guardar la partida actual con un nombre y ubicación definidos por el usuario.
     * - Salir: pregunta al usuario si desea salir del juego y cierra la aplicación si confirma.
     *
     * @return JMenuBar la barra de menú configurada para la ventana del juego.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();




        // Menú Archivo
        JMenu fileMenu = new JMenu("Archivo");
        menuBar.add(fileMenu);

        // Opción Pausar/Reanudar
        JMenuItem pauseItem = new JMenuItem("Pausar Juego");
        pauseItem.addActionListener(e -> togglePauseGame());
        fileMenu.add(pauseItem);

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

    /**
     * Alterna la pausa del juego.
     *
     * Si el juego no está pausado, detiene el temporizador del turno y el temporizador visual,
     * desactiva los botones de la interfaz, y muestra un diálogo informando que el juego está pausado.
     *
     * Cuando el usuario cierra el diálogo, el juego se reanuda automáticamente:
     * se reinician los temporizadores y se habilitan nuevamente los controles.
     *
     * Este método no hace nada si el juego ya está pausado.
     */
    private void togglePauseGame() {
        if (!isPaused) {
            isPaused = true;
            game.stopTurnTimer(); // Detiene el temporizador
            if (guiTimer != null && guiTimer.isRunning()) {
                guiTimer.stop();  // Detiene temporizador visual
            }

            // Desactivar botones
            setButtonsEnabled(false);

            JOptionPane.showMessageDialog(this,
                    "El juego ha sido pausado.\nPresiona OK para reanudar.",
                    "Juego Pausado", JOptionPane.INFORMATION_MESSAGE);

            // Al cerrar el mensaje, reanudar
            isPaused = false;
            game.startTurnTimer();
            if (guiTimer != null) {
                guiTimer.start();
            }
            setButtonsEnabled(true);
        }
    }

    /**
     * Activa o desactiva todos los botones y el combo de movimientos en la interfaz.
     *
     * @param enabled true para habilitar los botones y combo, false para deshabilitarlos.
     *
     * Esto es útil para controlar la interacción del usuario durante ciertas fases del juego,
     * como mientras se está procesando una acción o cuando el juego está en pausa.
     */
    private void setButtonsEnabled(boolean enabled) {
        attackButton.setEnabled(enabled);
        changeButton.setEnabled(enabled);
        itemButton.setEnabled(enabled);
        nextTurnButton.setEnabled(enabled);
        movesCombo.setEnabled(enabled);
    }

    /**
     * Abre un diálogo para que el usuario seleccione la ubicación y el nombre
     * del archivo donde desea guardar la partida actual.
     *
     * - Utiliza un JFileChooser con filtro para archivos con extensión ".pokemon".
     * - Asegura que el archivo seleccionado termine con la extensión ".pokemon".
     * - Invoca el método `saveToFile` del objeto `game`, pasando la ruta y el modo actual.
     * - Muestra un mensaje de confirmación si el guardado fue exitoso.
     * - Muestra un mensaje de error si ocurre una excepción al guardar.
     *
     * La interfaz queda a la espera de la selección del usuario, y no continúa
     * hasta que se haya completado o cancelado la acción.
     */
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

    /**
     * Carga y prepara las imágenes de fondo utilizadas en la interfaz de batalla.
     *
     * Este método realiza las siguientes tareas:
     *
     * 1. Intenta cargar la imagen de fondo principal "battle.png" desde el paquete de recursos
     *    (ruta "/front/battle.png"). Si no se encuentra en recursos, intenta cargarla desde el
     *    sistema de archivos en "POOBkemon/resources/front/battle.png".
     *
     * 2. Carga la imagen "battle_box.png" que representa la caja inferior de la batalla desde
     *    recursos o sistema de archivos de forma similar. Luego escala esta imagen para que tenga
     *    una altura fija (por defecto 180 píxeles) manteniendo la proporción del ancho.
     *
     * 3. Utiliza interpolación bilineal para escalar la imagen con buena calidad visual.
     *
     * 4. Si ocurre algún error durante la carga o lectura de las imágenes, se crean imágenes
     *    de respaldo sencillas con colores sólidos para que la interfaz no quede vacía ni lance
     *    excepciones.
     *
     * Variables de instancia afectadas:
     * - backgroundImage: BufferedImage con la imagen de fondo principal.
     * - battleBoxImage: BufferedImage con la imagen escalada para la caja inferior.
     */
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

    /**
     * Inicializa y configura todos los componentes gráficos de la interfaz de batalla.
     *
     * Este método construye la estructura visual principal del panel de batalla, incluyendo:
     * - Panel principal con layout BorderLayout.
     * - Panel de fondo de batalla con imagen personalizada.
     * - Panel inferior (boxPanel) con imagen de caja para mostrar controles.
     * - Panel de batalla dividido en dos secciones para cada entrenador.
     * - Labels para mostrar nombres de entrenadores y sprites de Pokémon.
     * - Barras de vida (HP) para cada Pokémon, con estilo transparente.
     * - Paneles con posicionamiento absoluto para colocar sprites, barras y nombres con coordenadas fijas.
     * - Panel de información adicional.
     * - Panel de movimientos con JComboBox y botones para acciones (atacar, cambiar Pokémon, usar objeto, siguiente turno, huir).
     * - Label y panel para el temporizador visual.
     *
     * Además, asigna los listeners a los botones para manejar las acciones del juego,
     * y configura el callback para manejar el evento cuando se agote el tiempo.
     *
     * El método también configura colores, fuentes y estilos visuales para mantener una apariencia consistente.
     */
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
        JButton fleeButton = createStyledButton("Huir");

        movesCombo.setPreferredSize(new Dimension(200, 25));
        movesCombo.setFont(new Font("Arial", Font.PLAIN, 14));

        movesPanel.add(new JLabel("Select Move: "));
        movesPanel.add(movesCombo);
        movesPanel.add(Box.createHorizontalStrut(10));
        movesPanel.add(attackButton);
        movesPanel.add(changeButton);
        movesPanel.add(itemButton);
        movesPanel.add(nextTurnButton);
        movesPanel.add(fleeButton);

        boxPanel.add(movesPanel, BorderLayout.CENTER);

        mainPanel.add(battleBackgroundPanel, BorderLayout.CENTER);
        mainPanel.add(boxPanel, BorderLayout.SOUTH);
        add(mainPanel);

        attackButton.addActionListener(e -> attack());
        changeButton.addActionListener(e -> changePokemon());
        itemButton.addActionListener(e -> useItem());
        nextTurnButton.addActionListener(e -> nextTurn());
        fleeButton.addActionListener(e -> attemptToFlee());


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

    /**
     * Intenta huir de la batalla actual.
     *
     * - Muestra un cuadro de diálogo de confirmación para que el jugador decida si desea huir.
     * - Si el jugador confirma la huida:
     *   - Detiene los temporizadores del GUI y del juego.
     *   - Deshabilita los botones de acción para evitar más interacciones.
     *   - Declara al entrenador actual como perdedor y al entrenador contrario como ganador.
     *   - Muestra un mensaje con el resultado de la batalla indicando la huida y el ganador por abandono.
     *   - Cierra la ventana actual y abre el menú principal.
     */
    private void attemptToFlee() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas huir de la batalla?\nEsto contará como derrota.",
                "Confirmar huida",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            guiTimer.stop();
            game.stopTurnTimer();
            setButtonsEnabled(false);

            Trainer loser = game.getCurrentTrainer();
            Trainer winner = game.getWaitingTrainer();

            String message = loser.getName() + " ha huido de la batalla.\n"
                    + "¡" + winner.getName() + " es el ganador por abandono!\n\n"
                    + getBattleResults();

            JOptionPane.showMessageDialog(this, message, "Huida confirmada", JOptionPane.INFORMATION_MESSAGE);
            // Puedes cerrar la ventana o redirigir al menú
            dispose();
            new MainMenuGUI().setVisible(true);
        }
    }


    /**
     * Maneja la situación cuando el tiempo de turno se agota.
     *
     * - Detiene el temporizador visual de la interfaz.
     * - Obtiene el Pokémon actual del entrenador activo.
     * - Construye un mensaje que informa que el tiempo se agotó y muestra
     *   la reducción de PP en los movimientos de categoría especial del Pokémon actual.
     * - Muestra un diálogo modal con la información, pausando la ejecución hasta que el usuario
     *   cierre el mensaje.
     * - Tras cerrar el diálogo, avanza al siguiente turno y actualiza la pantalla.
     */
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

    /**
     * Crea y devuelve un botón estilizado con apariencia personalizada.
     *
     * El botón tiene:
     * - Fondo de color azul (RGB 70, 120, 200).
     * - Texto en color blanco.
     * - Sin pintura de enfoque para evitar el borde por defecto al hacer foco.
     * - Borde compuesto que combina un borde en relieve elevado y un margen interno.
     *
     * @param text El texto que se mostrará en el botón.
     * @return Un JButton con estilo personalizado listo para usarse en la interfaz.
     */
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

    /**
     * Actualiza todos los elementos visuales de la interfaz gráfica para reflejar el estado actual del juego.
     *
     * Funciones principales:
     * - Detiene el temporizador gráfico anterior si está corriendo.
     * - Actualiza los nombres y el indicador de turno de los entrenadores.
     * - Configura colores de fondo y texto para las etiquetas de entrenadores.
     * - Verifica si el Pokémon actual está debilitado y, de ser así, fuerza el cambio de Pokémon.
     * - Muestra el nivel de los Pokémon según el modo de juego (SURVIVAL o normal).
     * - Actualiza las barras de HP con valores actuales y colores según la salud.
     * - Carga y muestra los sprites correspondientes de los Pokémon.
     * - Actualiza la lista desplegable de movimientos disponibles para el Pokémon actual.
     * - Maneja el turno automático para modos con maquinas.
     * - Actualiza y muestra el temporizador visual con colores según el tiempo restante.
     * - Inicia los temporizadores para la interfaz y el juego.
     *
     * Consideraciones:
     * - Si el Pokémon actual está debilitado, se llama a `forceSwitchFaintedPokemon` y se detiene la actualización hasta que el jugador elija un nuevo Pokémon.
     * - El temporizador visual se actualiza cada segundo para mostrar el tiempo restante del turno.
     *
     * @throws NullPointerException Si alguna referencia a objetos del juego es nula.
     */
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

        // 👈 NUEVO: Verificar si el Pokémon actual está debilitado
        Pokemon currentPokemon = game.getCurrentTrainer().getCurrentPokemon();
        if (currentPokemon.isFainted()) {
            forceSwitchFaintedPokemon(game.getCurrentTrainer());
            return; // Esperar a que el jugador seleccione un nuevo Pokémon
        }

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

    /**
     * Fuerza al entrenador a cambiar su Pokémon actual si está debilitado.
     *
     * - Revisa el equipo del entrenador para encontrar Pokémon no debilitados.
     * - Si no hay Pokémon disponibles, muestra un mensaje de error y verifica el fin del juego.
     * - Si hay Pokémon disponibles, muestra un diálogo para que el entrenador seleccione uno para cambiar.
     * - Realiza el cambio de Pokémon seleccionado y actualiza la interfaz gráfica.
     *
     * @param trainer El entrenador que debe cambiar de Pokémon debido a que el actual está debilitado.
     */
    private void forceSwitchFaintedPokemon(Trainer trainer) {
        List<Pokemon> team = trainer.getTeam();
        List<Pokemon> available = new ArrayList<>();

        for (Pokemon p : team) {
            if (!p.isFainted()) {
                available.add(p);
            }
        }

        if (available.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    trainer.getName() + " no tiene Pokémon disponibles.",
                    "Todos los Pokémon debilitados",
                    JOptionPane.ERROR_MESSAGE);
            checkEnd(); // Mostrar mensaje de fin de juego
            return;
        }

        Pokemon[] options = available.toArray(new Pokemon[0]);
        Pokemon selected = (Pokemon) JOptionPane.showInputDialog(
                this,
                trainer.getName() + ", tu Pokémon actual está debilitado.\nSelecciona otro Pokémon:",
                "Cambio de Pokémon",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (selected != null) {
            try {
                trainer.switchPokemon(team.indexOf(selected));
                JOptionPane.showMessageDialog(this,
                        "¡Adelante, " + selected.getName() + "!",
                        "Cambio de Pokémon",
                        JOptionPane.INFORMATION_MESSAGE);
                updateScreen(); // Redibujar pantalla con el nuevo Pokémon
            } catch (PoobkemonException e) {
                JOptionPane.showMessageDialog(this,
                        "Error al cambiar Pokémon: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /**
     * Actualiza el color y tamaño de la fuente de la etiqueta del temporizador según el tiempo restante.
     *
     * - Si quedan 5 segundos o menos, el color cambia a rojo y el tamaño de la fuente aumenta.
     * - En caso contrario, el color es blanco y la fuente tiene tamaño normal.
     *
     * Además, verifica si el juego ha terminado llamando a `checkEnd()`.
     */
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

    /**
     * Obtiene el color que representa el estado de salud basado en la proporción de HP actual sobre el máximo.
     *
     * - Verde si la salud es mayor al 50%.
     * - Amarillo si la salud está entre 20% y 50%.
     * - Rojo si la salud es igual o menor al 20%.
     *
     * @param current La cantidad actual de HP.
     * @param max     La cantidad máxima de HP.
     * @return        El color correspondiente al estado de salud.
     */
    private Color getHpColor(int current, int max) {
        double ratio = (double)current / max;
        if (ratio > 0.5) return Color.GREEN;
        if (ratio > 0.2) return Color.YELLOW;
        return Color.RED;
    }

    /**
     * Carga y asigna el sprite de un Pokémon a un JLabel dado.
     *
     * Este método utiliza la ruta del sprite obtenida mediante `getSpritePath` para cargar la imagen
     * del Pokémon desde los recursos internos del proyecto (classpath). Si no se encuentra en el classpath,
     * intenta cargar la imagen desde el sistema de archivos local (ruta relativa al directorio de trabajo).
     *
     * La imagen se escala a 200x200 píxeles antes de asignarla al JLabel.
     *
     * En caso de que la imagen no se encuentre o haya un error al cargarla,
     * el JLabel mostrará un texto indicativo ("Sprite no encontrado" o "Error en sprite").
     *
     * @param label  El JLabel donde se mostrará el sprite del Pokémon.
     * @param pokemon El objeto Pokémon del cual se obtiene el nombre para cargar su sprite.
     * @param back   Indica si se debe cargar la vista trasera (true) o frontal (false) del sprite.
     */
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

    /**
     * Obtiene la ruta del sprite correspondiente a un Pokémon dado su nombre y la vista requerida.
     *
     * Este método normaliza el nombre del Pokémon (convirtiendo a minúsculas y eliminando espacios)
     * y retorna la ruta relativa al recurso gráfico que representa al Pokémon.
     *
     * La ruta devuelta depende del parámetro `back`:
     * - Si `back` es true, se retorna la ruta del sprite de vista trasera (como se ve desde el jugador 1).
     * - Si `back` es false, se retorna la ruta del sprite de vista frontal (como se ve desde el jugador 2).
     *
     * Si el nombre del Pokémon no está en la lista, retorna una ruta por defecto a una imagen "unknown".
     *
     * @param pokemonName El nombre del Pokémon, sin importar mayúsculas/minúsculas o espacios extra.
     * @param back        Indica si se desea la ruta del sprite trasero (true) o frontal (false).
     * @return            La ruta relativa al recurso del sprite del Pokémon correspondiente.
     */
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

    /**
     * Ejecuta la acción de ataque del Pokémon actual sobre el Pokémon oponente.
     *
     * El método detiene el temporizador del turno, obtiene el movimiento seleccionado
     * por el jugador actual y calcula el daño causado al Pokémon oponente.
     *
     * Luego, aplica el daño, muestra un mensaje con la información del ataque,
     * ejecuta la animación de ataque, verifica si el juego ha terminado,
     * avanza al siguiente turno y actualiza la interfaz gráfica.
     *
     * Si ocurre alguna excepción durante el proceso, muestra un cuadro de diálogo con el error.
     */
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

    /**
     * Anima el sprite del Pokémon objetivo para simular un ataque recibido.
     *
     * El sprite del Pokémon objetivo se mueve de manera aleatoria en un área pequeña alrededor
     * de su posición original durante aproximadamente 300 milisegundos, creando un efecto de "temblor".
     * Al finalizar la animación, el sprite vuelve a su posición original.
     *
     * Esta animación se ejecuta usando dos temporizadores Swing: uno que mueve el sprite cada 50 ms
     * y otro que detiene la animación después de 300 ms.
     */
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

    /**
     * Permite al entrenador actual cambiar su Pokémon activo por otro disponible en su equipo.
     *
     * El método detiene el temporizador del turno, muestra un diálogo para que el usuario
     * seleccione un Pokémon disponible que no esté debilitado ni sea el actualmente activo.
     * Si se selecciona uno, se realiza el cambio de Pokémon, se notifica al usuario,
     * se avanza al siguiente turno y se actualiza la interfaz gráfica.
     *
     * Si no hay otros Pokémon disponibles para cambiar, se lanza una excepción personalizada
     * que muestra un mensaje de error.
     */
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

    /**
     * Permite al entrenador actual seleccionar y usar un ítem de su inventario durante su turno.
     *
     * El método detiene el temporizador del turno, muestra un diálogo para que el usuario
     * seleccione un ítem disponible, y si se selecciona uno, se aplica el efecto sobre el Pokémon actual.
     * Luego elimina el ítem usado del inventario, avisa al usuario y avanza al siguiente turno,
     * actualizando la interfaz gráfica.
     *
     * Si no hay ítems disponibles, se lanza una excepción personalizada que muestra un mensaje de error.
     */
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

    /**
     * Finaliza el temporizador del turno actual, avanza al siguiente turno en el juego
     * y actualiza la interfaz gráfica para reflejar el nuevo estado.
     */
    private void nextTurn() {
        game.stopTurnTimer();
        game.nextTurn();
        updateScreen();
    }

    /**
     * Realiza una acción aleatoria durante el turno.
     *
     * Se elige al azar entre tres posibles acciones:
     * - Atacar
     * - Cambiar de Pokémon
     * - Usar un ítem
     */
    private void randomAction() {
        Random random = new Random();
        int action = random.nextInt(3);
        switch (action) {
            case 0 -> attack();
            case 1 -> changePokemon();
            case 2 -> useItem();
        }
    }

    /**
     * Verifica si la batalla ha terminado.
     *
     * Si el juego está finalizado, detiene el temporizador de la interfaz gráfica y
     * el temporizador del turno del juego, deshabilita los botones de interacción,
     * determina el ganador o si hubo un empate, y muestra un cuadro de diálogo con
     * el mensaje correspondiente junto con los resultados de los equipos.
     */

    private void checkEnd() {
        if (game.isOver()) {
            guiTimer.stop();
            game.stopTurnTimer();
            setButtonsEnabled(false);

            Trainer winner = determineWinner();
            String message;

            if (winner != null) {
                message = "¡" + winner.getName() + " ha ganado la batalla!\n\n";
            } else {
                message = "¡La batalla terminó en empate!\n\n";
            }

            // Mostrar los resultados de cada equipo
            message += getBattleResults();

            JOptionPane.showMessageDialog(this, message, "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /**
     * Determina el ganador actual de la batalla en función del estado de los Pokémon activos de ambos entrenadores.
     *
     * @return El Trainer que ha ganado la batalla si uno de los Pokémon activos está debilitado
     *         (fainted) y el otro no. Retorna {@code null} si ninguno o ambos Pokémon están debilitados,
     *         lo que indica que no hay un ganador definido aún.
     */
    private Trainer determineWinner() {
        boolean p1Fainted = game.getTrainer1().getCurrentPokemon().isFainted();
        boolean p2Fainted = game.getTrainer2().getCurrentPokemon().isFainted();

        if (p1Fainted && !p2Fainted) {
            return game.getTrainer2();
        } else if (p2Fainted && !p1Fainted) {
            return game.getTrainer1();
        } else {
            return null;
        }
    }

    /**
     * Genera un resumen textual del estado actual de los Pokémon de ambos entrenadores en la batalla.
     *
     * Para cada entrenador, se lista su nombre seguido de la información de cada uno de sus Pokémon,
     * mostrando el nombre y los puntos de salud actuales y máximos en formato "actual/máximo HP".
     *
     *
     * @return Un String que contiene el resumen del estado de los equipos de ambos entrenadores,
     *         organizado por entrenador y con cada Pokémon en una línea separada.
     */

    private String getBattleResults() {
        StringBuilder sb = new StringBuilder();

        sb.append("--- ").append(game.getTrainer1().getName()).append(" ---\n");
        for (Pokemon p : game.getTrainer1().getTeam()) {
            sb.append(p.getName())
                    .append(": ")
                    .append(p.getCurrentHP())
                    .append("/")
                    .append(p.getMaxHP())
                    .append(" HP\n");
        }

        sb.append("\n--- ").append(game.getTrainer2().getName()).append(" ---\n");
        for (Pokemon p : game.getTrainer2().getTeam()) {
            sb.append(p.getName())
                    .append(": ")
                    .append(p.getCurrentHP())
                    .append("/")
                    .append(p.getMaxHP())
                    .append(" HP\n");
        }

        return sb.toString();
    }


    /**
     * Muestra una animación de lanzamiento de moneda para decidir quién comienza el juego.
     *
     * Se crea un diálogo no modal con una animación GIF que simula el lanzamiento de una moneda,
     * acompañado de una instrucción para el usuario. Después de unos segundos (3 segundos),
     * se selecciona aleatoriamente qué jugador inicia la partida.
     *
     * Una vez decidido el jugador inicial, el diálogo se cierra y se muestra un mensaje informativo
     * indicando quién comenzará la batalla. Finalmente, se actualiza la pantalla para continuar
     * con el juego.
     * El diálogo es un {@link JDialog} sin decoración, centrado respecto a la ventana principal.
     * La animación se carga desde un archivo GIF externo. Si la animación no puede cargarse,
     * se muestra un mensaje de error en rojo.
     *
     */
    private void showCoinFlipAnimation() {
        // Crear ventana de diálogo sin bloqueo
        JDialog dialog = new JDialog(this, "Lanzando moneda...", false);
        dialog.setUndecorated(true);
        dialog.setSize(280, 320);
        dialog.setLocationRelativeTo(this);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);

        JLabel gifLabel;
        try {
            ImageIcon gifIcon = new ImageIcon("POOBkemon/resources/front/monedag.gif");
            gifLabel = new JLabel(gifIcon, SwingConstants.CENTER);
        } catch (Exception e) {
            gifLabel = new JLabel("No se pudo cargar la animación", SwingConstants.CENTER);
            gifLabel.setForeground(Color.RED);
        }

        // Etiqueta con el texto
        JLabel instructionLabel = new JLabel(
                "<html><div style='text-align: center;'>Espera unos segundos<br>para que se elija aleatoriamente<br>quién empieza el juego</div></html>",
                SwingConstants.CENTER);
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        instructionLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        panel.add(gifLabel, BorderLayout.CENTER);
        panel.add(instructionLabel, BorderLayout.SOUTH);

        dialog.setContentPane(panel);
        dialog.setVisible(true);

        // Temporizador para cerrar y seleccionar jugador
        Timer timer = new Timer(3000, e -> {
            boolean player1Starts = new Random().nextBoolean();
            Trainer starter = player1Starts ? game.getTrainer1() : game.getTrainer2();

            System.out.println("Sorteado: " + starter.getName());

            game.setStartingTrainer(starter);

            dialog.dispose();

            JOptionPane.showMessageDialog(this,
                    starter.getName() + " comenzará la batalla.",
                    "Resultado del lanzamiento",
                    JOptionPane.INFORMATION_MESSAGE);

            updateScreen();
        });

        timer.setRepeats(false);
        timer.start();
    }
}
