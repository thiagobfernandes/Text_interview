package models;

public class Interaction {
    private int idScene;
    private String resposta;
    private int idSave;
    private int idInteraction;

    public int getIdSceneCurrent() {
        return idScene;
    }

    public void setIdSceneCurrent(int idScene) {
        this.idScene = idScene;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }


    public void setIdSave(int idSave) {
        this.idSave = idSave;
    }

    public int getIdInteraction() {
        return idInteraction;
    }

    public void setIdInteraction(int idInteraction) {
        this.idInteraction = idInteraction;
    }
    public Integer getIdSave() {
        // Supondo que você tenha um campo idSave na sua classe
        if (this.idSave == 0) { // Se 0 indica que não há um save válido
            return null; // Retorna null
        }
        return this.idSave; // Retorna o idSave válido
    }
}


