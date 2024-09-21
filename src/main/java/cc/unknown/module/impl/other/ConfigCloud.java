package cc.unknown.module.impl.other;

public class ConfigCloud {

package cc.unknown.module.impl.other;

import javax.swing .*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

    public class ConfigCloud {

        private JFrame frame;
        private JTextField configNameField;
        private JList<String> configList;
        private DefaultListModel<String> listModel;

        public ConfigCloud() {
            frame = new JFrame("Config Cloud");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            frame.add(panel);
            placeComponents(panel);

            frame.setVisible(true);
            updateConfigList();
        }

        private void placeComponents(JPanel panel) {
            panel.setLayout(null);

            JLabel configNameLabel = new JLabel("Config Name:");
            configNameLabel.setBounds(10, 20, 80, 25);
            panel.add(configNameLabel);

            configNameField = new JTextField(20);
            configNameField.setBounds(100, 20, 165, 25);
            panel.add(configNameField);

            JButton saveButton = new JButton("Save");
            saveButton.setBounds(10, 50, 80, 25);
            panel.add(saveButton);
            saveButton.addActionListener(this::onSaveButtonClicked);

            JButton loadButton = new JButton("Load");
            loadButton.setBounds(100, 50, 80, 25);
            panel.add(loadButton);
            loadButton.addActionListener(this::onLoadButtonClicked);

            listModel = new DefaultListModel<>();
            configList = new JList<>(listModel);
            configList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane listScroller = new JScrollPane(configList);
            listScroller.setBounds(10, 90, 465, 250);
            panel.add(listScroller);
        }

        private void onSaveButtonClicked(ActionEvent e) {
            try {
                saveConfig(configNameField.getText());
            } catch (IOException ex) {
                showMessage("Error saving config: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        }

        private void onLoadButtonClicked(ActionEvent e) {
            if (configList.getSelectedValue() != null) {
                try {
                    loadConfig(configList.getSelectedValue());
                } catch (IOException ex) {
                    showMessage("Error loading config: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                showMessage("Please select a configuration to load.", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void saveConfig(String name) throws IOException {
            if (name == null || name.trim().isEmpty()) {
                showMessage("Config name cannot be empty.", JOptionPane.WARNING_MESSAGE);
                return;
            }

            File configFile = new File(name + ".cfg");
            if (configFile.exists()) {
                showMessage("Config already exists: " + name, JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (configFile.createNewFile()) {
                showMessage("Config saved: " + name, JOptionPane.INFORMATION_MESSAGE);
                updateConfigList();
            } else {
                showMessage("Failed to create config: " + name, JOptionPane.ERROR_MESSAGE);
            }
        }

        private void loadConfig(String name) throws IOException {
            File configFile = new File(name + ".cfg");
            if (!configFile.exists()) {
                showMessage("Config not found: " + name, JOptionPane.WARNING_MESSAGE);
                return;
            }

            String configData = new String(Files.readAllBytes(Paths.get(name + ".cfg")));
// Assuming the config data is in key-value pairs, separated by new lines
            String[] lines = configData.split("\n");
            for (String line : lines) {
                String[] keyValue = line.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    // Apply configuration logic here
                    switch (key) {
                        case "theme":
                            applyThemeSetting(value);
                            break;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

                        public class ConfigCloud {

                            // This method loads the configuration from a file
                            private void loadConfig(String name) throws IOException {
                                File configFile = new File(name + ".cfg");
                                if (!configFile.exists()) {
                                    showMessage("Config not found: " + name, JOptionPane.WARNING_MESSAGE);
                                    return;
                                }

                                String configData = new String(Files.readAllBytes(Paths.get(name + ".cfg")));
                                // Assuming the config data is in key-value pairs, separated by new lines
                                String[] lines = configData.split("\n");
                                for (String line : lines) {
                                    String[] keyValue = line.split("=");
                                    if (keyValue.length == 2) {
                                        String key = keyValue[0].trim();
                                        String value = keyValue[1].trim();
                                        // Apply configuration logic here
                                        switch (key) {
                                            case "theme":
                                                applyThemeSetting(value);
                                                break;
                                            case "volume":
                                                applyVolumeSetting(value);
                                                break;
                                            case "difficulty":
                                                applyDifficultySetting(value);
                                                break;
                                            case "username":
                                                applyUsernameSetting(value);
                                                break;
                                            case "language":
                                                applyLanguageSetting(value);
                                                break;
                                            default:
                                                System.out.println("Unknown setting: " + key);
                                                // Optionally handle unknown settings
                                        }
                                    }
                                }

                                System.out.println("Configuration loaded: " + configData);
                                showMessage("Config loaded: " + name, JOptionPane.INFORMATION_MESSAGE);
                            }

                            // Applies the theme setting
                            private void applyThemeSetting(String value) {
                                System.out.println("Applying theme: " + value);
                                // Implement theme application logic here
                                // e.g., ThemeManager.setTheme(value);
                            }

                            // Applies the volume setting
                            private void applyVolumeSetting(String value) {
                                try {
                                    int volume = Integer.parseInt(value);
                                    if (volume >= 0 && volume <= 100) {
                                        System.out.println("Setting volume to: " + volume);
                                        // Implement volume application logic here
                                        // e.g., AudioManager.setVolume(volume);
                                    } else {
                                        System.out.println("Invalid volume level: " + value);
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid volume format: " + value);
                                }
                            }

                            // Applies the difficulty setting
                            private void applyDifficultySetting(String value) {
                                System.out.println("Setting difficulty to: " + value);
                                try {
                                    // Assuming difficulties are EASY, MEDIUM, HARD
                                    Difficulty difficulty = Difficulty.valueOf(value.toUpperCase());
                                    // Implement difficulty application logic here
                                    // e.g., GameSettings.setDifficulty(difficulty);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid difficulty level: " + value);
                                }
                            }

                            // Applies the username setting
                            private void applyUsernameSetting(String value) {
                                System.out.println("Setting username to: " + value);
                                // Implement username application logic here
                                // e.g., UserSettings.setUsername(value);
                            }

                            // Applies the language setting
                            private void applyLanguageSetting(String value) {
                                System.out.println("Setting language to: " + value);
                                // Implement language application logic here
                                // e.g., LanguageManager.setLanguage(value);
                            }

                            // Utility method to show messages
                            private void showMessage(String message, int messageType) {
                                JOptionPane.showMessageDialog(null, message, "ConfigCloud", messageType);
                            }

                            // Sample enumeration for difficulty levels
                            public enum Difficulty {
                                EASY, MEDIUM, HARD
                            }
                        }                        case "volume":
                            applyVolumeSetting(value);
                            break;
                        case "difficulty":
                            applyDifficultySetting(value);
                            break;
                        case "username":
                            applyUsernameSetting(value);
                            break;
                        case "language":
                            applyLanguageSetting(value);
                            break;
                        default:
                            System.out.println("Unknown setting: " + key);
                            // Optionally handle unknown settings
                    }
                }
            }

// Example methods to handle specific settings
            private void applyThemeSetting (String value){
                System.out.println("Applying theme: " + value);
                // Assuming themes are defined elsewhere in the application
                // e.g., ThemeManager.setTheme(value);
            }

            private void applyVolumeSetting (String value){
                try {
                    int volume = Integer.parseInt(value);
                    if (volume >= 0 && volume <= 100) {
                        System.out.println("Setting volume to: " + volume);
                        // e.g., AudioManager.setVolume(volume);
                    } else {
                        System.out.println("Invalid volume level: " + value);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid volume format: " + value);
                }
            }

            private void applyDifficultySetting (String value){
                System.out.println("Setting difficulty to: " + value);
                // e.g., GameSettings.setDifficulty(Difficulty.valueOf(value.toUpperCase()));
            }

            private void applyUsernameSetting (String value){
                System.out.println("Setting username to: " + value);
                // e.g., UserSettings.setUsername(value);
            }

            private void applyLanguageSetting (String value){
                System.out.println("Setting language to: " + value);
                // e.g., LanguageManager.setLanguage(value);
            }

            String configData = new String(Files.readAllBytes(Paths.get(name + ".cfg")));
            System.out.println("Configuration loaded: " + configData);
            showMessage("Config loaded: " + name, JOptionPane.INFORMATION_MESSAGE);
        }

        private void updateConfigList() {
            File dir = new File(".");
            List<String> configs = Arrays.stream(dir.listFiles((d, name) -> name.endsWith(".cfg")))
                    .map(File::getName)
                    .map(name -> name.substring(0, name.length() - 4))
                    .collect(Collectors.toList());

            listModel.clear();
            for (String config : configs) {
                listModel.addElement(config);
            }
        }

        private void showMessage(String message, int messageType) {
            JOptionPane.showMessageDialog(frame, message, "ConfigCloud", messageType);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(ConfigCloud::new);
        }
    }
}
