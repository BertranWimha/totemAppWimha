package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.data.validation.Constraints.Required;
import play.i18n.Messages;
import play.i18n.Lang;
import play.Logger;


public class PositionForm {

	@Required
	public String mail;
	public String message;

	@Required
	public String lat;

	@Required
	public String lon;


	public PositionForm() {
	}


}
