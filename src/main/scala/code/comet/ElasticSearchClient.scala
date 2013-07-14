package code
package comet

import net.liftweb.util._
import net.liftweb.util.Helpers._
import net.liftweb.http.{NamedCometActorTrait, SHtml}
import net.liftweb.http.js.{JsCmds, JsCmd, JE}
import net.liftweb.json._
import net.liftweb.json.JsonAST.JValue
import service._
import org.joda.time.DateTime
import lib._
import net.liftweb.http.js.jquery.JqJsCmds.AppendHtml
import xml.Text
import net.liftweb.common.Logger
import net.liftweb.http.js.JsCmds.{Run, Replace, SetHtml}
import util._
import scala.xml.{NodeSeq,Text,MetaData}


class ElasticSearchClient extends NamedCometActorTrait{


  private[this] var renderedMessages: Vector[MsgElasticSearch] = Vector()

  override def lowPriority = {
    case x @ MsgElasticSearch( id, title, content ) => {

      renderedMessages = renderedMessages :+ x

      partialUpdate(SetHtml(
        "render",
        (

          <p id="title">
            {title}
          </p>

          <p id="content">
            {content}
          </p>

          <hr/>
        )
      ))

      logger.debug("MsgElasticSearch :" + x )

    }

  }

  def render = {
    "#here" #> ""
  }


}
