Sandbox('*', function(box) {
	
	box.eventClick('.novo-aluno', function() {
		box.resetForm('.aluno-form');
		box.switchArea('.area-grid-alunos', '.area-form-alunos');
	});
	
	box.eventClick('.cancelar', function() {
		box.switchArea('.area-form-alunos', '.area-grid-alunos');
	});
	
	box.bootgrid('.grid-alunos',
		{
			formatters: {
				"commands": function(column, row) {
					return box.smallGridButton(column, row, "command-activities", "fa-graduation-cap")
						 + box.smallGridButton(column, row, "command-edit", "fa-pencil")
						 + box.smallGridButton(column, row, "command-delete", "fa-trash-o");
				}
			},
			
			callbacks : function() {
				
				box.eventClick('.command-activities', function() {
					alert($(this).data("row-id"));
				});
				
				box.eventClick('.command-edit', function() {
					editar($(this).data("row-id"));
				});
				
				box.eventClick('.command-delete', function() {
					alert($(this).data("row-id"));
				});
			}
		}
	);
	
	editar = function(id) {
		
		box.post(context + 'alunos-presidios/editar', id.toString(), {
			success : function(data) {
				box.set('#id', data.id);
				box.set('#nome', data.nome);
				box.set('#nascimento', data.dataNascimento);
				box.check('#frequentouIgreja', data.frequentouIgreja);
				box.check('#batizado', data.batizado);
				box.check('#possuiBiblia', data.possuiBiblia);
				carregarPresidio(data);
				box.switchArea('.area-grid-alunos', '.area-form-alunos');
			}
		});
	},
	
	carregarPresidio = function(data) {
		
		var idPresidio = data.caracterizacoes ? data.caracterizacoes[data.caracterizacoes.length - 1].presidio.id : '';
		
		box.set('#presidio', idPresidio);
		
		$( "#presidio" ).trigger( "change" );
	},

	box.eventClick('.save-aluno', function() {
		box.submitForm('.aluno-form');
	});

	box.postForm('.aluno-form', {
		success : function(data) {
			box.showMsg(data);
			box.switchArea('.area-form-alunos', '.area-grid-alunos');
			$('.grid-alunos').bootgrid('reload');
		}
	});
	
	box.eventChange('#presidio', function() {
		
		if (box.get('#presidio')) {

			box.post(context + 'alunos-presidios/detalhes-presidio', box.get('#presidio'), {
				success : function(data) {
					box.set('#numero-presidio', data.endereco.numero || 's/ nº');
					box.set('#bairro-presidio', data.endereco.bairro || '-');
					box.set('#cidade-presidio', data.endereco.cidade);
					box.set('#estado-presidio', data.endereco.estado.uf);
					box.set('#logradouro-presidio', data.endereco.logradouro);
					box.set('#complemento-presidio', data.endereco.complemento || '-');
				}
			});
			
		} else {
			clearDetalhesPresidio();
		}
	});

	clearDetalhesPresidio = function() {
		box.set('.detalhe-presidio', '');
	};
	
});
