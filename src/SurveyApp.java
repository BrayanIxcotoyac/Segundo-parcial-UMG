import javax.swing.*;
import java.awt.*;

public class SurveyApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Survey survey1, survey2;
    private SurveyPanel surveyPanel;

    public SurveyApp() {
        setTitle("Encuestas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);


        survey1 = new Survey(new String[]{
                "¿Qué te motivó a estudiar en la universidad?",
                "¿Qué carrera elegiste y por qué?",
                "¿Tus padres influyeron en tu decisión de estudiar?",
                "¿Qué tan satisfecho estás con tu decisión de estudiar en la universidad?"
        });

        survey2 = new Survey(new String[]{
                "¿Con qué frecuencia lees libros?",
                "¿Prefieres libros físicos o electrónicos?",
                "¿Lees por entretenimiento o por aprendizaje?"
        });


        JPanel surveySelectionPanel = new JPanel();
        surveySelectionPanel.setLayout(new GridLayout(3, 1));
        JLabel label = new JLabel("Selecciona una encuesta:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JButton startSurvey1Button = new JButton("Encuesta Razones para estudiar en la universidad");
        JButton startSurvey2Button = new JButton(" Encuesta Hábitos de lectura");
        surveySelectionPanel.add(label);
        surveySelectionPanel.add(startSurvey1Button);
        surveySelectionPanel.add(startSurvey2Button);
        cardPanel.add(surveySelectionPanel, "Selection");


        surveyPanel = new SurveyPanel();
        cardPanel.add(surveyPanel, "Survey");

        // Panel de agradecimiento con botón para volver
        JPanel thanksPanel = new JPanel(new BorderLayout());
        JLabel thanksLabel = new JLabel("¡Gracias por completar la encuesta!");
        thanksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JButton backButton = new JButton("Volver a la selección");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Selection"));  // Volver a la selección
        thanksPanel.add(thanksLabel, BorderLayout.CENTER);
        thanksPanel.add(backButton, BorderLayout.SOUTH);
        cardPanel.add(thanksPanel, "Thanks");

        add(cardPanel);


        startSurvey1Button.addActionListener(e -> {
            surveyPanel.setSurvey(survey1);
            cardLayout.show(cardPanel, "Survey");
        });


        startSurvey2Button.addActionListener(e -> {
            surveyPanel.setSurvey(survey2);  // Establecer encuesta 2
            cardLayout.show(cardPanel, "Survey");
        });


        surveyPanel.setOnSurveyCompleteListener(() -> cardLayout.show(cardPanel, "Thanks"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SurveyApp().setVisible(true));
    }
}
