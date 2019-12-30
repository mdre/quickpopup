package com.awesomecontrols.quickpopup;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;


@Tag("quick-popup-overlay")
//@StyleSheet("frontend://bower_components/menubar/cards.css")
@JsModule("./quickpopup/quick-popup-overlay.js")
class QuickPopupOverlay extends Component  {
    private static final long serialVersionUID = 270472077985436933L;
    
    private final static Logger LOGGER = Logger.getLogger(QuickPopupOverlay.class.getName());
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
        getElement().appendChild(quickPopup.getElement());
    }
    
    /**
     * Close and remove the overlay
     */
    public void hide() {
        UI.getCurrent().remove(this);
    }
    
    @ClientCallable
    private void onOverlayClick() {
        LOGGER.log(Level.FINER, "Overlay Click detectado!");
        this.quickPopup.hide();
    }
    
    
}

