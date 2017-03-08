Sandbox.modules.dom = function(box) {

	box.show = function(element) {
		$(element).removeClass('hide');
	};

	box.hide = function(element) {
		$(element).addClass('hide');
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

	box.showMsgsTooltip = function(array) {
		$(array).each(function(index, element) {
			var field = $('input[name=' + element.field + ']');
			box.tooltip(field, element.message);
		});
	};
	
};