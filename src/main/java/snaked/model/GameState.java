package snaked.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import snaked.App;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * GameState Singleton
 * An instance of this class is created once and only once, at the beginning of the program.
 * Later, the instance can be accessed anywhere using GameState.getInstance()
 */
public class GameState {
    public static final String SCORE_FILENAME = System.getProperty("user.home") + "/snaked-scores.txt";
    public static final String SETTINGS_FILENAME = System.getProperty("user.home") + "/snaked-settings.json";
    public static final String INITIAL_SETTINGS_FILENAME = "config/initialSettings.json";
    @Getter private static GameState instance;

    @Getter private final GameOptions options;
    @Getter @Setter private Snake snake;
    @Getter @Setter private GameBoard gameBoard;
    // A single random number generator should be shared in whole application
    @Getter private final Random randomGen = new Random();
    @Getter private final Logger logger = Logger.getLogger(App.class.getName());
    private List<Integer> scores = new ArrayList<>();

    /**
     * Private constructor, so only one instance can be created (using the createInstance method)
     *
     * @param options GameOptions of the GameState
     */
    private GameState(GameOptions options) {
        this.options = options;
    }

    /**
     * Creates an instance of GameState.
     *
     * @return the GameState instance
     * @throws IOException           if the inputStream cannot be read
     * @throws IllegalStateException if an instance was already created previously
     */
    public static GameState createInstance() throws IOException {
        if (instance != null) throw new IllegalStateException("Instance already exists");

        InputStream inputStream;

        if((new File(SETTINGS_FILENAME)).exists())
            inputStream = new FileInputStream(SETTINGS_FILENAME);
        else
            inputStream = App.class.getResourceAsStream(INITIAL_SETTINGS_FILENAME);

        GameOptions gameOptions = (new ObjectMapper()).readValue(inputStream, GameOptions.class);
        instance = new GameState(gameOptions);
        return instance;
    }

    /**
     * Add a new score to the list
     *
     * @param score Score to add to the list
     */
    public void addScore(int score) {
        if (score <= 0)
            throw new IllegalArgumentException("The score must be bigger than 0");

        scores.add(score);
    }

    /**
     * Serializes the scores and settings and saves them into a file
     *
     * @throws IOException When the file cannot be written to
     */
    public void save() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCORE_FILENAME))) {
            oos.writeObject(scores);
        }
       (new ObjectMapper()).writer().writeValue(new FileOutputStream(SETTINGS_FILENAME), getInstance().getOptions());

    }

    /**
     * Load scores from file
     *
     * @throws IOException            If the file cannot be opened
     * @throws ClassNotFoundException If the file cannot be deserialized into an object
     */
    public void loadScores() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SCORE_FILENAME))) {
            // Generic types cannot be type-checked ("instanceof")
            // We know that we can expect a List<Integer>, so we are suppressing the warning
            @SuppressWarnings("unchecked")
            List<Integer> parsedScores = (List<Integer>) ois.readObject();
            scores = parsedScores;
        } catch (FileNotFoundException fileNotFoundException) {
            scores = new ArrayList<>();
        }
    }

    /**
     * Get the N highest scores
     *
     * @param n The number of highest scores to get
     * @return the N highest scores
     */
    public List<Integer> getNHighestScores(int n) {
        return scores.stream().sorted((i1, i2) -> i2 - i1).limit(n).toList();
    }

    /*
    * Creates a string out of the scores list and formats it to the needs of the scoreboard
    */
    public String formatHighScores(List highScores){
        String formattedScores = "";
        for (int i = 0; i < highScores.size(); i++) {
            formattedScores += highScores.get(i) + "\n" + "\n";
        } return formattedScores;
    }
}
