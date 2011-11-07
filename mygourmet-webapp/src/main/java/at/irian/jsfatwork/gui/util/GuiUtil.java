package at.irian.jsfatwork.gui.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.application.FacesMessage;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class GuiUtil {
	private static final Log log = LogFactory.getLog(GuiUtil.class);

	public static String getResourceText(
            FacesContext ctx, String bundleName, String key, Object... args) {
    	Application app = ctx.getApplication();
		ResourceBundle bundle = app.getResourceBundle(ctx, bundleName);
        return getResourceText(bundle, key, args);
	}

	public static String getResourceText(ResourceBundle bundle, String key, Object... args) {
		String text;
		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			log.error("could not find labels resource '" + key + "'");
			return "???" + key + "???";
		}
		if (args != null) {
			text = MessageFormat.format(text, args);
		}
		return text;
	}

	public static FacesMessage getFacesMessage(
            FacesContext ctx, FacesMessage.Severity severity, String msgKey, Object... args) {
		Locale loc = ctx.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(
                ctx.getApplication().getMessageBundle(), loc);
		String msg = bundle.getString(msgKey);
		if (args != null) {
			MessageFormat format = new MessageFormat(msg);
			msg = format.format(args);
		}
		return new FacesMessage(severity, msg, null);
	}

}
