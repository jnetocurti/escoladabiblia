Sandbox('*', function(box) {
	
	box.bootgrid('.grid-alunos');

	box.eventClick('.save-aluno', function() {
		box.submitForm('.aluno-form');
	});

	box.ajaxForm('.aluno-form', function() {
		console.log('vai submeter');
	}, function() {
		console.log('submeter sucesso');
	}, function() {
		console.log('submeter erro');
	}, function() {
		console.log('submeter complete');
	});

});
