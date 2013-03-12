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
    
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        
        buffer.setLength(0);
        
        //if(localName.equals("channel")) {
        if(localName.equals("title")) {
            itemList = new ArrayList<RSSItemField>();
        }
        
        if(localName.equals("item")) {
            item = new RSSItemField();
        }
        
        
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(localName.equals("item")) {
            itemList.add(item);
        } 

        if(localName.equals("guid")) {
            item.guid = buffer.toString();
        }
        
        if(localName.equals("title")) {  // first
            if(itemList == null && item == null) {
                itemList = new ArrayList<RSSItemField>();
                item = new RSSItemField();
            } else {
                item.title = buffer.toString();
            }

        }        
        
        if(localName.equals("link")) {
            item.link = buffer.toString();
        }
        
        if(localName.equals("category")) {
            item.category = buffer.toString();
        }   
        
        if(localName.equals("pubDate")) {
            item.pubDate = buffer.toString();
        }        
        
        if(localName.equals("description")) {
            try {
                item.description = new String(buffer.toString().getBytes(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }        
    }
    
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }  
    
    public ArrayList<RSSItemField> retrieveXmlList() {
        return itemList;
    }
    
}
