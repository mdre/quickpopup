import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import { ThemableMixin } from '@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js';
import "quickpopup/quick-popup-overlay-css-loader.js";
//import { onOverlayClick } from './QuickPopupOverlayEvents.js';

/**
 * `menu-bar-overlay`
 * An overlay element
 *
 * @customElement
 * @polymer
 */
class QuickPopupOverlay extends ThemableMixin(PolymerElement) {
    static get template() {
        return html `
                <style>
                </style> 

                <div id="overlay" 
                    class="overlay"
                    on-click="onOverlayClick"
                    ><slot></slot>
                </div>
            `
    }

    static get is() {
        return 'quick-popup-overlay';
    }

    onOverlayClick() {
        this.$server.onOverlayClick();
    }
}

customElements.define(QuickPopupOverlay.is, QuickPopupOverlay);