import styles from '@vaadin/flow-frontend/quickpopup/quick-popup.css';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
  <dom-module id="quick-popup-css" theme-for="quick-popup">
    <template><style>${styles}</style></template>
  </dom-module>`;
document.head.appendChild($_documentContainer.content);


