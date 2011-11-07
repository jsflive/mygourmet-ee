package at.irian.jsfatwork.gui.jsf;

import at.irian.jsfatwork.gui.util.GuiUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.IntegerConverter;
import java.io.Serializable;

@FacesConverter(value = ZipCodeConverter.CONVERTER_ID)
public class ZipCodeConverter extends IntegerConverter implements Serializable {
    private static final long serialVersionUID = 7058986733877680578L;

    public static final String CONVERTER_ID = "at.irian.ZipCode";

    public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) throws ConverterException {
		
		if (value != null && value.length() > 0) {
			int pos = value.indexOf('-');
			for (int i = 0; i < pos; i++) {
				if (!Character.isLetter(value.charAt(i))) {
                    FacesMessage msg = GuiUtil.getFacesMessage(
                            ctx, FacesMessage.SEVERITY_ERROR, "zipCodeConverter.CONVERSION");
					throw new ConverterException(msg);
				}
			}
			if (pos > -1 && pos < value.length() - 1) {
				return super.getAsObject(ctx, component, value.substring(pos + 1));
			}
		}
		return super.getAsObject(ctx, component, value);
	}
}
