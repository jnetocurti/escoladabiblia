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

					box.switchArea('.area-grid-alunos', '.area-cadastro-historico-atividades');
				});
			}
		}
	);
	
	gerenciarAtividadesEstudo = function(id) {
		
		box.post(context + 'atividades-estudo/editar-atividades', id.toString(), {
			success : function(data) {
				
				box.set('#id-aluno', data.aluno.id);
				box.text('#nome-aluno', data.aluno.nome);
				box.set('#id-postagem', data.postagem.id);
				box.set('#data-proximo-envio', data.postagem.dataPrevistaEnvio);
				box.set('#materiais-disponiveis', '');
				
				// Para posterior edição de alguma atividade
				box.text('#certificado option[value=true]', data.postagem.dataPrevistaEnvio);
				box.text('#biblia option[value=true]', data.postagem.dataPrevistaEnvio);
				
				loadGridAtividadesEstudo(data);
				
				box.switchArea('.area-grid-alunos', '.area-atualizacao-atividades');
			}
		});
	};
	
	loadGridAtividadesEstudo = function(data) {
		
		box.bootgridAppended('#atividades-estudo', data.aluno.atividadesEstudo, 
			{
				formatters : {
					"material" : function(column, row) {
						return row.material.nome;
					},
					"certificado" : function(column, row) {
						return _.isNull(row.certificado) ? '' : 'Sim';
					},
					"biblia" : function(column, row) {
						return _.isNull(row.biblia) ? '' : 'Sim';
					},
					"commands" : function(column, row) {
						return getCommandsGridAtividadesEstudo(column, row);
					}
				},
				callbacks : function() {
					
					box.eventClick('.command-edit-atividade', function() {
						
						editarAtividade($(this).data("row-id"));
					});
					
					box.eventClick('.command-delete-atividades', function() {
						
						box.set('#id-atividade-excluir', $(this).data("row-id"));
						
						$('#modal-deletar-atividade').modal('show');
					});
				}
			}
		);
	};
	
	editarAtividade = function(id) {
		
		box.post(context + 'atividades-estudo/editar-atividade', id.toString(), {
			success : function(data) {
				box.set('#nota', data.nota);
				box.set('#biblia', !_.isNull(data.biblia) + '');
				box.set('#id-atividade-atualizar', data.id);
				box.text('#nome-material', data.material.nome);
				box.set('#data-retorno', data.dataRetornoEstudo);
				box.set('#certificado', !_.isNull(data.certificado) + '');
				$('#modal-edicao-atividade').modal('show');
			}
		});
	};
	
	box.eventClick('#atualizar-atividade', function() {
		box.submitForm('#atualizar-atividade-form');
	});
	
	box.postForm('#atualizar-atividade-form', {
		success : function(data) {
			$('#modal-edicao-atividade').modal('hide');
			box.showMsg({type: 'SUCCESS', message: 'Atividade atualizada com sucesso!'});
			loadGridAtividadesEstudo(data);
		}
	});
	
	box.eventClick('#deletar-atividade', function() {
		box.submitForm('#deletar-atividade-estudo-form');
	});
	
	box.postForm('#deletar-atividade-estudo-form', {
		success : function(data) {
			box.showMsg({type: 'SUCCESS', message: 'Atividade excluida com sucesso!'});
			loadGridAtividadesEstudo(data);
		}
	});
	
	getCommandsGridAtividadesEstudo = function(column, row) {
		
		var commands = '';
		
		if (!row.postagemEncerrada) {
			
			commands += box.smallGridButton(column, row, "command-delete-atividades", "fa-trash");
			
		} else if (row.postagemEncerrada) {
			
			commands += box.smallGridButton(column, row, "command-edit-atividade", "fa-pencil");
			
		} else {
			
			commands += box.smallGridButton(column, row, "command-visualizar-atividade", "fa-expand");
		}
		
		return commands;
	};
	
	box.eventClick('#add-atividade-estudo', function() {
		box.submitForm('#atividades-estudos-form');
	});
	
	box.postForm('#atividades-estudos-form', {
		resetForm : false,
		success : function(data) {
			
			box.showMsg({type: 'SUCCESS', message: 'Atividade cadastrada com sucesso!'});
			
			box.set('#materiais-disponiveis', '');
			
			loadGridAtividadesEstudo(data);
		}
	});
	
});
