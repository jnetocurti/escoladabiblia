Sandbox('*', function(box) {
	
	box.eventClick('#novo-presidio', function() {
		box.resetForm('#presidio-form');
		box.switchArea('.area-grid-presidios', '.area-form-presidios');
	});
	
	box.eventClick('.cancelar', function() {
		box.switchArea('.area-form-presidios', '.area-grid-presidios');
	});
	
	box.eventClick('#save-presidio', function() {
		box.submitForm('#presidio-form');
	});

	box.postForm('#presidio-form', {
		success : function(data) {
			box.showMsg(data);
			box.switchArea('.area-form-presidios', '.area-grid-presidios');
			$('.grid-presidios').bootgrid('reload');
		}
	});
	
	box.bootgridPagination('.grid-presidios',
		{
			formatters: {
				"commands": function(column, row) {
					return box.smallGridButton(column, row, "command-edit", "fa-pencil");
				}
			},
			
			callbacks : function() {
				
				box.eventClick('.command-edit', function() {
					editar($(this).data("row-id"));
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
	
	editar = function(id) {
		
		box.post(context + 'presidios/editar', id.toString(), {
			success : function(data) {
				
				box.set('#id', data.id);
				box.set('#nome', data.nome);
				
				box.set('#id-endereco', data.endereco.id);
				box.set('#cep', data.endereco.cep);
				box.set('#logradouro', data.endereco.logradouro);
				box.set('#numero', data.endereco.numero);
				box.set('#bairro', data.endereco.bairro);
				box.set('#complemento', data.endereco.complemento);
				box.set('#cidade', data.endereco.cidade);
				box.set('#estado', data.endereco.estado.id);
				
				box.switchArea('.area-grid-presidios', '.area-form-presidios');
			}
		});
	};
	
});
