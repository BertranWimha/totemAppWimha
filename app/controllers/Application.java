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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Application extends Controller {
  


	public static Form<PositionForm> positionForm = new Form<PositionForm>(
			PositionForm.class);
    public static Result index() {
        return ok(index.render("Your new application is ready.",positionForm));
    }

    public static Result map(){

   		ArrayList<Message> res=new ArrayList<Message>();
   		try{
			BufferedReader reader = new BufferedReader(new FileReader("public/positions.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(line, ",");
				Message m=new Message();

				m.name=tokenizer.nextToken();
				m.mail=tokenizer.nextToken();
				m.message=tokenizer.nextToken();
				m.lat=tokenizer.nextToken();
				m.lon=tokenizer.nextToken();
				res.add(m);
			}
		} catch (IOException e) {
		}
		return ok(map.render(res));
	}

    public static Result submit() {
		Form<PositionForm> filledForm = positionForm.bindFromRequest();
			String timestamp = (new Date().getTime()+"");

			if (filledForm.hasErrors()) {
				ObjectNode error = Json.newObject();
				error.put("error", filledForm.errorsAsJson());
				return ok(error);
			} else {

				String name=filledForm.data().get("name");
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
					bw.write(name+", "+mail+", "+message+", "+lat+", "+lon+", "+timestamp+"\n");
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

						m.name=tokenizer.nextToken();
						m.mail=tokenizer.nextToken();
						m.message=tokenizer.nextToken();
						m.lat=tokenizer.nextToken();
						m.lon=tokenizer.nextToken();
						m.timestamp=tokenizer.nextToken();
						res.add(m);
					}
				} catch (IOException e) {
				}
				ObjectNode json = Json.newObject();
				json.put("url", messages.render(res).toString());
				json.put("timestamp", getDate(timestamp));
				return ok(json);


		     }
    }

    public static int nb_to_display=2;
   	public static Result messages(String page) {

   		int p=Integer.parseInt(page);
   		ArrayList<Message> res=new ArrayList<Message>();
   		try{
			BufferedReader reader = new BufferedReader(new FileReader("public/positions.txt"));
			String line = null;
			
			Logger.debug(p+"p");

			int i=0;
			for(i=0;i<(nb_to_display*p);i++){
				reader.readLine();
				Logger.debug(i+"ia");
			}

			while ((line = reader.readLine()) != null && i<nb_to_display*(p+1)) {
				java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(line, ",");
				Message m=new Message();
				Logger.debug(i+"ib");

				m.name=tokenizer.nextToken();
				m.mail=tokenizer.nextToken();
				m.message=tokenizer.nextToken();
				m.lat=tokenizer.nextToken();
				m.lon=tokenizer.nextToken();
				m.timestamp=tokenizer.nextToken();
				res.add(m);
				i++;
			}
		} catch (IOException e) {
		}
			ObjectNode json = Json.newObject();
				json.put("html", messages.render(res).toString());
				return ok(json);	
	}
  

	public static Result address(final String lat,final String lon){
		Promise<WS.Response> request =
		WS.url("http://maps.googleapis.com/maps/api/geocode/json")
		.setQueryParameter("latlng",lat+","+lon).setQueryParameter("sensor", "true")
		.post("content");
							
		String addr = request.get().asJson().path("results").get(0).get("formatted_address").toString();
		
		ObjectNode json = Json.newObject();
		json.put("address", addr);
		return ok(json);
	}

	public static String getDate(String timestamp) {
  		Date date = new Date(Long.valueOf(timestamp.trim()));
  		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dat = dateFormat.format(date);
		return dat;
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
