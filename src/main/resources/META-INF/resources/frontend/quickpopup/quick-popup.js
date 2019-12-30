import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';

import "./card-styles.js";

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
                on-click="onPopupClick"
                >
            </div>
            `;
    }

    static get is() {
        return 'quick-popup';
    }

    updatePositionAndShow(targetid) {
        var rect = targetid.getBoundingClientRect();
        this.$server.targetPosition(rect.top, rect.right, rect.bottom, rect.left);
    }

	__setPosition(top,left) {
		var w = document.body.getBoundingClientRect().width; 
		var q = this.$.popup.getBoundingClientRect();
		if (left+q.width>w && w-q.width>0) left=w-q.width;
		this.$.popup.style.top =top+"px";
        this.$.popup.style.left=left+"px";
    }
        
    onPopupClick(event) {
        event.stopPropagation();
    }
}

customElements.define(QuickPopup.is, QuickPopup);