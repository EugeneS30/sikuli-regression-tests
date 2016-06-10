package com.eugenes.functional.glue.steps;

public enum ObserverEvent {
    
    ON_APPEAR("appear"),
    ON_VANISH("vanish"), 
    ON_CHANGE("change");
    
    private String text;

    ObserverEvent(String text) {
      this.text = text;
    }

    public String getText() {
      return this.text;
    }

    public static ObserverEvent fromString(String text) {
      if (text != null) {
        for (ObserverEvent b : ObserverEvent.values()) {
          if (text.equalsIgnoreCase(b.text)) {
            return b;
          }
        }
      }
      throw new IllegalArgumentException("No constant with text: " + text + " found");
    }

}
