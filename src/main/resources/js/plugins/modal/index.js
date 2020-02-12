const modalForName = (name) => $('#' + name);

export const ModalPlugin = {
	install(Vue) {
		Vue.prototype.$modal = {
			listeners: [],
			show(name, data) {
				this.listeners[name](data);
				modalForName(name).modal('show');
			},
			hide(name) {
				modalForName(name).modal('hide');
			},
			slot(name, listener) {
				this.listeners[name] = listener;
			}
		}
	}
}