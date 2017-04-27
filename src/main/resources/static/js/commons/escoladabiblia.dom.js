Sandbox.modules.dom = function(box) {

	box.show = function(element) {
		return $(element).removeClass('hide');
	};

	box.hide = function(element) {
		return $(element).addClass('hide');
	};

	box.get = function(element) {
		return $(element).val();
	};

	box.set = function(element, value) {
		$(element).val(value);
	};
	
	box.text = function(element, value) {
		$(element).text(value);
	};
	
	box.check = function(element, value) {
		$(element).prop('checked', value);
	};
	
	box.checkRadio = function(element, value) {
		$(element).filter('[value=' + value + ']').prop('checked', true);
	};
	
	box.clearSelect = function(element) {
		$(element).html('').append($("<option />").val('').text('Selecione'));
	};
	
	box.addOption = function(element, value, text) {
		$(element).append($("<option />").val(value).text(text));
	};
	
	box.resetForm = function(form) {
		box.set(form + ' select', '');
		box.set(form + ' textarea', '');
		box.set(form + ' input[type=text]', '');
		box.set(form + ' input[type=hidden]', '');
		box.check(form + ' input[type=radio]', false);
		box.check(form + ' input[type=checkbox]', false);
	};
	
	box.switchArea = function(hideDiv, showDiv) {
		box.hide(hideDiv);
		box.show(showDiv);
	};

	box.showMsg = function(msg) {
		var show = function(alert) {
			box.show(alert).find('span').text(msg.message);
			setTimeout(function() {
				box.hide(alert);
			}, 3000);
		};
		switch (msg.type) {
		case 'ERROR':
			show($('#error-message'));
			break;
		case 'SUCCESS':
			show($('#success-message'));
			break;
		default:
			break;
		}
	};
	
	box.showMsgsTooltip = function(array) {
		$(array).each(function(index, element) {
			var field = $('[name=' + element.field.replace(/\./g, '\\.') + ']');
			box.tooltip(field, element.message);
		});
	};
	
	box.tooltip = function(field, message) {
		$(field).tooltip({
			title : message,
			placement : "top",
			html : true,
			trigger : "manual"
		}).tooltip("show");
		field.click(function() {
			$(this).tooltip("destroy");
		});
	};

};