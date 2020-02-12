const defaultInfo = {
	type: 'info',
	headerClass: 'bg-dark',
	headerText: 'toasts.header.default.info'
}
const defaultError = {
	type: 'error',
	headerClass: 'bg-danger',
	headerText: 'toasts.header.default.error'
}


export const ToastsPlugin = {
	install(Vue) {
		Vue.prototype.$toast = {
			listeners: [],
			info(message, options = {}) {
				this.send(message, Object.assign({}, defaultInfo, options));
			},
			error(message, options = {}) {
				this.send(message, Object.assign({}, defaultError, options));
			},
			send(message, options, type) {
				for (const listener of this.listeners) {
					listener({ message, options, type })
				}
			},
			slot(listener) {
				if (this.listeners[listener] == null) {
					this.listeners.push(listener);
				}
			}
		}
	}
}