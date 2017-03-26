Sandbox('*', function(box) {
	
	box.eventClick('.novo-aluno', function() {
		box.switchArea('.area-grid-alunos', '.area-form-alunos');
	});
	
	box.eventClick('.cancelar', function() {
		box.switchArea('.area-form-alunos', '.area-grid-alunos');
	});
	
	box.bootgrid('.grid-alunos',
		{
			formatters: {
				"commands": function(column, row) {
					return box.gridEditButton(column, row) + box.gridDeleteButton(column, row);
				}
			},
			
			callbacks : function() {
				
				box.eventClick('.command-edit', function() {
					alert($(this).data("row-id"));
				});
				
				box.eventClick('.command-delete', function() {
					alert($(this).data("row-id"));
				});
			}
		}
	);

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

			box.post(context + '/alunos-presidios/detalhes-presidio', box.get('#presidio'), {
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
