Sandbox.modules.ajax = function(box) {

	box.ajaxForm = function(form, configs) {
		
		configs = configs || {};
		
		$(form).ajaxForm({
			
			beforeSubmit : function() {
				
				applyCallback(configs.before, null);
			},
			success : function(data, statusText, jqXHR, $form) {
				
				applyCallback(configs.success, jqXHR, data);
				
				applyCallback(configs.complete, jqXHR);
			},
			error : function(jqXHR, textStatus) {

				proccessErrors(jqXHR);
				
				applyCallback(configs.error, jqXHR);
				
				applyCallback(configs.complete, jqXHR);
			}
		});
	};

	applyCallback = function(callback, jqXHR, data) {

		if (typeof callback === 'function') {

			if (callback.name === 'before') {
				callback();

			} else if (callback.name === 'success') {
				callback(data, jqXHR);

			} else {
				callback(jqXHR);
			}
		}
	};
		
	proccessErrors = function(jqXHR) {
		
		switch (jqXHR.status) {
		
		case 400:
			box.showMsgsTooltip(jqXHR.responseJSON.errors);
			break;
			
		case 500:
			break;
			
		default:
			break;
		}
	};

};