package at.irian.jsfatwork.gui.page;

import at.irian.jsfatwork.domain.CreditCardType;
import at.irian.jsfatwork.domain.Customer;
import at.irian.jsfatwork.gui.util.GuiUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;

abstract class CustomerBeanBase implements Serializable {

    protected Customer customer;
    private UIInput creditCardTypeInput = null;

    public void useCreditCardChanged(ValueChangeEvent ev) {
        Boolean useCreditCard = (Boolean) ev.getNewValue();
        if (useCreditCard != null) {
            customer.setUseCreditCard(useCreditCard);
        }
        FacesContext.getCurrentInstance().renderResponse();
    }

    public String getSelectedGender() {
        return customer.getGender() != null ? getGenderLabel(customer.getGender()) : null;
    }

    public String getSelectedCreditCardType() {
        String selectedCCType = null;
        if (customer.getCreditCardType() != null) {
            selectedCCType = getCCTypeLabel(customer.getCreditCardType());
        }
        return selectedCCType;
    }

    public void postValidateCCType(ComponentSystemEvent event) throws AbortProcessingException {
        creditCardTypeInput = (UIInput)event.getComponent();
    }

    public void validateCreditNumber(FacesContext ctx, UIComponent component,
            Object value) throws ValidatorException {
        // Only validate credit card data if user specified to use it
        CreditCardType ccType = (CreditCardType)creditCardTypeInput.getValue();
        Boolean useCC = customer.getUseCreditCard();
        if (useCC != null && useCC && ccType != null) {
            // Check credit card number
            int length;
            if (ccType == CreditCardType.CARD_A) {
                length = 4;
            } else {
                length = 5;
            }
            String ccNumber = (String)value;
			if (ccNumber != null && !ccNumber.matches("\\d{" + length + "}")) {
                FacesMessage msg = GuiUtil.getFacesMessage(
                        ctx, FacesMessage.SEVERITY_ERROR, "validateCreditCardNumber.NUMBER", length);
                throw new ValidatorException(msg);
            }
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private String getCCTypeLabel(CreditCardType type) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String key = "credit_card_type_" + type.toString();
        return GuiUtil.getResourceText(ctx, "msgs", key);
    }

    private String getGenderLabel(char gender) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        return GuiUtil.getResourceText(ctx, "msgs", "gender_" + gender);
    }

}
