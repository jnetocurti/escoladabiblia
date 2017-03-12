Sandbox('*', function(box) {
	
	$('.data-nascimento').inputmask("99/99/9999");

	box.eventChange('#presidio', function() {
		
		if (getIdPresidio()) {

			box.post(context + '/alunos/detalhes-presidio', getIdPresidio(), {
				success : function(data) {
					setLogradouroPresidio(data.endereco.logradouro);
					setNumeroPresidio(data.endereco.numero);
					setBairroPresidio(data.endereco.bairro);
					setDescricaoPresidio(data.endereco.complemento);
					setCidadePresidio(data.endereco.cidade);
					setEstadoPresidio(data.endereco.estado.uf);
				}
			});
			
		} else {
			
			clearDetalhesPresidio();
		}
	});
	
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

	box.postForm('.aluno-form', {
		success : function(data) {
			box.showMsg(data);
			box.hide('.area-form-alunos');
			box.show('.area-grid-alunos');
			$('.grid-alunos').bootgrid('reload');
		}
	});

	clearDetalhesPresidio = function() {
		$('.detalhe-presidio').val('');
	};
	
	getIdPresidio = function() {
		return $('#presidio').val();
	};

	setLogradouroPresidio = function(value) {
		$('#logradouro-presidio').val(value);
	};

	setNumeroPresidio = function(value) {
		$('#numero-presidio').val(value);
	};

	setBairroPresidio = function(value) {
		$('#bairro-presidio').val(value);
	};

	setDescricaoPresidio = function(value) {
		$('#descricao-presidio').val(value);
	};

	setCidadePresidio = function(value) {
		$('#cidade-presidio').val(value);
	};

	setEstadoPresidio = function(value) {
		$('#estado-presidio').val(value);
	};
	
});
