Sandbox('*', function(box) {
	
	box.eventClick('#add-atividade-estudo', function() {
		box.submitForm('#atividades-estudos-form');
	});
	
	box.postForm('#atividades-estudos-form', {
		resetForm : false,
		success : function(data) {
			
			box.showMsg({type: 'SUCCESS', message: 'Atividade cadastrada com sucesso!'});
			
			box.set('#materiais-disponiveis', '');
			
			box.check('#enviarBiblia', false);
			
			loadGridAtividadesEstudo(data);
		}
	});
	
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
						
						prepararExcluirAtividade($(this).data("row-id"));
					});
					
					box.eventClick('.command-visualizar-atividade', function() {
						
						visualizarAtividade($(this).data("row-id"));
					});
				}
			}
		);
	};
	
	getCommandsGridAtividadesEstudo = function(column, row) {
		
		var commands = '';
		
		if (!row.postagemEncerrada) {
			
			commands += box.smallGridButton(column, row, "command-delete-atividades", "fa-trash");
			
		} else if (row.postagemEncerrada && !row.atividadeEncerrada && row.corrigible) {
			
			commands += box.smallGridButton(column, row, "command-edit-atividade", "fa-pencil");
			
		} else {
			
			commands += box.smallGridButton(column, row, "command-visualizar-atividade", "fa-expand");
		}
		
		return commands;
	};
	
	editarAtividade = function(id) {
		
		box.post(context + 'atividades-estudo/editar-atividade', id.toString(), {
			success : function(data) {
				box.set('#nota', data.nota);
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
	
	prepararExcluirAtividade = function(id) {
		
		box.set('#id-atividade-excluir', id);
		
		$('#modal-deletar-atividade').modal('show');
	};
	
	box.eventClick('#deletar-atividade', function() {
		box.submitForm('#deletar-atividade-estudo-form');
	});
	
	box.postForm('#deletar-atividade-estudo-form', {
		success : function(data) {
			box.showMsg({type: 'SUCCESS', message: 'Atividade excluida com sucesso!'});
			loadGridAtividadesEstudo(data);
		}
	});
	
	visualizarAtividade = function(id) {
		
		box.post(context + 'atividades-estudo/visualizar-atividade', id.toString(), {
			success : function(data) {
				$('#area-visualizacao-atividade').html(data);
				$('#modal-visualizacao-atividade').modal('show');
			}
		});
	};
	
});
