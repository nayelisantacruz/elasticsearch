package code
package snippet


import net.liftweb.common.{Loggable, Full, Box, Logger}
import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.http.js._
import net.liftweb.http._
import JsCmds._
import scala.xml.Text
import code.comet._


class SendTerm extends Loggable{


  private var term = ""
  private object lineCount extends SessionVar(0)

  def process(): JsCmd = {

    if( term.length != 0 ){
      if( term.length < 140 ){//50, 20 lines per session , term.length < 140 && lineCount < 20
        lineCount.set(lineCount.is + 1)
        InboxActor ! ( term )
        logger.debug( "the term is: " + term )
        println( "the term is: " + term )
        SetHtml( "result", Text( term ))
      }
    }
    else {
      SetHtml( "result", Text(S.?("no.term")) )
    }

  }

  def render = {
     "#term" #> (
      SHtml.text(term, term = _) ++
      SHtml.hidden(process) )
  }


}