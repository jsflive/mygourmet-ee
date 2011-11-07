package at.irian.jsfatwork.gui.jsf.component;

import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.TagAttribute;

public class InputSpinnerTagHandler extends ComponentHandler {

    private static final String ATTR_INC = "inc";

    private TagAttribute inc;

    public InputSpinnerTagHandler(ComponentConfig config) {
        super(config);
        this.inc = getRequiredAttribute(ATTR_INC);
    }

}
