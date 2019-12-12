import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import './QuickPopupEvents.js';

/**
 * `menu-bar-overlay`
 * An overlay element
 *
 * @customElement
 * @polymer
 */
class QuickPopupOverlay extends PolymerElement {
    static get template() {
        return html `
                <style>
                    .overlay {
                        z-index: 200;
                        position: fixed;
                        /*
                        Despite of what the names say, <vaadin-overlay> is just a container
                        for position/sizing/alignment. The actual overlay is the overlay part.
                        */
                        /*
                        Default position constraints: the entire viewport. Note: themes can
                        override this to introduce gaps between the overlay and the viewport.
                        */
                        top: 0;
                        right: 0;
                        bottom: 0;
                        left: 0;
                        /* background-color: #666666;
                        opacity: 0.5;*/
                    }
                </style> 

                <div id="overlay" 
                    class="overlay"
                    onClick="onOverlayClick(this, event);"
                    >
                </div>
            `
    }

    static get is() {
        return 'quick-popup-overlay';
    }

}

customElements.define(QuickPopupOverlay.is, QuickPopupOverlay);