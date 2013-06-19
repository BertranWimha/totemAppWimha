package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;

import views.html.*;

public class Application extends Controller {
  


	public static Form<PositionForm> positionForm = new Form<PositionForm>(
			PositionForm.class);
    public static Result index() {
        return ok(index.render("Your new application is ready.",positionForm));
    }

    public static Result submit() {


        return ok("position envoyée");
    }
  
}
