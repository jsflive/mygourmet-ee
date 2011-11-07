package at.irian.jsfatwork.gui.jsf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * PhaseListener that logs a short message before and after every phase.
 *
 * @author Michael Kurz
 */
public class DebugPhaseListener implements PhaseListener {
    private static final long serialVersionUID = 28697126271609506L;
    private static Log log = LogFactory.getLog(DebugPhaseListener.class);

    public void afterPhase(PhaseEvent ev) {
        String ajax = getAjaxText(ev.getFacesContext());
        PhaseId phaseId = ev.getPhaseId();
        log.debug("After phase: " + phaseId + ajax);
    }

    public void beforePhase(PhaseEvent ev) {
        String ajax = getAjaxText(ev.getFacesContext());
        PhaseId phaseId = ev.getPhaseId();
        log.debug("Before phase: " + phaseId + ajax);
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    private String getAjaxText(FacesContext ctx) {
        return ctx.getPartialViewContext().isAjaxRequest() ? " (Ajax)" : "";
    }
}
