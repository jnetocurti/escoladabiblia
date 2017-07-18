Sandbox('*', function(box) {
	
	box.eventClick('.novo-aluno', function() {
		box.resetForm('#aluno-form');
		box.switchArea('.area-grid-alunos', '.area-form-alunos');
	});
	
	box.eventClick('.cancelar', function() {
		box.switchArea('.area-form-alunos,.area-atualizacao-atividades,.area-cadastro-historico-atividades', '.area-grid-alunos');
	});
	
	box.eventClick('#save-aluno', function() {
		box.submitForm('#aluno-form');
	});

	box.postForm('#aluno-form', {
		success : function(data) {
			box.showMsg(data);
			box.switchArea('.area-form-alunos', '.area-grid-alunos');
			$('.grid-alunos').bootgrid('reload');
		}
	});
	
	box.bootgridPagination('.grid-alunos',
		{
			formatters: {
				"commands": function(column, row) {
					return box.smallGridButton(column, row, "command-edit", "fa-pencil")
						 + box.smallGridButton(column, row, "command-activities", "fa-graduation-cap")
						 + box.smallGridButton(column, row, "command-historic", "fa-history");
				}
			},
			
			callbacks : function() {
				
				box.eventClick('.command-edit', function() {
					editar($(this).data("row-id"));
				});
				
				box.eventClick('.command-activities', function() {
					gerenciarAtividadesEstudo($(this).data("row-id"));
				});
				
				box.eventClick('.command-historic', function() {
					cadastrarHistoricoAtividades($(this).data("row-id"));
				});
			}
		}
	);
	
	box.eventKeyup('#cep', function() {

		box.getEnderecoByCEP(box.get('#cep'), function(data) {

			box.set('#logradouro', data.logradouro);
			box.set('#bairro', data.bairro);
			box.set('#cidade', data.localidade);
			box.set('#estado', $('#estado option').filter(function() {	return $(this).html() == data.uf; }).val());
			
		}, function() {
			box.set('#logradouro,#bairro,#cidade,#estado', '');
		});
	});
	
});
