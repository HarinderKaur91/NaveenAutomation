package com.naveenautomation.Browsers;

public enum Browsers {
	
	GOOGLE_CHROME("chrome"),
	EDGE("edge"),
	FIREFOX("firefox");
	
	String browserName;
	
	private Browsers(String nameOfBrowser) {
		this.browserName = nameOfBrowser;
	}
	
	public String getName() {
		return browserName;
	}
}
