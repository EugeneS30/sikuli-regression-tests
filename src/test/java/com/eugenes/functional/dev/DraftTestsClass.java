package com.eugenes.functional.dev;

import java.util.Iterator;

import javax.inject.Inject;

import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import com.eugenes.functional.glue.steps.PatternSearchSteps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DraftTestsClass {
	
	@Inject
	private PatternSearchSteps patternSearch;
	
	@Test
	public void multiplePattern() throws FindFailed{
		
		ImagePath.setBundlePath("C:\\workspace\\sikuli-regression-tests\\src\\test\\resources\\patterns\\");
		
		Screen s = new Screen();
		Iterator<Match> it = s.findAll("downloadArrow.png");

		while(it.hasNext()){
			
		    it.next().highlight(1);
		}
	}
	
	@Test
	public void regionManipulation() {
		patternSearch.i_expand_the_region("up");
	}

}
