package at.irian.jsfatwork.gui.jsf.component;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@FacesComponent(CollapsiblePanel.COMPONENT_TYPE)
public class CollapsiblePanel extends UINamingContainer {

    public static final String COMPONENT_TYPE = "at.irian.CollapsiblePanel";

    enum PropertyKeys {
        collapsed
    }

    public boolean isCollapsed() {
        return (Boolean)getStateHelper().eval(PropertyKeys.collapsed, Boolean.FALSE);
    }

    public void setCollapsed(boolean collapsed) {
        getStateHelper().put(PropertyKeys.collapsed, collapsed);
    }

    public void toggle(ActionEvent e) {
        setCollapsed(!isCollapsed());
        setCollapsedValueExpression();
    }

    private void setCollapsedValueExpression() {
        ELContext ctx = FacesContext.getCurrentInstance().getELContext();
        ValueExpression ve = getValueExpression(PropertyKeys.collapsed.name());
        if (ve != null) {
            ve.setValue(ctx, isCollapsed());
        }
    }
    
}
