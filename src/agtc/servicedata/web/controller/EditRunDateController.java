package agtc.servicedata.web.controller;

import agtc.servicedata.model.*;
import agtc.servicedata.bus.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.*;

/**
 * @author Jianan Xiao 2005-11-16
 * 
 *         Used to modify one result's note
 */

public class EditRunDateController extends ServiceDataBasicController {

	private GeneralManager	generalManager;

	public EditRunDateController() {
		// initialize the form from the formBackingObject
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		int runId = RequestUtils.getIntParameter(request, "runId", -1);
		Run run = generalManager.getRun(runId);
		return run;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, java.lang.Object command,
			BindException errors) throws Exception {
		Run run = (Run) command;

		// System.out.println(run.getNote());

		generalManager.saveRun(run);

		String message = "Have successfully saved this result's note !";

		Map models = new HashMap();

		models.put("message", message);
		models.put("runId", run.getRunId().toString());

		return new ModelAndView(new RedirectView("editRunDateNote.htm"), models);

		// return new ModelAndView("successComplete", models);
	}

	protected java.util.Map referenceData(HttpServletRequest request, java.lang.Object command, Errors errors)
			throws java.lang.Exception {
		Map models = new HashMap();
		int runId = RequestUtils.getIntParameter(request, "runId", -1);
		String message = RequestUtils.getStringParameter(request, "message");
		List uniassay = generalManager.getUniAssaysByRunId(runId);
		models.put("uniAssayList", uniassay);
		models.put("runId", (new Integer(runId)).toString());
		models.put("message", message);

		// log.debug("referenceData is called");
		return models;
	}

	/**
	 * @return Returns the generalManager.
	 */
	public GeneralManager getGeneralManager() {
		return generalManager;
	}

	/**
	 * @param generalManager
	 *            The generalManager to set.
	 */
	public void setGeneralManager(GeneralManager generalManager) {
		this.generalManager = generalManager;
	}
}
