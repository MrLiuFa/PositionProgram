package day08;

import org.jsoup.nodes.Document;

public class Page {
	
	private Document document;
	private String nextUrl;
	private boolean hasNextPage;
	
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public String getNextUrl() {
		return nextUrl;
	}
	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
	@Override
	public String toString() {
		return "Page [document=" + document + ", nextUrl=" + nextUrl + ", hasNextPage=" + hasNextPage + "]";
	}
	
	

}
