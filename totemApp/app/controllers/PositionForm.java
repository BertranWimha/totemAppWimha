package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;
import play.i18n.Messages;
import play.i18n.Lang;
import play.Logger;
import java.util.regex.Pattern;


public class PositionForm {

	private static final Pattern rfc2822 = Pattern.compile(
	        "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
	);

	@Required
	public String mail;

	public String message;

	@Required
	public String lat;

	@Required
	public String lon;


	public PositionForm() {
	}


	public Map<String, List<ValidationError>> validate() {

		Map<String, List<ValidationError>> errorList = new HashMap<String, List<ValidationError>>();

		// if from before to
		List<ValidationError> toErrors = new ArrayList<ValidationError>();

		if (!rfc2822.matcher(mail).matches()) {
			toErrors.add(new ValidationError("mail", "This is not a mail address" , null));
			errorList.put("mail", toErrors);
		} 

		if (errorList.isEmpty()) {
			Logger.debug("null");
			return null;
		} else {
			return errorList;
		}
	}


}
