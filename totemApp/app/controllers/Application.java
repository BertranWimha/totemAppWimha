package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;

import play.libs.F.Function;
import play.libs.WS;

import views.html.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Application extends Controller {
  


	public static Form<PositionForm> positionForm = new Form<PositionForm>(
			PositionForm.class);
    public static Result index() {
        return ok(index.render("Your new application is ready.",positionForm));
    }

    public static Result submit() {
		Form<PositionForm> filledForm = positionForm.bindFromRequest();
		String mail=filledForm.data().get("mail");
		String message=filledForm.data().get("message");
		String lat=filledForm.data().get("lat");
		String lon=filledForm.data().get("lon");


		try {
 			 
			File file = new File("public/positions.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(mail+","+message+","+lat+","+lon+"\n");
			bw.close();
  
		} catch (IOException e) {
			e.printStackTrace();
			return ok("erreur");
		}
		return ok("ajouté");
		// String url="https://www.googleapis.com/fusiontables/v1/query?key=1ufKusfsyRj1Xr1zgwpplSrw8F6nXjTpNrnzgoWY";
		// return async(
  //       	WS.url(url)
  //   		.setQueryParameter("sql", "INSERT INTO 1ufKusfsyRj1Xr1zgwpplSrw8F6nXjTpNrnzgoWY (Number, Location) VALUES (1, 25)")
  //   		.post("").map(
  //           	new Function<WS.Response, Result>() {
  //            	   public Result apply(WS.Response response) {
  //             	      return ok(response.asJson());
  //             	  }
  //          	 	}
  //     	  	)
  //  		);
    }
  
}
