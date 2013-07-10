package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;

import play.libs.F.Function;
import play.libs.WS;

import views.html.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;
import play.libs.F;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.WS;

public class Application extends Controller {
  


	public static Form<PositionForm> positionForm = new Form<PositionForm>(
			PositionForm.class);
    public static Result index() {
        return ok(index.render("Your new application is ready.",positionForm));
    }

    public static Result submit() {
		Form<PositionForm> filledForm = positionForm.bindFromRequest();


			if (filledForm.hasErrors()) {
				ObjectNode error = Json.newObject();
				error.put("error", filledForm.errorsAsJson());
				return ok(error);
			} else {

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
					bw.write(mail+", "+message+", "+lat+", "+lon+", "+new Date().getTime()+"\n");
					bw.close();
		  
				} catch (IOException e) {
					e.printStackTrace();
					return ok("erreur");
				}

		   		ArrayList<Message> res=new ArrayList<Message>();
		   		try{
					BufferedReader reader = new BufferedReader(new FileReader("public/positions.txt"));
					String line = null;
					while ((line = reader.readLine()) != null) {
						java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(line, ",");
						Message m=new Message();

						m.mail=tokenizer.nextToken();
						m.message=tokenizer.nextToken();
						m.lat=tokenizer.nextToken();
						m.lon=tokenizer.nextToken();
						res.add(m);
					}
				} catch (IOException e) {
				}
				ObjectNode json = Json.newObject();
				json.put("url", address(lat,lon).toString());//messages.render(res).toString());
				return ok(json);


		     }
    }

   	public static Result messages() {
   		ArrayList<Message> res=new ArrayList<Message>();
   		try{
			BufferedReader reader = new BufferedReader(new FileReader("public/positions.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(line, ",");
				Message m=new Message();

				m.mail=tokenizer.nextToken();
				m.message=tokenizer.nextToken();
				m.lat=tokenizer.nextToken();
				m.lon=tokenizer.nextToken();
				res.add(m);
			}
		} catch (IOException e) {
		}
			ObjectNode json = Json.newObject();
				json.put("html", messages.render(res).toString());
				return ok(json);	
	}
  

	public static String address(final String lat,final String lon){
		Promise<WS.Response> request =
		WS.url("http://maps.googleapis.com/maps/api/geocode/json")
		.setQueryParameter("latlng",lat+","+lon).setQueryParameter("sensor", "true")
		.post("content");
							
		String addr = request.get().asJson().path("results").get(0).get("formatted_address").toString();
		return addr;
	}

}


//pour envoyer sur FUSION TABLES
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
