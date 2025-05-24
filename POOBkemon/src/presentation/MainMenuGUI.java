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
import java.util.*;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.util.List;
import domain.PokemonDefault;

/**
 * Clase que representa la interfaz gráfica del menú principal del juego POOBkemon.
 *
 * Esta clase extiende {@link JFrame} y maneja la carga de fuentes personalizadas,
 * configuración de la ventana, y la inicialización de la pantalla de inicio con
 * botones y navegación por teclado.
 * Contiene los componentes principales del menú, como botones para iniciar el juego
 * y salir, así como campos para ingreso de nombre y control de selección.
 *
 */
public class MainMenuGUI extends JFrame {
    private Font pokemonEmeraldFont;
    private Font pokemonFontBold;
    private int selectedButtonIndex = 0;
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

    /**
     * Carga las fuentes personalizadas necesarias para la aplicación.
     *
     * Intenta cargar la fuente "Pokemon Emerald Latin FC" desde las fuentes
     * instaladas en el sistema con dos estilos:
     *
     *   Normal (plain) tamaño 24
     *   Negrita (bold) tamaño 16
     *
     *
     * Si la fuente personalizada no está disponible o no se puede cargar,
     * captura la excepción y establece fuentes de respaldo estándar
     * ("Arial Rounded MT Bold") con estilos y tamaños equivalentes.
     *
     * Los objetos {@code pokemonEmeraldFont} y {@code pokemonFontBold} se actualizan
     * con las fuentes cargadas o con las fuentes de respaldo.
     * */

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

    /**
     * Configura la ventana principal de la aplicación.
     *
     * Este método establece el título, tamaño, comportamiento de cierre,
     * posición inicial y propiedades de redimensionamiento de la ventana.
     *
     * Además, intenta cargar un ícono personalizado para la ventana desde los
     * recursos internos del proyecto (en la ruta "/front/pokeball.png"). Si la carga
     * falla, imprime un mensaje de error en la consola.
     *
     * Se añade un listener para el evento de redimensionamiento del componente,
     * el cual llama a {@code revalidate()} para actualizar el layout si fuera necesario.
     *
     * Finalmente, invoca {@code initStartScreen()} para inicializar la pantalla de inicio.
     */
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

    /**
     * Inicializa la pantalla de inicio de la aplicación.
     *
     * Este método limpia el contenido actual del contenedor principal,
     * resetea las variables de control para el menú y crea una nueva interfaz
     * con un panel de fondo que muestra una imagen de portada o un fondo colorido
     * con título en texto si no hay imagen.
     *
     * Además, crea un panel con dos botones estilizados ("START" y "SALIR"),
     * con dibujo personalizado que incluye sombra sutil para el texto y tamaño
     * adaptativo.
     *
     * Se dibuja una flecha negra al lado del botón actualmente seleccionado
     * para indicar la selección del usuario, y se configuran las teclas de
     * navegación y acción mediante bindings de teclado.
     *
     * El botón "START" inicia la pantalla principal del menú, y el botón "SALIR"
     * cierra la aplicación.
     *
     * Se utiliza un diseño GridBagLayout para el panel de botones para facilitar
     * la alineación y espaciado de los mismos.
     */
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

    /**
     * Crea un JButton personalizado con un estilo inspirado en Pokémon Esmeralda,
     * que incluye una fuente específica, sombreado del texto y tamaño dinámico
     * basado en la longitud del texto.
     *
     * El botón calcula el tamaño óptimo de la fuente para ajustarse al texto,
     * dibuja el texto con sombra para un efecto visual más atractivo y ajusta
     * el tamaño preferido para que haya un padding adecuado, especialmente en textos largos.
     *
     * El botón no muestra el área de contenido ni el enfoque por defecto,
     * y usa un borde vacío para crear espacio adicional alrededor del texto.
     *
     * @param text El texto que se mostrará en el botón.
     * @return Un JButton con el estilo personalizado y el texto proporcionado.
     */
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

    /**
     * Calcula y ajusta el tamaño óptimo de la fuente para el texto del menú,
     * basándose en un conjunto predefinido de textos que podrían ser los más largos.
     *
     * Este método solo realiza el cálculo la primera vez que se llama, usando
     * una variable de control {@code calculatedFontSize} que inicialmente es 22f.
     *
     * El proceso consiste en:
     *
     *   Derivar una fuente base con tamaño 22f.
     *   Medir el ancho de varios textos de ejemplo representativos.
     *   Determinar el texto más ancho entre esos ejemplos.
     *   Comparar el ancho máximo con el ancho disponible en el panel (80% del ancho menos un padding de 60px).
     *   Ajustar el tamaño de fuente proporcionalmente si el texto más ancho no cabe en el espacio disponible.
     *   Garantizar que el tamaño no sea menor a 16 puntos para mantener legibilidad.
     *
     *
     * @param text El texto para el que se calcula el tamaño de fuente (no se usa directamente en el cálculo actual).
     */
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


    /**
     * Configura los atajos de teclado para el panel dado, permitiendo la navegación
     * y selección en el menú usando las teclas Enter, Flecha Arriba, Flecha Abajo
     * y Escape.
     *
     * Las acciones asignadas son:
     *
     *   Enter: Ejecuta la acción del botón actualmente seleccionado.
     *   Flecha Arriba: Mueve la selección hacia arriba en la lista de botones.
     *       Si está en el primer botón, salta al último.
     *   Flecha Abajo:Mueve la selección hacia abajo en la lista de botones.
     *       Si está en el último botón, salta al primero.
     *   Escape: Sale de la aplicación inmediatamente.
     *
     *
     * La selección actual se controla mediante las variables
     * {@code currentMenuButtons} (lista de botones) y {@code selectedButtonIndex}
     * (índice del botón seleccionado). Después de cambiar la selección, se
     * repinta el panel {@code currentButtonPanel} para reflejar visualmente la selección.
     *
     * @param panel El {@link JPanel} sobre el que se configuran los atajos de teclado.
     */
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

    /**
     * Intenta cargar la imagen "Portada.png" desde varias ubicaciones:
     * 1. Desde el classpath en "/front/" y "/resources/front/".
     * 2. Desde el sistema de archivos en la ruta relativa al directorio actual.
     *
     * @return BufferedImage cargada o null si no se encuentra.
     * @throws IOException si ocurre un error al leer la imagen.
     */
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

    /**
     * Muestra el menú principal de la aplicación con opciones para iniciar un nuevo juego,
     * ver instrucciones o volver a la pantalla inicial.
     *
     * El menú incluye:
     *
     *   Botón "NUEVO JUEGO" que lleva a la selección de modo de juego.
     *   Botón "COMO JUGAR" que muestra instrucciones.
     *   Botón "VOLVER" que regresa a la pantalla de inicio.
     *
     * El panel principal pinta un fondo con una imagen escalada o un color sólido si no hay imagen.
     * Además, el panel de opciones dibuja una flecha negra al lado del botón seleccionado para
     * indicar la opción activa.
     *
     * Se configuran atajos de teclado para facilitar la navegación por las opciones.
     *
     */

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
    /**
     * Muestra la interfaz para la selección del jugador 1 o la configuración inicial
     * según el modo de juego seleccionado.
     *
     * Este método soporta diferentes modos:
     *
     *   MvsM (Máquina vs Máquina): no se solicita nombre, solo color.
     *   PvsM (Jugador vs Máquina): se solicita nombre y color del jugador.
     *   Modo para dos jugadores: se solicita nombre y color para el jugador 1.
     *   Modo de un jugador: se solicita nombre y color del jugador.
     *
     * Dependiendo del modo, al confirmar se ejecutan diferentes acciones:
     *
     *   Iniciar modo automático Máquina vs Máquina.
     *   Mostrar selección de tipo de IA para jugador contra máquina.
     *   Pasar a la selección del segundo jugador en modo PvP.
     *
     * La interfaz incluye un fondo con imagen o color, campos para nombre (según modo),
     * selector de color y botones para volver o confirmar.
     *
     *
     * @param forTwoPlayers indica si la selección es para un modo de dos jugadores (true)
     *                      o un solo jugador (false)
     * @param isSurvivalMode indica si el modo actual es modo supervivencia (true) o no (false)
     * @param mode cadena que indica el modo de juego, puede ser "MvsM", "PvsM", u otro para modos normales
     */
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
                    startStandardMode("MvsM", "Máquina 1", colorChooser.getColor(),
                            "Máquina 2", new Color(200, 50, 100));
                } else if ("PvsM".equals(mode)) {
                    String playerName = nameField.getText().trim();
                    if (playerName.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "¡Ingresa un nombre válido!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    showAITypeSelection(playerName, colorChooser.getColor());
                } else if (forTwoPlayers) {
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

    /**
     * Muestra la interfaz para la selección del segundo jugador.
     *
     * Permite al usuario ingresar el nombre y seleccionar un color para el segundo jugador.
     * Según el modo de juego (supervivencia o normal), al confirmar:
     *
     *   Inicia el juego en modo supervivencia llamando a {@code startGame}.
     *   Abre la ventana de selección de Pokémon para PvP y cierra la ventana actual.
     *
     * Incluye controles para volver a la pantalla anterior y para confirmar la selección.
     * La ventana tiene un fondo con imagen o color sólido, y los elementos están estilizados
     * con fuentes y colores personalizados.
     *
     *
     * @param player1Name nombre del primer jugador, usado para pasar a la siguiente pantalla o modo
     * @param player1Color color asignado al primer jugador
     * @param isSurvivalMode indica si la selección es para el modo supervivencia (true) o modo normal (false)
     */

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
                new PokemonSelectionGUI("PvsP", player1Name, player1Color, player2Name, colorChooser.getColor(), false).setVisible(true);
                dispose(); // Cierra la ventana del menú
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

    /**
     * Inicia una partida en modo Jugador contra Jugador (PvP).
     *
     * Crea dos entrenadores con los nombres y colores proporcionados, asignándoles un equipo completo
     * de Pokémon (generado por {@link #createAllPokemons()}) y una lista de ítems específica para cada uno.
     *
     *
     * Los ítems para cada entrenador están limitados de la siguiente manera:
     *
     *   Jugador 1: hasta 2 pociones y 1 revive
     *   Jugador 2: hasta 2 superpociones y 1 revive
     *
     * Luego crea y muestra la interfaz gráfica del juego {@link GameGUI} con la instancia de {@link Game}
     * configurada para PvP, pasando ambos entrenadores.
     * En caso de que ocurra alguna excepción relacionada con el dominio del juego ({@link PoobkemonException}),
     * se captura y muestra un cuadro de diálogo de error.
     *
     * @param player1Name nombre del primer jugador
     * @param player1Color color asociado al primer jugador
     * @param player2Name nombre del segundo jugador
     * @param player2Color color asociado al segundo jugador
     */
    private void startPvPMode(String player1Name, Color player1Color,
                              String player2Name, Color player2Color) {
        try {
            // Crear listas de ítems separadas para cada entrenador respetando los límites
            List<Item> itemsPlayer1 = List.of(
                    new Potion(),
                    new Potion(),  // Máximo 2 pociones
                    new Revive()   // Máximo 1 revive
            );

            List<Item> itemsPlayer2 = List.of(
                    new SuperPotion(),
                    new SuperPotion(), // Máximo 2 superpociones
                    new Revive()       // Máximo 1 revive
            );

            List<Pokemon> allPokemons = createAllPokemons();

            Trainer trainer1 = new Trainer(
                    player1Name,
                    player1Color,
                    new ArrayList<>(allPokemons),
                    itemsPlayer1
            );

            Trainer trainer2 = new Trainer(
                    player2Name,
                    player2Color,
                    new ArrayList<>(allPokemons),
                    itemsPlayer2
            );

            new GameGUI(new Game(trainer1, trainer2), "PvsP").setVisible(true);
        } catch (PoobkemonException e) {
            // Manejar la excepción (mostrar mensaje de error, etc.)
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error al crear los entrenadores: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Muestra el panel con las opciones disponibles para el "Modo Normal" de juego.
     *
     * Este método limpia el contenido actual del contenedor principal y configura
     * una interfaz gráfica que permite al usuario elegir entre las siguientes opciones:
     *
     *   JUGADOR vs JUGADOR
     *   JUGADOR vs MAQUINA
     *   MAQUINA vs MAQUINA
     *   VOLVER al menú anterior
     *
     * El panel principal utiliza un fondo con imagen escalada si está disponible, o un color sólido en caso contrario.
     * En el centro, se presenta un título estilizado y botones para cada opción.
     * Además, se dibuja una flecha negra para indicar visualmente cuál es la opción seleccionada.
     *
     * También se configuran atajos de teclado para navegar y seleccionar las opciones usando el teclado.
     *
     *
     * Cada botón ejecuta una acción específica:
     *
     *   "JUGADOR vs JUGADOR" muestra la selección de jugadores para partida PvP.
     *   "JUGADOR vs MAQUINA" muestra la selección de jugador para partida PvM.
     *   "MAQUINA vs MAQUINA" muestra la selección para partida MvM.
     *   "VOLVER" regresa al menú de selección de modo de juego.
     *
     *
     * Finalmente, actualiza la interfaz para mostrar la nueva pantalla.
     */


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
        pvpButton.addActionListener(e -> {
            showPlayerSelection(true, false, "PvsP");
        });

        pvmButton.addActionListener(e -> {
            showPlayerSelection(false, false, "PvsM");
        });

        mvmButton.addActionListener(e -> {
            showPlayerSelection(false, false, "MvsM");
        });

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

    /**
     * Muestra el panel de selección del modo de juego en la ventana principal.
     *
     * Este método limpia el contenido actual del contenedor principal y configura
     * una nueva interfaz gráfica donde el usuario puede elegir entre diferentes modos
     * de juego: "Modo Normal", "Modo Supervivencia" o volver al menú principal.
     *
     * El panel principal de selección incluye un fondo con una imagen escalada (si está disponible),
     * un título estilizado y botones para cada opción de modo de juego.
     * Además, se dibuja una flecha negra indicando el botón actualmente seleccionado para
     * mejorar la navegación visual con teclado.
     *
     * Se configuran también los atajos de teclado para navegar entre los botones y seleccionar opciones.
     *
     *
     * Componentes principales:
     *
     *   Fondo con imagen escalada o color sólido si la imagen no está disponible.
     *   Panel central con título y botones para cada modo de juego.
     *   Dibujo de una flecha indicando el botón seleccionado.
     *
     *
     * Botones configurados:
     *
     *   "MODO NORMAL": Invoca opciones de modo normal.
     *   "MODO SUPERVIVENCIA": Muestra selección para modo supervivencia (jugador vs jugador).
     *   "VOLVER": Regresa al menú principal.
     *
     *
     * Este método actualiza y refresca la ventana para mostrar la nueva interfaz.
     */
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

    /**
     * Aplica un estilo visual personalizado a una etiqueta JLabel para el tema Pokémon.
     * El estilo incluye una fuente específica en negrita, un color de texto personalizado,
     * y un borde compuesto que combina un borde negro sólido con un espacio interno.
     *
     * @param label La etiqueta JLabel que será estilizada.
     * @param color El color que se aplicará al texto de la etiqueta.
     */
    private void stylePokemonLabel(JLabel label, Color color) {
        label.setFont(pokemonFontBold);
        label.setForeground(color);
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
    }

    /**
     * Muestra una ventana emergente con las instrucciones del juego.
     * El mensaje está formateado en HTML para mejorar la presentación visual,
     * incluyendo estilos como colores, bordes y tamaños de texto.
     * Describe brevemente los modos de juego disponibles y los controles básicos.
     * La ventana utiliza un ícono personalizado escalado para acompañar el mensaje.
     */
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

    /**
     * Carga una imagen desde un recurso dado y la escala al tamaño especificado.
     * El método intenta obtener un flujo de entrada del recurso ubicado en la ruta proporcionada,
     * luego lee la imagen y la escala suavemente a las dimensiones indicadas (ancho x alto).
     * Si ocurre algún error durante la carga o la lectura, se captura la excepción, se imprime
     * un mensaje de error y se devuelve {@code null}.
     *
     * @param path  la ruta del recurso de la imagen dentro del classpath (ejemplo: "/images/icon.png")
     * @param width el ancho deseado para la imagen escalada
     * @param height la altura deseada para la imagen escalada
     * @return un {@link ImageIcon} con la imagen escalada, o {@code null} si no pudo cargarse o procesarse la imagen
     */
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


    /**
     * Inicia el modo supervivencia donde los jugadores no tienen ítems.
     *
     * @param player1Name Nombre del jugador 1
     * @param player1Color Color del jugador 1
     * @param player2Name Nombre del jugador 2
     * @param player2Color Color del jugador 2
     */
    private void startSurvivalMode(String player1Name, Color player1Color,
                                   String player2Name, Color player2Color) {
        try {
            // Generar equipos aleatorios para el modo supervivencia
            List<Pokemon> team1 = SurvivalMode.generateRandomTeam();
            List<Pokemon> team2 = SurvivalMode.generateRandomTeam();

            // Crear entrenadores sin ítems (lista vacía)
            Trainer trainer1 = new Trainer(
                    player1Name,
                    player1Color,
                    team1,
                    Collections.emptyList() // Mejor práctica que new ArrayList<>()
            );

            Trainer trainer2 = new Trainer(
                    player2Name,
                    player2Color,
                    team2,
                    Collections.emptyList()
            );

            new GameGUI(new Game(trainer1, trainer2), "SURVIVAL").setVisible(true);

        } catch (PoobkemonException e) {
            // Manejar posibles excepciones (aunque es poco probable con listas vacías)
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error al iniciar el modo supervivencia: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    // En el método startStandardMode, reemplaza el código existente con:
    private void startStandardMode(String mode, String player1Name, Color player1Color,
                                   String player2Name, Color player2Color) {
        dispose(); // Cerrar menú principal

        try {
            // Crear lista de Pokémon disponibles
            List<Pokemon> allPokemons = createAllPokemons();

            // Crear listas de ítems que respeten los límites (2 pociones/1 revive)
            List<Item> defaultItems = List.of(
                    new Potion(),
                    new SuperPotion(),
                    new Revive()
            );

            if ("PvsP".equals(mode)) {
                PokemonSelectionGUI selectionGUI = new PokemonSelectionGUI(
                        mode,
                        player1Name,
                        player1Color,
                        player2Name,
                        player2Color,
                        false
                );
                selectionGUI.setVisible(true);
            } else if ("PvsM".equals(mode)) {
                PokemonSelectionGUI selectionGUI = new PokemonSelectionGUI(
                        mode,
                        player1Name,
                        player1Color,
                        player2Name,
                        player2Color,
                        true
                );
                selectionGUI.setVisible(true);
            } else if ("MvsM".equals(mode)) {
                // Modo máquina vs máquina - equipos predefinidos con ítems limitados
                Trainer trainer1 = new Trainer(
                        player1Name,
                        player1Color,
                        List.of(
                                allPokemons.get(0).clone(),  // Absol
                                allPokemons.get(5).clone(),  // Charizard
                                allPokemons.get(9).clone(),  // Dragonite
                                allPokemons.get(15).clone(), // Grumpig
                                allPokemons.get(22).clone(), // Moltres
                                allPokemons.get(28).clone()  // Snorlax
                        ),
                        defaultItems
                );

                Trainer trainer2 = new Trainer(
                        player2Name,
                        player2Color,
                        List.of(
                                allPokemons.get(3).clone(),  // Blastoise
                                allPokemons.get(12).clone(), // Gengar
                                allPokemons.get(31).clone(), // Tyranitar
                                allPokemons.get(30).clone(), // Togetic
                                allPokemons.get(21).clone(), // Metagross
                                allPokemons.get(4).clone()   // Blaziken
                        ),
                        defaultItems
                );

                new GameGUI(new Game(trainer1, trainer2), mode).setVisible(true);
            }
        } catch (PoobkemonException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error al iniciar el modo estándar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }



    // Sobrecarga para compatibilidad con llamadas existentes
    private void startStandardMode(String mode, String playerName, Color playerColor) {
        startStandardMode(
                mode,
                (playerName == null || playerName.trim().isEmpty()) ? "Jugador" : playerName,
                playerColor != null ? playerColor : Color.BLUE,
                "Máquina",
                new Color(200, 50, 100)
        );
    }

    /**
     * Crea y devuelve una lista con todas las instancias de los Pokémon disponibles.
     * Cada Pokémon se instancia individualmente y se añade a una lista mutable. En caso de
     * ocurrir alguna excepción durante la creación, se captura el error, se imprime un mensaje
     * en la consola y se devuelve una lista básica con un conjunto reducido de Pokémon como respaldo.
     *
     * @return una lista de objetos {@link Pokemon} que representa todos los Pokémon disponibles;
     *         en caso de error, devuelve una lista con tres Pokémon básicos (Charizard, Blastoise, Venusaur).
     */
    private List<Pokemon> createAllPokemons() {
        try {
            // Crear una lista mutable para manejar posibles errores
            List<Pokemon> pokemons = new ArrayList<>();

            // Añadir cada Pokémon con manejo de errores individual
            pokemons.add(new Absol());
            pokemons.add(new Altaria());
            pokemons.add(new Banette());
            pokemons.add(new Blastoise());
            pokemons.add(new Blaziken());
            pokemons.add(new Charizard());
            pokemons.add(new Crobat());
            pokemons.add(new Delibird());
            pokemons.add(new Donphan());
            pokemons.add(new Dragonite());
            pokemons.add(new Flygon());
            pokemons.add(new Gardevoir());
            pokemons.add(new Gengar());
            pokemons.add(new Glalie());
            pokemons.add(new Granbull());
            pokemons.add(new Grumpig());
            pokemons.add(new Machamp());
            pokemons.add(new Manectric());
            pokemons.add(new Masquerain());
            pokemons.add(new Mawile());
            pokemons.add(new Medicham());
            pokemons.add(new Metagross());
            pokemons.add(new Moltres());
            pokemons.add(new Ninjask());
            pokemons.add(new Pidgeot());
            pokemons.add(new Raichu());
            pokemons.add(new Sceptile());
            pokemons.add(new Seviper());
            pokemons.add(new Snorlax());
            pokemons.add(new Solrock());
            pokemons.add(new Swampert());
            pokemons.add(new Togetic());
            pokemons.add(new Tyranitar());
            pokemons.add(new Umbreon());
            pokemons.add(new Venusaur());
            pokemons.add(new Zangoose());

            return pokemons;
        } catch (Exception e) {
            System.err.println("Error al crear lista de Pokémon: " + e.getMessage());
            // Devolver lista básica como fallback
            return List.of(new Charizard(), new Blastoise(), new Venusaur());
        }
    }

    /**
     * Muestra la pantalla de selección del tipo de entrenador de inteligencia artificial (IA).
     *
     * Este método limpia el contenido actual del contenedor principal, crea un panel vertical con
     * botones que representan los diferentes tipos de entrenadores IA disponibles, y permite
     * al usuario seleccionar uno para iniciar una partida contra dicha IA.
     * *
     * El panel contiene:
     *   Un título que indica la acción a realizar.
     *   Un botón para cada tipo de entrenador IA ("Agresivo", "Defensivo", "Cambiante", "Experto").
     *   Un botón para volver al menú anterior.
     * Al seleccionar un tipo de IA, se crea un objeto {AbstractMachine} correspondiente y
     * se lanza la partida PvM (Player vs Machine) con el jugador actual y el entrenador IA seleccionado.
     * En caso de error durante la creación o lanzamiento del juego, se muestra un diálogo de error.
     *
     * @param playerName   Nombre del jugador humano actual, usado para iniciar el juego.
     * @param playerColor  Color asociado al jugador humano, usado para personalización visual.
     */
    private void showAITypeSelection(String playerName, Color playerColor) {
        getContentPane().removeAll();
        currentMenuButtons.clear();
        selectedButtonIndex = 0;

        JPanel aiPanel = new JPanel();
        aiPanel.setLayout(new BoxLayout(aiPanel, BoxLayout.Y_AXIS));
        aiPanel.setOpaque(false);
        aiPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        JLabel titleLabel = new JLabel("SELECCIONA EL TIPO DE ENTRENADOR");
        stylePokemonLabel(titleLabel, Color.RED);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiPanel.add(titleLabel);
        aiPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] aiTypes = { "Agresivo", "Defensivo", "Cambiante", "Experto" };

        for (String aiType : aiTypes) {
            JButton button = createEmeraldStyleButton(aiType);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> {
                try {
                    AbstractMachine aiTrainer = createAITrainer(aiType, "Maquina", new Color(200, 50, 100));
                    launchPvMGame(playerName, playerColor, aiTrainer);
                } catch (PoobkemonException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Error al crear el juego: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
            currentMenuButtons.add(button);
            aiPanel.add(button);
            aiPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        JButton backButton = createEmeraldStyleButton("VOLVER");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> showPlayerSelection(false, false, "PvsM"));
        currentMenuButtons.add(backButton);
        aiPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        aiPanel.add(backButton);

        currentButtonPanel = aiPanel;
        add(aiPanel);
        setupKeyBindings(aiPanel);
        revalidate();
        repaint();
    }

    /**
     * Crea un entrenador controlado por IA según el tipo especificado.
     *
     * El equipo base es una sublista de los primeros 6 Pokémon generados por createAllPokemons().
     * Asocia cada Pokémon con sus movimientos actuales para inicializar el mapa de movimientos.
     *
     * @param type Tipo de IA: "Agresivo", "Defensivo", "Cambiante", "Experto".
     * @param name Nombre del entrenador IA.
     * @param color Color representativo del entrenador.
     * @return Instancia concreta de AbstractTrainer según el tipo.
     * @throws IllegalArgumentException si el tipo es desconocido.
     */
    private AbstractMachine createAITrainer(String type, String name, Color color) {
        List<Pokemon> team = createAllPokemons().subList(0, 6); // por simplicidad
        Map<Pokemon, List<Move>> moveMap = new HashMap<>();
        for (Pokemon p : team) {
            moveMap.put(p, p.getMoves()); // o un método para generar movimientos
        }

        switch (type) {
            case "Agresivo":
                return new AttackingMachine(name, color, team, moveMap);
            case "Defensivo":
                return new DefensiveMachine(name, color, team, moveMap);
            case "Cambiante":
                return new ChangingMachine(name, color, team, moveMap);
            case "Experto":
                return new ExpertMachine(name, color, team, moveMap);
            default:
                throw new IllegalArgumentException("Tipo de IA desconocido: " + type);
        }
    }

    /**
     * Inicia una partida jugador contra máquina (PvM).
     *
     * Construye un equipo de 6 Pokémon para el jugador clonando
     * Pokémon predeterminados (índices 6 a 11 de la lista general).
     * Asigna al jugador un conjunto de ítems estándar.
     *
     * Luego crea un entrenador humano y lanza la interfaz de juego con el entrenador
     * AI proporcionado.
     *
     * Finalmente, cierra la ventana actual.
     *
     * @param playerName Nombre del jugador humano.
     * @param playerColor Color representativo del jugador.
     * @param aiTrainer Entrenador controlado por IA para el oponente.
     * @throws PoobkemonException si hay un error al configurar el juego.
     */
    private void launchPvMGame(String playerName, Color playerColor, AbstractMachine aiTrainer) throws PoobkemonException {
        List<Item> items = List.of(new Potion(), new SuperPotion(), new Revive());
        List<Pokemon> playerTeam = new ArrayList<>();

        for (Pokemon p : createAllPokemons().subList(6, 12)) {
            playerTeam.add(p.clone());
        }

        Trainer human = new Trainer(playerName, playerColor, playerTeam, items);
        new GameGUI(new Game(human, aiTrainer), "PvsM").setVisible(true);
        dispose();
    }


    /**
     * Método principal para iniciar la aplicación POOBkemon.
     *
     * Ejecuta la creación de la interfaz gráfica en el hilo de eventos de Swing.
     *
     * Se configura el "look and feel" a un tema multiplataforma antes de mostrar
     * la ventana principal del menú.
     *
     * En caso de error al iniciar, muestra un diálogo de error.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
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