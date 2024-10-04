import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurveyPanel extends JPanel {
    private Survey survey;
    private JTextField[] responseFields;
    private OnSurveyCompleteListener listener;

    public SurveyPanel() {
        setLayout(new GridLayout(0, 1));
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
        removeAll();  // Limpiar el panel anterior

        // Crear nuevo panel de preguntas
        setLayout(new GridLayout(survey.getNumberOfQuestions() + 1, 1));
        responseFields = new JTextField[survey.getNumberOfQuestions()];

        for (int i = 0; i < survey.getNumberOfQuestions(); i++) {
            JPanel questionPanel = new JPanel(new BorderLayout());
            JLabel questionLabel = new JLabel(survey.getQuestion(i));
            responseFields[i] = new JTextField();
            questionPanel.add(questionLabel, BorderLayout.NORTH);
            questionPanel.add(responseFields[i], BorderLayout.CENTER);
            add(questionPanel);
        }

        JButton submitButton = new JButton("Enviar");
        add(submitButton);

        // Acción al enviar las respuestas
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < survey.getNumberOfQuestions(); i++) {
                    String response = responseFields[i].getText();
                    survey.setResponse(i, response);
                }

                if (survey.isComplete()) {
                    if (listener != null) {
                        listener.onComplete();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, responde todas las preguntas.");
                }
            }
        });

        revalidate();
        repaint();
    }

    public void setOnSurveyCompleteListener(OnSurveyCompleteListener listener) {
        this.listener = listener;
    }


    public interface OnSurveyCompleteListener {
        void onComplete();
    }
}
