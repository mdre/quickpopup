import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';

import "./card-styles.js";
import "./QuickPopupEvents.js";


/**
 * `quick-popup`
 * A popup element
 *
 * @customElement
 * @polymer
 */
class QuickPopup extends PolymerElement {
    static get template() {
        return html `
            <style include="card-styles">
                .popup {
                    /*z-index: 10000;*/
                    position: absolute;
                }
            </style> 

            <div id="popup" 
                class="popup card card-1"
                onClick="onPopupClick(this, event);"
                >
            </div>
            `;
    }

    static get is() {
        return 'quick-popup';
    }

    updatePositionAndShow(targetid) {
        //console.log("***************************************");
        //console.log(this);
        //var targetid = this.getProperty("targetid");

        // 
        console.log("Searching ", targetid, "...");
        var rect = targetid.getBoundingClientRect();
        console.log(rect.top, rect.right, rect.bottom, rect.left);

        this.$server.targetPosition(rect.top, rect.right, rect.bottom, rect.left);
    }
}

customElements.define(QuickPopup.is, QuickPopup);