public class Survey {
    private String[] questions;
    private String[] responses;

    public Survey(String[] questions) {
        this.questions = questions;
        this.responses = new String[questions.length];
    }

    public String getQuestion(int index) {
        if (index >= 0 && index < questions.length) {
            return questions[index];
        }
        return null;
    }

    public void setResponse(int index, String response) {
        if (index >= 0 && index < responses.length) {
            responses[index] = response;
        }
    }

    public String getResponse(int index) {
        if (index >= 0 && index < responses.length) {
            return responses[index];
        }
        return null;
    }

    public int getNumberOfQuestions() {
        return questions.length;
    }

    public boolean isComplete() {
        for (String response : responses) {
            if (response == null || response.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
