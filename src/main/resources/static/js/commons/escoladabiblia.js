// Função construtora do padrão "Sandbox". O único objeto que será global.
function Sandbox() {
	
		// transformar os argumentos em um array	
	var args = Array.prototype.slice.call(arguments),
		// o ultimo argumento é a função de callback
		callback = args.pop(),
		// os módulos poderão ser passados como um array ou individualmente
		modules = ( args[0] && typeof args[0] === 'string' ) ? args : args[0],
		i;
	
	// garantir que a função seja chamada como um construtor
	if ( !(this instanceof Sandbox) ) {
		return new Sandbox(modules, callback);
	}
	
	// adicionar os módulos ao "this" central
	// vazio ou "*" indica o uso de todos os múdulos
	if ( !modules || modules[0] === '*' ) {
		modules = [];
		for ( i in Sandbox.modules ) {
			if ( Sandbox.modules.hasOwnProperty(i) ) {
				modules.push(i);
			}
		}
	}
	
	// inicializa os módulos obrigatórios
	for ( i = 0; i < modules.length; i += 1 ) {
		Sandbox.modules[modules[i]](this);
	}
	
	callback(this);
};

Sandbox.modules = {};