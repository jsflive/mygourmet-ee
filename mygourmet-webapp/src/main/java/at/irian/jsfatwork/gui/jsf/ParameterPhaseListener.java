package at.irian.jsfatwork.gui.jsf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.Map;

/**
 * PhaseListener that logs request parameters before restore view phase.
 * 
 * @author Michael Kurz
 */
public class ParameterPhaseListener implements PhaseListener {
    private static final long serialVersionUID = -7280164859565844793L;

	private static Log log = LogFactory.getLog(ParameterPhaseListener.class);

    public void afterPhase(PhaseEvent event) {
	}

	public void beforePhase(PhaseEvent event) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> map = fc.getExternalContext().getRequestParameterMap();
		for (String key : map.keySet()) {
			StringBuilder param = new StringBuilder();
			param.append("Parameter: ");
			param.append(key);
			param.append(" = ");
			param.append(map.get(key));
			log.debug(param.toString());
		}
	}

	public PhaseId getPhaseId() {
		return PhaseId.APPLY_REQUEST_VALUES;
	}

}
