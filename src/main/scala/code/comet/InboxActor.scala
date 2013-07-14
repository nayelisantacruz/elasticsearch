package code
package comet

import net.liftweb.actor.LiftActor
import net.liftweb.common._
import lib._
import net.liftweb.http.NamedCometListener
import net.liftweb.util.Schedule
import net.liftweb.util.Helpers._
import net.liftweb.common.Full

object InboxActor extends LiftActor with Logger{


  override def messageHandler ={

    case s: String => {
      ElasticSearch.search( s )
    }

    case m @ MsgElasticSearch(_,_,_) => {
      println("\nGot a MsgElasticSearch\n" + m)
      info("\n\nGot a MsgElasticSearch\n\n" + m)
      NamedCometListener.getDispatchersFor(Full("term")).foreach(actor => actor.map(_ ! m))
    }

  }


}