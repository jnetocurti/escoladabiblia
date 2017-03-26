Sandbox.modules.inputmask = function(box) {

	Inputmask.extendAliases({
		'data' : {
			mask : "99/99/9999"
		}
	});

	$(":input").inputmask();
};