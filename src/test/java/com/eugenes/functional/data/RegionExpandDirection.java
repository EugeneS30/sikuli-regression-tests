package com.eugenes.functional.data;

public enum RegionExpandDirection {

	UPWARDS("up"), DOWNWARDS("down"), LEFT("left"), RIGHT("right");

	private String text;

	private RegionExpandDirection(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static RegionExpandDirection fromString(String text) {
		if (text != null) {
			for (RegionExpandDirection b : RegionExpandDirection.values()) {
				if (text.equalsIgnoreCase(b.text)) {
					return b;
				}
			}
		}
		throw new IllegalArgumentException("No constant with text: " + text
				+ " found");
	}

}
