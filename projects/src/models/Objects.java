package models;

public class Objects {
    private Integer idObject;
    private String nameObject;
    private String descObject;
    private String commandCorrect;
    private String resultPositive;
    private String resultNegative;
    private Integer idScene;
    private Integer idSceneCurrent;
    private Integer possibleToCarry;

    public Integer getIdObject() {
        return idObject;
    }

    public void setIdObject(Integer idObject) {
        this.idObject = idObject;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public String getDescObject() {
        return descObject;
    }

    public void setDescObject(String descObject) {
        this.descObject = descObject;
    }

    public String getCommandCorrect() {
        return commandCorrect;
    }

    public void setCommandCorrect(String commandCorrect) {
        this.commandCorrect = commandCorrect;
    }

    public String getResultPositive() {
        return resultPositive;
    }

    public void setResultPositive(String resultPositive) {
        this.resultPositive = resultPositive;
    }

    public String getResultNegative() {
        return resultNegative;
    }

    public void setResultNegative(String resultNegative) {
        this.resultNegative = resultNegative;
    }

    public Integer getIdScene() {
        return idScene;
    }

    public void setIdScene(Integer idScene) {
        this.idScene = idScene;
    }

    public Integer getIdSceneCurrent() {
        return idSceneCurrent;
    }

    public void setIdSceneCurrent(Integer idSceneCurrent) {
        this.idSceneCurrent = idSceneCurrent;
    }

    public Integer getPossibleToCarry() {
        return possibleToCarry;
    }

    public void setPossibleToCarry(Integer possibleToCarry) {
        this.possibleToCarry = possibleToCarry;
    }
}
