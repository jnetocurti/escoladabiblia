Sandbox('*', function(box) {
	
	box.bootgrid('.grid-alunos');
	
	box.eventClick('.novo-aluno', function() {
		box.hide('.area-grid-alunos');
		box.show('.area-form-alunos');
	});
	
	box.eventClick('.cancelar', function() {
		box.hide('.area-form-alunos');
		box.show('.area-grid-alunos');
	});

	box.eventClick('.save-aluno', function() {
		box.submitForm('.aluno-form');
	});
	
	box.ajaxForm('.aluno-form');

});
