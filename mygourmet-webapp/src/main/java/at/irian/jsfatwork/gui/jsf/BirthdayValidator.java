package at.irian.jsfatwork.gui.jsf;

import at.irian.jsfatwork.gui.util.GuiUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@FacesValidator(value = BirthdayValidator.VALIDATOR_ID)
public class BirthdayValidator implements Validator, Serializable {
    private static final long serialVersionUID = 7946668244924263955L;

    public static final String VALIDATOR_ID = "at.irian.Birthday";

    public void validate(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        Date date = (Date) value;
        if (date != null) {
            // Check if birthdate is after now
            if (date.after(new Date())) {
                FacesMessage msg = GuiUtil.getFacesMessage(
                        ctx, FacesMessage.SEVERITY_ERROR, "validateBirthday.MAXIMUM");
                throw new ValidatorException(msg);
            }

            // Check if birthdate is before 1.1.1900
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 1900);
            cal.set(Calendar.MONTH, 0);
            cal.set(Calendar.DAY_OF_MONTH, 1);

            if (date.before(cal.getTime())) {
                FacesMessage msg = GuiUtil.getFacesMessage(
                        ctx, FacesMessage.SEVERITY_ERROR, "validateBirthday.MINIMUM");
                throw new ValidatorException(msg);
            }
        }
    }

}