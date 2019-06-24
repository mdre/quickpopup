package com.awesomecontrols.quickpopup;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;
import java.util.logging.Level;
import java.util.logging.Logger;

@Tag("quick-popup-overlay")
@StyleSheet("frontend://bower_components/menubar/cards.css")
@HtmlImport("bower_components/menubar/quick-popup-overlay.html")
class QuickPopupOverlay extends PolymerTemplate<TemplateModel>  {
    private final static Logger LOGGER = Logger.getLogger(QuickPopupOverlay.class .getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.FINER);
        }
    }
    
    @Id("overlay")
    Div overlay;
    QuickPopup quickPopup;
    
    public QuickPopupOverlay() {
    }
    
    public void addComponent(QuickPopup qp) {
        quickPopup = qp;
        overlay.add(quickPopup);
    }
    
    /**
     * Close and remove the overlay
     */
    public void hide() {
        UI.getCurrent().remove(this);
    }
    
    @ClientCallable
    private void onOverlayClick() {
        LOGGER.log(Level.INFO, "Overlay Click detectado!");
        this.quickPopup.hide();
    }
    
    
}

