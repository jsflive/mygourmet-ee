package at.irian.jsfatwork.gui.jsf.component;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;

@FacesComponent("at.irian.InputSpinner")
public class InputSpinner extends UIInput {

    public static final String COMPONENT_TYPE = "at.irian.InputSpinner";

    enum PropertyKeys {
        inc
    }

    public InputSpinner() {
        setRendererType("at.irian.InputSpinner");
    }

    public int getInc() {
        return (Integer)getStateHelper().eval(PropertyKeys.inc, 1);
    }

    public void setInc(int inc) {
        getStateHelper().put(PropertyKeys.inc, inc);
    }

}
