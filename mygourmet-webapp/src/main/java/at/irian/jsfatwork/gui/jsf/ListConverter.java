package at.irian.jsfatwork.gui.jsf;

import at.irian.jsfatwork.gui.util.GuiUtil;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;
import java.util.Collection;

@FacesConverter(value = ListConverter.CONVERTER_ID)
public class ListConverter implements Converter, Serializable {
    private static final long serialVersionUID = -8459299536537047210L;

    public static final String CONVERTER_ID = "at.irian.List";

    private String separator;
    private String bundleName;

    public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
        throw new UnsupportedOperationException("not implemented");
    }

    public String getAsString(FacesContext ctx, UIComponent comp, Object value) {
        StringBuilder builder = new StringBuilder();
        if (value instanceof Collection) {
            for (Object obj : (Collection)value) {
                String item = obj.toString();
                if (builder.length() > 0 && separator != null) {
                    builder.append(separator);
                }
                if (bundleName != null && bundleName.length() > 0) {
                    builder.append(GuiUtil.getResourceText(ctx, bundleName, item));
                } else {
                    builder.append(item);
                }
            }
        }
        return builder.toString();
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

}
