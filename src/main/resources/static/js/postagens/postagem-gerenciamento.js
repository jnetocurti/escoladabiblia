Sandbox('*', function(box) {
	
	box.eventClick('#pesquisar-postagens', function() {
		box.submitForm('#gerenciamento-form');
	});
	
	box.postForm('#gerenciamento-form', {
		resetForm : false,
		success : function(data) {
			loadGridPostagens(data);
		}
	});
	
	loadGridPostagens = function(data) {
		
		box.bootgridAppended('#postagens', data, 
			{
				formatters : {
					"processado" : function(column, row) {
						return row.dataEfetivaEnvio ? 'Sim' : 'Não';
					},
					"commands" : function(column, row) {
						return box.smallGridButton(column, row, "command-proccess", "fa-print") +
							   box.smallGridButton(column, row, "command-show-report", "fa-file-pdf-o");
					}
				},
				callbacks : function() {
					
					box.eventClick('.command-proccess', function() {
						
						box.set('#id-postagem', $(this).data("row-id"));
						box.check('#encerrar-postagem', false);
						
						$('#modal-processamento-postagem').modal('show');
					});
					
					box.eventClick('.command-show-report', function() {
						window.open(context + 'postagens/gerenciamento/relatorio/' + $(this).data("row-id"));
					});
				}
			}
		);
	};
	
	box.eventClick('#processar-postagem', function() {
		
		window.open(context + 'postagens/gerenciamento/processar/' + getIdPostagem() + '/' + isEncerrarPostagem());
	});
	
	getIdPostagem = function() {
		return box.get('#id-postagem');
	};
	
	isEncerrarPostagem = function() {
		return $('#encerrar-postagem').prop('checked');
	};
	
	// Caso exista ja uma postagem em aberto, apresenta na grid ao iniciar a tela
	$.get(context + 'postagens/gerenciamento/postagem-em-aberto', function(data) {
		loadGridPostagens(data);
	});

});
