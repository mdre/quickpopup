package com.awesomecontrols.quickpopup;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.Style;

@Tag("quick-popup") 
//@StyleSheet("frontend://bower_components/menubar/cards.css")
@JsModule("./quickpopup/quick-popup.js")
public class QuickPopup extends PolymerTemplate<IQuickPopupModel> implements  HasSize, HasTheme, HasStyle, HasComponents {
    private static final long serialVersionUID = 8843104328921005320L;

    private final static Logger LOGGER = Logger.getLogger(QuickPopup.class.getName());
    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.FINER);
        }
    }
    
    @Id("popup")
    Div popup;
    
    double top;
    double left;
    
    Element targetId;
    
    
    public enum Align {
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_LEFT
    }
    
    Align alignTo = Align.TOP_RIGHT;
    
    int x_offset = 0;
    int y_offset = 0;
    
    private List<IQuickPopupVisibilityEvent> visibilityEventListeners = new ArrayList<>();
    
    /**
     * the content to be shown
     */
    Component content;
    
    // overlay utilziado para mostrar el QuickPopup
    QuickPopupOverlay overlay;
    
    /**
     * Create a popup near to the target component
     * @param target is the ID of the target component
     * @param content  the popup content.
     */
    public QuickPopup(Element target, Component content) {
        this.targetId = target;
        
        
        overlay = new QuickPopupOverlay();
        this.content = content;
        
        this.popup.removeAll();
        this.popup.add(this.content);
        
        this.overlay.addComponent(this);
    }
    
    private void setPosition(double top, double left) {
        this.top = top;
        this.left = left;
        popup.getStyle().set("top", ""+top+"px");
        popup.getStyle().set("left", ""+left+"px");
    }
    
    /**
     * Set the popup content 
     * @param content to shown
     */
    public void setContent(Component content) {
        this.popup.removeAll();
        this.popup.add(content);
    }
    
    /**
     * Remove de popup content
     */
    public void clearContent() {
        this.popup.removeAll();
    }
    
//    public void setPopupForComponentId(String target) {
//        this.targetId = target;
//    }
    
    /**
     * Show the popup
     */
    public void show() {
        LOGGER.log(Level.FINER, "llamando a updatePositionAndShow...");
        UI.getCurrent().add(overlay);
        
        LOGGER.log(Level.FINER, "targetId: "+this.targetId);
        getElement().callJsFunction("updatePositionAndShow",this.targetId);
        
        this.fireVisibilityChangeEvent();
    }
    
    /**
     * Hide the popup
     */
    public void hide() {
        this.overlay.hide();
        this.fireVisibilityChangeEvent();
    }
    
    @ClientCallable
    private void targetPosition(double top, double right, double bottom, double left) {
        LOGGER.log(Level.FINER, "showInternal!!!!");
        // agregar el overlay
        double popupTop = top;
        double popupLeft = right;
        
        switch (this.alignTo) {
            case TOP_RIGHT:
                popupTop = top + this.y_offset;
                popupLeft = right + this.x_offset;
                break;
                
            case BOTTOM_RIGHT:
                popupTop = bottom + this.y_offset;
                popupLeft = right + this.x_offset;
                break;
                
            case BOTTOM_LEFT:
                popupTop = bottom + this.y_offset;
                popupLeft = left + this.x_offset;
                break;
                
            case TOP_LEFT:
                popupTop = top + this.y_offset;
                popupLeft = left + this.x_offset;
                break;
        }
        
        this.setPosition(popupTop,popupLeft);
    }
    
    /**
     * Set the component align based on target ID
     * @param align enum with the available target aligns
     * @return this
     */
    public QuickPopup setAlign(Align align) {
        this.alignTo = align;
        return this;
    }
    
    /**
     * Set the x offset to be added to the align 
     * @param offset in pixels
     * @return this
     */
    public QuickPopup setXOffset(int offset) {
        this.x_offset = offset;
        return this;
    }
    
    /**
     * Set the y offset to be added to the align 
     * @param offset in pixels
     * @return this 
     */
    public QuickPopup setYOffset(int offset) {
        this.y_offset = offset;
        return this;
    }
    
    public boolean isVisible() {
        return this.overlay.getParent().isPresent();
    }
    
    
    @Override
    public Style getStyle() {
        return popup.getStyle();
    }
    
    /**
     * Add a visibility change listener.
     * @param event listener
     * @return this
     */
    public QuickPopup addVisibilityChangeListener(IQuickPopupVisibilityEvent event) {
        if (this.visibilityEventListeners == null) {
            this.visibilityEventListeners = new ArrayList<>();
        }
        this.visibilityEventListeners.add(event);
        return this;
    }
    
    /**
     * Remove the event listener.
     * @param event listener
     * @return true if the listener exist and was removed.
     */
    public boolean removeVisibilityChangeListener(IQuickPopupVisibilityEvent event) {
        this.visibilityEventListeners.remove(event);
        return this.visibilityEventListeners.remove(event);
    }
    
    
    
    private void fireVisibilityChangeEvent() {

        for (IQuickPopupVisibilityEvent visibilityEventListener : this.visibilityEventListeners) {
            visibilityEventListener.visibilityChanged();
        }
    }
}

