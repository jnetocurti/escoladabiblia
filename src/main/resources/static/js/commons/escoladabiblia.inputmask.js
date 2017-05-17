Sandbox.modules.inputmask = function(box) {

	Inputmask.extendAliases({
		'data' : {
			mask : "99/99/9999"
		},
		'cep' : {
			mask : "99999-999"
		},
		'nota' : {
			mask : "9[9].99",
			placeholder : ''
		}
	});

	$(":input").inputmask();
};