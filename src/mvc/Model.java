package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {

    private boolean unsavedChanges;
    private String fName;
    private boolean changed;

    public Model() {
        unsavedChanges = false;
    }

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public void setUnsavedChanges(boolean value) {
        unsavedChanges = value;
    }

    public String getFileName() {
        return fName;
    }

    public void setFileName(String fName) {
        this.fName = fName;
    }

    public void changed() {
        changed = true;
        notifySubscribers();
    }

    

}
