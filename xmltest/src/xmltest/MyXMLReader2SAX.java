package xmltest;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyXMLReader2SAX extends DefaultHandler {

java.util.Stack tags = new java.util.Stack();

public MyXMLReader2SAX() {
  super();
}

public static void main(String args[]) {
  long lasting = System.currentTimeMillis();
  try {
    SAXParserFactory sf = SAXParserFactory.newInstance();
    SAXParser sp = sf.newSAXParser();
    MyXMLReader2SAX reader = new MyXMLReader2SAX();
    sp.parse(new InputSource("C:/Users/wuzai/Desktop/APDA/ffdfe4804d705408cf6866861c9a9017.xml"), reader);
   } catch (Exception e) {
    e.printStackTrace();
   }

   System.out.println("运行时间：" + (System.currentTimeMillis() - lasting)
     + "毫秒");
}

public void characters(char ch[], int start, int length)
   throws SAXException {
    System.out.println(new String(ch, start, length));
}

public void startElement(String uri, String localName, String qName,
    Attributes attrs) {
   tags.push(qName);
}
}
