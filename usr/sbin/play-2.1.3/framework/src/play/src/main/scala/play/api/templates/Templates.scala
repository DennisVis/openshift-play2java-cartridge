package play.api.templates

import play.api.mvc._
import play.templates._

/**
 * Content type used in default HTML templates.
 *
 * @param buffer the HTML text
 */
class Html(val buffer: StringBuilder) extends Appendable[Html] with Content with play.mvc.Content {

  /**
   * Appends another Html fragment to this, modifying this.
   */
  def +=(other: Html): Html = {
    buffer.append(other.buffer)
    this
  }

  @deprecated(message="Use += method instead.", since="2012/12")
  def +(other: Html): Html = {
    this += other
  }

  override def toString = buffer.toString

  /**
   * Content type of HTML (`text/html`).
   */
  def contentType: String = "text/html"

  def body: String = toString

}

/**
 * Helper for HTML utility methods.
 */
object Html {

  /**
   * Creates an HTML fragment with initial content specified.
   */
  def apply(text: String): Html = {
    new Html(new StringBuilder(text))
  }

  /**
   * Creates an empty HTML fragment.
   */
  def empty: Html = new Html(new StringBuilder)
}

/**
 * Formatter for HTML content.
 */
object HtmlFormat extends Format[Html] {

  /**
   * Creates a raw (unescaped) HTML fragment.
   */
  def raw(text: String): Html = Html(text)

  /**
   * Creates a safe (escaped) HTML fragment.
   */
  def escape(text: String): Html = {
    // Using our own algorithm here because commons lang escaping wasn't designed for protecting against XSS, and there
    // don't seem to be any other good generic escaping tools out there.
    val sb = new StringBuilder(text.length)
    text.foreach {
      case '<' => sb.append("&lt;")
      case '>' => sb.append("&gt;")
      case '"' => sb.append("&quot;")
      case '\'' => sb.append("&#x27;")
      case '&' => sb.append("&amp;")
      case c => sb += c
    }
    new Html(sb)
  }

}

/**
 * Content type used in default text templates.
 *
 * @param text The plain text.
 */
class Txt(text: String) extends Appendable[Txt] with Content with play.mvc.Content {
  private val buffer = new StringBuilder(text)

  /**
   * Appends another text fragment to this, modifying this.
   */
  def +=(other: Txt): Txt = {
    buffer.append(other.buffer)
    this
  }

  @deprecated(message="Use += method instead.", since="2012/12")
  def +(other: Txt): Txt = {
    this += other
  }

  override def toString = buffer.toString

  /**
   * Content type of text (`text/plain`).
   */
  def contentType = "text/plain"

  def body = toString

}

/**
 * Helper for utilities Txt methods.
 */
object Txt {

  /**
   * Creates a text fragment with initial content specified.
   */
  def apply(text: String): Txt = {
    new Txt(text)
  }


  /**
   * Creates an empty text fragment.
   */
  def empty = new Txt("")

}

/**
 * Formatter for text content.
 */
object TxtFormat extends Format[Txt] {

  /**
   * Create a text fragment.
   */
  def raw(text: String) = Txt(text)

  /**
   * No need for a safe (escaped) text fragment.
   */
  def escape(text: String) = Txt(text)

}

/**
 * Content type used in default XML templates.
 *
 * @param text the plain xml text
 */
class Xml(text: String) extends Appendable[Xml] with Content with play.mvc.Content {
  private val buffer = new StringBuilder(text)

  /**
   * Appends another XML fragment to this, modifying this.
   */
  def +=(other: Xml): Xml = {
    buffer.append(other.buffer)
    this
  }

  @deprecated(message="Use += method instead.", since="2012/12")
  def +(other: Xml): Xml = {
    this += other
  }

  override def toString = buffer.toString

  /**
   * Content type of XML (`text/xml`).
   */
  def contentType = "text/xml"

  def body = toString

}

/**
 * Helper for XML utility methods.
 */
object Xml {

  /**
   * Creates an XML fragment with initial content specified.
   */
  def apply(text: String): Xml = {
    new Xml(text)
  }

  /**
   * Create an empty XML fragment.
   */
  def empty = new Xml("")

}

/**
 * Formatter for XML content.
 */
object XmlFormat extends Format[Xml] {

  /**
   * Creates an XML fragment.
   */
  def raw(text: String) = Xml(text)

  /**
   * Creates an escaped XML fragment.
   */
  def escape(text: String) = Xml(org.apache.commons.lang3.StringEscapeUtils.escapeXml(text))

}

/** Defines a magic helper for Play templates. */
object PlayMagic {

  /**
   * Generates a set of valid HTML attributes.
   *
   * For example:
   * {{{
   * toHtmlArgs(Seq('id -> "item", 'style -> "color:red"))
   * }}}
   */
  def toHtmlArgs(args: Map[Symbol, Any]) = Html(args.map(a => a._1.name + "=\"" + HtmlFormat.escape(a._2.toString).body + "\"").mkString(" "))

}
