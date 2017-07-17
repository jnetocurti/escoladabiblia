Sandbox.modules.event = function(box) {
	
	box.eventClick = function(element, callback) {
		$(element).on('click', callback);
	};
	
	box.eventChange = function(element, callback) {
		$(element).on('change', callback);
	};
	
	box.eventKeyup = function(element, callback) {
		$(element).on('keyup', callback);
	};
	
	box.submitForm = function(form) {
		$(form).submit();
	};
	
};