package at.irian.jsfatwork.gui.jsf;

import at.irian.jsfatwork.domain.BaseEntity;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

public abstract class EntityConverter<T extends BaseEntity> implements Converter, Serializable {
    private static final long serialVersionUID = -1176470402158735L;

    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        long id = new Long(value);
        return find(id);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((BaseEntity)value).getId().toString();
    }

    protected abstract T find(long id);
}
