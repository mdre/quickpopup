package com.awesomecontrols.quickpopup;

import com.awesomecontrols.quickpopup.QuickPopup;
import com.awesomecontrols.quickpopup.QuickPopup.Align;
import com.vaadin.flow.component.html.Span;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@PWA(name = "Test QuickPopup", shortName = "Quckpopup")
public class TestView extends VerticalLayout {

	public TestView() {
		Span target = new Span("TARGET");
		add(target);
		
		Span vl = new Span("CONTENT");
		QuickPopup qp = new QuickPopup(target.getElement(), vl);
						
		qp.setAlign(Align.BOTTOM_LEFT);		
		
		add(new Button("Toggle", ev->{
			if (qp.isVisible()) {
				qp.hide();
			} else {
				qp.show();
			}
		}));
		
    }
	
}
