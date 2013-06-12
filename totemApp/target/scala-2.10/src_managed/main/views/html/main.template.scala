
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object main extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*10.54*/routes/*10.60*/.Assets.at("stylesheets/main.css"))),format.raw/*10.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*11.59*/routes/*11.65*/.Assets.at("images/favicon.png"))),format.raw/*11.97*/("""">
    	<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
       
        <script src=""""),_display_(Seq[Any](/*14.23*/routes/*14.29*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*14.74*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*15.23*/routes/*15.29*/.Assets.at("javascripts/getLocation.js"))),format.raw/*15.69*/("""" type="text/javascript"></script>
    </head>
    <body>
        """),_display_(Seq[Any](/*18.10*/content)),format.raw/*18.17*/("""
    </body>
</html>
"""))}
    }
    
    def render(title:String,content:Html): play.api.templates.Html = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.Html) = (title) => (content) => apply(title)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jun 12 23:09:22 CEST 2013
                    SOURCE: /Users/SV/Desktop/totemMobilApp/totemApp/app/views/main.scala.html
                    HASH: 8c20e47234b042c5160dabaff95233c5f2d9dcac
                    MATRIX: 727->1|834->31|922->84|948->89|1126->231|1141->237|1197->271|1294->332|1309->338|1363->370|1504->475|1519->481|1586->526|1679->583|1694->589|1756->629|1859->696|1888->703
                    LINES: 26->1|29->1|35->7|35->7|38->10|38->10|38->10|39->11|39->11|39->11|42->14|42->14|42->14|43->15|43->15|43->15|46->18|46->18
                    -- GENERATED --
                */
            