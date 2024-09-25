package models;

public class Inventory {
    private Integer idObject;
    private Integer idSave;
    private String name;

    public Integer getIdObject() {
        return idObject;
    }

    public void setIdObject(Integer idObject) {
        this.idObject = idObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdSave() {
        return idSave;
    }

    public void setIdSave(Integer idSave) {
        this.idSave = idSave;
    }
}
