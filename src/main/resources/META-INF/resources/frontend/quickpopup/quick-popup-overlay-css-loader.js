import styles from 'quickpopup/quick-popup-overlay.css';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
  <dom-module id="quick-popup-overlay-css" theme-for="quick-popup-overlay">
    <template><style>${styles}</style></template>
  </dom-module>`;
document.head.appendChild($_documentContainer.content);


