export default function NotAuthorizedError(message) {
	Error.call(this, message);
	this.name = "NotAuthorizedError";

	this.message = message;

	if (Error.captureStackTrace) {
		Error.captureStackTrace(this, NotAuthorizedError);
	} else {
		this.stack = (new Error()).stack;
	}

}

NotAuthorizedError.prototype = Object.create(Error.prototype);
