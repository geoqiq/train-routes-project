package org.example.trenuricaps.domain;

public class Entity <ID>{
    private ID id;

    /**
     * Returns the id of the current entity
     * @return ID - id of the current entity
     */
    public ID getId() {
        return id;
    }

    /**
     * Sets the id of the current entity
     * @param id - new id, of type ID
     */
    public void setId(ID id) {
        this.id = id;
    }
}
