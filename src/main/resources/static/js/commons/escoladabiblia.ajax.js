Sandbox.modules.ajax = function(box) {

	box.ajaxForm = function(form, before, success, error, complete) {

		$(form).ajaxForm({

			dataType:  'json',
			
			beforeSubmit : function() {
				if (before) {
					before();
				}
			},

			success : function(data, statusText, jqXHR, $form) {
				if (success) {
					success(data, statusText, jqXHR, $form);
				}
				if (complete) {
					complete(jqXHR, textStatus);
				}
			},

			error : function(jqXHR, textStatus) {
				if (error) {
					error(jqXHR, textStatus);
				}
				if (complete) {
					complete(jqXHR, textStatus);
				}
			}
		});
	};

};