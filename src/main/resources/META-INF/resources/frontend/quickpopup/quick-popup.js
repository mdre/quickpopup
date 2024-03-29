import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import { ThemableMixin } from '@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js';
import "quickpopup/card-styles.js";
import "quickpopup/quick-popup-css-loader.js";


/**
 * `quick-popup`
 * A popup element
 *
 * @customElement
 * @polymer
 */
class QuickPopup extends ThemableMixin(PolymerElement) {
    static get template() {
        return html `
            <style include="card-styles">
            </style> 

            <div id="popup" 
                class="popup card card-1"
                on-click="onPopupClick"
                ><slot></slot>
            </div>
            `;
    }

    static get is() {
        return 'quick-popup';
    }

    updatePositionAndShow(targetid, alignTo, x_offset, y_offset) {
        var rect = targetid.getBoundingClientRect();
//        console.log(targetid,alignTo,x_offset,y_offset);
        
        var popupTop = rect.top;
        var popupLeft = rect.right;
        
        switch (alignTo) {
            case "TOP_RIGHT":
//                console.log("TOP_RIGHT: "+rect.top+":"+rect.right);
                popupTop = rect.top + y_offset;
                popupLeft = rect.right + x_offset;
                break;
                
            case "BOTTOM_RIGHT":
                popupTop = rect.bottom + y_offset;
                popupLeft = rect.right + x_offset;
                break;
                
            case "BOTTOM_LEFT":
                popupTop = rect.bottom + y_offset;
                popupLeft = rect.left + x_offset;
                break;
                
            case "TOP_LEFT":
                popupTop = rect.top + y_offset;
                popupLeft = rect.left + x_offset;
                break;
        }
//        console.log("pos: "+popupTop+","+popupLeft);
        
        var w = document.body.getBoundingClientRect().width;
        var q = this.$.popup.getBoundingClientRect();
        if (popupLeft + q.width > w && w - q.width > 0) popupLeft = w - q.width;
        this.$.popup.style.top = popupTop + "px";
        this.$.popup.style.left = popupLeft + "px";
        
        this.$.popup.classList.add("show");
    }

    hide() {
        this.$.popup.classList.remove("show");
    }

    onPopupClick(event) {
        event.stopPropagation();
    }
}

customElements.define(QuickPopup.is, QuickPopup);