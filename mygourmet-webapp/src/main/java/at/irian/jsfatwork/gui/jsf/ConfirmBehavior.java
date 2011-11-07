package at.irian.jsfatwork.gui.jsf;

import javax.faces.component.behavior.ClientBehaviorBase;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.FacesBehavior;

@FacesBehavior("at.irian.ConfirmBehavior")
public class ConfirmBehavior extends ClientBehaviorBase {

    private String text;

    @Override
    public String getScript(ClientBehaviorContext behaviorContext) {
        return "return confirm('" + text + "');";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
