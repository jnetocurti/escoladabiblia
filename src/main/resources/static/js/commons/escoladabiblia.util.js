Sandbox.modules.util = function(box) {

	box.getEnderecoByCEP = function(cep, callback) {

		var param = cep.replace(/[^\d]/, ''), url = 'https://viacep.com.br/ws/';

		if (!cep || cep.length < 8 || !validCEP(cep))
			return false;

		url += param + '/json';

		$.get(url, function(data) {

			if (!("erro" in data) && _.isFunction(callback)) {
				callback(data);
			}
		});
	};

	validCEP = function(cep) {

		var cepRegex = /^[\d]{5}-?[\d]{3}$/;

		return cepRegex.test(cep);
	};
};