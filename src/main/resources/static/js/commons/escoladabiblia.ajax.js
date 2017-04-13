Sandbox.modules.ajax = function(box) {

	box.postForm = function(form, configs) {
		
		configs = _.isObject(configs) ? configs : {};
		
		_.defaults( configs, { resetForm : true } );
		
		$(form).ajaxForm({
			
			resetForm : configs.resetForm,
			
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

	box.post = function(url, data, configs) {

		configs = _.isObject(configs) ? configs : {};
		
		$.ajax({
			type: 'POST',
		    url: url,
		    data: data,
		    contentType: "application/json",
		    success: function(data, statusText, jqXHR) {
		    	
		    	applyCallback(configs.success, jqXHR, data);
		    },
		    error: function(jqXHR, textStatus) {
		    	
		    	applyCallback(configs.error, jqXHR);
			},
			complete : function(jqXHR, textStatus) {
				
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
			box.showMsg(jqXHR.responseJSON);
			break;
			
		default:
			box.showMsg({type : 'ERROR', message : 'Erro desconhecido no cliente'});
			break;
		}
	};

};