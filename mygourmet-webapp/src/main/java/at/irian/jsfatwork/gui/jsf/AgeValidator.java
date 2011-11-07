package at.irian.jsfatwork.gui.jsf;

import at.irian.jsfatwork.gui.util.GuiUtil;
import at.irian.jsfatwork.gui.util.MyGourmetUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.Date;

@FacesValidator(value = AgeValidator.VALIDATOR_ID)
public class AgeValidator implements Validator, Serializable {

    private static final long serialVersionUID = 3261427653947008453L;

    public static final String VALIDATOR_ID = "at.irian.Age";

    private Integer maxAge;
    private Integer minAge;

    public void validate(FacesContext ctx, UIComponent comp, Object value)
            throws ValidatorException {
        if (value != null) {
            Date date = (Date) value;

            if (minAge != null && MyGourmetUtil.getAge(date) < minAge) {
                FacesMessage msg = GuiUtil.getFacesMessage(
                        ctx, FacesMessage.SEVERITY_ERROR, "validateAge.TOO_YOUNG", minAge);
                throw new ValidatorException(msg);
            }

            if (maxAge != null && MyGourmetUtil.getAge(date) > maxAge) {
                FacesMessage msg = GuiUtil.getFacesMessage(
                        ctx, FacesMessage.SEVERITY_ERROR, "validateAge.TOO_OLD", maxAge);
                throw new ValidatorException(msg);
            }
        }
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }
}