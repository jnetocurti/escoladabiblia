Sandbox('*', function(box) {
	
	box.eventClick('.novo-aluno', function() {
		box.resetForm('#aluno-form');
		box.switchArea('.area-grid-alunos', '.area-form-alunos');
	});
	
	box.eventClick('.cancelar', function() {
		box.switchArea('.area-form-alunos,.area-atualizacao-atividades', '.area-grid-alunos');
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
					return box.smallGridButton(column, row, "command-activities", "fa-graduation-cap")
						 + box.smallGridButton(column, row, "command-edit", "fa-pencil")
						 + box.smallGridButton(column, row, "command-delete", "fa-trash-o");
				}
			},
			
			callbacks : function() {
				
				box.eventClick('.command-activities', function() {
					gerenciarAtividadesEstudo($(this).data("row-id"));
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
	
	gerenciarAtividadesEstudo = function(id) {
		
		box.post(context + 'atividades-estudo/editar-atividades', id.toString(), {
			success : function(data) {
				
				box.set('#id-aluno', data.aluno.id);
				box.text('#nome-aluno', data.aluno.nome);
				box.set('#id-postagem', data.postagem.id);
				box.set('#data-proximo-envio', data.postagem.dataPrevistaEnvio);
				
				loadComboMateriais(data);
				
				loadGridAtividadesEstudo(data);
				
				box.switchArea('.area-grid-alunos', '.area-atualizacao-atividades');
			}
		});
	};
	
	loadComboMateriais = function(data) {
		
		var $materiais = $('#materiais-disponiveis');
		
		box.clearSelect($materiais);
		
		$(data.materiais).each(function() {
			box.addOption($materiais, this.id, this.nome);
		});
	};
	
	loadGridAtividadesEstudo = function(data) {
		
		box.bootgridAppended('#atividades-estudo', data.aluno.atividadesEstudo, 
			{
				formatters : {
					"material" : function(column, row) {
						return row.material.nome;
					},
					"envioCertificado" : function(column, row) {
						return row.envioCertificado ? 'Sim' : row.material.enviaCertificado ? 'Sim' : 'Não';
					},
					"envioBiblia" : function(column, row) {
						return row.envioBiblia ? 'Sim' : 'Não';
					},
					"commands" : function(column, row) {
						return box.smallGridButton(column, row, "command-edit-atividades", "fa-pencil");
					}
				},
				callbacks : function() {
					
					box.eventClick('.command-edit-atividades', function() {
						
						$('#modal-edicao-atividade').modal('show');
//						editar($(this).data("row-id"));
					});
				}
			}
		);
	};
	
	box.eventClick('#add-atividade-estudo', function() {
		box.submitForm('#atividades-estudos-form');
	});
	
	box.postForm('#atividades-estudos-form', {
		resetForm : false,
		success : function(data) {
			
			box.showMsg({type: 'SUCCESS', message: 'Atividade cadastrada com sucesso!'});
			
			loadComboMateriais(data);
			
			loadGridAtividadesEstudo(data);
		}
	});
	
});
