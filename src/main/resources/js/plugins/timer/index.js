
export const TimerPlugin = {
	install(Vue) {
		Vue.prototype.$tim = {
			timers: [],
			wait(name, action, time = 3000) {
				if (this.timers[name]) {
					this.stopTimer(name);
				}
				this.startTimer(name, action, time);
			},
			startTimer(name, action, time) {
				let self = this;
				this.timers[name] = setInterval(() => {
					self.stopTimer(name);
					action();
				}, time);
			},
			stopTimer(name) {
				clearInterval(this.timers[name]);
				this.timers[name] = null;
			}
		}
	}
}