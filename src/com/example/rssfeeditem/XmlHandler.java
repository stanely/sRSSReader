package com.example.rssfeeditem;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.jar.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.example.rssfeeditem.*;

public class XmlHandler extends DefaultHandler {

    private StringBuffer buffer = new StringBuffer();
    private ArrayList<RSSItemField> itemList;
    private RSSItemField item;
    

    @Override
    public void startElement(String uri, String localName, String qName,
            org.xml.sax.Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub

        buffer.setLength(0);
        
        if(localName.equals("channel")) {
            itemList = new ArrayList<RSSItemField>();
        }
        
        //if(localName.equals("item")) {
        if(localName.equals("title")) {
            item = new RSSItemField();
        }
        
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        /*
        if(itemList == null) {
            itemList = new ArrayList<RSSItemField>();
        }
        
        if(item == null) {  // allocate resource
            item = new RSSItemField();
        }
        */
        
        if(localName.equals("item")) {
            itemList.add(item);
            item = null;
        } 
        

        if(localName.equals("guid")) {
            item.guid = removeEnter(buffer.toString());
        }
        
        if(localName.equals("title")) {  // first
            item.title = removeEnter(buffer.toString());
        }        
        
        if(localName.equals("link")) {
            item.link = removeEnter(buffer.toString());
        }
        
        if(localName.equals("category")) {
            item.category = removeEnter(buffer.toString());
        }   
        
        if(localName.equals("pubDate")) {
            item.pubDate = removeEnter(buffer.toString());
        }        
        
        if(localName.equals("description")) {
            item.description = removeEnter(buffer.toString());
        }        
    }
    
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }  
    
    public ArrayList<RSSItemField> retrieveXmlList() {
        return itemList;
    }
    
    // the data contains "\n" we have to remove them.
    private String removeEnter(String in) {
        return in;
        //return in.replaceAll("[\n]", "");        
    }
}
