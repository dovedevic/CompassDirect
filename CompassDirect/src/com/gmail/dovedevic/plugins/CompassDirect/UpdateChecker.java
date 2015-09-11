package com.gmail.dovedevic.plugins.CompassDirect;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class UpdateChecker {
	private String version;
	private String link;
	private CompassDirect plugin;
	private URL filesFeed;
	public UpdateChecker(CompassDirect instance, String url){
		plugin = instance;
		try {
			this.filesFeed = new URL(url);
		} catch (MalformedURLException e) {e.printStackTrace();}
	}
	public boolean UpdateNeeded(){
		try {
			InputStream is = this.filesFeed.openConnection().getInputStream();
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			Node ls = doc.getElementsByTagName("item").item(0);
			NodeList cld = ls.getChildNodes();
			this.version = cld.item(1).getTextContent().replaceAll("[a-zA-Z ]", "");
			this.link = cld.item(3).getTextContent();
			if(!plugin.getDescription().getVersion().equals(this.version)){
				return true;
			}
		} catch (Exception e) {e.printStackTrace();}
		return false;
	}
	public String getVersion(){
		return this.version;
	}
	public String getLink(){
		return this.link;
	}
}
