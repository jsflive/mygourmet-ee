package at.irian.jsfatwork.gui.jsf.component;

import javax.el.ValueExpression;
import javax.faces.application.Resource;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

@ResourceDependencies({
    @ResourceDependency(library = "mygourmet", name = "inputSpinner.js", target = "head"),
    @ResourceDependency(library = "mygourmet", name = "components.css")}
)
@FacesRenderer(componentFamily = "javax.faces.Input", rendererType = "at.irian.InputSpinner")
public class InputSpinnerRenderer extends Renderer {

    @Override
    public void decode(FacesContext ctx, UIComponent component) {
        Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
        String clientId = component.getClientId();
        String value = params.get(clientId);
        ((UIInput)component).setSubmittedValue(value);
    }

    @Override
    public Object getConvertedValue(FacesContext ctx, UIComponent component, Object submittedValue)
            throws ConverterException {
        Converter converter = getConverter(ctx, component);
        if (converter != null ) {
            return converter.getAsObject(ctx, component, (String) submittedValue);
        } else {
            return submittedValue;
        }
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        InputSpinner spinner = (InputSpinner)component;
        String clientId = spinner.getClientId();

        encodeInput(context, spinner, clientId);
        encodeButtons(context, spinner, clientId);
    }

    private void encodeInput(FacesContext context, InputSpinner spinner, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("input", spinner);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("name", clientId, null);
        Object value = getValue(context, spinner);
        if (value != null) {
            writer.writeAttribute("value", value.toString(), null);
        }
        writer.writeAttribute("class", "inputSpinner-input", null);
        writer.endElement("input");
    }

    private void encodeButtons(FacesContext ctx, InputSpinner spinner, String clientId) throws IOException {
        ResponseWriter writer = ctx.getResponseWriter();
        ResourceHandler resourceHandler = ctx.getApplication().getResourceHandler();
        MessageFormat onclick = new MessageFormat("return changeNumber(''{0}'', {1});");

        writer.startElement("span", spinner);
        writer.writeAttribute("class", "inputSpinner-buttons", null);

        Resource spinUpResource = resourceHandler.createResource("spin-up.png", "mygourmet");
        String onclickUp = onclick.format(new Object[]{clientId, spinner.getInc()});
        encodeSpinButton(spinner, writer, spinUpResource, onclickUp);

        Resource spinDownResource = resourceHandler.createResource("spin-down.png", "mygourmet");
        String onclickDown = onclick.format(new Object[]{clientId, -spinner.getInc()});
        encodeSpinButton(spinner, writer, spinDownResource, onclickDown);

        writer.endElement("span");
    }

    private void encodeSpinButton(InputSpinner spinner, ResponseWriter writer, Resource resource, String onclick) throws IOException {
        writer.startElement("img", spinner);
        writer.writeAttribute("class", "inputSpinner-button", null);
        writer.writeAttribute("src", resource.getRequestPath(), null);
        writer.writeAttribute("onclick", onclick, null);
        writer.endElement("img");
    }

    private Object getValue(FacesContext ctx, InputSpinner spinner) {
        Object submittedValue = spinner.getSubmittedValue();
        if (submittedValue != null) {
            return submittedValue;
        }
        Object value = spinner.getValue();
        Converter converter = getConverter(ctx, spinner);
        if (converter != null) {
            return converter.getAsString(ctx, spinner, value);
        } else if (value != null) {
            return value.toString();
        } else {
            return "";
        }
    }

    private Converter getConverter(FacesContext ctx, UIComponent component) {
        Converter converter = ((UIInput)component).getConverter();
        if (converter != null) return converter;

        ValueExpression exp = component.getValueExpression("value");
        if (exp == null) return null;

        Class valueType = exp.getType(ctx.getELContext());
        if (valueType == null) return null;

        return ctx.getApplication().createConverter(valueType);
    }

}
